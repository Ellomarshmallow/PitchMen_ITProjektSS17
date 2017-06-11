package de.pitchMen.client.report;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.ReportGeneratorAsync;

/**
 * Diese Klasse ist der Einsteigsprunkt für die Report-Funktionen
 * der PitchMen-Applikation.
 * 
 * @author Simon
 *
 */

public class ReportDisplay implements EntryPoint {
	/**
	 * Wird dem Nutzer ausgegeben, wenn der Server nicht erreichbar ist.
	 */
	private static final String SERVER_ERROR = "Beim Verbindungsaufbau mit dem Server ist ein Fehler aufgereten."
			+ " Bitte überprüfen Sie Ihre Internetverbindung und versuchen Sie es erneut.";

	/**
	 * Initialisierung eines <code>ReportGenerator</code>-Objekts ist nötig, um
	 * einen zentralen ReportGenerator nutzen zu können.
	 */
	private final ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();

	/**
	 * Die Einstiegspunkt-Methode.
	 */
	@Override
	public void onModuleLoad() {
		ReportNavigation repNav = new ReportNavigation();
		RootPanel.get("nav").add(repNav);
		
	}

}
