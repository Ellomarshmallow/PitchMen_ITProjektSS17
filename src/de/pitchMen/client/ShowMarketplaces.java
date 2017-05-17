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
		this.add(new HTML("<h3>Was sind Projekte</h3>"));
		
		PitchMenAdminAsync verwaltung = ClientsideSettings.getPitchMenAdmin(); 
		verwaltung.getMarketplaces(new GetMarketplaceCallback(this));
	}
	
	
	class GetMarketplaceCallback implements AsyncCallback<ArrayList<Marketplace>>{
		
		private BasicContent content = null; 
		
		public GetMarketplaceCallback (BasicContent content){
			this.content = content; 
		}
		
		public void onFailure(Throwable caught) {
		      this.content.add(new HTML("Fehler: " + caught.getMessage()));
		       }
		
		
		public void onSuccess(ArrayList<Marketplace> marketplace){			
			if(marketplace != null){			
				
				for (Marketplace m : marketplace){
					
					this.content.add(new HTML("ALle Plätze: " +m.getTitle() + m.getDescription() + m.getId()));
			
				}
					
			}
			
		
		
			
			
		}
		
		
	}
	
	
	

}
