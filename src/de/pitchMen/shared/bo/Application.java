package de.pitchMen.shared.bo;

import java.util.Date;

/**
 * Repräsentiert eine Bewerbung.
 * 
 * @author EleonoraRenz
 */

public class Application extends BusinessObject {

	private Date dateCreated = null;

	private OrganisationUnit applicant = null;

	/**
	 * Der default Wert von Strings ist "null", jedoch wird hier ein leerer
	 * String verwendet, damit man das Werfen einer NullPointException
	 * vermeidet.
	 */
	private String text = "";

	private Rating rating = null;

	private static final long serialVersionUID = 1L;

	/**
	 * @return dateCreated
	 */
	public Date getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * @return application
	 */
	public OrganisationUnit getApplicant() {
		return this.applicant;
	}

	/**
	 * @param applicant
	 */
	public void setApplicant(OrganisationUnit applicant) {
		this.applicant = applicant;
	}

	/**
	 * @return text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return rating
	 */
	public Rating getRating() {
		return this.rating;
	}

	/**
	 * @param rating
	 */
	public void setRating(Rating rating) {
		this.rating = rating;
	}

	/**
	 * Bewertet das aufrufende Application-Objekt. Hierfür werden ein
	 * Bewertungswert und eine Stellungnahme übergeben. Erzeugt ein
	 * Rating-Objekt.
	 * 
	 * @param score
	 * @param statement
	 */
	public void rate(float score, String statement) {
		Rating rate = new Rating(score, statement);
		this.setRating(rate);
	}

	/**
	 * Überprüft ob die Bewerbung eine Bewertung hat. Ist eine Bewertung
	 * vorhanden, wird true ausgegeben, wenn nicht, false.
	 * 
	 * @return
	 */
	public boolean isRated() {
		if (this.getRating() != null) {
			return true;
		} else {
			return false;
		}
	}

}
