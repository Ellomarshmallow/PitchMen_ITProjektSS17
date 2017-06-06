package de.pitchMen.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

import de.pitchMen.client.elements.PitchMenTreeViewModel;

/**
 * Die Klasse <code>Navigation</code> erweitert die
 * von GWT angebotene Klasse VerticalPanel und dient
 * der Darstellung von Buttons, mit deren hilfe der
 * Nutzer zwischen verschiedenen Ansichten der 
 * Projektmarktplatz-Applikation wechseln kann.
 * 
 * @author Simon
 */

public class Navigation extends VerticalPanel {
	
	/* TODO useTreeNavigation-Variable
	 * 
	 * Helfer-Variable, um zwischen der alten,
	 * Button-basierten Navigation und der von Herrn
	 * Rathke vorgeschlagenen Baum-Navigation hin
	 * und her zu wechseln. Sollte beizeiten entfernt
	 * werden.
	 * Simon, 04.06.2017, 14:10 Uhr
	 * 
	 */
	private boolean useTreeNavigation = true;
	
	/**
	 * Aktuell verzweigt die onLoad()-Methode nur zwischen
	 * den Methoden onLoadWithButtons() und onLoadWithTree()
	 * (s. todo Z. 21). Wenn eine Entscheidung bzgl. der
	 * Darstellung getroffen ist, kann der Inhalt der 
	 * entsprechenden Methode hier eingefügt und der Inhalt
	 * der anderen gelöscht werden.
	 */
	public void onLoad() {
		if(this.useTreeNavigation) {
			this.onLoadWithTree();
		} else {
			this.onLoadWithButtons();
		}
	}
	
	/**
	 * Wird aufgerufen, wenn die Variable useTreeNavigation
	 * false ist.
	 */
	private void onLoadWithButtons() {
		/*
		 * Die folgenden Buttons gibt es in der 
		 * PitchMen-Navigation:
		 */
		Button homeBtn = new Button("Startseite");
		Button marketplacesBtn = new Button("Projektmarktplätze");
		Button applicationsBtn = new Button("Bewerbungen");
		Button repGenBtn = new Button("Report Generator");
		Button helpBtn = new Button("Hilfe");
		
		/*
		 * Sie werden der Navigation hinzugefügt 
		 */
		this.add(homeBtn);
		this.add(marketplacesBtn);
		this.add(applicationsBtn);
		this.add(repGenBtn);
		this.add(helpBtn);		
		
		/*
		 * Jeder Button benötigt einen ClickHandler,
		 * um auf Interaktionen des Nutzers entsprechend
		 * reagieren zu können.
		 */
		homeBtn.addClickHandler(new HomeClickHandler());
		marketplacesBtn.addClickHandler(new MarketplacesClickHandler());
		applicationsBtn.addClickHandler(new ApplicationsClickHandler());
		repGenBtn.addClickHandler(new RepGenClickHandler());
		helpBtn.addClickHandler(new HelpClickHandler());
	}
	
	/**
	 * Wird aufgerufen, wenn die Variable useTreeNavigation
	 * true ist.
	 */
	private void onLoadWithTree() {
		// Modell für den Baum erstellen. 
		TreeViewModel navTreeModel = new PitchMenTreeViewModel();
		
		/*
		 * Anlegen des Baumes mit dem zuvor definierten TreeViewModel.
		 * Als zweiter Parameter wird null übergeben, damit wird der 
		 * Default-Wert für einen Baumknoten festgelegt.  
		 */
		CellTree navTree = new CellTree(navTreeModel, null);
		
		// Baum wird der Navigation hinzugefügt
		this.add(navTree);
	}
	
	/**
	 *  Die geschachtelte Klasse <code>HomeClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt den Fall, dass ein Nutzer
	 *  auf den home-Button klickt.
	 *  
	 * @author Simon
	 */
	private class HomeClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			HomePage homePage = new HomePage();
			RootPanel.get("content").clear();
			RootPanel.get("content").add(homePage);
		}
		
	}
	
	/**
	 *  Die geschachtelte Klasse <code>MarketplacesClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt den Fall, dass ein Nutzer
	 *  auf den Marketplaces-Button klickt.
	 *  
	 * @author Leon
	 */
	private class MarketplacesClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			ShowMarketplaces marketPlace = new ShowMarketplaces();
			RootPanel.get("content").clear();
			RootPanel.get("content").add(marketPlace);
			
		}
		
	}
	
	/**
	 *  Die geschachtelte Klasse <code>ApplicationsClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt den Fall, dass ein Nutzer
	 *  auf den Applications-Button klickt.
	 *  
	 * @author Simon
	 */
	private class ApplicationsClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			RootPanel.get("content").clear();
			
		}
		
	}
	
	/**
	 *  Die geschachtelte Klasse <code>RepGenClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt den Fall, dass ein Nutzer
	 *  auf den RepGen-Button klickt.
	 *  
	 * @author Simon
	 */
	private class RepGenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 *  Die geschachtelte Klasse <code>HelpClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt den Fall, dass ein Nutzer
	 *  auf den Help-Button klickt.
	 *  
	 * @author Leon
	 */
	private class HelpClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			ShowHelp sh = new ShowHelp(); 
			RootPanel.get("content").clear();
			RootPanel.get("content").add(sh);
		}
		
	}

}
