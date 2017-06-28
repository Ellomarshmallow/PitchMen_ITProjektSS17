package de.pitchMen.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

import de.pitchMen.client.elements.MarketplaceForm;
import de.pitchMen.client.elements.PitchMenTreeViewModel;

/**
 * Die Klasse <code>Navigation</code> erweitert die
 * von GWT angebotene Klasse VerticalPanel und dient
 * der Darstellung eines Navigationsbaumes, mit dessen
 * Hilfe der Nutzer zwischen verschiedenen Ansichten der 
 * Projektmarktplatz-Applikation wechseln kann.
 * 
 * @author Simon
 */

public class Navigation extends VerticalPanel {
	
	/**
	 * <code>onLoad()</code>-Methode, die aufgerufen wird,
	 * sobald das Widget zur Anzeige gebracht wird.
	 */
	public void onLoad() {
		// Modell für den Baum erstellen. 
		PitchMenTreeViewModel navTreeModel = new PitchMenTreeViewModel();
		
		/*
		 * Anlegen des Baumes mit dem zuvor definierten TreeViewModel.
		 * Als zweiter Parameter wird null übergeben, damit wird der 
		 * Default-Wert für einen Baumknoten festgelegt.  
		 */
		CellTree navTree = new CellTree(navTreeModel, null);
		
		// Baum wird der Navigation hinzugefügt
		
		Button newMarketplaceBtn = new Button("Neuen Projektmarktplatz anlegen");
		newMarketplaceBtn.addStyleName("editor");
		
		newMarketplaceBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MarketplaceForm addMarketplaceForm = new MarketplaceForm();
			}
			
		});
		
		this.add(newMarketplaceBtn);
		
		this.add(navTree);
	}
}