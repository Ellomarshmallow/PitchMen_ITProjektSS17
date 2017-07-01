package de.pitchMen.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.server.PitchMenAdminImpl;
import de.pitchMen.server.db.PersonMapper;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.ReportGenerator;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Participation;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Trait;
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
 * Implemetierungsklasse des Interface ReportGenerator. Sie enth�lt die
 * Applikationslogik, stellt die Zusammenh�nge konstistent dar und ist
 * zust�ndig f�r einen geordneten Ablauf.
 * 
 * @author JuliusDigel
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private PersonMapper personMapper = null;

	private static final long serialVersionUID = 1L;
	// private PitchMenAdminImpl administration = null;
	/**
	 * Ein ReportGenerator benötigt Zugriff auf die PitchMenAdministration, da
	 * diese die essentiellen Methoden f�r die Koexistenz von Datenobjekten
	 * (vgl. bo-Package) bietet.
	 */
	private PitchMenAdmin pitchMenAdmin = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {
	}

	/**
	 * Seperate Instanzmethode, welche Client-seitig direkt nach
	 * GWT.create(Klassenname.class) aufgerufen wird, um eine Initialisierung
	 * der Instanz vorzunehmen.
	 * 
	 * @see #ReportGeneratorImpl()
	 */
	@Override
	public void init() throws IllegalArgumentException {
		/**
		 * Ein ReportGeneratorImpl-Objekt instantiiert f�r seinen Eigenbedarf
		 * eine PitchMenAdministration-Instanz.
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
	 * @param r
	 *            der um das Impressum zu erweiternde Report.
	 * 
	 *            /** protected void addImprint(Report r) {
	 * 
	 *            /** Das Impressum soll wesentliche Informationen �ber den
	 *            Report enthalten.
	 */

	/** Bank bank = this.pitchMenAdmin.getMarketplaceByID(id); */
	/**
	 * Das Imressum soll mehrzeilig sein.
	 */
	/**
	 * CompositeParagraph imprint = new CompositeParagraph();
	 * 
	 * imprint.addSubParagraph(new SimpleParagraph(bank.getName()));
	 * imprint.addSubParagraph(new SimpleParagraph(bank.getStreet()));
	 * imprint.addSubParagraph(new SimpleParagraph(bank.getZip() + " " +
	 * bank.getCity()));
	 * 
	 * // Das eigentliche Hinzufügen des Impressums zum Report.
	 * r.setImprint(imprint);
	 * 
	 * }
	 */
	/**
	 * Erstellen von <code>AllJobPostings Report</code>-Objekten.
	 * 
	 * @param c
	 *            das Ausschreibungsobjekt bzgl. dessen der Report erstellt
	 *            werden soll.
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
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 */
		Row headline = new Row(); // Erste Zeile im Report

		// Titel der Ausschreibung
		headline.addColumn(new Column("JobPosting Titel"));

		// Text der Ausschreibung
		headline.addColumn(new Column("JobPosting Text"));

		//F�r AllJobPostings, brauchen wir keine "Dazugeh�rige Projekte
//		// das dazugeh�rige Projekt der Ausschreibung
		//headline.addColumn(new Column("dazugehoeriges Projekt"));

		// Deadline der Ausschreibung
		headline.addColumn(new Column("Deadline"));

		// Deadline der Ausschreibung
		headline.addColumn(new Column("Status der Ausschreibung"));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Ausschreibungen ausgelesen und deren Titel,
		 * Text, Projekt und Deadline sukzessive in die Tabelle eingetragen.
		 */
		ArrayList<JobPosting> jobPostings = pitchMenAdmin.getJobPostings();

		for (JobPosting jobPosting : jobPostings) {
			Row jobPostingZeile = new Row();

			jobPostingZeile.addColumn(new Column(jobPosting.getTitle()));
			jobPostingZeile.addColumn(new Column(jobPosting.getText()));
			//jobPostingZeile.addColumn(new Column(jobPosting.getProjectId()));
			jobPostingZeile.addColumn(new Column(jobPosting.getDeadline().toString()));
			jobPostingZeile.addColumn(new Column(jobPosting.getStatus().toString()));
			result.addRow(jobPostingZeile);

		}
		// R�ckgabe des fertigen Reports f�r alle Ausschreibungen
		return result;
	}

	/**
	 * Erstellen von
	 * <code>AllJobPostingsMatchingPartnerProfileofUser Report</code>-Objekten.
	 * 
	 * @param c
	 *            das Partnerprofilobjekt bzgl. dessen der Report erstellt
	 *            werden soll.
	 * @return der fertige Report
	 */
	@Override
	public AllJobPostingsMatchingPartnerProfileOfUser showAllJobPostingsMatchingPartnerProfileOfUser(
			PartnerProfile partnerProfile) throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
			return null;
		}
		/**
		 * Zunächst legen wir uns einen leeren Report an.
		 **/
		AllJobPostingsMatchingPartnerProfileOfUser result = new AllJobPostingsMatchingPartnerProfileOfUser();

		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitle("Alle Ausschreibungen passend zum Partnerprofil des Benutzers");
		/**
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 **/
		result.setDatecreated(new Date());
		/**
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 **/
		Row headline = new Row();

		headline.addColumn(new Column("JobPosting Titel"));

		headline.addColumn(new Column("JobPosting Beschreibung"));

		headline.addColumn(new Column("Passende Eigenschaft"));

		result.addRow(headline);

		/**
		 * ArrayListe zum abfragen aller jobPostings, dessen Methode in der PitchmenAdmin
		 * implementiert ist. 
		 * 
		 */
		ArrayList<JobPosting> jobPostings = pitchMenAdmin.getJobPostings();

		/**
		 * Implementiert eine ArrayList von Person-Traits, 
		 * welche anhand der Partnerpfofile-ID abgefragt werden.
		 */
		ArrayList<Trait> personTraits = pitchMenAdmin.getTraitsByPartnerProfileId(partnerProfile.getId());
		
		/**
		 * Implementiert eine ArrayListe mit  jobPosting-Traits. 
		 * Die Methode selbst ist im PitchMenADmin implemementiert.
		 * Realisiert durch einen direkten zugriff auf einen Mapper.
		 */
		ArrayList<Trait> jobPostingTraits = pitchMenAdmin.getTraitsFromJobPostings();

		
		/**
		 * Erster Durchlauf der Schleife zum Abfragen der Person Traits, 
		 * durch das komplette Array, nach Ablauf des gesamten Aufrufs. 
		 */
		for (int i = 0; i < personTraits.size(); i++) {
			// Row traitRow = new Row();
			// traitRow.addColumn(new Column(t.getName()));
			// traitRow.addColumn(new Column(t.getValue()));
			// result.addRow(traitRow);
			String personTrait = personTraits.get(i).getName();
			// traitRow.addColumn(new Column(personTrait));
			// result.addRow(traitRow);

			/**
			 * Im zweiten Durchlauf werden alle JobPosting-Traits aus dem Array ausgelesen 
			 * und in eine lokale String Variable jobPostingTrait gespeichert.
			 * 
			 * Des weiteren wird die PartnerProfilId in eine int Variable abgespeichert. Diese wird sp�ter dazu verwendet, 
			 * das dazugeh�rige JobPosting zu finden.
			 */
			for (Trait jpt : jobPostingTraits) {
				// Row traitRowJobPostings = new Row();
				// traitRowJobPostings.addColumn(new Column(jpt.getName()));
				// traitRowJobPostings.addColumn(new Column(jpt.getValue()));
				// result.addRow(traitRowJobPostings);
				String jobPostingTrait = jpt.getName();
				int PPTraitId = jpt.getPartnerProfileId();
				// traitRowJobPostings.addColumn(new Column(jobPostingTrait));
				// traitRowJobPostings.addColumn(new Column(PPTraitId));
				// result.addRow(traitRowJobPostings);

				/**
				 * If Abfrage, welche die Inhaltiche �bereinstimmung 
				 * der Variablen personTrait und jobPostingTrait pr�ft. 
				 * Wenn die Pr�fung positiv ausf�llt, wird Anhand der PartnerProfil ID des Traits das
				 * dazugeh�rige JObPosting aus der Datenbank gelesen. Anschlie�end wird davon lediglich der 
				 * Titel und der Text an das ErgebnisObjekt �bergeben. 
				 * Zus�tzlich wird die Eigenschaft ausgegeben, welche sowohl im JobPosting wie auch in dem Person
				 * PartnerProfil �bereinstimmt.
				 */
				if (personTrait.equals(jobPostingTrait)) {

						Row jobPostingZeile = new Row();
						String jobPostingTitle = pitchMenAdmin.getJobPostingByPPId(PPTraitId).getTitle();
						String jobPostingText = pitchMenAdmin.getJobPostingByPPId(PPTraitId).getText();
						jobPostingZeile.addColumn(new Column(jobPostingTitle));
						jobPostingZeile.addColumn(new Column(jobPostingText));
						jobPostingZeile.addColumn(new Column(personTrait));
						result.addRow(jobPostingZeile);

					
				}

			}
		}

		/**
		 * Ergebnis-Objekt welches dann an die Zust�ndige Klasse im HTML Report-Writer �bergeben wird.
		 */
		return result;

	}
	
	
	
	
	/**
	 * Erstellen von
	 * <code>ApplicationsRelatedToJobPostingsOfUser Report</code>-Objekten.
	 * 
	 * @param c
	 *            das Personenobjekt bzgl. dessen der Report erstellt werden
	 *            soll.
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
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 */
//		Row headline = new Row();
		// Erstellungsdatum der Bewerbung
