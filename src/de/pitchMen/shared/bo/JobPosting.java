package de.pitchMen.shared.bo;

import java.sql.Date;

/**
 * ReprÃ¤sentiert eine Ausschreibung.
 * 
 * @author EleonoraRenz
 */
public class JobPosting extends BusinessObject {

	/**
	 * Realisierung der Beziehung zu einem Projekt durch einen Fremdschlüssel.
	 */
	private int projectId = 0;

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

	private String status = "";

	private static final long serialVersionUID = 1L;

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
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param deadline
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return projectid
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectid
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

}