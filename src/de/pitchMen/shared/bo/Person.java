package de.pitchMen.shared.bo;

import java.util.ArrayList;

/**
 * Repräsentiert eine Person und erbt von der Superklasse OrganisationUnit.
 * 
 * @author JuliusDigel
 */
public class Person extends OrganisationUnit {


	private String firstName = "";

	private static final long serialVersionUID = 1L;

	private ArrayList<Project> projects = null;



	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return projects
	 */
	public ArrayList<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 */
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

}