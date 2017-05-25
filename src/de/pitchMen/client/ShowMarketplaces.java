package de.pitchMen.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.Marketplace;


/**
 * Die Klasse <code>ShowMarketplaces</code> erweitert die abstrakte
 * Klasse {@link BasicContent}. Sie dient der Darstellung
 * der Marktplätze
 * 
 * @author Leon
 */

public class ShowMarketplaces extends BasicContent {
	@Override
	protected String getHeadline(){
			return "Alle Marktplätze"; 		
	}
	
	protected String getDescription(){
		return "Wählen Sie einen Marktplatz aus und sehen Sie sich die darin enthaltenen Projekte an"; 
	}
	
	protected void run(){		
		PitchMenAdminAsync pitchmenadmin = ClientsideSettings.getPitchMenAdmin(); 
		pitchmenadmin.getMarketplaces(new GetMarketplacesCallback(this));
	}
	
	
	class GetMarketplacesCallback implements AsyncCallback<ArrayList<Marketplace>>{
		
		private BasicContent content = null; 
		
		public GetMarketplacesCallback (BasicContent content){
			this.content = content; 
		}
		
		public void onFailure(Throwable caught) {
		      this.content.add(new HTML("Fehler beim RPC-Aufruf: " + caught.getMessage()));
		       }
		
		
		public void onSuccess(ArrayList<Marketplace> marketplaces){			
			if(marketplaces != null){			
				
				for (Marketplace m : marketplaces){
				
				this.content.add(new HTML("<p>Marktplatz " + m.getId() + ": <em>" +m.getTitle() + "</em><br />" + m.getDescription() + "</p>"));
			
				}
					
			}
			
					
			
		}
		
		
	}
	
	
	

}
