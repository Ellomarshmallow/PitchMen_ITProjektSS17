package de.pitchMen.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.client.PitchMen;
import de.pitchMen.server.*;
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
import de.pitchMen.shared.report.CompositeParagraph;
import de.pitchMen.shared.report.FanInAndOutReport;
import de.pitchMen.shared.report.FanInJobPostingsOfUser;
import de.pitchMen.shared.report.FanOutApplicationsOfUser;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;
import de.pitchMen.shared.report.Report;
import de.pitchMen.shared.report.Row;
import de.pitchMen.shared.report.SimpleParagraph;
import de.pitchMen.shared.report.SimpleReport;

/**
 * Implemetierungsklasse des Interface ReportGenerator.  Sie enthält die Applikationslogik, stellt die Zusammenhänge konstistent dar und ist zuständig für einen geordneten Ablauf.
 * 
 * @author JuliusDigel
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private static final long serialVersionUID = 1L;
	//private PitchMenAdminImpl administration = null;
	/**
	   * Ein ReportGenerator benÃ¶tigt Zugriff auf die PitchMenAdministration, da diese die
	   * essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
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
		 Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
		 * PitchMenAdministration-Instanz.
		 */
		PitchMenAdminImpl a = new PitchMenAdminImpl();
		a.init();
		pitchMenAdmin = a;	
	}

	/**
	 * Auslesen der zugehÃ¶rigen PitchMenAdministration (interner Gebrauch).
	 * 
	 * @return PitchMenAdmin Objekt
	 */
	protected PitchMenAdmin getPitchMenAdmin() {
		return this.pitchMenAdmin;
	}

	/**
	 * HinzufÃ¼gen des Report-Impressums. Diese Methode ist aus den
	 * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	 * diese TÃ¤tigkeiten redundant auszufÃ¼hren hÃ¤tte. Stattdessen rufen die
	 * <code>create...</code>-Methoden diese Methode auf.
	 * 
	 * @param r der um das Impressum zu erweiternde Report.
	 
	/**
	protected void addImprint(Report r) {
		    
	 /** Das Impressum soll wesentliche Informationen über den Report enthalten. */
	 
	/** Bank bank = this.pitchMenAdmin.getMarketplaceByID(id);*/
	 /**
	 * Das Imressum soll mehrzeilig sein.
	 */
	/** CompositeParagraph imprint = new CompositeParagraph();

		    imprint.addSubParagraph(new SimpleParagraph(bank.getName()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getStreet()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getZip() + " "
		        + bank.getCity()));

		    // Das eigentliche HinzufÃ¼gen des Impressums zum Report.
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
	public AllJobPostings showAllJobPostings(JobPosting jopPosting) throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
			return null;
		}
		/*
	     * ZunÃ¤chst legen wir uns einen leeren Report an.
	     */
		AllJobPostings result = new AllJobPostings();

		 // Jeder Report hat einen Titel (Bezeichnung / Ãœberschrift).
		result.setTitle("Alle Job Postings");
		 /*
	     * Datum der Erstellung hinzufÃ¼gen. new Date() erzeugt autom. einen
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
		
		// das dazugehörige Projekt der Ausschreibung
		headline.addColumn(new Column("dazugehöriges Projekt"));
		
		// Deadline der Ausschreibung
		headline.addColumn(new Column("Deadline"));
		
		// HinzufÃ¼gen der zusammengestellten Kopfdaten zu dem Report
		result.addRow(headline);

		/*
	     * Nun werden sÃ¤mtliche Ausschreibungen ausgelesen und deren Titel, Text, Projekt und
	     * Deadline sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<JobPosting> jobPostings = pitchMenAdmin.getJobPostings();

		for (JobPosting jobPosting : jobPostings) {
			Row jobPostingZeile = new Row();

			jobPostingZeile.addColumn(new Column(jobPosting.getTitle()));
			jobPostingZeile.addColumn(new Column(jobPosting.getText()));
			jobPostingZeile.addColumn(new Column(jobPosting.getProjectId()));
			jobPostingZeile.addColumn(new Column(jobPosting.getDeadline().toString()));
			result.addRow(jobPostingZeile);

		}
		//Rückgabe des fertigen Reports für alle Ausschreibungen
		return result;
	}

	 /**
	   * Erstellen von <code>AllJobPostingsMatchinPartnerProfileofUser Report</code>-Objekten.
	   * 
	   * @param c das Partnerprofilobjekt bzgl. dessen der Report erstellt werden soll.
	   * @return der fertige Report
	   */
	@Override
	public AllJobPostingsMatchingPartnerProfileOfUser showAllJobPostingsMatchingPartnerProfileOfUser(PartnerProfile partnerProfile)
			throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
			return null;
		}
		//Rückgabe des fertigen Reports 
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
	     * ZunÃ¤chst legen wir uns einen leeren Report an.
	     */
		ApplicationsRelatedToJobPostingsOfUser result = new ApplicationsRelatedToJobPostingsOfUser();
		
		// Jeder Report hat einen Titel (Bezeichnung / Ãœberschrift).
		result.setTitle("Alle Bewerbungen auf Ausschreibungen des Users");
		
		/*
	     * Datum der Erstellung hinzufÃ¼gen. new Date() erzeugt autom. einen
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
		// HinzufÃ¼gen der zusammengestellten Kopfdaten zu dem Report
		result.addRow(headline);
		
		/*
	     * Nun werden sÃ¤mtliche Bewerbungen ausgelesen und deren Erstellungsdatum und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<Application> applications = pitchMenAdmin.getApplications();	
		for (Application a : applications) {

			if(a.getPartnerProfileId() == p.getId()) {};
			Row applicationsrow = new Row();

			applicationsrow.addColumn(new Column(a.getDateCreated().toString()));
			applicationsrow.addColumn(new Column(a.getText()));
			
			//Hinzufügen der Row zum Result
			result.addRow(applicationsrow);
		}

		//Rückgabe des fertigen Reports
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
	     * ZunÃ¤chst legen wir uns einen leeren Report an.
	     */
		AllApplicationsToOneJobPostingOfUser result = new AllApplicationsToOneJobPostingOfUser();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Ãœberschrift) in welchem der Titel der Ausschreibung
		sowie die ID der Ausschreibung festehalten werden.*/
		result.setTitle("Alle Bewerbungen auf die Ausschreibung " + jobPosting.getTitle() + "mit der ID: " + jobPosting.getId());
		
		/*
	     * Datum der Erstellung hinzufÃ¼gen. new Date() erzeugt autom. einen
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
	     * Nun werden sÃ¤mtliche Bewerbungen ausgelesen und deren Erstellungsdatum, Status und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		for(Application a : applications){

			Row applicationRow = new Row();

			applicationRow.addColumn(new Column(a.getDateCreated().toString()));
			applicationRow.addColumn(new Column(a.getText()));
			applicationRow.addColumn(new Column(a.getStatus()));


			//Hinzufügen der Row zum Result
			result.addRow(applicationRow);
		}
		//Rückgabe des fertigen Reports 
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
	     * ZunÃ¤chst legen wir uns einen leeren Report an.
	     */
		AllApplicationsOfUser result = new AllApplicationsOfUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Ãœberschrift) */
		result.setTitle("Alle Bewerbungen eines Nutzers mit den dazugehörigen Ausschreibungen");
		
		/*
	     * Datum der Erstellung hinzufÃ¼gen. new Date() erzeugt autom. einen
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
		//Hinzufügen der Row zum Resultobjekt
		result.addRow(headline);

		/*
	     * Nun werden sÃ¤mtliche Bewerbungen ausgelesen und deren Erstellungsdatum, Status und
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
			
			//Hinzufügen der Row zum Result
			result.addRow(applicationsrow);

		}
		//Rückgabe des fertigen Reports 
		return null;
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
	     * ZunÃ¤chst legen wir uns einen leeren Report an.
	     */
		AllParticipationsOfOneUser result = new AllParticipationsOfOneUser();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Ãœberschrift) */
		result.setTitle("Report für Alle Beteiligungen eines Nutzers");
		
		/*
	     * Datum der Erstellung hinzufÃ¼gen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		
		Row headline = new Row();

		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind einzeilig, daher
	     * die Verwendung von Rows.
	     */
		// Dazugehöriges Projekt
		headline.addColumn(new Column("Projekt"));
		// Startdatum des Projekts
		headline.addColumn(new Column("Startdatum"));
		// Enddatum des Projekts
		headline.addColumn(new Column("Enddatum"));
		// Beschreibung des Projekts
		headline.addColumn(new Column("Projektbeschreibung"));
		
		result.addRow(headline);
		/*
	     * Nun werden sÃ¤mtliche Projekte ausgelesen und deren Erstellungsdatum, Beschreibung, Titel und
	     * Text sukzessive in die Tabelle eingetragen.
	     */
		ArrayList<Project> allProjects = pitchMenAdmin.getProjectsByPerson(p.getId());
		for(Project project : allProjects){
			
			Row projectRow = new Row();
			
			projectRow.addColumn(new Column(project.getTitle()));
			projectRow.addColumn(new Column(project.getDateOpened()));
			projectRow.addColumn(new Column(project.getDateClosed()));
			projectRow.addColumn(new Column(project.getDescription()));
			
			//Hinzufügen der Row zum Result
			result.addRow(projectRow);
		}
		//Rückgabe des fertigen Reports 
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
	     * ZunÃ¤chst legen wir uns einen leeren Report an.
	     */
		ProjectInterweavingsWithParticipationsAndApplications result = new ProjectInterweavingsWithParticipationsAndApplications();
		
		/* Dieser Report hat einen Titel (Bezeichnung / Ãœberschrift) */
		result.setTitle("Des Nutzers Projektverflechtungen");
		/*
	     * Datum der Erstellung hinzufÃ¼gen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
		result.setDatecreated(new Date());
		//Dieser Report ist ein Composite Report und setzt sich aus dem Report "showAllApplicationsOfUser" und "showAllParticipationsOfOneUser" zusammen
		result.addSubReport(this.showAllApplicationsOfUser(p));
		result.addSubReport(this.showAllParticipationsOfOneUser(p));
		//Rückgabe des fertigen Reports 
		return result;
	}


	@Override
	public FanInJobPostingsOfUser showFanInJobPostingsOfUser() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		
		FanInJobPostingsOfUser result = new FanInJobPostingsOfUser();
		
		result.setTitle("Die FanIn-Analyse");
		result.setDatecreated(new Date());

		Row headline = new Row();
		headline.addColumn(new Column("ID"));
		headline.addColumn(new Column("Person"));
		headline.addColumn(new Column("Bewerbungsstatus"));
		
		result.addRow(headline);
		
		//ArrayList<Person> allPersons = pitchMenAdmin.getAllPeople();
		
		//for(Person person : allPersons) {
			
		ArrayList<Application> allApplications = pitchMenAdmin.getApplications();
		
			ArrayList<Application> ongoing = new ArrayList<Application>();
			ArrayList<Application> declined = new ArrayList<Application>();
			ArrayList<Application> accepted = new ArrayList<Application>();
		
			for(Application ap : allApplications){
				
								
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
				
				applicationCount.addColumn(new Column(String.valueOf(ongoing.size())));
				applicationCount.addColumn(new Column(String.valueOf(declined.size())));
				applicationCount.addColumn(new Column(String.valueOf(accepted.size())));
				
				result.addRow(applicationCount);
				
			}
			
			
		//}
		
		
		return result;
		
		
		
	}


	@Override
	public FanOutApplicationsOfUser showFanOutApplicationsOfUser() throws IllegalArgumentException {
	
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		
		FanOutApplicationsOfUser  result = new FanOutApplicationsOfUser();
		
		result.setTitle("Die FanOut-Analyse");
		result.setDatecreated(new Date());

		Row headline = new Row();
		headline.addColumn(new Column("ID"));
		headline.addColumn(new Column("Person"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("abgebrochen"));
		headline.addColumn(new Column("besetzt"));
		
		result.addRow(headline);
		
		//ArrayList<Person> allPersons = pitchMenAdmin.getAllPeople();
		
		//for(Person person : allPersons) {
			
		ArrayList<JobPosting> allJobPostings = pitchMenAdmin.getJobPostings();
		
			ArrayList<JobPosting> ongoing = new ArrayList<JobPosting>();
			ArrayList<JobPosting> deleted = new ArrayList<JobPosting>();
			ArrayList<JobPosting> occupied = new ArrayList<JobPosting>();
		
			for(JobPosting j : allJobPostings){
				
								
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
				
				jobPostingCount.addColumn(new Column(String.valueOf(ongoing.size())));
				jobPostingCount.addColumn(new Column(String.valueOf(deleted.size())));
				jobPostingCount.addColumn(new Column(String.valueOf(occupied.size())));
				
				result.addRow(jobPostingCount);
				
			}
			
			
		//}
		
		
		return result;
		
	}

	@Override
	public FanInAndOutReport showFanInAndOutReport() throws IllegalArgumentException {
		
		if(this.getPitchMenAdmin() == null){
			return null;
		}
		
		FanInAndOutReport result = new FanInAndOutReport();
		
		result.setTitle("Report für die FanIn bzw FanOut Analyse");
		result.setDatecreated(new Date());
		
		result.addSubReport(this.showFanInJobPostingsOfUser());
		result.addSubReport(this.showFanOutApplicationsOfUser());
		
		
		return result;
		
		
	}
	/**
	 * Default constructor
	 */

}