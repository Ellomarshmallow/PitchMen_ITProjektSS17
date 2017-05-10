package de.pitchMen.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;


import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.OrganisationUnit;
import de.pitchMen.shared.bo.Participation;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Rating;
import de.pitchMen.shared.bo.Trait;

public interface PitchMenAdminAsync {

	void init(AsyncCallback<Void> callback);
	/**
	 * Fügt ein Project-Objekt zur ArrayList projects hinzu.
	 * 
	 *  @param das hinzuzufügende Project-Objekt
	 * @param project  
	 * @return
	 */
	void createProject(Marketplace marketplace, AsyncCallback<Project> callback);

	/**
	 * Fügt ein PartnerProfile-Objekt zur ArrayList partnerprofiles hinzu.
	 * 
	 *  @param das hinzuzufügende PartnerProfile-Objekt
	 * @param trait 
	 * @return
	 */
	void addTrait(Trait Trait, AsyncCallback<Void> callback);

	/**
	 * Fügt ein JobPosting-Objekt zur ArrayList jobPostings hinzu.
	 * 
	 *  @param das hinzuzufügende JobPosting-Objekt
	 * @param jobPosting  
	 * @return
	 */
	void addJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	/**
	 * Fügt ein Marketplace-Objekt zur ArrayList marketplaces hinzu.
	 * 
	 *  @param das hinzuzufügende Marketplace-Objekt
	 * @param marketplace 
	 * @return
	 */
	void addMarketplace(Marketplace marketplace, AsyncCallback<Void> callback);

	/**
	 * Erstellt ein neues Marketplace-Objekt.
	 * 
	 *  @return das neu erstellte Marketplace-Objekt
	 * @return Erstellt einen neuen marketplace.
	 * 
	 * @return neues marketplace Objekt
	 */
	void createMarketplace(String title, String description, AsyncCallback<Marketplace> callback);

	/**
	 * erstellt ein neues partnerProfile-Objekt
	 * 
	 *  @param das zu -Objekt
	 * @param jobPosting  
	 * @return
	 * @throws IllegalArgumentException
	 */

	void createPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit, Date dateCreated, Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting, AsyncCallback<PartnerProfile> callback);

	/**
	 * Erstellt ein neues Project-Objekt.
	 * 
	 *  @return das neu erstellte Project-Objekt
	 * @return
	 */
	void addProject(Project p,  AsyncCallback<Void> callback);

	/**
	 * Erstellt ein neues Trait-Objekt.
	 * 
	 *  @return das neu erstellte Trait-Objekt
	 * @return
	 */
	void createTrait(String name, String value,  AsyncCallback<Trait> callback);

	/**
	 * Löscht ein Trait-Objekt aus der ArrayList traits.
	 * 
	 *  @param das zu löschende Trait-Objekt
	 * @param trait 
	 * @return
	 */
	void deleteTrait(Trait trait,  AsyncCallback<Void> callback);

	/**
	 * Löscht das PartnerProfile-Objekt.
	 * 
	 *  @param das zu löschende PartnerProfil-Objekt
	 * @return
	 */
	void deletePartnerProfile(PartnerProfile partnerProfile,  AsyncCallback<Void> callback);

	/**
	 * Löscht das Rating-Objekt.
	 * 
	 *  @param das zu löschende Rating-Objekt
	 * @return
	 */
	void deleteRating(Rating rating,  AsyncCallback<Void> callback);

	/**
	 * Löscht das JobPosting-Objekt.
	 * 
	 *  @param das zu löschende JobPosting-Objekt
	 * @param jobPosting  
	 * @return
	 */
	void deleteJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback );
	/**
	 * erstellt ein neues JobPosting-Objekt
	 * 
	 *  @param das zu löschende JobPosting-Objekt
	 * @param jobPosting  
	 * @return
	 * @throws IllegalArgumentException
	 */
	void createJobPosting(String title, String text, Date deadline, PartnerProfile partnerprofile, AsyncCallback<JobPosting> callback) throws IllegalArgumentException;
	/**
	 * Löscht das Participation-Objekt.
	 * 
	 *  @param das zu löschende Participation-Objekt
	 * @param participation  
	 * @return
	 */
	void deleteParticipation(Participation participation,  AsyncCallback<Void> callback);

	/**
	 * Löscht das Project-Objekt.
	 * 
	 *  @param das zu löschende Project-Objekt
	 * @param project 
	 * @return
	 */
	void deleteProject(Project p,  AsyncCallback<Void> callback);

	/**
	 * Löscht ein Marketplace-Objekt aus der ArrayList marketplaces.
	 * 
	 *  @param das zu löschende Marketplace-Objekt
	 * @param marketplace 
	 * @return
	 */
	void deleteMarketplace(Marketplace m,  AsyncCallback<Void> callback);

	/**
	 * @return
	 */
	public void getMarketplaces(AsyncCallback<ArrayList<Marketplace>> callback);

	/**
	 * @param value 
	 * @return
	 */
	void setMarketplaces(Marketplace m,  AsyncCallback<Void> callback);

}