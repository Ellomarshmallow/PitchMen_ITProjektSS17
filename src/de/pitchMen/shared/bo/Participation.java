package de.pitchMen.shared.bo;

import java.util.ArrayList;
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

	private Rating rating = null;

	private OrganisationUnit associatedApplicant = null;

	private Project associatedProject = null;

	private static final long serialVersionUID = 1L;

	public Project project = null;

	public ArrayList<OrganisationUnit> partners = null;

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
	 * @return associatedApplication
	 */
	public OrganisationUnit getAssociatedApplicant() {
		return this.associatedApplicant;
	}

	/**
	 * @param associatedApplicant
	 */
	public void setAssociatedApplicant(OrganisationUnit associatedApplicant) {
		this.associatedApplicant = associatedApplicant;
	}

	/**
	 * @return associatedProject
	 */
	public Project getAssociatedProject() {
		return this.associatedProject;
	}

	/**
	 * @param associatedProject
	 */
	public void setAssociatedProject(Project associatedProject) {
		this.associatedProject = associatedProject;
	}

	/**
	 * @return partners
	 */
	public ArrayList<OrganisationUnit> getPartners() {
		return partners;
	}

	/**
	 * @param partners
	 */
	public void setPartners(ArrayList<OrganisationUnit> partners) {
		this.partners = partners;
	}

}