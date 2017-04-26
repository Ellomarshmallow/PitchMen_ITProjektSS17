package de.pitchMen.shared;

/**
 * Ist das Gegenstück des Interfaces ReportGenerator. Das Google Plugin erstellt und pflegt dies semiautomatisch und ist asynchron.
 * 
 * @author
 */
public interface ReportGeneratorAsync {


    /**
     * 
     */
    public void AllJobPostingsMatchingPartnerProfileOfUser(): void();

    /**
     * 
     */
    public void AllApplicationsOfUser(): AllApplicationsOfUser();

    /**
     * 
     */
    public void ProjectInterweavingsWithParticipationsAndApplications(): void();

    /**
     * 
     */
    public void FanInJobPostingsOfUser():void();

    /**
     * 
     */
    public void FanOutApplicationsOfUser(): void();

    /**
     * 
     */
    public void ApplicationsRelatedToJobPostingsOfUser(): void();

    /**
     * 
     */
    public void AllJobPostings(): void();

}