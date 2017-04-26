package de.pitchMen.shared;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Schnittstelle für die  RPC-Fähige Klasse ReportGeneratorImpl.
 * 
 * @author
 */
public interface ReportGenerator extends RemoteService {


    /**
     * @return
     */
    public AllJobPostings AllJobPostings();

    /**
     * 
     */
    public void AllJobPostingsMatchingPartnerProfileOfUser(): AllJobPostingsMatchingPartnerProfileOfUser();

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