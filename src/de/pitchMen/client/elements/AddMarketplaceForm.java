package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Marketplace;

public class AddMarketplaceForm extends Formular {
	
	PitchMenTreeViewModel pitchMenTreeViewModel = null; 
	private Marketplace selectedMarketplace = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Marktplatzes:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung des Marktplatzes:");
	TextArea descBox = new TextArea();
	boolean addMarketplace = false; 

	//FIXME TextArea richtige größe ? 
	public AddMarketplaceForm(Marketplace selectedMarketplace,PitchMenTreeViewModel pitchMenTreeViewModel,boolean addMarketplace ) {
		
			this.selectedMarketplace = selectedMarketplace;
			this.pitchMenTreeViewModel = pitchMenTreeViewModel; 
			this.addMarketplace = addMarketplace; 
		//TODO beim anzeigen der TextBoxes: addMarketplace = true dann alles leer, bei false die vorherigen Daten übernehmen
		
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
				
			
			Button cancelButton = new Button("Abbrechen" + new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*
				 * Wenn man auf den Cancel Button drückt, wird man auf den vorherigen
				 * Projektmarktplatz zurückgeführt.
				 */
				MarketplaceForm mpf = new MarketplaceForm(getSelectedMarketplace());
				
			}
		});
			buttonsPanel.add(cancelButton);
			
		// ---------- Speichern-Button
		Button saveButton = new Button("Speichern" + new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (Window.confirm("Sind alle Angaben korrekt?")) {
					
					if(AddMarketplaceForm.this.addMarketplace){
						
						// bei Click wird die unten implementierte Methode save()
						// aufgerufen.
						save();
						MarketplaceForm mpf = new MarketplaceForm(getSelectedMarketplace());
					}
					else{
						update();
						MarketplaceForm mpf = new MarketplaceForm(getSelectedMarketplace());
					}

				}

			}
		});
			buttonsPanel.add(saveButton);
	}

		public Marketplace getSelectedMarketplace() {
		return this.selectedMarketplace ;
	}

	// ---------- speichern
	public void save() {
		//FIXME companyID und TeamID
		super.getPitchMenAdmin().addMarketplace(titleBox.getText(), descBox.getText(),
				ClientsideSettings.getCurrentUser().getId(), 0, 0, new AddMarketplaceFormCallback(this));
	}

	class AddMarketplaceFormCallback implements AsyncCallback<Marketplace> {

		private AddMarketplaceForm addMarketplaceForm = null;

		public AddMarketplaceFormCallback(AddMarketplaceForm addMarketplaceForm) {
			this.addMarketplaceForm = addMarketplaceForm;
		}

		public void onFailure(Throwable caught) {

			this.addMarketplaceForm.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}

		public void onSuccess(Marketplace marketplace) {

			Window.alert("erfolgreich gespeichert");
			pitchMenTreeViewModel.addMarketplace(marketplace); 
		}

	}
	
		// ---------- update Methode
	
	public void update() {
		if (selectedMarketplace != null) {
			selectedMarketplace.setTitle(titleBox.getText());
			selectedMarketplace.setDescription(descBox.getText());
		super.getPitchMenAdmin().updateMarketplace(selectedMarketplace, new UpdateMarketplaceCallback());
	}}

	
		// ---------- update Callback
	 class UpdateMarketplaceCallback implements AsyncCallback<Void> {
	

	 public void onFailure(Throwable caught) {
	 Window.alert("Das Bearbeiten des Projektmarktplatzes ist fehlgeschlagen!");
	
	 }
	
	 public void onSuccess(Void result) {
		 pitchMenTreeViewModel.updateMarketplace(selectedMarketplace);
	 }
	 }

}
