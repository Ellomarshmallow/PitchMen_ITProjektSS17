package de.pitchMen.shared.report;

public class AllApplicationsToOneJobPostingOfUser extends SimpleReport {
	private static final long serialVersionUID = 1L;
	private int personId;

	private int partnerProfileId;
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getPartnerProfileId() {
		return partnerProfileId;
	}

	public void setPartnerProfileId(int partnerProfileId) {
		this.partnerProfileId = partnerProfileId;
	}

}
