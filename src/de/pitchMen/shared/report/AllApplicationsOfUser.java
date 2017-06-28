package de.pitchMen.shared.report;

/**
 * Report zur Abfrage von allen Bewerbungen auf Ausschreibungen des Benutzers.
 * Subklasse von SimpleReport. Enthält keine eigenen Attribute und Methoden.
 * Dient der Objektorientierten-Programmierung.
 * 
 * @author
 */
public class AllApplicationsOfUser extends SimpleReport {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	// private int applicationId;

	/**
	 * 
	 */
	private int personId;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	// public int getApplicationId() {
	// return applicationId;
	// }
	// public void setApplicationId(int applicationId) {
	// this.applicationId = applicationId;
	// }
}
