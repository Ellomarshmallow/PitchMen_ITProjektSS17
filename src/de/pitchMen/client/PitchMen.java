package de.pitchMen.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.elements.FirstLoginForm;
import de.pitchMen.client.elements.JobPostingForm;
import de.pitchMen.client.elements.MarketplaceForm;
import de.pitchMen.client.elements.PartnerProfileForm;
import de.pitchMen.client.elements.PitchMenTreeViewModel;
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
	public final PitchMenAdminAsync pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();

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
		
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
		pitchMenAdmin.login(GWT.getHostPageBaseURL() + "PitchMen.html", new LoginCallback(this));
	
	}

	/**
	 * Die lokale Klasse LoginCallback wird instantiiert, wenn wir von der 
	 * Applikationsschicht einen Callback bezüglich des Logins erwarten. 
	 * Ihre Instantiierung erfolgt in der onModuleLoad()-Methode.
	 * @author Leon
	 *
	 */
	class LoginCallback implements AsyncCallback<Person> {
		
		private PitchMen pitchMen = null;
		
		public LoginCallback(PitchMen pitchMen) {
			this.pitchMen = pitchMen;
		}

		/*
		 * Fehlerbehandlung, sollte ein Problem bei der 
		 * Überprüfung des Logins auf Serverseite auftreten.
		 *  
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
		 */
		public void onFailure(Throwable caught) {
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new HTML("<h2>Herzlich willkommen bei PitchMen. Leider hat das mit dem Login nicht so ganz funktioniert.</h2>"));
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
					loadPitchMen();
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
					FirstLoginForm firstLoginForm = new FirstLoginForm(this.pitchMen);
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new HTML("<h2>Herzlich willkommen bei PitchMen.</h2>"
														  + "<p>Da dies Ihre erste Anmeldung bei PitchMen ist, "
														  + "bitten wir Sie, das untenstehende Formular auszufüllen.</p>"));
					RootPanel.get("content").add(firstLoginForm);
				}
				
			} else {	
				// Ansonsten gebe einen Link zur Anmeldung aus
				VerticalPanel logPanel = new VerticalPanel();
				HTML logHeading = new HTML("<h1>PitchMen Login</h1>");
				Label logLabel = new Label("Nur ein Schritt trennt Sie noch von spannenden Projekten, die auf Ihre Teilnahme warten! Melden Sie sich jetzt mit einem Google-Konto an, um PitchMen nutzen zu können.");
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
	public void loadPitchMen() {		
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
		partnerProfileLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PartnerProfileForm partnerProfileForm = new PartnerProfileForm();
				RootPanel.get("content").clear();
				RootPanel.get("content").add(partnerProfileForm);
			}
			
		});
		RootPanel.get("usermenu").add(partnerProfileLink);
		RootPanel.get("usermenu").add(logoutLink);

		/*
		 * Navigation-Objekt zur Darstellung der Navigationselemente
		 */
		Navigation navigation = new Navigation();

		// Das navPanel der Seite im Bereich der id "nav" hinzufügen
		RootPanel.get("nav").add(navigation);
		
		RootPanel.get("content").clear();
		
		// Der Begrüßungstext der startseite 
		RootPanel.get("content").add(new HTML(
				"<h2>Herzlich Willkommen bei PitchMen,</h2>"
				+ "<p>dem unabhängigen Projektmarktplatz für Ihre Projekte und Ausschreibungen. "
				+ "<br/>"
				+ "<br/>"
				+ "PitchMen bietet Ihnen die Möglichkeit sowohl an Projektausschreibungen teilzunehmen als auch eigene Projekte "
				+ "anzubieten. Diese Plattform lebt von Ihnen und mit Ihnen. "
				+ "<br/>"
				+ "<br/>"
				+ "Bevor Sie sich jedoch in dem vielfältigen Angebot vertiefen, bitten wir Sie zunächst Ihr eigenes Partnerprofil"
				+ " zu erstellen in dem Sie Ihre Skills präsentieren können. Sie erreichen Ihr Partnerprofil über einen Klick "
				+ "auf Ihren Namen in der rechten Hauptnavigation. Für weitere Fragen steht Ihnen das PitchMen Team "
				+ "gerne zur Verfügung.</p>"
		));
		
		
		
		
		HorizontalPanel footer = new HorizontalPanel();
		Anchor startPage = new Anchor ("Startseite | ", "PitchMen.html");
		Anchor reportGeneratorLink = new Anchor (" ReportGenerator", "ReportGenerator.html");
		HTML copyrightText = new HTML(" | © 2017 PitchMen | ");
		Anchor impressumLink = new Anchor("Impressum");
		footer.add(startPage);
		footer.add(reportGeneratorLink);
		footer.add(copyrightText);
		footer.add(impressumLink);
		
		impressumLink.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML("<h2>Impressum nach §5 TMG</h2>"
													+ "<h3>Verantwortlich</h3>"
													+ "<p>Hochschule der Medien<br />"
													+ "Nobelstraße 8<br />"
													+ "70569 Stuttgart<br /></p>"
													+ "<p><strong>Projektarbeit innerhalb des Studiengangs "
													+ "Wirtschaftsinformatik und digitale Medien, "
													+ "Modul: 335105 IT-Projekt SS 17.</strong></p>"
													+ "<h3>Projektteam</h3>"
													+ "<ul><li>Digel, Julius</li>"
													+ "<li>Drews, Heike</li>"
													+ "<li>Kienzler, Simon</li>"
													+ "<li>Leenen, Lars</li>"
													+ "<li>Schelle, Leon</li>"
													+ "<li>Renz, Eleonora</li></ul>"
													+ "<h3>Kontakt</h3>"
													+ "<p><strong>Telefon:</strong> 0711 8923 10 (Zentrale)</p>"
													+ "<p><strong>Website:</strong> <a href='http://www.hdm-stuttgart.de' target='_blank'>"
													+ "www.hdm-stuttgart.de</a></p>"));
			}
			
		});
		RootPanel.get("footer").add(footer);
		
	}
}
