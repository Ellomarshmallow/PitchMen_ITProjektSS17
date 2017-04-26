package de.pitchMen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
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
//	private final PitchMenAdminAsync pitchMenAdmin = GWT.create(PitchMenAdmin.class);

	/**
	 * Die Einstiegspunkt-Methode.
	 */
	public void onModuleLoad() {
		// VerticalPanel zur Darstellung der Navigationselemente
		VerticalPanel navPanel = new VerticalPanel();
				
		// Erstellung der Navigations-Buttons
		final Button homeBtn = new Button("Startseite anzeigen");
		final Button marketplaceBtn = new Button("Projektmarktplätze");
		final Button jobPostingBtn = new Button("Ausschreibungen");
		final Button partnerProfileBtn = new Button("Mein Partnerprofil");
			
		// Erstellung von Beispielinhalten
		final HTML homeText = new HTML("<h2>Startseite</h2><p>blababla</p>");
		final HTML marketplaceText = new HTML("<h2>Projektmarktplatz-Übersicht</h2><p>blababla</p>");
		final HTML jobPostingText = new HTML("<h2>Sehen Sie sich diese Ausschreibung an</h2><p>blababla</p>");
		final HTML partnerProfileText = new HTML("<h2>Das ist Ihr Partnerprofil</h2><p>In Ihrem Partnerprofil stehen Ihre Eigenschaften</p>");
		final HTML defaultText = new HTML("<h2>Wählen Sie eine Option links</h2>");
			
		// Die Navigations-Buttons dem navPanel hinzufügen
		navPanel.add(homeBtn);
		navPanel.add(marketplaceBtn);
		navPanel.add(jobPostingBtn);
		navPanel.add(partnerProfileBtn);
		
		// Das navPanel der Seite im Bereich der id "nav" hinzufügen 
		RootPanel.get("nav").add(navPanel);
		
		// Den HTML-Text defaultText im Bereich der id "content" hinzufügen
		RootPanel.get("content").add(defaultText);
		
		// Die Startseite laden, wenn homeBtn geklickt wird
		homeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(homeText);
			}
			
		});
				
		// Die Projektmarktplatz-Seite laden, wenn marketplaceBtn geklickt wird
		marketplaceBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(marketplaceText);
			}
			
		});
		
		// Die Ausschreibungs-Seite laden, wenn jobPostingBtn geklickt wird
		jobPostingBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(jobPostingText);
			}
						
		});
				
	}
}
