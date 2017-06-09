package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class MarketplaceForm extends Formular {
	
	/* titleBox und descBox sind hier labels, erst beim Erstellen oder Bearbeiten ist titleBox 
	 * eine Textbox und descBox eine TextArea
	*/
	Marketplace selectedMarketplace = null;
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Marktplatzes:");
	Label titleBox = new Label();
	Label descLabel = new Label("Beschreibung des Marktplatzes:");
	Label descBox = new Label();

	public MarketplaceForm(Marketplace marketplace) {
		super();

		//Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel(); 
		this.add(labelsPanel);
		
		//labels und Boxen dem Vertical Panel hinzufügen
		labelsPanel.add(idLabel);
		labelsPanel.add(titleLabel);
		labelsPanel.add(titleBox);
		labelsPanel.add(descLabel);
		labelsPanel.add(descBox);
		
		//HorizontalPanel für die Buttons erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		this.add(buttonsPanel);

		// ---------- Neuer Projektmarktplatz Button, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen
		Button addMarketplaceBtn = new Button("+ Neuer Projektmarktplatz");
		addMarketplaceBtn.addClickHandler(new addMarketplaceClickHandler());
		buttonsPanel.add(addMarketplaceBtn);


		// ---------- Neues Projekt Button, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen
		Button addProjectBtn = new Button("+ Neues Projekt hinzufügen");
		addProjectBtn.addClickHandler(new addProjectClickHandler());
		buttonsPanel.add(addProjectBtn);

		/*
		 * Wenn der aktuelle User gleich der PersonId ist, dann hat
		 * dieser die Buttons Löschen und Bearbeiten zur verfügung. Vgl. hasPermission() in Formular.java
		 */
		if (hasPermission(this.selectedMarketplace)) {		


			// ---------- Projektmarktplatz löschen, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen
			Button deleteMarketplaceBtn = new Button("- Projektmarktplatz löschen"); 
			deleteMarketplaceBtn.addClickHandler(new deleteMarketplaceClickHandler());
			buttonsPanel.add(deleteMarketplaceBtn);


			// ---------- Projektmarktplatz bearbeiten, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen

			Button updateMarketplaceBtn = new Button("Bearbeiten");
			updateMarketplaceBtn.addClickHandler(new updateMarketplaceClickHandler());
			buttonsPanel.add(updateMarketplaceBtn);


		}
	}


			// ---------- ClickHandler


			// ---------- addMarketplaceClickHandler
	private class addMarketplaceClickHandler implements ClickHandler{

		public void onClick(ClickEvent event) {

			/* bei Click wird ein Objekt vom Typ AddMarketplaceForm erzeugt und
			 * der aktuell gewählte Marktplatz, den pitchMenTreeViewModel und 
			 * einen booleanschen Wert übergeben. Dieser Wert sagt aus, ob der 
			 * addMarketplaceBtn oder der updateMarketplaceBtn gedrückt wurde,
			 * da beide ClickHandler ein Objekt vom Typ AddMarketplaceForm erzeugen.
			 * Der booleansche Wert wird benötigt um festzulegen ob bei dem Click auf
			 * den saveButton in AddMarketplaceForm.java die save() oder die update() Methode
			 * verwendet wird.			 
			 * addMarketplaceBtn = true
			 * updateMarketplaceBtn = false
			*/
			AddMarketplaceForm addMarketplace = new AddMarketplaceForm(MarketplaceForm.this.selectedMarketplace,MarketplaceForm.this.pitchMenTreeViewModel, true);


		}
	}

			//---------- addProjectClickHandler
	private class addProjectClickHandler implements ClickHandler{

		public void onClick(ClickEvent event) {

			AddProjectForm addProject = new AddProjectForm();

			addProject.onLoad(mpID);

		}
	}

			//---------- deleteMarketplaceClickHandler
	private class deleteMarketplaceClickHandler implements ClickHandler{
	
		public void onClick(ClickEvent event) {

			// bei Click wird die delete() Methode  aufgerufen

			if (Window.confirm("Sind Sie sich sicher, dass Sie das löschen wollen?")) {
				delete();

			}

		}
	}
	

			// ---------- updateMarketplaceClickHandler
	private class updateMarketplaceClickHandler implements ClickHandler{
	public void onClick(ClickEvent event) {

		// bei Click wird die update() Methode aufgerufen
		AddMarketplaceForm addMarketplace = new AddMarketplaceForm(MarketplaceForm.this.selectedMarketplace,MarketplaceForm.this.pitchMenTreeViewModel, false);

	}
}

	
	
	// ---------- delete Methode

	public void delete(){
		

		super.getPitchMenAdmin().deleteMarketplace(selectedMarketplace, new DeleteMarketplaceCallback(selectedMarketplace));
	}

	class DeleteMarketplaceCallback implements AsyncCallback<Void> {
		
		Marketplace m = null; 
		public DeleteMarketplaceCallback(Marketplace m){
			this.m = m; 
		}

		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Projektmarktplatzes ist fehlgeschlagen!");

		}

		public void onSuccess(Void result) {
			if(m!= null) {
				setSelectedMarketplace(null); 
				pitchMenTreeViewModel.deleteMarketplace(m); 
			}
		}
	}
	

	
	
	// ----------pitchMenTreeViewModelsetter
		public void setPitchMenTreeViewModel(PitchMenTreeViewModel pitchMenTreeViewModel) {
			this.pitchMenTreeViewModel = pitchMenTreeViewModel; 
		}
		
		
	// ---------- selectedMarketplace setter
		public void setSelectedMarketplace(Marketplace m) {
			if (m != null) {
				this.selectedMarketplace = m;
				titleBox.setText(selectedMarketplace.getTitle());
				descBox.setText(selectedMarketplace.getDescription());				
				idLabel.setText(Integer.toString(selectedMarketplace.getId()));
			} 
			else {
				idLabel.setText("");
				titleBox.setText("");
				descBox.setText("");
			}
		}
		

}
