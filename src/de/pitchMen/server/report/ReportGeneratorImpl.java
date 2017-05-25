
package de.pitchMen.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.pitchMen.server.*;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.ReportGenerator;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllApplicationsOfUser;
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
	 * Auslesen der zugehÃ¶rigen BankAdministration (interner Gebrauch).
	 * 
	 * @return das BankVerwaltungsobjekt
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

/**
 * Methode zum Erstellen des Reports für alle JobPostings
 */
 
	@Override
	public AllJobPostings showAllJobPostings(JobPosting j) throws IllegalArgumentException {
		if (pitchMenAdmin == null) {
		return null;
		}
		//zu befüllenden Report erstellen
		AllJobPostings result = new AllJobPostings();
		
		result.setTitle("Alle Job Postings");
		
		result.setDatecreated(new Date());
		
		SimpleParagraph recruiter = new SimpleParagraph(j.getRecruiter().getFirstName());
		
		SimpleParagraph title = new SimpleParagraph(j.getTitle());
		
		SimpleParagraph deadline = new SimpleParagraph(j.getDeadline().toString());
		
		
		
		ArrayList<JobPosting> allJobPostings = pitchMenAdmin.getJobPosting();
		
		for (AllJobPostings c : allJobPostings) {
		//SimpleReport AllPostings = new SimpleReport();
		Row posting = new Row();
		posting.addCo
		lumn(new Column().setValue(c.getTitle()));
				
		
			
		}
	}


	@Override
	public AllJobPostingsMatchingPartnerProfileOfUser showAllJobPostingsMatchingPartnerProfileOfUser(Person p)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AllApplicationsOfUser showAllApplicationsOfUser(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
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


	@Override
	public ApplicationsRelatedToJobPostingsOfUser showApplicationsRelatedToJobPostingsOfUser(JobPosting j)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Default constructor
	 */

}