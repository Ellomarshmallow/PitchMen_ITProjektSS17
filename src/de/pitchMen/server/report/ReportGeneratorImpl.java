package de.pitchMen.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.pitchMen.server.*;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.ReportGenerator;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllApplicationsToOneJobPostingOfUser;
import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.Column;
import de.pitchMen.shared.report.FanInJobPostingsOfUser;
import de.pitchMen.shared.report.FanOutApplicationsOfUser;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;
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
	private PitchMenAdmin pitchMenAdmin = null;
	public ReportGeneratorImpl() throws IllegalArgumentException{}


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
	   */
	// AUSKOMMENTIERT WEIL FÜR TEST NOCH NICHT NOTWENDIG
	/* protected void addImprint(Report r) {
		    /*
		     * Das Impressum soll wesentliche Informationen Ã¼ber die Bank enthalten.
		     */
		   /* Bank bank = this.administration.getBank();

		    /*
		     * Das Imressum soll mehrzeilig sein.
		     */
		   /* CompositeParagraph imprint = new CompositeParagraph();

		    imprint.addSubParagraph(new SimpleParagraph(bank.getName()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getStreet()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getZip() + " "
		        + bank.getCity()));

		    // Das eigentliche HinzufÃ¼gen des Impressums zum Report.
		    r.setImprint(imprint);

		  }*/


	@Override
	public AllJobPostings showAllJobPostings(JobPosting jopPosting) throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
		return null;
		}
		AllJobPostings result = new AllJobPostings();
		
			
		result.setTitle("Alle Job Postings");
		
		result.setDatecreated(new Date());
		
				
		Row headline = new Row(); //Erste Zeile im Report
		
		headline.addColumn(new Column("JobPosting Titel"));
		headline.addColumn(new Column("JobPosting Text"));
		headline.addColumn(new Column("dazugehöriges Projekt"));
		headline.addColumn(new Column("Deadline"));
		
		result.addRow(headline);
		
		ArrayList<JobPosting> jobPostings = pitchMenAdmin.getJobPostings();
		
		for (JobPosting jobPosting : jobPostings) {
			Row jobPostingZeile = new Row();
			
			jobPostingZeile.addColumn(new Column(jobPosting.getTitle()));
			jobPostingZeile.addColumn(new Column(jobPosting.getText()));
			jobPostingZeile.addColumn(new Column(jobPosting.getProjectId()));
			jobPostingZeile.addColumn(new Column(jobPosting.getDeadline().toString()));
			result.addRow(jobPostingZeile);
		
		}
		return result;
	}


	@Override
	public AllJobPostingsMatchingPartnerProfileOfUser showAllJobPostingsMatchingPartnerProfileOfUser(PartnerProfile partnerProfile)
			throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
			return null;
			}
		return null;
		// TODO Auto-generated method stub
	}
	
		public AllApplicationsToOneJobPostingOfUser showAllApplicationsToOneJobPostingOfUser(int jobPostingId) throws IllegalArgumentException{
			if (pitchMenAdmin == null) {
				return null;
				}
			
			
			
			
		};
	
	
	@Override
		public ApplicationsRelatedToJobPostingsOfUser showApplicationsRelatedToJobPostingsOfUser(Person p)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			if (pitchMenAdmin == null) {
				return null;
				}
			ApplicationsRelatedToJobPostingsOfUser result = new ApplicationsRelatedToJobPostingsOfUser();
			
			result.setTitle("Alle Bewerbungen auf eine Ausschreibung des Users");
			result.setDatecreated(new Date());
			Row headline = new Row();
			headline.addColumn(new Column("Erstellungsdatum"));
			headline.addColumn(new Column("Bewerbungstext"));
			result.addRow(headline);
			
			ArrayList<Application> applications = pitchMenAdmin.getApplications();	
			for (Application a : applications) {
				
					if(a.getPartnerProfileId() == p.getId()) {};
				Row applicationsrow = new Row();
				
				applicationsrow.addColumn(new Column(application.getDateCreated().toString()));
				applicationsrow.addColumn(new Column(application.getText()));
				
				result.addRow(applicationsrow);
			}
			
			
			return result;
		}
		
			
			
			
		
	}


	@Override
	public AllApplicationsOfUser showAllApplicationsOfUser(Person p) throws IllegalArgumentException {
		//TODO Fehlersuche
		if (pitchMenAdmin == null) {
			return null;
			}
		AllApplicationsOfUser result = AllApplicationsOfUser();
		
		result.setTitle("Alle Bewerbungen eines Nutzers");
		result.setDatecreated(new Date());
		
		Row headline = new Row(); //Erste Zeile im Report
		
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Bewerbungstext"));
	
		
		result.addRow(headline);
		
		ArrayList<Application> applications = pitchMenAdmin.getApplicationsByPerson(p);	
		for (Application application : applications) {
			Row applicationsrow = new Row();
		
			
			applicationsrow.addColumn(new Column(application.getDateCreated().toString()));
			applicationsrow.addColumn(new Column(application.getText()));
			
			result.addRow(applicationsrow);
		
		}
		
		return null;
	}


	@Override
	public ProjectInterweavingsWithParticipationsAndApplications showProjectInterweavingsWithParticipationsAndApplications(
			Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FanInJobPostingsOfUser showFanInJobPostingsOfUser(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FanOutApplicationsOfUser showFanOutApplicationsOfUser(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	
	/**
	 * Default constructor
	 */

}