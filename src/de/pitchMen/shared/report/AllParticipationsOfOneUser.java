package de.pitchMen.shared.report;

public class AllParticipationsOfOneUser extends SimpleReport{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int participationId;
    private int personId;
    
    
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getApplicationId() {
		return participationId;
	}
	public void setApplicationId(int applicationId) {
		this.participationId = applicationId;
	}

}
