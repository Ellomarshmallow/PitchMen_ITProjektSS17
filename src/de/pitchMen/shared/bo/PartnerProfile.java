package de.pitchMen.shared.bo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Jedes PartnerProfile-Objekt gehört zu genau einem Teilnehmer des Projektmarktplatzes bzw. einer Ausschreibung.
 * 
 * @author JuliusDigel
 */ 
public class PartnerProfile extends BusinessObject {

	private ArrayList<Trait> traits = null;

	private Date dateCreated = null;

	private Date dateChanged = null;

	private static final long serialVersionUID = 1L;

	public OrganisationUnit OrganisationUnit = null;

	public JobPosting JobPosting = null;

	/**
	 * @return
	 */
	public ArrayList<Trait> getTraits() {
		return traits;
	}
	/**
	 * @return
	 */
	public void setTraits(ArrayList<Trait> traits) {
		this.traits = traits;
	}
	/**
	 * @return
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	/**
	 * @return
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return
	 */
	public Date getDateChanged() {
		return dateChanged;
	}

	/**
	 * @return
	 */
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	/**
	 * Vergleicht das aufrufende PartnerProfile-Objekt mit dem übergebenen PartnerProfil-Objekt und ermittelt einen Übereinstimmungswert.
	 * 
	 * @param partnerProfile 
	 * @return
	 */
	public float compareWith(PartnerProfile partnerProfile) {
		
		return 0.0f;
	}








}