package de.pitchMen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.PitchMenAdminAsync;
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
	
	/**
	 * Das Erzeugen eines <code>PitchMenAdmin</code>-Objekts ist nötig, um eine
	 * zentrale Applikations-Verwaltung zu initialisieren, die die Aktivitäten
	 * der Applikation steuert.
	 */
	private final PitchMenAdminAsync pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();

	/**
	 * Initialisierung eines <code>ReportGenerator</code>-Objekts ist nötig, um
	 * einen zentralen ReportGenerator nutzen zu können.
	 */
	// Zeile auskommentiert, da das Interface aktuell nicht lauffähig ist.
	// Stand: 03.05.2017 19:00 Uhr - Simon
	// private final ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();

	/**
	 * Die Einstiegspunkt-Methode. Zunächst Anmeldung
	 */
	public void onModuleLoad() {
		
		pitchMenAdmin.login(GWT.getHostPageBaseURL() + "PitchMen.html", new LoginCallback());
	
	}

	/**
	 * Die lokale Klasse LoginCallback wird instantiiert, wenn wir von der 
	 * Applikationsschicht einen Callback bezüglich des Logins erwarten. 
	 * Ihre Instantiierung erfolgt in der onModuleLoad()-Methode.
	 * @author Leon
	 *
	 */
	class LoginCallback implements AsyncCallback<Person> {

		/*
		 * Fehlerbehandlung, sollte ein Problem bei der 
		 * Überprüfung des Logins auf Serverseite auftreten.
		 *  
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
		 */
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Login fehlgeschlagen");
		}

		// Der Aufruf kommt erfolgreich wieder zurück
		public void onSuccess(Person person) {
			/*
			 * Sollte der Nutzer wider erwarten null sein, wird eine entsprechende
			 * Fehlermeldung ausgegeben.
			 */
			if(person == null) {
				ClientsideSettings.getLogger().severe("Zurückgegebenes User-Objekt ist null.");
			}
			
			// Die Person wird in den ClientsideSettings hinterlegt.
			ClientsideSettings.setCurrentUser(person);
			
			// Ist der Nutzer bereits eingeloggt?
			if (person.isLoggedIn()) {
				// Dann lade die Applikation
				loadPitchMen();
			} else {	
				// Ansonsten gebe einen Link zur Anmeldung aus
				VerticalPanel logPanel = new VerticalPanel();
				Label logLabel = new Label("Bitte melden Sie sich bei Ihrem Google Account an, um die Webseite nutzen zu können");
				Anchor logLink = new Anchor("Anmelden");
				
				logLink.setHref(person.getLoginUrl());
				logPanel.add(logLabel);
				logPanel.add(logLink);
				logPanel.addStyleName("login-link-container");
				
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
		 * Informationen zum angemeldeten User in der Topbar ausgeben.
		 * Hierzu gehört auch der Logout-Link, über den der Nutzer
		 * sich von der PitchMen-Applikation abmelden kann.
		 */
		RootPanel.get("top").add(new HTML("<p><span class='fa fa-user-circle-o'></span> &nbsp; " +
				  ClientsideSettings.getCurrentUser().getFirstName() +
				  " " +
				  ClientsideSettings.getCurrentUser().getName() +
				  "<a href='" +
				  ClientsideSettings.getCurrentUser().getLogoutUrl() +
				  "' title='Ausloggen'><span class='fa fa-sign-out'></span></a></p>"));

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
