package de.pitchMen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.ReportGeneratorAsync;
import de.pitchMen.shared.bo.Person;

/**
 * Die Klasse EntryPoint definiert die Methode <code>onModuleLoad()</code>, die
 * beim Aufrufen der Applikation ausgeführt wird.
 */
public class PitchMen implements EntryPoint {
	/**
	 * Wird dem Nutzer ausgegeben, wenn der Server nicht erreichbar ist.
	 */
	private static final String SERVER_ERROR = "Beim Verbindungsaufbau mit dem Server ist ein Fehler aufgereten."
			+ " Bitte überprüfen Sie Ihre Internetverbindung und versuchen Sie es erneut.";
	/*
	 * Instanzvariablen 
	 */
	private Person logInf = null;
	private VerticalPanel logPanel = new VerticalPanel();
	private Label logLabel = new Label(
			"Bitte melden Sie sich bei Ihrem Google Account an, um die Webseite nutzen zu können");
	private Anchor logLink = new Anchor("Anmelden");
	/**
	 * Das Erzeugen eines <code>PitchMenAdmin</code>-Objekts ist nötig, um eine
	 * zentrale Applikations-Verwaltung zu initialisieren, die die Aktivitäten
	 * der Applikation steuert.
	 */

	// Zeile auskommentiert, da das Interface aktuell nicht lauffähig ist.
	// Stand: 03.05.2017 19:00 Uhr - Simon
	// private final PitchMenAdminAsync pitchMenAdmin =
	// ClientsideSettings.getPitchMenAdmin();

	/**
	 * Initialisierung eines <code>ReportGenerator</code>-Objekts ist nötig, um
	 * einen zentralen ReportGenerator nutzen zu können.
	 */

	// Zeile auskommentiert, da das Interface aktuell nicht lauffähig ist.
	// Stand: 03.05.2017 19:00 Uhr - Simon
	// private final ReportGeneratorAsync reportGenerator =
	// ClientsideSettings.getReportGenerator();

	PitchMenAdminAsync pmVerwaltung = ClientsideSettings.getPitchMenAdmin();

	/**
	 * Die Einstiegspunkt-Methode. Zunächst Anmeldung
	 */
	public void onModuleLoad() {

	
		pmVerwaltung.login(GWT.getHostPageBaseURL() + "PitchMen.html", new LoginCallback());
	}

	class LoginCallback implements AsyncCallback<Person> {

		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Login fehlgeschlagen");
		}

		public void onSuccess(Person rs) {

			ClientsideSettings.setCurrentUser(rs);
			// Überprüfen des Login Status
			if (rs.isLoggedIn()) {
				loadPitchMen();
			}

			else {
				
				
				logInf = rs;
				logLink.setHref(logInf.getLoginUrl());
				logPanel.add(logLabel);
				logPanel.add(logLink);
				RootPanel.get("content").add(logPanel);
			
			}
		}

	}
	/*
	 * Auslagerung des Startens in die Methode loadPitchMen(), da in der
	 * onModuleLoad() Methode zunächst geprüft werden muss, ob der User
	 * eingeloggt ist.
	 * 
	 */

	private void loadPitchMen() {

		/*
		 * Navigation-Objekt zur Darstellung der Navigationselemente
		 */
		Navigation navigation = new Navigation();

		// Das navPanel der Seite im Bereich der id "nav" hinzufügen
		RootPanel.get("nav").add(navigation);

		/*
		 * Einen report laden, wenn der reportBtn geklickt wird. Aktuell ist das
		 * der Report ShowAllJobPostings. Dieser wiederum implementiert
		 * Reportgenerator und PitchMenAdmin, beide aktuell noch nicht
		 * funktionsfähig. Durch auskommentieren dieses Aufrufs bleibt die GUI
		 * aktuell compilierfähig. Auskommentierung kann zum Testen und final
		 * nach Fertigstellung der beiden Interfaces entfernt werden. Stand:
		 * 03.05.2017 19:00 Uhr - Simon
		 */
		// reportBtn.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// RootPanel.get("content").clear();
		// VerticalPanel report = (VerticalPanel) new ShowAllJobPostings();
		// RootPanel.get("content").add(report);
		// }
		//
		// });

	}
}
