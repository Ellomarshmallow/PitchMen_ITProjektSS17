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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.Marketplace;

public class AddMarketplaceForm extends Formular {
	
	PitchMenTreeViewModel pitchMenTreeViewModel = null; 
	private Marketplace selectedMarketplace = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Marktplatzes:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung des Marktplatzes:");
	TextArea descBox = new TextArea();
	private boolean isSave = false; 

	//FIXME TextArea richtige größe ? NEIN zu gro�
	public AddMarketplaceForm(Marketplace selectedMarketplace,PitchMenTreeViewModel pitchMenTreeViewModel,boolean isSave ) {
		
			this.selectedMarketplace = selectedMarketplace;
			this.pitchMenTreeViewModel = pitchMenTreeViewModel; 
			this.isSave = isSave; 
			
			
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
			
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

		public Marketplace getSelectedMarketplace() {
		return this.selectedMarketplace ;
	}

	// ---------- speichern
	public void save() {
		//FIXME companyID und TeamID
//		getPitchMenAdmin().addMarketplaceByPerson(titleBox.getText(), descBox.getText(), 
//				ClientsideSettings.getCurrentUser().getId(), new AddMarketplaceFormCallback(this));
		ClientsideSettings.getPitchMenAdmin().addMarketplaceByPerson(titleBox.getText(), descBox.getText(), 
				ClientsideSettings.getCurrentUser().getId(), new AddMarketplaceFormCallback(this));
	}

	private class AddMarketplaceFormCallback implements AsyncCallback<Marketplace> {

		private AddMarketplaceForm addMarketplaceForm = null;

		public AddMarketplaceFormCallback(AddMarketplaceForm addMarketplaceForm) {
			this.addMarketplaceForm = addMarketplaceForm;
		}

		public void onFailure(Throwable caught) {
			this.addMarketplaceForm.add(new HTML("Fehler bei RPC Aufruf"));

		}

//		public void onSuccess(Marketplace marketplace) {
//			Window.alert("erfolgreich gespeichert");
//			pitchMenTreeViewModel.addMarketplace(marketplace); 
//		}

		public void onSuccess(Marketplace result) {
			if(Window.confirm("Sind Sie sich sicher, dass Sie die speichern wollen?")){
				
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new MarketplaceForm(getSelectedMarketplace()));
				Window.alert("erfolgreich gespeichert");
		}
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
	 
	 // ---------- getIsSave
	 
	 public boolean getIsSave(){
		 return this.isSave; 
	 }
	 
	   
	 // ---------- saveClickHandler
	 private class saveClickHandler implements ClickHandler{
		 public void onClick(ClickEvent event) {

			 RootPanel.get("content").clear();
			 
			 if (Window.confirm("Möchten Sie den Projektmarktplatz speichern?")) {

				 if(getIsSave()){

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
	 }

	// ---------- cancelClickHandler
		 private class cancelClickHandler implements ClickHandler{
				public void onClick(ClickEvent event) {

					/* Wenn man auf den Cancel Button drückt, wird man auf den vorherigen
					 * Projektmarktplatz zurückgeführt.*/
					RootPanel.get("content").clear();
					MarketplaceForm cancel = new MarketplaceForm(getSelectedMarketplace());

				}
			}
		 
}
