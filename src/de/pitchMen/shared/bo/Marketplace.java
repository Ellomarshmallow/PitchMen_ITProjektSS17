package de.pitchMen.shared.bo;

import java.util.ArrayList;

/**
 * Repräsentiert eine Marktplatz.
 * 
 * @author EleonoraRenz
 */
public class Marketplace extends BusinessObject {

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
	private String description = "";

	private ArrayList<Project> projects = null;

	private static final long serialVersionUID = 1L;

	private ArrayList<OrganisationUnit> organisationUnits = null;

	public void setProject(ArrayList<Project> projects) {
		this.projects = projects;
	}

	public void setParticipant(ArrayList<OrganisationUnit> organisationUnits) {
		this.setOrganisationUnits(organisationUnits);
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
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return projects
	 */
	public ArrayList<Project> getProjects() {
		return this.projects;
	}

	/**
	 * @return organisationUnits
	 */
	public ArrayList<OrganisationUnit> getOrganisationUnits() {
		return organisationUnits;
	}

	/**
	 * @param organisationUnits
	 */
	public void setOrganisationUnits(ArrayList<OrganisationUnit> organisationUnits) {
		this.organisationUnits = organisationUnits;
	}

	

}