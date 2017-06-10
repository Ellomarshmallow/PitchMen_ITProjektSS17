package de.pitchMen.client.elements;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.client.elements.AddMarketplaceForm.UpdateMarketplaceCallback;
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

		Button cancelButton = new Button("Abbrechen" + new ClickHandler(){
			public void onClick(ClickEvent event){
				/* Wenn man auf den Cancel Button drückt, wird man auf den angewählten Projektmarktplatz
				 * zurückgeführt
				 * */
				MarketplaceForm mpf = new MarketplaceForm(selectedMarketplace); 

			}
		}); 
		buttonsPanel.add(cancelButton);

		// Speichern Button
		Button saveButton = new Button("Speichern" + new ClickHandler(){

			public void onClick(ClickEvent event) {

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
		});
		buttonsPanel.add(saveButton);
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void save(){


		super.getPitchMenAdmin().addProject(fromBox.getValue(), toBox.getValue(), titleBox.getText(), descBox.getText(),
				ClientsideSettings.getCurrentUser().getId(),selectedProject.getMarketplaceId(), new AddProjectFormCallback(this));
	}


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
			selectedProject.setTitle(titleBox.getText());
			selectedProject.setDescription(descBox.getText());
			selectedProject.setDateOpened(fromBox.getValue());
			selectedProject.setDateClosed(toBox.getValue());
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
}

