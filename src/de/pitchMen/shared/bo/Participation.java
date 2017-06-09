package de.pitchMen.shared.bo;

import java.util.Date;

/**
 * Repräsentiert eine Beteiligung.
 * 
 * @author EleonoraRenz
 */
public class Participation extends BusinessObject {

	private Date dateOpened = null;

	private Date dateClosed = null;

	private float workload = 0.0f;

	private static final long serialVersionUID = 1L;

	private int projectId;
	
	private int personId;
	
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

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}


	
}