package de.pitchMen.shared.report;

public class AllParticipationsOfOneUser {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int applicationId;
    private int personId;
    
    
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

}
