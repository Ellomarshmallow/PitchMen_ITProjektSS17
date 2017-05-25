package de.pitchMen.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.OrganisationUnit;
import de.pitchMen.shared.bo.Participation;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Rating;
import de.pitchMen.shared.bo.Trait;

/**
 * Schnittstelle für die RPC-Fähige Klasse PitchMenAdminImpl.
 * 
 * @author
 */
@RemoteServiceRelativePath("pitchmenadmin")
public interface PitchMenAdmin extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von
	 * GWT RPC zusätzlich zum No Argument Constructor der implementierenden
	 * Klasse {@link PitchMenAdminImpl} notwendig. Bitte diese Methode direkt
	 * nach der Instantiierung aufrufen.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;

	// ------------------- PROJECTS

	/**
	 * Fügt ein Project-Objekt zur ArrayList projects hinzu.
	 * 
	 * @param das
	 *            hinzuzufügende Project-Objekt
	 * @param project
	 * @return ein fertiges projekt Objekt
	 * @throws IllegalArgumentException
	 */
	public Project createProject(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * Erstellt ein neues Project-Objekt.
	 * 
	 * @return das neu erstellte Project-Objekt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void addProject(Project project) throws IllegalArgumentException;

	/**
	 * Löscht das Project-Objekt.
	 * 
	 * @param das
	 *            zu löschende Project-Objekt
	 * @param project
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deleteProject(Project project) throws IllegalArgumentException;

	// --------------------------- MARKETPLACE

	/**
	 * Fügt ein Marketplace-Objekt zur ArrayList marketplaces hinzu.
	 * 
	 * @param das
	 *            hinzuzufügende Marketplace-Objekt
	 * @param marketplace
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void addMarketplace(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * Erstellt ein neues Marketplace-Objekt.
	 * 
	 * @return das neu erstellte Marketplace-Objekt
	 * @return Erstellt einen neuen marketplace.
	 * 
	 * @return neues marketplace Objekt
	 * @throws IllegalArgumentException
	 */
	public Marketplace createMarketplace(String title, String description) throws IllegalArgumentException;

	/**
	 * Löscht ein Marketplace-Objekt aus der ArrayList marketplaces.
	 * 
	 * @param das
	 *            zu löschende Marketplace-Objekt
	 * @param marketplace
	 * @return
	 * @throws IllegalArgumentException
	 */

	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * @return
	 * @throws IllegalArgumentException
	 */

	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException;

	/**
	 * @param value
	 * @return
	 * @throws IllegalArgumentException
	 */

	public void setMarketplaces(Marketplace marketplace) throws IllegalArgumentException;

	// -------------------------- TRAITS

	/**
	 * Fügt ein PartnerProfile-Objekt zur ArrayList partnerprofiles hinzu.
	 * 
	 * @param das
	 *            hinzuzufügende PartnerProfile-Objekt
	 * @param trait
	 * @return ein fertiges Trait Objekt
	 * @throws IllegalArgumentException
	 */
	public void addTrait(Trait trait) throws IllegalArgumentException;

	/**
	 * Erstellt ein neues Trait-Objekt.
	 * 
	 * @return das neu erstellte Trait-Objekt
	 * @return
	 * @throws IllegalArgumentException
	 */

	public Trait createTrait(String name, String value) throws IllegalArgumentException;

	/**
	 * Löscht ein Trait-Objekt aus der ArrayList traits.
	 * 
	 * @param das
	 *            zu löschende Trait-Objekt
	 * @param trait
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deleteTrait(Trait trait) throws IllegalArgumentException;

	// ---------------------JOBPOSTINGS

	/**
	 * Fügt ein JobPosting-Objekt zur ArrayList jobPostings hinzu.
	 * 
	 * @param das
	 *            hinzuzufügende JobPosting-Objekt
	 * @param jobPosting
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void addJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	/**
	 * Löscht das JobPosting-Objekt.
	 * 
	 * @param das
	 *            zu löschende JobPosting-Objekt
	 * @param jobPosting
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deleteJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	/**
	 * erstellt ein neues JobPosting-Objekt
	 * 
	 * @param das
	 *            zu löschende JobPosting-Objekt
	 * @param jobPosting
	 * @return
	 * @throws IllegalArgumentException
	 */
	public JobPosting createJobPosting(String title, String text, Date deadline, PartnerProfile partnerprofile)
			throws IllegalArgumentException;

	// --------------------- PARTNERPROFILES
	/**
	 * Löscht das PartnerProfile-Objekt.
	 * 
	 * @param das
	 *            zu erstellende PartnerProfile-Objekt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	/**
	 * erstellt ein neues partnerProfile-Objekt
	 * 
	 * @param das
	 *            zu -Objekt
	 * @param jobPosting
	 * @return
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile createPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit,
			Date dateCreated, Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting)
			throws IllegalArgumentException;

	// ------------------------ RATING

	/**
	 * Löscht das Rating-Objekt.
	 * 
	 * @param das
	 *            zu löschende Rating-Objekt
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deleteRating(Rating rating) throws IllegalArgumentException;

	// --------------------------- PARTICIPATION

	/**
	 * Löscht das Participation-Objekt.
	 * 
	 * @param das
	 *            zu löschende Participation-Objekt
	 * @param participation
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void deleteParticipation(Participation participation) throws IllegalArgumentException;

}