package de.pitchMen.shared;

import de.pitchMen.shared.report.AllJobPostings;
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
	 * @return
	 */

	public abstract AllJobPostings AllJobPostings() throws IllegalArgumentException;

	/**
	 * 
	 */
	public abstract AllJobPostingsMatchingPartnerProfileOfUser AllJobPostingsMatchingPartnerProfileOfUser() throws IllegalArgumentException;

	/**
	 * 
	 */
	public void AllApplicationsOfUser(): AllApplicationsOfUser();

	/**
	 * 
	 */
	public void ProjectInterweavingsWithParticipationsAndApplications(): ProjectInterweavingsWithParticipationsAndApplications();

	/**
	 * 
	 */
	public void FanInJobPostingsOfUser(): FanInJobPostingsOfUser();

	/**
	 * 
	 */
	public void FanOutApplicationsOfUser(): FanOutApplicationsOfUser();

	/**
	 * 
	 */
	public void ApplicationsRelatedToJobPostingsOfUser(): ApplicationsRelatedToJobPostingsOfUser();

}