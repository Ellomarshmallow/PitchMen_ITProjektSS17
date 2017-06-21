package de.pitchMen.client.report;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.client.Navigation;
import de.pitchMen.client.PitchMen;
import de.pitchMen.client.report.ReportDisplay.LoginCallback;
import de.pitchMen.client.elements.FirstLoginForm;
import de.pitchMen.client.elements.JobPostingForm;
import de.pitchMen.client.elements.MarketplaceForm;
import de.pitchMen.client.elements.PartnerProfileForm;
import de.pitchMen.client.elements.PitchMenTreeViewModel;
import de.pitchMen.shared.ReportGeneratorAsync;
import de.pitchMen.shared.bo.Person;

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
	 * Die Einstiegspunkt-Methode. Zun�chst die Anmeldung durchf�hren. 
	 */
	public void onModuleLoad() {
		
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
		RootPanel.get("content").add(new HTML("<p class='load-msg'>Warte auf Nutzereingabe.</p>"));
		ClientsideSettings.getPitchMenAdmin().login(GWT.getHostPageBaseURL() + "ReportGenerator.html", new LoginCallback(this));
	}

	/**
	 * Die lokale Klasse LoginCallback wird instantiiert, wenn wir von der 
	 * Applikationsschicht einen Callback bez�glich des Logins erwarten. 
	 * Ihre Instantiierung erfolgt in der onModuleLoad()-Methode.
	 * @author Heike
	 *
	 */
	class LoginCallback implements AsyncCallback<Person> {
		
		private ReportDisplay reportDisplay = null;
		
		public LoginCallback(ReportDisplay reportDisplay) {
			this.reportDisplay = reportDisplay;
		}
		/*
		 * Fehlerbehandlung, sollte ein Problem bei der 
		 * Überprüfung des Logins auf Serverseite auftreten.
		 *  
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
		 */
		public void onFailure(Throwable caught) {
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new HTML("<h2>Herzlich willkommen beim ReportGenerator von PitchMen. Leider hat das mit dem Login nicht so ganz funktioniert.</h2>"));
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
							// Ist der Nutzer bereits in der Datenbank? 
						if(person.getIsExisting()){					
							// Dann lade die Applikation
							loadReportGenerator();
						}
						else{
							/*
							 * Informationen zum angemeldeten User in der Topbar ausgeben.
							 * Hierzu gehört auch der Logout-Link, über den der Nutzer
							 * sich von der PitchMen-Applikation abmelden kann.
							 */
							RootPanel.get("usermenu").add(new HTML("<p><span class='fa fa-user-circle-o'></span> &nbsp; " +
									  ClientsideSettings.getCurrentUser().getEmailAdress() +
									  "<a href='" +
									  ClientsideSettings.getCurrentUser().getLogoutUrl() +
									  "' title='Ausloggen'><span class='fa fa-sign-out'></span></a></p>"));
							
							//Wenn der Nutzer sich das erste Mal eingeloggt hat, dann wird ein Formular aufgerufen.
							FirstLoginForm firstLoginForm = new FirstLoginForm(this.reportDisplay);
							RootPanel.get("content").clear();
							RootPanel.get("content").add(new HTML("<h2>Wir freuen uns, "
																  + "dass Sie den Weg zum ReportGenerator von PitchMen gefunden haben.</h2>"
																  + "<p>Da dies Ihre erste Anmeldung bei PitchMen ist,"
																  + "würden wir gerne Ihren Namen wissen.</p>"));
							RootPanel.get("content").add(firstLoginForm);
						}		

						
					} else {	
						// Ansonsten gebe einen Link zur Anmeldung aus
						VerticalPanel logPanel = new VerticalPanel();
						HTML logHeading = new HTML("<h1>ReportGenerator Login</h1>");
						Label logLabel = new Label("Nur ein Schritt trennt Sie noch von spannenden Analysen! Melden Sie sich jetzt mit einem Google-Konto an, um den ReportGenerator nutzen zu können.");
						Anchor logLink = new Anchor("Mit Google anmelden");
						
						logLink.setHref(person.getLoginUrl());
						logPanel.add(logHeading);
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
	public void loadReportGenerator() {		
		/*
		 * Informationen zum angemeldeten User in der Topbar ausgeben.
		 * Hierzu gehört auch der Logout-Link, über den der Nutzer
		 * sich von der PitchMen-Applikation abmelden kann.
		 */
		HTML partnerProfileLink = new HTML("<p><span class='fa fa-user-circle-o'></span> &nbsp; " 
									+ ClientsideSettings.getCurrentUser().getFirstName()
									+ " " 
									+ ClientsideSettings.getCurrentUser().getName()
									+ "</p>");
		
		HTML logoutLink = new HTML("<p><a href='" 
									+ ClientsideSettings.getCurrentUser().getLogoutUrl() 
									+ "'><span class='fa fa-sign-out'></span></a></p>");
		RootPanel.get("usermenu").add(partnerProfileLink);
		RootPanel.get("usermenu").add(logoutLink);

		/*
		 * Navigation-Objekt zur Darstellung der Navigationselemente
		 */
		ReportNavigation repNav = new ReportNavigation();
		RootPanel.get("nav").add(repNav);
		
		HorizontalPanel footer = new HorizontalPanel();
		HTML copyrightText = new HTML("© 2017 PitchMen | ");
		Anchor impressumLink = new Anchor("Impressum");
		footer.add(copyrightText);
		footer.add(impressumLink);
		RootPanel.get("footer").add(footer);
	}
		
				
}
