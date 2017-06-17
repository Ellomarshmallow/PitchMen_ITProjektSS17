package de.pitchMen.client.elements;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class AddProjectForm extends Formular{

	private Project selectedProject = null;
	private Marketplace selectedMarketplace = null; 
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Projektes:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung des Projektes:");
	TextBox descBox = new TextBox();
	Label fromLabel = new Label("Von:");
	DatePicker fromBox = new DatePicker();
	Label toLabel = new Label("Bis:");
	DatePicker toBox = new DatePicker();
	private boolean isSave = false;

	AddProjectForm(Project selectedProject,PitchMenTreeViewModel pitchMenTreeViewModel,boolean isSave){

		this.selectedProject = selectedProject;
		this.pitchMenTreeViewModel = pitchMenTreeViewModel; 
		this.isSave = isSave; 
		this.selectedMarketplace = pitchMenTreeViewModel.getSelectedMarketplace(); 
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));

		//TODO beim anzeigen der TextBoxes: addProject = true dann alles leer, bei false die vorherigen Daten übernehmen

		//Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel(); 
		this.add(labelsPanel);

		//labels und Boxen dem Vertical Panel hinzufügen
		labelsPanel.add(idLabel);
		labelsPanel.add(titleLabel);
		labelsPanel.add(titleBox);
		labelsPanel.add(descLabel);
		labelsPanel.add(descBox);
		labelsPanel.add(fromLabel);
		labelsPanel.add(fromBox);
		labelsPanel.add(toLabel);
		labelsPanel.add(toBox);

				
		//HorizontalPanel für die Buttons erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		this.add(buttonsPanel);	
		
		//Die zwei VerticalPanels hinzufügen
		RootPanel.get("content").clear();
		RootPanel.get("content").add(labelsPanel);
		RootPanel.get("content").add(buttonsPanel);
		
		Button cancelBtn = new Button("Abbrechen");
		cancelBtn.addClickHandler(new cancelClickHandler());
		buttonsPanel.add(cancelBtn);
		
		Button saveBtn = new Button("Speichern"); 
		saveBtn.addClickHandler(new saveClickHandler()); 
		buttonsPanel.add(saveBtn);
	
	}
	
	// get SelectedProject
	public Project getSelectedProject() {
		return selectedProject;
	}
	//get SelectedMarketplace
	public Marketplace getSelectedMarketplace(){
		return selectedMarketplace; 
	}
	
	// save Methode
	public void save(){

		java.sql.Date convertedFromDate = new java.sql.Date(fromBox.getValue().getTime()); 
		java.sql.Date convertedToDate = new java.sql.Date(toBox.getValue().getTime()); 
		super.getPitchMenAdmin().addProject(convertedFromDate, convertedToDate, titleBox.getText(), descBox.getText(),
				ClientsideSettings.getCurrentUser().getId(),selectedProject.getMarketplaceId(), new AddProjectFormCallback(this));
	}
	
	//AddProjectFormCallback
	public class AddProjectFormCallback implements AsyncCallback<Project>{

		private AddProjectForm addProjectForm = null; 

		public AddProjectFormCallback(AddProjectForm addProjectForm){
			this.addProjectForm = addProjectForm; 
		}

		public void onFailure(Throwable caught){

			this.addProjectForm.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}
		public void onSuccess(Project project){

			Window.alert("erfolgreich gespeichert"); 
			pitchMenTreeViewModel.addProject(selectedProject, selectedMarketplace);
		}

	}	
	// ---------- update Methode

	public void update() {
		if (selectedProject != null) {
			
			java.sql.Date convertedFromDate = new java.sql.Date(fromBox.getValue().getTime()); 
			java.sql.Date convertedToDate = new java.sql.Date(toBox.getValue().getTime()); 
			
			selectedProject.setTitle(titleBox.getText());
			selectedProject.setDescription(descBox.getText());
			selectedProject.setDateOpened(convertedFromDate);
			selectedProject.setDateClosed(convertedToDate);
			super.getPitchMenAdmin().updateProject(selectedProject, new UpdateProjectCallback());
		}}


	// ---------- update Callback
	class UpdateProjectCallback implements AsyncCallback<Void> {


		public void onFailure(Throwable caught) {
			Window.alert("Das Bearbeiten des Projekts ist fehlgeschlagen!");

		}

		public void onSuccess(Void result) {
			pitchMenTreeViewModel.updateProject(selectedProject);
		}
	}


	// ---------- getIsSave

	public boolean getIsSave(){
		return this.isSave; 
	}
	

	// ---------- ClickHandler

	// ---------- cancelClickHandler
	private class cancelClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {

			/* Wenn man auf den Cancel Button drückt, wird man auf den vorherigen
			 * Projektmarktplatz zurückgeführt.*/
			RootPanel.get("content").clear();
			MarketplaceForm cancel = new MarketplaceForm(getSelectedMarketplace());

		}
	}


	// ---------- saveClickHandler
	private class saveClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {


			RootPanel.get("content").clear();

			if (Window.confirm("Sind alle Angaben korrekt?")) {

				if(getIsSave()){

					/* bei Click wird die unten implementierte Methode save()
					 * aufgerufen.
					 */
					save();
					ProjectForm pf = new ProjectForm(getSelectedProject());
				}
				else{
					update();
					ProjectForm pf = new ProjectForm(getSelectedProject());
				}

			}
		}
	}

	 
	 
}

