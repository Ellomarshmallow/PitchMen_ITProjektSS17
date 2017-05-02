package de.pitchMen.shared.bo;

import java.util.ArrayList;
import java.util.Date;


/**
 * Repräsentiert eine Projekt.
 * 
 * @author JuliusDigel
 */
public class Project extends BusinessObject {


	private Date dateOpened = null;


	private Date dateClosed = null;


	private String title = "";


	private String description = "";


	private Person manager = null;


	private ArrayList<JobPosting> jobPostings = null;


	private ArrayList<Participation> participations = null;


	private static final long serialVersionUID = 1L;


	public Person Manager = null;

	/**
	 * Auslesen der JobPostings
	 * @return jobPostings
	 */

	public ArrayList<Participation> getParticipations(){
		return participations;
	}

	/**
	 * Setzen der participations
	 * @param participations
	 */   

	public void setParticipations(ArrayList<Participation> participations){
		this.participations = participations;
	}

	/**
	 * Auslesen der JobPostings
	 * @return jobPostings
	 */   

	public ArrayList<JobPosting> getJobPostings(){
		return jobPostings;
	}

	/**
	 * Setzen der JobPostings
	 * @param jobPostings
	 */   

	public void setJobPostings(ArrayList<JobPosting> jobPostings){
		this.jobPostings = jobPostings;
	}

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
	 * @return manager
	 */
	public Person getManager() {
		return this.manager;
	}




}