package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class AddMarketplaceForm extends Formular {



	Label titleLabel = new Label("Name des Marktplatzes:");
	TextBox titleBox = new TextBox();		
	Label descLabel = new Label("Beschreibung des Marktplatzes:");
	TextBox descBox = new TextBox();
	Label projectLabel = new Label("Name des ersten Projektes: ");
	public void run(){

		Button cancelButton = new Button("Abbrechen" + new ClickHandler(){
			public void onClick(ClickEvent event){
				/* Wenn man auf den Cancel Button drückt, wird man auf den ersten Projektmarktplatz
				 * der Webseite weitergeleitet.
				 * */
				MarketplaceForm mpf = new MarketplaceForm(1); 
				
			}
		}); 

		Button saveButton = new Button("Speichern" + new ClickHandler(){

			public void onClick(ClickEvent event) {
				// bei Click wird die unten implementierte Methode save() aufgerufen.
				AddMarketplaceForm f1 = new AddMarketplaceForm(); 
				f1.save();


			}
		});

	}
	public void save(){

		super.getPitchMenAdmin().addMarketplace(titleBox.getText(), descBox.getText(), 
				ClientsideSettings.getCurrentUser(), projects, new AddMarketplaceFormCallback(this));
			// TODO: das projects wird aus der addMarketplace Methode noch entfernt (ellis Aufgabe)
		super.setCreator(ClientsideSettings.getCurrentUser().getId());
	}

	
	
	class AddMarketplaceFormCallback implements AsyncCallback<Marketplace>{
		  
		  private AddMarketplaceForm addMarketplaceForm = null; 
			
			public AddMarketplaceFormCallback(AddMarketplaceForm addMarketplaceForm){
				this.addMarketplaceForm = addMarketplaceForm; 
			}
			
			public void onFailure(Throwable caught){
				
				this.addMarketplaceForm.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));
				
			}
			public void onSuccess(Marketplace marketplace){
				
				//muss da noch was rein? 
			}
		
	  }	

}
