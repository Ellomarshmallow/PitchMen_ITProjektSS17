package de.pitchMen.shared.bo;

import java.util.Date;

/**
 * Repräsentiert eine Bewerbung.
 * 
 * @author EleonoraRenz
 */

public class Application extends BusinessObject {

	private Date dateCreated = null;

	/**
	 * Realisierung der Beziehung zu einem jobPosting durch einen
	    Fremdschl�ssel.
	 */
	private int jobPostingId = 0;
	/**
	 * Realisierung der Beziehung zu einer partnerprofil durch einen
	    Fremdschl�ssel.
	 */
	private int partnerProfileId= 0;
	
	
	/**
	 * Der default Wert von Strings ist "null", jedoch wird hier ein leerer
	 * String verwendet, damit man das Werfen einer NullPointException
	 * vermeidet.
	 */
	private String text = "";

	private static final long serialVersionUID = 1L;

	
	/**
	 * @return dateCreated
	 */
	public Date getDateCreated() {
		return this.dateCreated;
	}

	
	/**
	 * @return text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param dateCreated
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	
	/**
	 * Bewertet das aufrufende Application-Objekt. Hierfür werden ein
	 * Bewertungswert und eine Stellungnahme übergeben. Erzeugt ein
	 * Rating-Objekt.
	 * 
	 * @param score
	 * @param statement
	 
	public void rate(float score, String statement) {
		Rating rate = new Rating(score, statement);
		this.setRating(rate);
	}*/

	/**
	 * Überprüft ob die Bewerbung eine Bewertung hat. Ist eine Bewertung
	 * vorhanden, wird true ausgegeben, wenn nicht, false.
	 * 
	 * @return
	 
	public boolean isRated() {
		if (this.getRating() != null) {
			return true;
		} else {
			return false;
		}
	}
*/
	
	/**
	 * @return jobpostingid
	 */
	public int getJobpostingid() {
		return jobPostingId;
	}
	/**
	 * @param jobpostingid
	 */
	public void setJobpostingid(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
	/**
	 * @return partnerprofilid
	 */
	public int getPartnerProfileId() {
		return partnerProfileId;
	}
	/**
	 * @param partnerProfilId
	 */
	public void setPartnerprofileId(int partnerProfileId) {
		this.partnerProfileId = partnerProfileId;
	}

}
