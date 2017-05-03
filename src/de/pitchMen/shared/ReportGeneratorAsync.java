package de.pitchMen.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.FanInJobPostingsOfUser;
import de.pitchMen.shared.report.FanOutApplicationsOfUser;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;

/**
 * Ist das Gegenstück des Interfaces ReportGenerator. Das Google Plugin erstellt und pflegt dies semiautomatisch und ist asynchron.
 * 
 * @author
 */
public interface ReportGeneratorAsync {

	
	void init(AsyncCallback<Void> callback);

    /**
     * 
     */
	void AllJobPostings(AsyncCallback<AllJobPostings> callback);
	/**
	 * 
	 */
	void AllJobPostingsMatchingPartnerProfileOfUser(Person p, AsyncCallback<AllJobPostingsMatchingPartnerProfileOfUser> callback);
	/**
	 * 
	 */
	void AllApplicationsOfUser(Person p, AsyncCallback<AllApplicationsOfUser> callback);
	/**
	 * 
	 */
	void ProjectInterweavingsWithParticipationsAndApplications(Person p, AsyncCallback<ProjectInterweavingsWithParticipationsAndApplications> callback);
	/**
	 * 
	 */
	void FanInJobPostingsOfUser(Person p, AsyncCallback<FanInJobPostingsOfUser> callback);
	/**
	 * 
	 */
	void FanOutApplicationsOfUser(Person p, AsyncCallback<FanOutApplicationsOfUser> callback);
	/**
	 * 
	 */
	void ApplicationsRelatedToJobPostingsOfUser(JobPosting j, AsyncCallback<ApplicationsRelatedToJobPostingsOfUser> callback);
}