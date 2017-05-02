package de.pitchMen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.PitchMenAdminAsync;

/**
 * Die Klasse EntryPoint definiert die Methode <code>onModuleLoad()</code>, die beim Aufrufen der Applikation ausgeführt wird.
 */
public class PitchMen implements EntryPoint {
	/**
	 * Wird dem Nutzer ausgegeben, wenn der Server nicht erreichbar ist.
	 */
	private static final String SERVER_ERROR = "Beim Verbindungsaufbau mit dem Server ist ein Fehler aufgereten." + 
												" Bitte überprüfen Sie Ihre Internetverbindung und versuchen Sie es erneut.";

	/**
	 * Das Erzeugen eines <code>PitchMenAdmin</code>-Objekts ist nötig, 
	 * um eine zentrale Applikations-Verwaltung zu initialisieren,
	 * die die Aktivitäten der Applikation steuert.
	 */

	//private final PitchMenAdminAsync pitchMenAdmin = GWT.create(PitchMenAdmin.class);


	/**
	 * Die Einstiegspunkt-Methode.
	 */
	public void onModuleLoad() {
		// VerticalPanel zur Darstellung der Navigationselemente
		VerticalPanel navPanel = new VerticalPanel();
				
		// Erstellung der Navigations-Buttons
		final Button homeBtn = new Button("Startseite");
		final Button marketplaceBtn = new Button("Projektmarktplätze");
		final Button myApplicationsBtn = new Button("Meine Bewerbungen");
		final Button myPartnerProfileBtn = new Button("Mein Partnerprofil");
		final Button reportBtn = new Button("Berichte");
		final Button helpBtn	= new Button("Hilfe");
		final Content homeContent = new Content("Startseite", "Hier erfahren Sie mehr über PitchMen und wie man es benutzt.");
			
		// Die Navigations-Buttons dem navPanel hinzufügen
		navPanel.add(homeBtn);
		navPanel.add(marketplaceBtn);
		navPanel.add(myApplicationsBtn);
		navPanel.add(myPartnerProfileBtn);
		navPanel.add(reportBtn);
		navPanel.add(helpBtn);
		
		// Beispiel-Inhalte für homeContent
		homeContent.addTopBarButton(new Button("Anlegen"));
		homeContent.addTopBarButton(new Button("Bearbeiten"));
		homeContent.addTopBarButton(new Button("Löschen"));
		
		// Das navPanel der Seite im Bereich der id "nav" hinzufügen 
		RootPanel.get("nav").add(navPanel);
		
		// Default homeContent im Bereich der id "content" hinzufügen
		RootPanel.get("content").add(homeContent);
		
		// Die Startseite laden, wenn homeBtn geklickt wird
		homeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(homeContent);
			}
			
		});
				
//		// Die Projektmarktplatz-Seite laden, wenn marketplaceBtn geklickt wird
//		marketplaceBtn.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				RootPanel.get("content").clear();
//				//RootPanel.get("content").add(marketplaceText);
//			}
//			
//		});
//		
//		// Die Ausschreibungs-Seite laden, wenn jobPostingBtn geklickt wird
//		jobPostingBtn.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				RootPanel.get("content").clear();
//				//RootPanel.get("content").add(jobPostingText);
//			}
//						
//		});
//		
//		// Die Partnerprofil-Seite laden, wenn partnerProfileBtn geklickt wird
//				partnerProfileBtn.addClickHandler(new ClickHandler() {
//
//					@Override
//					public void onClick(ClickEvent event) {
//						RootPanel.get("content").clear();
//						RootPanel.get("content").add(partnerProfileText);
//					}
//								
//				});
//				
//				// Die Bewerbungen laden, wenn applicationBtn geklickt wird
//				applicationBtn.addClickHandler(new ClickHandler() {
//
//					@Override
//					public void onClick(ClickEvent event) {
//						RootPanel.get("content").clear();
//						RootPanel.get("content").add(applicationText);
//					}
//								
//				});
	}
}
