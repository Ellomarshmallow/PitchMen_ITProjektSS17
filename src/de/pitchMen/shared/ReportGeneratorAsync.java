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
    void showAllJobPostings(AsyncCallback<AllJobPostings> callback);
	/**
	 * 
	 */
	void showAllJobPostingsMatchingPartnerProfileOfUser(Person p, AsyncCallback<AllJobPostingsMatchingPartnerProfileOfUser> callback);
	/**
	 * 
	 */
	void showAllApplicationsOfUser(Person p, AsyncCallback<AllApplicationsOfUser> callback);
	/**
	 * 
	 */
	void showProjectInterweavingsWithParticipationsAndApplications(Person p, AsyncCallback<ProjectInterweavingsWithParticipationsAndApplications> callback);
	/**
	 * 
	 */
	void showFanInJobPostingsOfUser(Person p, AsyncCallback<FanInJobPostingsOfUser> callback);
	/**
	 * 
	 */
	void showFanOutApplicationsOfUser(Person p, AsyncCallback<FanOutApplicationsOfUser> callback);
	/**
	 * 
	 */
	void showApplicationsRelatedToJobPostingsOfUser(JobPosting j, AsyncCallback<ApplicationsRelatedToJobPostingsOfUser> callback);
}