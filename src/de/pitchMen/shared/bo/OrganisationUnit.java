package de.pitchMen.shared.bo;

import java.util.ArrayList;

/**
 * Abstrakte Superklasse der Klassen Person, Team, Company.
 * 
 * @author EleonoraRenz
 */
public abstract class OrganisationUnit extends BusinessObject {

	private String description = "";

	private String name = "";

	private PartnerProfile partnerProfile = null;

	private ArrayList<Participation> participations = null;

	private static final long serialVersionUID = 1L;

	private ArrayList<Marketplace> marketplaces = null;

	// public PartnerProfile PartnerProfile;

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

	/**
	 * @return partnerProfile
	 */
	public PartnerProfile getPartnerProfile() {
		return this.partnerProfile;
	}

	/**
	 * @param partnerProfile
	 */
	public void setPartnerProfile(PartnerProfile partnerProfile) {
		this.partnerProfile = partnerProfile;
	}

	/**
	 * @return participations
	 */
	public ArrayList<Participation> getParticipations() {
		return participations;
	}

	/**
	 * @param participations
	 */
	public void setParticipations(ArrayList<Participation> participations) {
		this.participations = participations;
	}

	/**
	 * @return marketplaces
	 */
	public ArrayList<Marketplace> getMarketplaces() {
		return marketplaces;
	}

	/**
	 * 
	 * @param marketplaces
	 */
	public void setMarketplaces(ArrayList<Marketplace> marketplaces) {
		this.marketplaces = marketplaces;
	}

}