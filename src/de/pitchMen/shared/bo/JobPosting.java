package de.pitchMen.shared.bo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Repräsentiert eine Ausschreibung.
 * 
 * @author EleonoraRenz
 */
public class JobPosting extends BusinessObject {

	private ArrayList<Application> applications;

	private Person recruiter = null;
	/**
	 * Der default Wert von Strings ist "null", jedoch wird hier ein leerer
	 * String verwendet, damit man das Werfen einer NullPointException
	 * vermeidet.
	 */
	private String title = "";
	/**
	 * Der default Wert von Strings ist "null", jedoch wird hier ein leerer
	 * String verwendet, damit man das Werfen einer NullPointException
	 * vermeidet.
	 */
	private String text = "";

	private Date deadline = null;

	private PartnerProfile partnerProfile = null;

	private static final long serialVersionUID = 1L;

	// public PartnerProfile PartnerProfile;

	public Set<Application> Application;

	/**
	 * @return applications
	 */
	public ArrayList<Application> getApplications() {
		return this.applications;
	}

	/**
	 * @return recruiter
	 */
	public Person getRecruiter() {
		return this.recruiter;
	}

	/**
	 * @param recruiter
	 */
	public void setRecruiter(Person recruiter) {
		this.recruiter = recruiter;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return deadline
	 */
	public Date getDeadline() {
		return this.deadline;
	}

	/**
	 * @param deadline
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return partnerProfile
	 */
	public PartnerProfile getPartnerProfile() {
		return this.partnerProfile;
	}

	/**
	 * @param partnerProfile
	 */
	public void setPartnerProfile(PartnerProfile partnerProfile) {
		this.partnerProfile = partnerProfile;
	}

}