//		headline.addColumn(new Column("Erstellungsdatum"));
		// Bewerbungstext der Bewerbung
//		headline.addColumn(new Column("Bewerbungstext"));
		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
//		result.addRow(headline);

		/*
		 * Nun werden sämtliche Bewerbungen ausgelesen und deren
		 * Erstellungsdatum und Text sukzessive in die Tabelle eingetragen.
		 */
		ArrayList<JobPosting> jobPostings = pitchMenAdmin.getJobPostingsByPersonId(p.getId());
		
		for (JobPosting a : jobPostings) {
			
						
						
				// Hinzuf�gen der Row zum Result
				result.addSubReport(this.showAllApplicationsToOneJobPostingOfUser(a.getId()));
			}

		
		
		// R�ckgabe des fertigen Reports
		return result;
	}
	/**
	 * Erstellen von
	 * <code>AllApplicationsToOneJobPostingOfUser Report</code>-Objekten.
	 * 
	 * @param jobPostingId
	 *            der ForeignKey anhand dessenn der Report erstellt werden soll.
	 * @return der fertige Report
	 */
	public AllApplicationsToOneJobPostingOfUser showAllApplicationsToOneJobPostingOfUser(int jobPostingId)
			throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
			return null;
		}
		JobPosting jobPosting = pitchMenAdmin.getJobPostingByID(jobPostingId);
		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AllApplicationsToOneJobPostingOfUser result = new AllApplicationsToOneJobPostingOfUser();

		/*
		 * Dieser Report hat einen Titel (Bezeichnung / Überschrift) in welchem
		 * der Titel der Ausschreibung sowie die ID der Ausschreibung
		 * festehalten werden.
		 */
		result.setTitle("Alle Bewerbungen auf die Ausschreibung " + jobPosting.getTitle() + "mit der ID: "
				+ jobPosting.getId());

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setDatecreated(new Date());

		Row headline = new Row();
		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 */
		// Bewerber
		//headline.addColumn(new Column("BewerbungsID"));
		// Erstellungsdatum der Bewerbung
		headline.addColumn(new Column("Erstellungsdatum"));
		// Text der Bewerbung
		headline.addColumn(new Column("Bewerbungstext"));
		// Status der Bewerbung
		headline.addColumn(new Column("Status der Bewerbung"));
		result.addRow(headline);
		// TODO getApplicationsByJobPostingId in PitchmenAdmin implementieren
		ArrayList<Application> applications = pitchMenAdmin.getApplicationsByJobPostingId(jobPostingId);
		/*
		 * Nun werden sämtliche Bewerbungen ausgelesen und deren
		 * Erstellungsdatum, Status und Text sukzessive in die Tabelle
		 * eingetragen.
		 */
		for (Application a : applications) {

			Row applicationRow = new Row();
		
//			Person bewerber =  pitchMenAdmin.getPersonByApplicationId(a.getPartnerProfileId());
				
			
		//	applicationRow.addColumn(new Column(a.getId()));
			applicationRow.addColumn(new Column(a.getDateCreated().toString()));
			applicationRow.addColumn(new Column(a.getText()));
			applicationRow.addColumn(new Column(a.getStatus().toString()));

			// Hinzuf�gen der Row zum Result
			result.addRow(applicationRow);
		}
		// R�ckgabe des fertigen Reports
		return result;
	};

	/**
	 * Erstellen von <code>AllApplicationsOfUser Report</code>-Objekten.
	 * 
	 * @param Personenobjekt
	 *            bzgl. dessen der Report erstellt werden soll.
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
		result.setTitle("Alle Bewerbungen eines Nutzers");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setDatecreated(new Date());

		Row headline = new Row(); // Erste Zeile im Report

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 */
		// Erstellungsdatum der Bewerbung
		headline.addColumn(new Column("Erstellungsdatum"));
		// Text der Bewerbung
		headline.addColumn(new Column("Bewerbungstext"));
		// Ersteller der Ausschreibung
		headline.addColumn(new Column("Titel der Ausschreibung"));
		// Beschreibung der Ausschreibung
		headline.addColumn(new Column("Beschreibung der Ausschreibung"));
		//Status der Bewerbung abfragen
		headline.addColumn(new Column("Status der Bewerbung"));
		// Hinzuf�gen der Row zum Resultobjekt
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Bewerbungen ausgelesen und deren
		 * Erstellungsdatum, Status und Text sukzessive in die Tabelle
		 * eingetragen.
		 */
		ArrayList<Application> applications = pitchMenAdmin.getApplicationsByPerson(p.getId());
		for (Application a : applications) {

			
			// Person jobPoster =
			// pitchMenAdmin.getPersonByID(application.getJobPostingId());
			JobPosting jobPosting = pitchMenAdmin.getJobPostingByID(a.getJobPostingId());

			Row applicationsrow = new Row();

			applicationsrow.addColumn(new Column(a.getDateCreated().toString()));
			applicationsrow.addColumn(new Column(a.getText()));
			// applicationsrow.addColumn(new Column(jobPoster.getFirstName() + "
			// " + jobPoster.getName()));
			// applicationsrow.addColumn(new
			// Column(jobPoster.getDescription()));
			applicationsrow.addColumn(new Column(jobPosting.getTitle()));
			applicationsrow.addColumn(new Column(jobPosting.getText()));
			applicationsrow.addColumn(new Column(jobPosting.getStatus()));

			// Hinzuf�gen der Row zum Result
			result.addRow(applicationsrow);

		}
		 //R�ckgabe des fertigen Reports
		return result;
	}

	/*
	 * Es ist noch nicht Sicher ob ich diese Klasse brauche. Kann ich erst
	 * herausfinden wenn wir testen
	 */
	@Override
	public AllApplicationsOfOneUser showAllApplicationsOfOneUser(int id) throws IllegalArgumentException {

		if (this.getPitchMenAdmin() == null) {
			return null;
		}
		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AllApplicationsOfOneUser result = new AllApplicationsOfOneUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Report für Alle Bewerbungen des Nutzers");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		//result.setDatecreated(new Date());

		Row headline = new Row();

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 */
		// Dazugeh�riges Projekt
		headline.addColumn(new Column("Erstellungsdatum"));
		// Startdatum des Projekts
		headline.addColumn(new Column("Bewerbungstext"));
		// Enddatum des Projekts
		headline.addColumn(new Column("Status"));
		// Beschreibung des Projekts
		headline.addColumn(new Column("Titel der dazugeh�rigen Ausschreibung"));

		result.addRow(headline);
		/*
		 * Nun werden sämtliche Projekte ausgelesen und deren Erstellungsdatum,
		 * Beschreibung, Titel und Text sukzessive in die Tabelle eingetragen.
		 */
		ArrayList<Application> allApplications = pitchMenAdmin.getApplicationsByPerson(id);
		for (Application p : allApplications) {
			
			Row projectRow = new Row();
			JobPosting receivingJp = pitchMenAdmin.getJobPostingByID(p.getJobPostingId());
		
			projectRow.addColumn(new Column(p.getDateCreated().toString()));
			projectRow.addColumn(new Column(p.getText()));
			projectRow.addColumn(new Column(p.getStatus()));
			projectRow.addColumn(new Column(receivingJp.getTitle()));

			// Hinzuf�gen der Row zum Result
			result.addRow(projectRow);
		}
		// R�ckgabe des fertigen Reports
		return result;
	
	}
	
	
	
	

	/**
	 * Erstellen von <code>AllParticipationsOfOneUser</code>-Objekten.
	 * 
	 * @param Personenobjekt
	 *            bzgl. dessen der Report erstellt werden soll.
	 * @return der fertige Report
	 */
	@Override
	public AllParticipationsOfOneUser showAllParticipationsOfOneUser(int id) throws IllegalArgumentException {

		if (this.getPitchMenAdmin() == null) {
			return null;
		}
		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AllParticipationsOfOneUser result = new AllParticipationsOfOneUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Report für Alle Beteiligungen eines Nutzers");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setDatecreated(new Date());

		Row headline = new Row();

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
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
		 * Nun werden sämtliche Projekte ausgelesen und deren Erstellungsdatum,
		 * Beschreibung, Titel und Text sukzessive in die Tabelle eingetragen.
		 */
		ArrayList<Participation> allParticipations = pitchMenAdmin.getParticipationsByPersonId(id);
		for (Participation p : allParticipations) {

			Row projectRow = new Row();
			
			Project project = pitchMenAdmin.getProjectByID(p.getProjectId());
			

			projectRow.addColumn(new Column(project.getTitle()));
			projectRow.addColumn(new Column(p.getDateOpened().toString()));
			projectRow.addColumn(new Column(p.getDateClosed().toString()));
			projectRow.addColumn(new Column(project.getDescription()));

			// Hinzuf�gen der Row zum Result
			result.addRow(projectRow);
		}
		// R�ckgabe des fertigen Reports
		return result;
	}

	/**
	 * Erstellen von
	 * <code>ProjectInterweavingsWithParticipationsAndApplications</code>-Objekten.
	 * 
	 * @param Personenobjekt
	 *            bzgl. dessen der Report erstellt werden soll.
	 * @return der fertige Report
	 */
	@Override
	public ProjectInterweavingsWithParticipationsAndApplications showProjectInterweavingsWithParticipationsAndApplications(
			int id) throws IllegalArgumentException {

		if (this.getPitchMenAdmin() == null) {
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
		// Dieser Report ist ein Composite Report und setzt sich aus dem Report
		// "showAllApplicationsOfUser" und "showAllParticipationsOfOneUser"
		// zusammen
		result.addSubReport(this.showAllApplicationsOfOneUser(id));
		result.addSubReport(this.showAllParticipationsOfOneUser(id));
		// R�ckgabe des fertigen Reports
		return result;
	}
	
	/**
	 * Erstellen von
	 * <code>getApplicatorsOnOwnJobPostings</code>-Objekten.
	 * 
	 * @param Personenobjekt
	 *            bzgl. dessen der Report erstellt werden soll.
	 * @return der fertige Report
	 */
	public ArrayList<Person> getApplicatorsOnOwnJobPostings(Person p) throws IllegalArgumentException {
		
		ArrayList<Person> applicants = new ArrayList<Person>();
	
		ArrayList<JobPosting> myjobpostings = pitchMenAdmin.getJobPostingsByPersonId(p.getId());
		
			for (JobPosting jobposting : myjobpostings) {
				
				ArrayList<Application> applications = pitchMenAdmin.getApplicationsByJobPostingId(jobposting.getId());
				
				for (Application application : applications) {
					
								
					if(applicants.contains(pitchMenAdmin.getPersonByApplicationId(application.getId()))){
					}else{
						applicants.add(pitchMenAdmin.getPersonByApplicationId(application.getId()));
					}
				}
			}
		return applicants;
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

		if (this.getPitchMenAdmin() == null) {
			return null;
		}
		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		FanInJobPostingsOfUser result = new FanInJobPostingsOfUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Anzahl der Bewerbungen");
		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setDatecreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * einzeilig, daher die Verwendung von Rows.
		 */
		Row headline = new Row();
		// ID der Bewerbung
		headline.addColumn(new Column("ID"));
		// Ersteller der Bewerbung
		headline.addColumn(new Column("Person"));
		// Status der Bewerbung
		headline.addColumn(new Column("laufend"));

		headline.addColumn(new Column("abgelehnt"));
		
		headline.addColumn(new Column("angenommen"));
		
		result.addRow(headline);

		// ArrayList<Person> allPersons = pitchMenAdmin.getAllPeople();

		// for(Person person : allPersons) {

		ArrayList<Person> allePersonen = pitchMenAdmin.getAllPeople();
		
		for (Person person : allePersonen){
			
					/*
					 * Hier werden die Bewerbungen in 3 neue ArrayLists vom Typ Application
					 * aufgeteilt Und zwar in "ongoing", "declined" und "accepted" welche
					 * die Stati der Bewerbungen wiederspiegeln
					 */
					ArrayList<Application> ongoing = new ArrayList<Application>();
					ArrayList<Application> declined = new ArrayList<Application>();
					ArrayList<Application> accepted = new ArrayList<Application>();
			
					ArrayList<Application> allApplications = pitchMenAdmin.getApplicationsByPerson(person.getId());
					
					
					for (Application ap : allApplications) {
			
						/*
						 * Hier werden die Bewerbungen den jeweiligen Stati entsprechend
						 * zugeteilt
						 */
			
						if (ap.getStatus().toString().equals("laufend")) {
							ongoing.add(ap);
						} else if (ap.getStatus().toString().equals("abgelehnt")) {
							declined.add(ap);
						} else if (ap.getStatus().toString().equals("angenommen")) {
							accepted.add(ap);
						}
						;
			
						
					}
					Row applicationCount = new Row();
					// hinzuf�gen der Spalte f�r die jeweiligen Stati
					applicationCount.addColumn(new Column(String.valueOf(person.getId())));
					applicationCount.addColumn(new Column(person.getFirstName()+" "+person.getName()));
					applicationCount.addColumn(new Column(String.valueOf(ongoing.size())));
					applicationCount.addColumn(new Column(String.valueOf(declined.size())));
					applicationCount.addColumn(new Column(String.valueOf(accepted.size())));
					// Hinzuf�gen der Row zum Result
					result.addRow(applicationCount);
		

		 }

		// R�ckgabe des fertigen Reports
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

		if (this.getPitchMenAdmin() == null) {
			return null;
		}

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		FanOutApplicationsOfUser result = new FanOutApplicationsOfUser();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Anzahl der Ausschreibungen");

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

		ArrayList<Person> allePersonen = pitchMenAdmin.getAllPeople();
		
		for (Person person : allePersonen){
			
		
		ArrayList<JobPosting> allJobPostings = pitchMenAdmin.getJobPostingsByPersonId(person.getId());

		/*
		 * Hier werden die Ausschreibungen in 3 neue ArrayLists vom Typ
		 * Application aufgeteilt Und zwar in "ongoing", "deleted" und
		 * "occupied" welche die Stati der Ausschreibungen wiederspiegeln
		 */
		ArrayList<JobPosting> ongoing = new ArrayList<JobPosting>();
		ArrayList<JobPosting> deleted = new ArrayList<JobPosting>();
		ArrayList<JobPosting> occupied = new ArrayList<JobPosting>();

		for (JobPosting j : allJobPostings) {

			/*
			 * Hier werden die Bewerbungen den jeweiligen Stati entsprechend
			 * zugeteilt den neuen ArrayLists zugeteilt.
			 */
			if (j.getStatus().toString().equals("laufend")) {
				ongoing.add(j);
			} else if (j.getStatus().toString().equals("abgelehnt")) {
				deleted.add(j);
			} else if (j.getStatus().toString().equals("angenommen")) {
				occupied.add(j);
			}
		}
		Row jobPostingCount = new Row();
		// hinzuf�gen der Spalte f�r die jeweiligen Stati zur Row
		jobPostingCount.addColumn(new Column(String.valueOf(person.getId())));
		jobPostingCount.addColumn(new Column(person.getFirstName()+" "+person.getName()));
		jobPostingCount.addColumn(new Column(String.valueOf(ongoing.size())));
		jobPostingCount.addColumn(new Column(String.valueOf(deleted.size())));
		jobPostingCount.addColumn(new Column(String.valueOf(occupied.size())));
		// Hinzuf�gen der Row zum Result
		result.addRow(jobPostingCount);
		}
		// R�ckgabe des fertigen Reports
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

		if (this.getPitchMenAdmin() == null) {
			return null;
		}
		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		FanInAndOutReport result = new FanInAndOutReport();

		/* Dieser Report hat einen Titel (Bezeichnung / Überschrift) */
		result.setTitle("Report fuer die FanIn bzw FanOut Analyse");
		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setDatecreated(new Date());

		/*
		 * Dieser Report ist ein Composite Report und setzt sich aus dem Report
		 * "showFanInJobPostingsOfUser" und "showFanOutApplicationsOfUser"
		 * zusammen
		 */
		result.addSubReport(this.showFanInJobPostingsOfUser());
		result.addSubReport(this.showFanOutApplicationsOfUser());

		// R�ckgabe des fertigen Reports
		return result;
	}
	// --------------------------- LOGIN

	public Person login(String requestUri) {

		ClientsideSettings.getLogger().info("login()-Methode wurde aufgerufen.");

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Person logInf = new Person();

		/*
		 * Ist der User angemeldet, hat der Methodenaufruf von
		 * userService.getCurrentUser() ein vollwertiges User-Objekt
		 * zurückgegeben. Ist der Nutzer nicht angemeldet, gibt die Methode
		 * null zurück. Wir springen dann in den else-Block.
		 */
		if (user != null) {
			// Der Nutzer hat die erste Hürde genommen und sich angemeldet

			ClientsideSettings.getLogger().info("User-Objekt ist nicht null.");

			/*
			 * Wir fragen zunächst bei der Datenbank an, ob der Nutzer, der
			 * sich gerade angemeldet hat, bereits vorhanden ist.
			 */
			Person existingPerson = personMapper.findByEmail(user.getEmail());

			/*
			 * Hat der Mapper ein passendes Person-Objekt gefunden, gibt er
			 * dieses zurück. Ansonsten returnt er null. Darauf basierend
			 * können wir folgende Fallunterscheidung vornehemen:
			 */
			if (existingPerson != null) {
				// Der Nutzer ist dem System bereits bekannt.
				ClientsideSettings.getLogger().info("User mit der E-Mai-Adresse [" + user.getEmail() + "]  existiert.");

				/*
				 * Hier werden nun noch alle Attribute gesetzt, die in der
				 * Datenbank nicht gespeichert sind.
				 */

				// der Nutzer ist eingeloggt
				existingPerson.setLoggedIn(true);
				// über diese URL kann er sich ausloggen
				existingPerson.setLogoutUrl(userService.createLogoutURL(requestUri));
				// außerdem existiert er bereits. Dieser Wert sagt der GUI:
				// lade die eigentliche Applikation
				existingPerson.setIsExisting(true);

				return existingPerson;

			}

			// Hier landet das Programm, wenn der Nutzer angemeldet, aber noch
			// unbekannt ist
			logInf.setLoggedIn(true);
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
			logInf.setEmailAdress(user.getEmail());
			// Der GUI wird mit diesem Wert mitgeteilt, dass der Nutzer erst
			// seine Daten eingeben muss
			logInf.setIsExisting(false);
		} else {
			// Hier landen wir wenn der Nutzer nicht angemeldet ist

			ClientsideSettings.getLogger().info("User-Objekt ist null.");

			/*
			 * Mit dem setzen dieses Wertes auf false teilen wir der GUI mit,
			 * dass der Nutzer sich erst anmelden muss. Diese erzeugt daraufhin
			 * ein Overlay mit einem Link, der den Nutzer zum Anmeldeformular
			 * weiterleitet.
			 */
			logInf.setLoggedIn(false);

			/*
			 * Dem per Callback an die GUI weitergereichten Person-Obbjekt
			 * logInf wird eine LoginURL mitgegeben. Diese wird zum Ziel des
			 * Links im Overlay. Darüber meldet sich der Nutzer an und kehrt
			 * dann auf die Seite zurück. Wieder wird diese login()- Methode
			 * aufgerufen, nun ist der User aber nicht mehr null. Jetzt geht es
			 * im if-Block "weiter".
			 */
			logInf.setLoginUrl(userService.createLoginURL(requestUri));
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
		}
		return logInf;
	}

}