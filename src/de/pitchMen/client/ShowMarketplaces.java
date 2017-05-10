package de.pitchMen.client;

import com.google.gwt.user.client.ui.HTML;

/**
 * Die Klasse <code>ShowMarketplaces</code> erweitert die abstrakte
 * Klasse {@link BasicContent}. Sie dient der Darstellung
 * der Marktplätze, 
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
	}
	

}
