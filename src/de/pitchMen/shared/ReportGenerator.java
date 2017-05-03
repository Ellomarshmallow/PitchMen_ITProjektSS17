package de.pitchMen.shared;

import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
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
	public abstract AllJobPostings AllJobPostings() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract AllJobPostingsMatchingPartnerProfileOfUser AllJobPostingsMatchingPartnerProfileOfUser() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract AllApplicationsOfUser AllApplicationsOfUser() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract ProjectInterweavingsWithParticipationsAndApplications ProjectInterweavingsWithParticipationsAndApplications() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract FanInJobPostingsOfUser FanInJobPostingsOfUser() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract FanOutApplicationsOfUser FanOutApplicationsOfUser() throws IllegalArgumentException;
	/**
	 * 
	 */
	public abstract ApplicationsRelatedToJobPostingsOfUser ApplicationsRelatedToJobPostingsOfUser() throws IllegalArgumentException;

}