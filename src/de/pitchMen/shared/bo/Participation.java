package de.pitchMen.shared.bo;

import java.util.ArrayList;
import java.util.Date;

/**
 * ReprÃ¤sentiert eine Beteiligung.
 * 
 * @author EleonoraRenz
 */
public class Participation extends BusinessObject {

	private Date dateOpened = null;

	private Date dateClosed = null;

	private float workload = 0.0f;

	private static final long serialVersionUID = 1L;

	/**
	 * TODO ForeignKey Attribute hinzufügen
	 */
	
	/**
	 * @return dateOpened
	 */
	public Date getDateOpened() {
		return this.dateOpened;
	}

	/**
	 * @param dateOpened
	 */
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	/**
	 * @return workload
	 */
	public float getWorkload() {
		return this.workload;
	}

	/**
	 * @param workload
	 */
	public void setWorkload(float workload) {
		this.workload = workload;
	}

	/**
	 * @return dateClosed
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

	
}