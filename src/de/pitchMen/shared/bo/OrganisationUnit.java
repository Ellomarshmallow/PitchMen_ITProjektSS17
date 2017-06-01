package de.pitchMen.shared.bo;


/**
 * Abstrakte Superklasse der Klassen Person, Team, Company.
 * 
 * @author EleonoraRenz
 */
public abstract class OrganisationUnit extends BusinessObject {

	private String description = "";

	private String name = "";
	
	


	private static final long serialVersionUID = 1L;

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
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}