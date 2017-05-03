package de.pitchMen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.ReportGeneratorAsync;

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

	// Zeile auskommentiert, da das Interface aktuell nicht lauffähig ist. 
	//Stand: 03.05.2017 19:00 Uhr - Simon
	//private final PitchMenAdminAsync pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();
	
	/**
	 * Initialisierung eines <code>ReportGenerator</code>-Objekts ist nötig, 
	 * um einen zentralen ReportGenerator nutzen zu können.
	 */

	// Zeile auskommentiert, da das Interface aktuell nicht lauffähig ist. 
	//Stand: 03.05.2017 19:00 Uhr - Simon
	//private final ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();


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
		final Content homeContent = new Content("Startseite", "Hier erfahren Sie mehr über PitchMen und wie man es benutzt.",2);
			
		// Die Navigations-Buttons dem navPanel hinzufügen
		navPanel.add(homeBtn);
		navPanel.add(marketplaceBtn);
		navPanel.add(myApplicationsBtn);
		navPanel.add(myPartnerProfileBtn);
		navPanel.add(reportBtn);
		navPanel.add(helpBtn);
		
		// Beispiel-Inhalte für homeContent
		Button createBtn = new Button("Anlegen");
		Button editBtn = new Button("Bearbeiten");
		Button deleteBtn = new Button("Löschen");
		homeContent.addTopBarButton(createBtn);
		homeContent.addTopBarButton(editBtn);
		homeContent.addTopBarButton(deleteBtn);
		
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
		
		// füge neues Grid-Element hinzu, wenn createBtn geklickt wird
		createBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				homeContent.addToGrid(
						new HTML("<div class='item'><h3>Test-Item</h3><p>Beschreibender Text, "
								+ "der etwas länger ist und hoffentlich schön aussieht.</p></div>"));
			}
			
		});
				
		/*
		 *  Einen report laden, wenn der reportBtn geklickt wird. Aktuell ist das der Report
		 *  ShowAllJobPostings. Dieser wiederum implementiert Reportgenerator und PitchMenAdmin,
		 *  beide aktuell noch nicht funktionsfähig. Durch auskommentieren dieses Aufrufs
		 *  bleibt die GUI aktuell compilierfähig. Auskommentierung kann zum Testen und final
		 *  nach Fertigstellung der beiden Interfaces entfernt werden.
		 *  Stand: 03.05.2017 19:00 Uhr - Simon
		 */
		reportBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				RootPanel.get("content").clear();
//				VerticalPanel report = (VerticalPanel) new ShowAllJobPostings();
//				RootPanel.get("content").add(report);
			}
			
		});
	}
}
