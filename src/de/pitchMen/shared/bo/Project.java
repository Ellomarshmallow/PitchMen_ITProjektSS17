package de.pitchMen.shared.bo;

import java.sql.Date;

/**
 * ReprÃ¤sentiert eine Projekt.
 * 
 * @author JuliusDigel
 */
public class Project extends BusinessObject {

	private Date dateOpened = null;

	private Date dateClosed = null;

	private String title = "";

	private String description = "";

	private static final long serialVersionUID = 1L;

	/**
	 * Realisierung der Beziehung zu einer Person durch einen Fremdschlüssel.
	 */
	private int personId = 0;
	
	/**
	 * Realisierung der Beziehung zu einem Marktplatz durch einen
	 * Fremdschlüssel.
	 */
	private int marketplaceId = 0;

	/**
	 * @return dateOpened
	 */
	public Date getDateOpened() {
		return this.dateOpened;
	}

	/**
	 * @param dateOpened
	 **/
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	/**
	 * @return
	 */
	public Date getDateClosed() {
		return this.dateClosed;
	}

	/**
	 * @param dateClosed
	 */
	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
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
	 * @return describtion
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param describtion
	 */
	public void setDescription(String describtion) {
		this.description = describtion;
	}

	/**
	 * @return personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * @return marketplaceId
	 */
	public int getMarketplaceId() {
		return marketplaceId;
	}

	/**
	 * @param marketplaceId
	 */
	public void setMarketplaceId(int marketplaceId) {
		this.marketplaceId = marketplaceId;
	}

}