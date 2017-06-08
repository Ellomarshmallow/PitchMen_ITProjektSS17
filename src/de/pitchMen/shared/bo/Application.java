package de.pitchMen.shared.bo;

import java.util.Date;

/**
 * ReprÃ¤sentiert eine Bewerbung.
 * 
 * @author EleonoraRenz
 */

public class Application extends BusinessObject {

	private Date dateCreated = null;
	
	private String status = "";
	
	private Rating rating = null;

	/**
	 * Realisierung der Beziehung zu einem jobPosting durch einen
	 * Fremdschlüssel.
	 */
	private int jobPostingId = 0;
	/**
	 * Realisierung der Beziehung zu einer partnerprofil durch einen
	 * Fremdschlüssel.
	 */
	private int partnerProfileId = 0;

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
	 * @return jobpostingId
	 */
	public int getJobPostingId() {
		return jobPostingId;
	}

	/**
	 * @param jobpostingid
	 */
	public void setJobPostingId(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}

	/**
	 * @return partnerProfilId
	 */
	public int getPartnerProfileId() {
		return partnerProfileId;
	}

	/**
	 * @param partnerProfilId
	 */
	public void setPartnerProfileId(int partnerProfileId) {
		this.partnerProfileId = partnerProfileId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the rating
	 */
	public Rating getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Rating rating) {
		this.rating = rating;
	}

}
