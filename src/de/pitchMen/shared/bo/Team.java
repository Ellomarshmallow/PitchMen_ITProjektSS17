package de.pitchMen.shared.bo;


/**
 * Repräsentiert ein Team und erbt von der Superklasse OrganisationUnit.
 * 
 * @author JuliusDigel
 */
public class Team extends OrganisationUnit {

	private static final long serialVersionUID = 1L;
	
	private int teamSize = 0;

	/**
	 * @return the teamSize
	 */
	public int getTeamSize() {
		return teamSize;
	}

	/**
	 * @param teamSize the teamSize to set
	 */
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	

}