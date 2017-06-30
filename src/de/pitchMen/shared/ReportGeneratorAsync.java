package de.pitchMen.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllApplicationsOfOneUser;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllApplicationsToOneJobPostingOfUser;
import de.pitchMen.shared.report.AllJobPostings;
//import de.pitchMen.shared.report.AllApplicationsToOneJobPostingOfUser;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.AllParticipationsOfOneUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.FanInAndOutReport;
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
    void showAllApplicationsToOneJobPostingOfUser(int jobPostingId, AsyncCallback<AllApplicationsToOneJobPostingOfUser> callback);
    /** 
	 * 
	 */
    void showAllJobPostingsMatchingPartnerProfileOfUser(PartnerProfile partnerProfile, AsyncCallback<AllJobPostingsMatchingPartnerProfileOfUser> callback);
	/**
	 * 
	 */
    void showFanInAndOutReport(AsyncCallback<FanInAndOutReport> callback);
    /**
	 * 
	 */
	void showAllApplicationsOfUser(Person p, AsyncCallback<AllApplicationsOfUser> callback);
	/**
	 * 
	 */
	void showAllApplicationsOfOneUser(int id, AsyncCallback<AllApplicationsOfOneUser> callback);
	/**
	 * 
	 */
	void showAllParticipationsOfOneUser(int id, AsyncCallback<AllParticipationsOfOneUser> callback);
	/**
	 * 
	 */
	void showProjectInterweavingsWithParticipationsAndApplications(int id, AsyncCallback<ProjectInterweavingsWithParticipationsAndApplications> callback);
	/**
	 * 
	 */
	void getApplicatorsOnOwnJobPostings(Person p, AsyncCallback<ArrayList<Person>> callback);
	/**
	 * 
	 */
	void showFanInJobPostingsOfUser(AsyncCallback<FanInJobPostingsOfUser> callback);
	/**
	 * 
	 */
	void showFanOutApplicationsOfUser(AsyncCallback<FanOutApplicationsOfUser> callback);
	/**
	 * 
	 */
	void showApplicationsRelatedToJobPostingsOfUser(Person p, AsyncCallback<ApplicationsRelatedToJobPostingsOfUser> callback);
	
	void login(String requestUri, AsyncCallback<Person> callback);

}