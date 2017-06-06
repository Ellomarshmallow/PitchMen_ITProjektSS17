package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

import de.pitchMen.shared.bo.Marketplace;

//TODO noch Buttons (löschen bearbeiten) hinzufügen falls hasPermission() is true, bei false nur + Btn
public class MarketplaceForm extends Formular {
	
	private int mpID ; 
	

	

	public MarketplaceForm(int MarketplaceID){
		super();  
		
		this.mpID = MarketplaceID; 
		
		super.getPitchMenAdmin().getMarketplaceByID(this.mpID, new MarketplaceFormCallback(this));
		

		
		
	}
	
	class MarketplaceFormCallback implements AsyncCallback<Marketplace>{
		
		private MarketplaceForm marketplaceform = null; 
		
		public MarketplaceFormCallback(MarketplaceForm marketplaceForm){
			this.marketplaceform = marketplaceForm; 
		}
		
		public void onFailure(Throwable caught){
			
			this.marketplaceform.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));
			
		}
		
		public void onSuccess(Marketplace marketplace){
			if (marketplace != null){
				
							this.marketplaceform.add(new HTML(marketplace.getTitle()));
							this.marketplaceform.add(new HTML(marketplace.getDescription()));
							
							Button addMarketplaceBtn = new Button("+ Neuer Projektmarktplatz", new ClickHandler(){
								
																
								public void onClick(ClickEvent event) {
									
								//bei Click wird die Methode run() aus der Klasse AddMarketplaceForm aufgerufen	
								AddMarketplaceForm addMarketplace = new AddMarketplaceForm(); 
								
								addMarketplace.run(); 
									
							}}); 
											
																		
							this.marketplaceform.add(addMarketplaceBtn); 
							
							//Neues Projekt Button:
							
							Button addProjectBtn = new Button("+ Neues Projekt hinzufügen", new ClickHandler(){
								
																
								public void onClick(ClickEvent event) {
									
								AddProjectForm addProject = new AddProjectForm(); 
								
								addProject.run(mpID);
									
							}}); 
											
																		
							this.marketplaceform.add(addProjectBtn); 
							
							
				
				
			}
		}
	
}}
