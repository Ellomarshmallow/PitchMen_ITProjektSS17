package de.pitchMen.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.pitchMen.shared.bo.*;

/**
 * Schnittstelle für die RPC-Fähige Klasse PitchMenAdminImpl.
 * 
 * @author Eleonora Renz
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

	// ---------- APPLICATION

	/**
	 * Erstellt ein neues Application-Objekt.
	 * 
	 * @return das neu erstellte Application-Objekt
	 * @throws IllegalArgumentException
	 */
	public Application addApplication(Date dateCreated, OrganisationUnit applicant, String text, Rating rating)
			throws IllegalArgumentException;

	/**
	 * Fügt ein Application-Objekt zur ArrayList applications hinzu.
	 * 
	 * @return ein fertiges Application-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateApplication(Application application) throws IllegalArgumentException;

	/**
	 * Löscht das Project-Objekt.
	 * 
	 * @param application
	 * @throws IllegalArgumentException
	 */
	public void deleteApplication(Application application) throws IllegalArgumentException;

	public ArrayList<Application> getApplications() throws IllegalArgumentException;

	public Application getApplicationByID(int id) throws IllegalArgumentException;

	// ------------------- COMPANY

	public Company addCompany(AsyncCallback<Application> callback) throws IllegalArgumentException;

	public void updateCompany(Company company, AsyncCallback<Void> callback) throws IllegalArgumentException;

	void deleteCompany(Company company, AsyncCallback<Void> callback) throws IllegalArgumentException;

	void getCompanyByID(int id, AsyncCallback<Application> callback) throws IllegalArgumentException;

	// ------------------- PROJECTS

	/**
	 * Erstellt ein neues Project-Objekt.
	 * 
	 * @return das neu erstellte Project-Objekt
	 * @throws IllegalArgumentException
	 */
	public Project addProject(Date dateOpened, Date dateClosed, String title, String description, Person manager,
			ArrayList<JobPosting> jobPostings, ArrayList<Participation> participation) throws IllegalArgumentException;

	/**
	 * Fügt ein Project-Objekt zur ArrayList projects hinzu.
	 * 
	 * @return ein fertiges Project-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateProject(Project project) throws IllegalArgumentException;

	/**
	 * Löscht das Project-Objekt.
	 * 
	 * @param project
	 * @throws IllegalArgumentException
	 */
	public void deleteProject(Project project) throws IllegalArgumentException;

	public ArrayList<Project> getProject() throws IllegalArgumentException;

	public Project getProjectByID(int id) throws IllegalArgumentException;

	public void setProject(Project project) throws IllegalArgumentException;

	// --------------------------- MARKETPLACE

	/**
	 * Erstellt ein neues Marketplace-Objekt.
	 * 
	 * @return neues Marketplace-Objekt
	 * @throws IllegalArgumentException
	 */
	public Marketplace addMarketplace(String title, String description, ArrayList<OrganisationUnit> organisationUnits,
			ArrayList<Project> projects) throws IllegalArgumentException;

	/**
	 * Fügt ein Marketplace-Objekt zur ArrayList marketplaces hinzu.
	 * 
	 * @param marketplace
	 * @throws IllegalArgumentException
	 */
	public void updateMarketplace(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * Löscht ein Marketplace-Objekt aus der ArrayList marketplaces.
	 *
	 * @param marketplace
	 * @throws IllegalArgumentException
	 */

	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * @throws IllegalArgumentException
	 */

	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException;

	public Marketplace getMarketplaceByID(int id) throws IllegalArgumentException;

	/**
	 * @param marketplace
	 * @throws IllegalArgumentException
	 */

	public void setMarketplaces(Marketplace marketplace) throws IllegalArgumentException;

	// -------------------------- TRAITS

	/**
	 * Erstellt ein neues Trait-Objekt.
	 * 
	 * @return das neu erstellte Trait-Objekt
	 * @throws IllegalArgumentException
	 */

	public Trait addTrait(String name, String value) throws IllegalArgumentException;

	/**
	 * Fügt ein Trait-Objekt zur ArrayList traits hinzu.
	 * 
	 * @param trait
	 * @return ein fertiges Trait Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateTrait(Trait trait) throws IllegalArgumentException;

	/**
	 * Löscht ein Trait-Objekt aus der ArrayList traits.
	 * 
	 * @param trait
	 * @throws IllegalArgumentException
	 */
	public void deleteTrait(Trait trait) throws IllegalArgumentException;

	public ArrayList<Trait> getTraits() throws IllegalArgumentException;

	public Trait getTraitByID(int id) throws IllegalArgumentException;
	// ---------------------JOBPOSTINGS

	/**
	 * Erstellt ein neues JobPosting-Objekt
	 * 
	 * @param jobPosting
	 * @throws IllegalArgumentException
	 */
	public JobPosting addJobPosting(String title, String text, Date deadline, PartnerProfile partnerprofile,
			Person recruiter, ArrayList<Application> applications) throws IllegalArgumentException;

	/**
	 * Fügt ein JobPosting-Objekt zur ArrayList jobPostings hinzu.
	 * 
	 * @param jobPosting
	 * @throws IllegalArgumentException
	 */
	public void updateJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	/**
	 * Löscht das JobPosting-Objekt.
	 * 
	 * @param jobPosting
	 * @throws IllegalArgumentException
	 */
	public void deleteJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	public ArrayList<JobPosting> getJobPostings() throws IllegalArgumentException;

	public JobPosting getJobPostingByID(int id) throws IllegalArgumentException;

	public void setJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	// --------------------- PARTNERPROFILES

	/**
	 * Erstellt ein neues partnerProfile-Objekt
	 * 
	 * @param partnerProfile
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile addPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit,
			Date dateCreated, Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting)
			throws IllegalArgumentException;

	public void updatePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	/**
	 * Löscht das PartnerProfile-Objekt.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	public ArrayList<PartnerProfile> getPartnerProfiles() throws IllegalArgumentException;

	public PartnerProfile getPartnerProfileByID(int id) throws IllegalArgumentException;

	// ------------------------ RATING

	/**
	 * Erstellt ein neues Rating-Objekt
	 * 
	 * @param rating
	 * @throws IllegalArgumentException
	 */
	public Rating addRating(String statement, float score) throws IllegalArgumentException;

	/**
	 * Fügt ein Rating-Objekt zur ArrayList ratings hinzu.
	 * 
	 * @param rating
	 * @throws IllegalArgumentException
	 */
	public void updateRating(Rating rating) throws IllegalArgumentException;

	/**
	 * Löscht das Rating-Objekt.
	 * 
	 * @param rating
	 * @throws IllegalArgumentException
	 */
	public void deleteRating(Rating rating) throws IllegalArgumentException;

	public ArrayList<Rating> getRatings() throws IllegalArgumentException;

	public Rating getRatingByID(int id) throws IllegalArgumentException;

	// --------------------------- PARTICIPATION

	/**
	 * Erstellt ein neues Participation-Objekt
	 * 
	 * @param participation
	 * @throws IllegalArgumentException
	 */
	public Participation addParticipation(Date dateOpened, Date dateClosed, float workload, Rating rating,
			OrganisationUnit associatedApplicant, Project associatedProject) throws IllegalArgumentException;

	/**
	 * Fügt ein Participation-Objekt zur ArrayList ratings hinzu.
	 * 
	 * @param participation
	 * @throws IllegalArgumentException
	 */
	public void updateParticipation(Participation participation) throws IllegalArgumentException;

	/**
	 * Löscht das Participation-Objekt.
	 * 
	 * @param participation
	 * @throws IllegalArgumentException
	 */
	public void deleteParticipation(Participation participation) throws IllegalArgumentException;

	public ArrayList<Participation> getParticipations() throws IllegalArgumentException;

	public Participation getParticipationByID(int id) throws IllegalArgumentException;
}