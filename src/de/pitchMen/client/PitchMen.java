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
		/*
		 *  Navigation-Objekt zur Darstellung der Navigationselemente
		 */
		Navigation navigation = new Navigation();
		
		// Das navPanel der Seite im Bereich der id "nav" hinzufügen 
		RootPanel.get("nav").add(navigation);
				
		/*
		 *  Einen report laden, wenn der reportBtn geklickt wird. Aktuell ist das der Report
		 *  ShowAllJobPostings. Dieser wiederum implementiert Reportgenerator und PitchMenAdmin,
		 *  beide aktuell noch nicht funktionsfähig. Durch auskommentieren dieses Aufrufs
		 *  bleibt die GUI aktuell compilierfähig. Auskommentierung kann zum Testen und final
		 *  nach Fertigstellung der beiden Interfaces entfernt werden.
		 *  Stand: 03.05.2017 19:00 Uhr - Simon
		 */
//		reportBtn.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				RootPanel.get("content").clear();
//				VerticalPanel report = (VerticalPanel) new ShowAllJobPostings();
//				RootPanel.get("content").add(report);
//			}
//			
//		});
	}
}
