package de.pitchMen.server.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.ReportGenerator;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.FanInJobPostingsOfUser;
import de.pitchMen.shared.report.FanOutApplicationsOfUser;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;
import de.pitchMen.shared.report.Row;
import de.pitchMen.shared.report.SimpleReport;

/**
 * Implemetierungsklasse des Interface ReportGenerator.  Sie enth‰lt die Applikationslogik, stellt die Zusammenh‰nge konstistent dar und ist zust‰ndig f¸r einen geordneten Ablauf.
 * 
 * @author JuliusDigel
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private static final long serialVersionUID = 1L;
	//private PitchMenAdminImpl administration = null;
	private ReportGeneratorImpl reportadmin = null;
	public ReportGeneratorImpl() throws IllegalArgumentException{}


	@Override
	public void init() throws IllegalArgumentException{
		/**	
		 Ein ReportGeneratorImpl-Objekt instantiiert f¸r seinen Eigenbedarf eine
		 * PitchMenAdministration-Instanz.
		 */
		ReportGeneratorImpl a = new ReportGeneratorImpl();
		a.init();
		reportadmin = a;	
	}

	/**
	 * Auslesen der zugeh√∂rigen BankAdministration (interner Gebrauch).
	 * 
	 * @return das BankVerwaltungsobjekt
	 */
	/*protected PitchMenAdmin getPitchMenAdmin() {
		return this.administration;
	}*/

	 /**
	   * Hinzuf√ºgen des Report-Impressums. Diese Methode ist aus den
	   * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	   * diese T√§tigkeiten redundant auszuf√ºhren h√§tte. Stattdessen rufen die
	   * <code>create...</code>-Methoden diese Methode auf.
	   * 
	   * @param r der um das Impressum zu erweiternde Report.
	   */
	// AUSKOMMENTIERT WEIL F‹R TEST NOCH NICHT NOTWENDIG
	/* protected void addImprint(Report r) {
		    /*
		     * Das Impressum soll wesentliche Informationen √ºber die Bank enthalten.
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

		    // Das eigentliche Hinzuf√ºgen des Impressums zum Report.
		    r.setImprint(imprint);

		  }*/


	@Override
	public AllJobPostings showAllJobPostings() throws IllegalArgumentException {
		if (reportadmin == null) {
		return null;
		}
		AllJobPostings result = new AllJobPostings();
		
		result.setTitle("Alle Job Postings");
		
		result.setDatecreated(new Date());
		
		ArrayList<AllJobPostings> allJobPostings = reportadmin.showAllJobPostings();
		
		for (AllJobPostings c : allJobPostings) {
		SimpleReport AllPostings = new SimpleReport();
		Row Posting = new Row();
		Posting.addColumn();
		
			
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