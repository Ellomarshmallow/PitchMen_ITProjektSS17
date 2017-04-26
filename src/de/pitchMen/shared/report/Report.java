package de.pitchMen.shared.report;

import java.io.Serializable;

/**
 * Implemetierungsklasse des Interface Serializable. Ist die Superklasse von
 * SimpleReport und CompositeReport.
 * 
 * @author
 */
public abstract class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private Date dateCreated;

	/**
	 * 
	 */
	private Paragraph headerDatea;

	/**
	 * @return
	 */
	public String getTitle() {
		// TODO implement here
		return "";
	}

	/**
	 * @param value
	 */
	public void setTitle(String value) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public Date getDatecreated() {
		// TODO implement here
		return null;
	}

	/**
	 * @param value
	 */
	public void setDatecreated(Date value) {
		// TODO implement here
	}

}
