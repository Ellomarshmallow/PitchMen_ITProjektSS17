package de.pitchMen.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.server.PitchMenAdminImpl;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.ReportGenerator;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.report.AllApplicationsOfOneUser;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllApplicationsToOneJobPostingOfUser;
//import de.pitchMen.shared.report.AllApplicationsToOneJobPostingOfUser;
import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.AllParticipationsOfOneUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.Column;
import de.pitchMen.shared.report.FanInAndOutReport;
import de.pitchMen.shared.report.FanInJobPostingsOfUser;
import de.pitchMen.shared.report.FanOutApplicationsOfUser;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;
import de.pitchMen.shared.report.Row;

/**
 * Implemetierungsklasse des Interface ReportGenerator.  Sie enth�lt die Applikationslogik, stellt die Zusammenh�nge konstistent dar und ist zust�ndig f�r einen geordneten Ablauf.
 * 
 * @author JuliusDigel
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private static final long serialVersionUID = 1L;
	//private PitchMenAdminImpl administration = null;
	/**
	   * Ein ReportGenerator benötigt Zugriff auf die PitchMenAdministration, da diese die
	   * essentiellen Methoden f�r die Koexistenz von Datenobjekten (vgl.
	   * bo-Package) bietet.
	   */
	private PitchMenAdmin pitchMenAdmin = null;
	
	
	public ReportGeneratorImpl() throws IllegalArgumentException{}
	/**
	   * Seperate Instanzmethode, welche Client-seitig direkt nach GWT.create(Klassenname.class) aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	   * 
	   * @see #ReportGeneratorImpl()
	   */
	@Override
	public void init() throws IllegalArgumentException{
		/**	
		 Ein ReportGeneratorImpl-Objekt instantiiert f�r seinen Eigenbedarf eine
		 * PitchMenAdministration-Instanz.
		 */
		PitchMenAdminImpl a = new PitchMenAdminImpl();
		a.init();
		pitchMenAdmin = a;	
	}

	/**
	 * Auslesen der zugehörigen PitchMenAdministration (interner Gebrauch).
	 * 
	 * @return PitchMenAdmin Objekt
	 */
	protected PitchMenAdmin getPitchMenAdmin() {
		return this.pitchMenAdmin;
	}

	/**
	 * Hinzufügen des Report-Impressums. Diese Methode ist aus den
	 * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	 * diese Tätigkeiten redundant auszuführen hätte. Stattdessen rufen die
	 * <code>create...</code>-Methoden diese Methode auf.
	 * 
	 * @param r der um das Impressum zu erweiternde Report.
	 
	/**
	protected void addImprint(Report r) {
		    
	 /** Das Impressum soll wesentliche Informationen �ber den Report enthalten. */
	 
	/** Bank bank = this.pitchMenAdmin.getMarketplaceByID(id);*/
	 /**
	 * Das Imressum soll mehrzeilig sein.
	 */
	/** CompositeParagraph imprint = new CompositeParagraph();

		    imprint.addSubParagraph(new SimpleParagraph(bank.getName()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getStreet()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getZip() + " "
		        + bank.getCity()));

		    // Das eigentliche Hinzufügen des Impressums zum Report.
		    r.setImprint(imprint);

		  }
*/
	 /**
	   * Erstellen von <code>AllJobPostings Report</code>-Objekten.
	   * 
	   * @param c das Ausschreibungsobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	@Override
	public AllJobPostings showAllJobPostings() throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
			return null;
		}
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		AllJobPostings result = new AllJobPostings();

		 // Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitle("Alle Job Postings");
		 /*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());

		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		Row headline = new Row(); //Erste Zeile im Report

		// Titel der Ausschreibung
		headline.addColumn(new Column("JobPosting Titel"));
		
		// Text der Ausschreibung
		headline.addColumn(new Column("JobPosting Text"));
		
		// das dazugeh�rige Projekt der Ausschreibung
		headline.addColumn(new Column("dazugeh�riges Projekt"));
		
		// Deadline der Ausschreibung
		headline.addColumn(new Column("Deadline"));
		
		// Deadline der Ausschreibung
		headline.addColumn(new Column("Status der Ausschreibung"));
				
		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.addRow(headline);

		/*
	     * Nun werden sämtliche Ausschreibungen ausgelesen und deren Titel, Text, Projekt und
	     * Deadline sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<JobPosting> jobPostings = pitchMenAdmin.getJobPostings();

		for (JobPosting jobPosting : jobPostings) {
			Row jobPostingZeile = new Row();

			jobPostingZeile.addColumn(new Column(jobPosting.getTitle()));
			jobPostingZeile.addColumn(new Column(jobPosting.getText()));
			jobPostingZeile.addColumn(new Column(jobPosting.getProjectId()));
			jobPostingZeile.addColumn(new Column(jobPosting.getDeadline().toString()));
			jobPostingZeile.addColumn(new Column(jobPosting.getStatus()));
			result.addRow(jobPostingZeile);

		}
		//R�ckgabe des fertigen Reports f�r alle Ausschreibungen
		return result;
	}

	 /**
	   * Erstellen von <code>AllJobPostingsMatchingPartnerProfileofUser Report</code>-Objekten.
	   * 
	   * @param c das Partnerprofilobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	@Override
	public AllJobPostingsMatchingPartnerProfileOfUser showAllJobPostingsMatchingPartnerProfileOfUser(PartnerProfile partnerProfile)
			throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
						/*
		     * Zunächst legen wir uns einen leeren Report an.
		     */	
		AllJobPostingsMatchingPartnerProfileOfUser result = new AllJobPostingsMatchingPartnerProfileOfUser();
		 
		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitle("Alle Ausschreibungen passend zum Partnerprofil des Benutzers");
		 /*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		Row headline = new Row();
		
		headline.addColumn(new Column("JobPosting Titel"));
		
		headline.addColumn(new Column("JobPosting Beschreibung"));
		
		headline.addColumn(new Column("Dazugeh�riges Projekt"));
		
		result.addRow(headline);
		
		ArrayList<JobPosting> allJobPostings = this.pitchMenAdmin.getJobPostingsMatchingTraits(partnerProfile);

		for(JobPosting jp : allJobPostings){
			
			Row jobPostingRow = new Row();
			
			jobPostingRow = new Row();
			
			jobPostingRow.addColumn(new Column(jp.getTitle()));
			jobPostingRow.addColumn(new Column(jp.getText()));
			//FIXME
		//	jobPostingRow.addColumn(new Column(pitchMenAdmin.getProjectByID(jp.getProjectId())));
			
			
			result.addRow(jobPostingRow);
		}
		return result;
			
		}
		//R�ckgabe des fertigen Reports 
		return null;
		// TODO Methode noch zu erledigen!
	}
	
	
	 /**
	   * Erstellen von <code>ApplicationsRelatedToJobPostingsOfUser Report</code>-Objekten.
	   * 
	   * @param c das Personenobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	@Override
	public ApplicationsRelatedToJobPostingsOfUser showApplicationsRelatedToJobPostingsOfUser(Person p)
			throws IllegalArgumentException {
		
		if (pitchMenAdmin == null) {
			return null;
		}
		
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		ApplicationsRelatedToJobPostingsOfUser result = new ApplicationsRelatedToJobPostingsOfUser();
		
		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitle("Alle Bewerbungen auf Ausschreibungen des Users");
		
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		
		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		Row headline = new Row();
		// Erstellungsdatum der Bewerbung
		headline.addColumn(new Column("Erstellungsdatum"));
		// Bewerbungstext der Bewerbung
		headline.addColumn(new Column("Bewerbungstext"));
		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.addRow(headline);
		
		/*
	     * Nun werden sämtliche Bewerbungen ausgelesen und deren Erstellungsdatum und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<Application> applications = pitchMenAdmin.getApplications();	
		for (Application a : applications) {

			if(a.getPartnerProfileId() == p.getId()) {};
			Row applicationsrow = new Row();

			applicationsrow.addColumn(new Column(a.getDateCreated().toString()));
			applicationsrow.addColumn(new Column(a.getText()));
			
			//Hinzuf�gen der Row zum Result
			result.addRow(applicationsrow);
		}

		//R�ckgabe des fertigen Reports
		return result;
	}
	
	
	 /**
	   * Erstellen von <code>AllApplicationsToOneJobPostingOfUser Report</code>-Objekten.
	   * 
	   * @param jobPostingId der ForeignKey anhand dessenn der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	 public AllApplicationsToOneJobPostingOfUser showAllApplicationsToOneJobPostingOfUser(int jobPostingId) throws IllegalArgumentException{
		if (pitchMenAdmin == null) {
			return null;
		}
		JobPosting jobPosting = pitchMenAdmin.getJobPostingByID(jobPostingId);
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		AllApplicationsToOneJobPostingOfUser result = new AllApplicationsToOneJobPostingOfUser();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) in welchem der Titel der Ausschreibung
		sowie die ID der Ausschreibung festehalten werden.*/
		result.setTitle("Alle Bewerbungen auf die Ausschreibung " + jobPosting.getTitle() + "mit der ID: " + jobPosting.getId());
		
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());

		Row headline = new Row();
		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		// Erstellungsdatum der Bewerbung
		headline.addColumn(new Column("Erstellungsdatum"));
		// Text der Bewerbung
		headline.addColumn(new Column("Bewerbungstext"));
		// Status der Bewerbung
		headline.addColumn(new Column("Status der Bewerbung"));
		result.addRow(headline);
		//TODO getApplicationsByJobPostingId in PitchmenAdmin implementieren
		ArrayList<Application> applications = pitchMenAdmin.getApplicationsByJobPostingId(jobPostingId);
		/*
	     * Nun werden sämtliche Bewerbungen ausgelesen und deren Erstellungsdatum, Status und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		for(Application a : applications){

			Row applicationRow = new Row();

			applicationRow.addColumn(new Column(a.getDateCreated().toString()));
			applicationRow.addColumn(new Column(a.getText()));
			applicationRow.addColumn(new Column(a.getStatus()));


			//Hinzuf�gen der Row zum Result
			result.addRow(applicationRow);
		}
		//R�ckgabe des fertigen Reports 
		return result;
	};

	/**
	   * Erstellen von <code>AllApplicationsOfUser Report</code>-Objekten.
	   * 
	   * @param Personenobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	@Override
	public AllApplicationsOfUser showAllApplicationsOfUser(Person p) throws IllegalArgumentException {
		
		if (pitchMenAdmin == null) {
			return null;
		}
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		AllApplicationsOfUser result = new AllApplicationsOfUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Alle Bewerbungen eines Nutzers mit den dazugeh�rigen Ausschreibungen");
		
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());

		Row headline = new Row(); //Erste Zeile im Report

		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		// Erstellungsdatum der Bewerbung
		headline.addColumn(new Column("Erstellungsdatum"));
		// Text der Bewerbung
		headline.addColumn(new Column("Bewerbungstext"));
		// Ersteller der Ausschreibung
		headline.addColumn(new Column("Ersteller der Ausschreibung"));
		// Beschreibung der Ausschreibung
		headline.addColumn(new Column("Beschreibung der Ausschreibung"));
		//Hinzuf�gen der Row zum Resultobjekt
		result.addRow(headline);

		/*
	     * Nun werden sämtliche Bewerbungen ausgelesen und deren Erstellungsdatum, Status und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<Application> applications = pitchMenAdmin.getApplicationsByPerson(p.getId());	
		for (Application a : applications) {
			
			
			Application application = pitchMenAdmin.getApplicationByID(a.getJobPostingId());
			Person jobPoster = pitchMenAdmin.getPersonByID(application.getJobPostingId());
			
			Row applicationsrow = new Row();


			applicationsrow.addColumn(new Column(a.getDateCreated().toString()));
			applicationsrow.addColumn(new Column(a.getText()));
			applicationsrow.addColumn(new Column(jobPoster.getFirstName() + " " + jobPoster.getName()));
			applicationsrow.addColumn(new Column(jobPoster.getDescription()));
			
			//Hinzuf�gen der Row zum Result
			result.addRow(applicationsrow);

		}
		//R�ckgabe des fertigen Reports 
		return result;
	}
	
	
	/* Es ist noch nicht Sicher ob ich diese Klasse brauche. Kann ich erst herausfinden wenn wir testen*/
	@Override
	public AllApplicationsOfOneUser showAllApplicationsOfOneUser(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	   * Erstellen von <code>AllParticipationsOfOneUser</code>-Objekten.
	   * 
	   * @param Personenobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	@Override
	public AllParticipationsOfOneUser showAllParticipationsOfOneUser(Person p) throws IllegalArgumentException {
		
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		AllParticipationsOfOneUser result = new AllParticipationsOfOneUser();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Report f�r Alle Beteiligungen eines Nutzers");
		
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		
		Row headline = new Row();

		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		// Dazugeh�riges Projekt
		headline.addColumn(new Column("Projekt"));
		// Startdatum des Projekts
		headline.addColumn(new Column("Startdatum"));
		// Enddatum des Projekts
		headline.addColumn(new Column("Enddatum"));
		// Beschreibung des Projekts
		headline.addColumn(new Column("Projektbeschreibung"));
		
		result.addRow(headline);
		/*
	     * Nun werden sämtliche Projekte ausgelesen und deren Erstellungsdatum, Beschreibung, Titel und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<Project> allProjects = pitchMenAdmin.getProjectsByPerson(p.getId());
		for(Project project : allProjects){
			
			Row projectRow = new Row();
			
			projectRow.addColumn(new Column(project.getTitle()));
			projectRow.addColumn(new Column(project.getDateOpened()));
			projectRow.addColumn(new Column(project.getDateClosed()));
			projectRow.addColumn(new Column(project.getDescription()));
			
			//Hinzuf�gen der Row zum Result
			result.addRow(projectRow);
		}
		//R�ckgabe des fertigen Reports 
		return result;
	}


	/**
	   * Erstellen von <code>ProjectInterweavingsWithParticipationsAndApplications</code>-Objekten.
	   * 
	   * @param Personenobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */	
	@Override
	public ProjectInterweavingsWithParticipationsAndApplications showProjectInterweavingsWithParticipationsAndApplications(
			Person p) throws IllegalArgumentException {
	
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		ProjectInterweavingsWithParticipationsAndApplications result = new ProjectInterweavingsWithParticipationsAndApplications();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Des Nutzers Projektverflechtungen");
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		//Dieser Report ist ein Composite Report und setzt sich aus dem Report "showAllApplicationsOfUser" und "showAllParticipationsOfOneUser" zusammen
		result.addSubReport(this.showAllApplicationsOfUser(p));
		result.addSubReport(this.showAllParticipationsOfOneUser(p));
		//R�ckgabe des fertigen Reports 
		return result;
	}

	/**
	   * Erstellen von <code>FanInJobPostingsOfUser</code>-Objekten.
	   * 
	   * 
	   * @return der fertige Report
	   */	
	@Override
	public FanInJobPostingsOfUser showFanInJobPostingsOfUser() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		FanInJobPostingsOfUser result = new FanInJobPostingsOfUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Die FanIn-Analyse");
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());

		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		Row headline = new Row();
		// ID der Bewerbung
		headline.addColumn(new Column("ID"));
		// Ersteller der Bewerbung
		headline.addColumn(new Column("Person"));
		// Status der Bewerbung
		headline.addColumn(new Column("Bewerbungsstatus"));
		
		result.addRow(headline);
		
		//ArrayList<Person> allPersons = pitchMenAdmin.getAllPeople();
		
		//for(Person person : allPersons) {
			
		ArrayList<Application> allApplications = pitchMenAdmin.getApplications();
		
		/*
	     * Hier werden die Bewerbungen in 3 neue ArrayLists vom Typ Application aufgeteilt 
	     * Und zwar in "ongoing", "declined" und "accepted" welche die Stati der Bewerbungen
	     * wiederspiegeln
	     */
			ArrayList<Application> ongoing = new ArrayList<Application>();
			ArrayList<Application> declined = new ArrayList<Application>();
			ArrayList<Application> accepted = new ArrayList<Application>();
		
			for(Application ap : allApplications){

				/*
			     * Hier werden die Bewerbungen den jeweiligen Stati entsprechend zugeteilt
			     */
								
				if(ap.getStatus().equals("laufend")){
					ongoing.add(ap);
				}
				else if(ap.getStatus().equals("abgelehnt")){
					declined.add(ap);
				}
				else if(ap.getStatus().equals("angenommen")){
					accepted.add(ap);
				};
				
				Row applicationCount = new Row();
				//hinzuf�gen der Spalte f�r die jeweiligen Stati 
				applicationCount.addColumn(new Column(String.valueOf(ongoing.size())));
				applicationCount.addColumn(new Column(String.valueOf(declined.size())));
				applicationCount.addColumn(new Column(String.valueOf(accepted.size())));
				//Hinzuf�gen der Row zum Result
				result.addRow(applicationCount);
				
			}
			
			
		//}
		
			//R�ckgabe des fertigen Reports
		return result;
		
		
		
	}


	/**
	   * Erstellen von <code>FanOutApplicationsOfUser</code>-Objekten.
	   * 
	   * 
	   * @return der fertige Report
	   */	
	@Override
	public FanOutApplicationsOfUser showFanOutApplicationsOfUser() throws IllegalArgumentException {
	
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		FanOutApplicationsOfUser  result = new FanOutApplicationsOfUser();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Die FanOut-Analyse");
		
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());

		Row headline = new Row();
		// ID der Ausschreibungen
		headline.addColumn(new Column("ID"));
		// Ersteller der Ausschreibungen
		headline.addColumn(new Column("Person"));
		// Status der Ausschreibungen
		headline.addColumn(new Column("laufend"));
		// Status der Ausschreibungen
		headline.addColumn(new Column("abgebrochen"));
		// Status der Ausschreibungen
		headline.addColumn(new Column("besetzt"));
		
		result.addRow(headline);
		
		//ArrayList<Person> allPersons = pitchMenAdmin.getAllPeople();
		
		//for(Person person : allPersons) {
			
		ArrayList<JobPosting> allJobPostings = pitchMenAdmin.getJobPostings();
		
		/*
	     * Hier werden die Ausschreibungen in 3 neue ArrayLists vom Typ Application aufgeteilt 
	     * Und zwar in "ongoing", "deleted" und "occupied" welche die Stati der Ausschreibungen
	     * wiederspiegeln
	     */
			ArrayList<JobPosting> ongoing = new ArrayList<JobPosting>();
			ArrayList<JobPosting> deleted = new ArrayList<JobPosting>();
			ArrayList<JobPosting> occupied = new ArrayList<JobPosting>();
		
			for(JobPosting j : allJobPostings){
				
				/*
			     * Hier werden die Bewerbungen den jeweiligen Stati entsprechend zugeteilt
			     * den neuen ArrayLists zugeteilt.
			     */
				if(j.getStatus().equals("laufend")){
					ongoing.add(j);
				}
				else if(j.getStatus().equals("abgelehnt")){
					deleted.add(j);
				}
				else if(j.getStatus().equals("angenommen")){
					occupied.add(j);
				};
				
				Row jobPostingCount = new Row();
				//hinzuf�gen der Spalte f�r die jeweiligen Stati zur Row
				jobPostingCount.addColumn(new Column(String.valueOf(ongoing.size())));
				jobPostingCount.addColumn(new Column(String.valueOf(deleted.size())));
				jobPostingCount.addColumn(new Column(String.valueOf(occupied.size())));
				//Hinzuf�gen der Row zum Result
				result.addRow(jobPostingCount);
				
			}
		//}	
		//R�ckgabe des fertigen Reports
		return result;	
	}

	/**
	   * Erstellen von <code> FanInAndOutReport</code>-Objekten.
	   * 
	   * 
	   * @return der fertige Report
	   */	
	@Override
	public FanInAndOutReport showFanInAndOutReport() throws IllegalArgumentException {
		
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		/*
	     * Zunächst legen wir uns einen leeren Report an.
	     */
		FanInAndOutReport result = new FanInAndOutReport();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Report f�r die FanIn bzw FanOut Analyse");
		/*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		
		/* Dieser Report ist ein Composite Report und setzt sich aus dem Report "showFanInJobPostingsOfUser"
		 * und "showFanOutApplicationsOfUser" zusammen
		 */
		result.addSubReport(this.showFanInJobPostingsOfUser());
		result.addSubReport(this.showFanOutApplicationsOfUser());
		
		//R�ckgabe des fertigen Reports
		return result;
		
		
	}
}