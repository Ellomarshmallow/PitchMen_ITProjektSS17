package de.pitchMen.shared;

import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllApplicationsOfOneUser;
import de.pitchMen.shared.report.AllApplicationsOfUser;
//import de.pitchMen.shared.report.AllApplicationsToOneJobPostingOfUser;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.AllParticipationsOfOneUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.FanInAndOutReport;
import de.pitchMen.shared.report.FanInJobPostingsOfUser;
import de.pitchMen.shared.report.FanOutApplicationsOfUser;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Schnittstelle für die  RPC-Fähige Klasse ReportGeneratorImpl.
 * 
 * @author
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {


	/**
	 * Initialisierung des objektes mit anschließendem No Argument Konstruktor
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract AllJobPostings showAllJobPostings(JobPosting jobPosting) throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract AllJobPostingsMatchingPartnerProfileOfUser showAllJobPostingsMatchingPartnerProfileOfUser(PartnerProfile partnerProfile) throws IllegalArgumentException;
	/**
	 * 
	 */
//	public abstract AllApplicationsToOneJobPostingOfUser showAllApplicationsToOneJobPostingOfUser(int jobPostingId) throws IllegalArgumentException;
	/**
	* 
	*/
	public abstract AllApplicationsOfUser showAllApplicationsOfUser(Person p) throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract AllApplicationsOfOneUser showAllApplicationsOfOneUser(int id) throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract AllParticipationsOfOneUser showAllParticipationsOfOneUser (Person p)  throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract ProjectInterweavingsWithParticipationsAndApplications showProjectInterweavingsWithParticipationsAndApplications(Person p) throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract FanInJobPostingsOfUser showFanInJobPostingsOfUser() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract FanOutApplicationsOfUser showFanOutApplicationsOfUser() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract FanInAndOutReport showFanInAndOutReport() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract ApplicationsRelatedToJobPostingsOfUser showApplicationsRelatedToJobPostingsOfUser(Person p) throws IllegalArgumentException;

}