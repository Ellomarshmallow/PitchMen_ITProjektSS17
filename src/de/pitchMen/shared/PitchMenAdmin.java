package de.pitchMen.shared;

import java.util.ArrayList;
import java.util.Date;

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

	public Application addApplication(Date dateCreated, String text, String status, int jobPostingId,
			int partnerProfileId) throws IllegalArgumentException;

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

	public ArrayList<Application> getApplicationsByPerson(int personId) throws IllegalArgumentException;

	public ArrayList<Application> getApplicationsByJobPostingId(int jobPostingId) throws IllegalArgumentException;

	// ------------------- COMPANY

	public Company addCompany() throws IllegalArgumentException;

	public void updateCompany(Company company) throws IllegalArgumentException;

	public void deleteCompany(Company company) throws IllegalArgumentException;

	public Company getCompanyByID(int id) throws IllegalArgumentException;

	public ArrayList<Company> getCompanies() throws IllegalArgumentException;

	// ---------------------JOBPOSTINGS

	/**
	 * Erstellt ein neues JobPosting-Objekt
	 * 
	 * @param jobPosting
	 * @throws IllegalArgumentException
	 */
	public JobPosting addJobPosting(String title, String text, String status, Date deadline, int projectId)
			throws IllegalArgumentException;

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

	public ArrayList<JobPosting> getJobPostingsByProjectId(int projectId) throws IllegalArgumentException;

	// ------------------- PERSON

	/**
	 * Erstellt ein neues Person-Objekt.
	 * 
	 * @return das neu erstellte Person-Objekt
	 * @throws IllegalArgumentException
	 */

	public Person addPerson(String firstName, boolean loggedIn, String emailAdress, String nickname, String loginUrl,
			String logoutUrl) throws IllegalArgumentException;

	public void updatePerson(Person person) throws IllegalArgumentException;

	/**
	 * Löscht das Person-Objekt.
	 * 
	 * @param person
	 * @throws IllegalArgumentException
	 */

	public void deletePerson(Person person) throws IllegalArgumentException;

	public Person getPersonByID(int id) throws IllegalArgumentException;

	public ArrayList<Person> getAllPeople() throws IllegalArgumentException;

	// ------------------- PROJECTS

	/**
	 * Erstellt ein neues Project-Objekt.
	 * 
	 * @return das neu erstellte Project-Objekt
	 * @throws IllegalArgumentException
	 */

	public Project addProject(Date dateOpened, Date dateClosed, String title, String description, int personId,
			int marketplaceId) throws IllegalArgumentException;

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

	public ArrayList<Project> getProjectsByMarketplaceId(int marketplaceId) throws IllegalArgumentException;
	
	public ArrayList<Project> getProjectsByPerson(int personId) throws IllegalArgumentException;

	// --------------------------- MARKETPLACE

	/**
	 * Erstellt ein neues Marketplace-Objekt.
	 * 
	 * @return neues Marketplace-Objekt
	 * @throws IllegalArgumentException
	 */

	public Marketplace addMarketplace(String title, String description, int personId, int teamId, int companyId)
			throws IllegalArgumentException;

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

	public ArrayList<Trait> getTraitsByPartnerProfileId(int partnerProfileId) throws IllegalArgumentException;

	// --------------------- PARTNERPROFILE

	/**
	 * Erstellt ein neues partnerProfile-Objekt
	 * 
	 * @param partnerProfile
	 * @throws IllegalArgumentException
	 */

	public PartnerProfile addPartnerProfile(Date dateCreated, Date dateChanged, int personId, int teamId, int companyId,
			int jobPostingId) throws IllegalArgumentException;

	public void updatePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	/**
	 * Löscht das PartnerProfile-Objekt.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	public ArrayList<PartnerProfile> getPartnerProfiles() throws IllegalArgumentException;

	public PartnerProfile getPartnerProfileByID(int id) throws IllegalArgumentException;

	// ------------------- Team

	/**
	 * Erstellt ein neues Team-Objekt.
	 * 
	 * @return das neu erstellte Team-Objekt
	 * @throws IllegalArgumentException
	 */
	public Team addTeam() throws IllegalArgumentException;

	public void updateTeam(Team team) throws IllegalArgumentException;

	/**
	 * Löscht das Team-Objekt.
	 * 
	 * @param team
	 * @throws IllegalArgumentException
	 */

	public void deleteTeam(Team team) throws IllegalArgumentException;

	public Team getTeamByID(int id) throws IllegalArgumentException;

	public ArrayList<Team> getTeams() throws IllegalArgumentException;

	// ------------------------ RATING

	/**
	 * Erstellt ein neues Rating-Objekt
	 * 
	 * @param rating
	 * @throws IllegalArgumentException
	 */

	public Rating addRating(String statement, float score, int applicationId) throws IllegalArgumentException;

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

	public Rating getRatingByApplicationId(int applicationId) throws IllegalArgumentException;

	// --------------------------- PARTICIPATION

	/**
	 * Erstellt ein neues Participation-Objekt
	 * 
	 * @param participation
	 * @throws IllegalArgumentException
	 */

	public Participation addParticipation(Date dateOpened, Date dateClosed, float workload, int projectId, int personId)
			throws IllegalArgumentException;

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
	
	public ArrayList<Participation> getParticipationsByPersonId(int personId) throws IllegalArgumentException;

	// --------------------------- LOGIN

	public Person login(String requestUri);

}