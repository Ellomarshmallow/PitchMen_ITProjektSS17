package de.pitchMen.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	
	public void onLoad() {
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
			RootPanel.get("content").add(homePage);
		}
		
	}
	
	/**
	 *  Die geschachtelte Klasse <code>MarketplacesClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt den Fall, dass ein Nutzer
	 *  auf den Marketplaces-Button klickt.
	 *  
	 * @author Simon
	 */
	private class MarketplacesClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
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
	 * @author Simon
	 */
	private class HelpClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
