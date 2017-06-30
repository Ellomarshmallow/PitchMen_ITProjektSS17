package de.pitchMen.shared.report;

/**
 * Report zur Abfrage von Projektverflechtungen mit Beteiligungen und
 * Bewerbungen. Subklasse von CompositeReport. Enthält keine eigenen Attribute
 * und Methoden. Dient der Objektorientierten-Programmierung.
 * 
 * @author
 */
public class ProjectInterweavingsWithParticipationsAndApplications extends CompositeReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int applicationId;
	private int personId;
	private int participationId;


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
	public int getParticipationId() {
		return participationId;
	}
	public void setParticipationId(int participationId) {
		this.participationId = participationId;
	}

}
