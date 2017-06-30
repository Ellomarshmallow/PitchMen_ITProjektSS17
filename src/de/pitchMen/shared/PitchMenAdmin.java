package de.pitchMen.shared;

import java.util.ArrayList;
import java.sql.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.pitchMen.server.PitchMenAdminImpl;
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

	// ----------------------------------------- APPLICATION

	/**
	 * Erstellt ein neues Application-Objekt.
	 * 
	 * @return neu erstelltes Application-Objekt
	 * @throws IllegalArgumentException
	 */
	public Application addApplication(Date dateCreated, String text, String status, int jobPostingId,
			int partnerProfileId) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Application-Objekt.
	 * 
	 * @return aktualisiertes Application-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateApplication(Application application) throws IllegalArgumentException;

	/**
	 * Löscht ein Application-Objekt und alle eventuell darauf basierenden
	 * Objekte.
	 * 
	 * @param application
	 * @throws IllegalArgumentException
	 */
	public void deleteApplication(Application application) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Bewerbungen aus.
	 * 
	 * @return ArrayList aller Bewerbungen
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Application> getApplications() throws IllegalArgumentException;

	/**
	 * Gibt eine spezifische Bewerbung anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return eine spezifische Bewerbung
	 * @throws IllegalArgumentException
	 */
	public Application getApplicationByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle Bewerbungen einer spezifischen Person aus.
	 * 
	 * @param personId
	 * @return ArrayList aller Bewerbungen einer Person
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Application> getApplicationsByPerson(int personId) throws IllegalArgumentException;

	/**
	 * Gibt alle Bewerbungen auf eine spezische Ausschreibung aus.
	 * 
	 * @param jobPostingId
	 * @return ArrayList aller Bewerbungen einer Ausschreibung
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Application> getApplicationsByJobPostingId(int jobPostingId) throws IllegalArgumentException;

	// -------------------------------------------- COMPANY

	/**
	 * Erstellt ein neues Company-Objekt.
	 * 
	 * @return neu erstelltes Comany-Objekt
	 * @throws IllegalArgumentException
	 */
	public Company addCompany(String name, String description) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Company-Objekt.
	 * 
	 * @return aktualisiertes Company-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateCompany(Company company) throws IllegalArgumentException;

	/**
	 * Löscht ein Company-Objekt und alle eventuell darauf basierenden Objekte.
	 * 
	 * @param company
	 * @throws IllegalArgumentException
	 */
	public void deleteCompany(Company company) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Firmen aus.
	 * 
	 * @return ArrayList aller Firmen
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Company> getCompanies() throws IllegalArgumentException;

	/**
	 * Gibt eine spezifische Firma anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return eine spezifische Firma
	 * @throws IllegalArgumentException
	 */
	public Company getCompanyByID(int id) throws IllegalArgumentException;

	// -------------------------------------------JOBPOSTING

	/**
	 * Erstellt ein neues JobPosting-Objekt
	 * 
	 * @param jobPosting
	 * @throws IllegalArgumentException
	 */
	public JobPosting addJobPosting(String title, String text, String status, Date deadline, int projectId)
			throws IllegalArgumentException;
	


	/**
	 * Aktuallisiert ein JobPosting-Objekt.
	 * 
	 * @return aktualisiertes JobPosting-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	/**
	 * Löscht ein JobPosting-Objekt und alle eventuell darauf basierenden
	 * Objekte.
	 * 
	 * @param jobPosting
	 * @throws IllegalArgumentException
	 */
	public void deleteJobPosting(JobPosting jobPosting) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Ausschreibungen aus.
	 * 
	 * @return ArrayList aller Ausschreibungen
	 * @throws IllegalArgumentException
	 */
	public ArrayList<JobPosting> getJobPostings() throws IllegalArgumentException;

	/**
	 * Gibt eine spezifische Ausschreibung anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return eine spezifische Ausschreibung
	 * @throws IllegalArgumentException
	 */
	public JobPosting getJobPostingByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle Ausschreibungen auf ein spezisches Projekt aus.
	 * 
	 * @param projectId
	 * @return ArrayList aller Ausschreibungen eines Projekts
	 * @throws IllegalArgumentException
	 */
	public ArrayList<JobPosting> getJobPostingsByProjectId(int projectId) throws IllegalArgumentException;
	
	
	/**
	 * Gibt eine spezifische Ausschreibung anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return eine spezifische Ausschreibung zu einem PartnerProfil
	 * @throws IllegalArgumentException
	 */
	public JobPosting getJobPostingByPPId(int id) throws IllegalArgumentException;

	
	

	// ---------------------------------------- MARKETPLACE

	/**
	 * Eine Firma erstellt ein neues Marketplace-Objekt.
	 * 
	 * @return Marketplace-Objekt
	 * @throws IllegalArgumentException
	 */
	public Marketplace addMarketplaceByCompany(String title, String description, int companyId)
			throws IllegalArgumentException;
	/**
	 * Ein Team erstellt ein neues Marketplace-Objekt.
	 * 
	 * @return Marketplace-Objekt
	 * @throws IllegalArgumentException
	 */
	public Marketplace addMarketplaceByTeam(String title, String description, int teamId) throws IllegalArgumentException;
	
	/**
	 * Eine Person erstellt ein neues Marketplace-Objekt.
	 * 
	 * @return Marketplace-Objekt
	 * @throws IllegalArgumentException
	 */
	public Marketplace addMarketplaceByPerson(String title, String description, int personId) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Marketplace-Objekt.
	 * 
	 * @return aktualisiertes Marketplace-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateMarketplace(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * Löscht ein Marketplace-Objekt und alle eventuell darauf basierenden
	 * Objekte.
	 * 
	 * @param marketplace
	 * @throws IllegalArgumentException
	 */
	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Marktpl�tze aus.
	 * 
	 * @return ArrayList aller Marktpl�tze
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException;

	/**
	 * Gibt einen spezifischen Marktplatz anhand seiner Id aus.
	 * 
	 * @param id
	 * @return ein spezifischer Marktplatz
	 * @throws IllegalArgumentException
	 */
	public Marketplace getMarketplaceByID(int id) throws IllegalArgumentException;
	
	/**
	 * Gibt alle Marktpl�tze die einer bestimmten Person gehören aus.
	 * 
	 * @param personId
	 * @return ArrayList aller Marktpl�tze die der Person gehören
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Marketplace> getMarketplacesByPersonId(int personId) throws IllegalArgumentException;

	// ------------------------------------ PARTICIPATION

	/**
	 * Erstellt ein neues Participation-Objekt
	 * 
	 * @param participation
	 * @throws IllegalArgumentException
	 */
	public Participation addParticipation(Date dateOpened, Date dateClosed, float workload, int projectId, int personId)
			throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Participation-Objekt.
	 * 
	 * @return aktualisiertes Participation-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateParticipation(Participation participation) throws IllegalArgumentException;

	/**
	 * Löscht ein Participation-Objekt und alle eventuell darauf basierenden
	 * Objekte.
	 * 
	 * @param participation
	 * @throws IllegalArgumentException
	 */
	public void deleteParticipation(Participation participation) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Beteiligungen aus.
	 * 
	 * @return ArrayList aller Beteiligungen
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Participation> getParticipations() throws IllegalArgumentException;

	/**
	 * Gibt eine spezifische Beteiligung anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return ein spezifischer Marktplatz
	 * @throws IllegalArgumentException
	 */
	public Participation getParticipationByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle Beteiligungen einer spezischen Person aus.
	 * 
	 * @param personId
	 * @return ArrayList aller Beteiligungen einer Person
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Participation> getParticipationsByPersonId(int personId) throws IllegalArgumentException;

	/**
	 * Gibt alle Beteiligungen eines spezischen Teams aus.
	 * 
	 * @param teamId
	 * @return ArrayList aller Beteiligungen eines Teams
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Participation> getParticipationsByTeamId(int teamId) throws IllegalArgumentException;

	/**
	 * Gibt alle Beteiligungen einer spezischen Firma aus.
	 * 
	 * @param companyId
	 * @return ArrayList aller Beteiligungen einer Firma
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Participation> getParticipationsByCompanyId(int companyId) throws IllegalArgumentException;

	// -------------------------------------- PARTNERPROFILE

	/**
	 * Erstellt ein neues PartnerProfile-Objekt f�r eine Firma
	 * 
	 * @return neu erstelltes PartnerProfile-Objekt
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile addPartnerProfileForCompany(Date dateCreated, Date dateChanged, int companyId)
			throws IllegalArgumentException;

	/**
	 * Erstellt ein neues PartnerProfile-Objekt f�r ein Team
	 * 
	 * @return neu erstelltes PartnerProfile-Objekt
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile addPartnerProfileForTeam(Date dateCreated, Date dateChanged, int teamId)
			throws IllegalArgumentException;

	/**
	 * Erstellt ein neues PartnerProfile-Objekt f�r eine Person
	 * 
	 * @return neu erstelltes PartnerProfile-Objekt
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile addPartnerProfileForPerson(Date dateCreated, Date dateChanged, int personId)
			throws IllegalArgumentException;

	/**
	 * Erstellt ein neues PartnerProfile-Objekt f�r eine Ausschreibung
	 * 
	 * @return neu erstelltes PartnerProfile-Objekt
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile addPartnerProfileForJobPosting(Date dateCreated, Date dateChanged, int jobPostingId)
			throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein PartnerProfile-Objekt.
	 * 
	 * @return aktualisiertes PartnerProfile-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updatePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	/**
	 * Löscht ein PartnerProfile-Objekt und alle eventuell darauf basierenden
	 * Objekte.
	 * 
	 * @param PartnerProfile
	 * @throws IllegalArgumentException
	 */
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Partnerprofile aus.
	 * 
	 * @return ArrayList aller PartnerProfile
	 * @throws IllegalArgumentException
	 */
	public ArrayList<PartnerProfile> getPartnerProfiles() throws IllegalArgumentException;

	/**
	 * Gibt das Partnerprofil anhand seiner id aus.
	 * 
	 * @param id
	 * @return PartnerProfile
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile getPartnerProfileByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle Partnerprofile einer spezischen Firma aus.
	 * 
	 * @param companyId
	 * @return ArrayList aller Partnerprofile einer Firma
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile getPartnerProfileByCompanyId(int companyId) throws IllegalArgumentException;

	/**
	 * Gibt das Partnerprofil eines spezischen Teams aus.
	 * 
	 * @param teamId
	 * @return PartnerProfile eines Teams
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile getPartnerProfileByTeamId(int teamId) throws IllegalArgumentException;

	/**
	 * Gibt das Partnerprofil einer spezischen Person aus.
	 * 
	 * @param personId
	 * @return PartnerProfile einer Person
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile getPartnerProfileByPersonId(int personId) throws IllegalArgumentException;

	/**
	 * Gibt das Partnerprofil einer spezischen Ausschreibung aus.
	 * 
	 * @param teamId
	 * @return PartnerProfile eines Ausschreibung
	 * @throws IllegalArgumentException
	 */
	public PartnerProfile getPartnerProfilesByJobPostingId(int jobPostingId) throws IllegalArgumentException;

	// -------------------------------------------- PERSON

	/**
	 * Erstellt ein neues Person-Objekt.
	 * 
	 * @return neu erstelltes Person-Objekt
	 * @throws IllegalArgumentException
	 */
	public Person addPerson(String firstName, String name, String description, String emailAdress, String loginUrl, String logoutUrl,
			boolean loggedIn, boolean isExisting) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Person-Objekt.
	 * 
	 * @return aktualisiertes Person-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updatePerson(Person person) throws IllegalArgumentException;

	/**
	 * Löscht ein Person-Objekt und alle eventuell darauf basierenden Objekte.
	 * 
	 * @param person
	 * @throws IllegalArgumentException
	 */
	public void deletePerson(Person person) throws IllegalArgumentException;

	/**
	 * Gibt die Person anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return PartnerProfile
	 * @throws IllegalArgumentException
	 */
	public Person getPersonByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Personen aus.
	 * 
	 * @return ArrayList aller Person-Objekte
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Person> getAllPeople() throws IllegalArgumentException;

	// ---------------------------------------------- PROJECT

	/**
	 * Erstellt ein neues Project-Objekt.
	 * 
	 * @return neu erstelltes Project-Objekt
	 * @throws IllegalArgumentException
	 */
	public Project addProject(Date dateOpened, Date dateClosed, String title, String description, int personId,
			int marketplaceId) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Project-Objekt.
	 * 
	 * @return aktualisiertes Project-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateProject(Project project) throws IllegalArgumentException;

	/**
	 * Löscht ein Project-Objekt und alle eventuell darauf basierenden Objekte.
	 * 
	 * @param project
	 * @throws IllegalArgumentException
	 */
	public void deleteProject(Project project) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Projekte aus.
	 * 
	 * @return ArrayList aller Project-Objekte
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Project> getProjects() throws IllegalArgumentException;

	/**
	 * Gibt das Projekt anhand seiner Id aus.
	 * 
	 * @param id
	 * @return Project
	 * @throws IllegalArgumentException
	 */
	public Project getProjectByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle Projekte eines spezischen Marktplatzes aus.
	 * 
	 * @param marketplaceId
	 * @return ArrayList aller Projekte eines Marktplatzes
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Project> getProjectsByMarketplaceId(int marketplaceId) throws IllegalArgumentException;

	/**
	 * Gibt alle Projekte einer spezischen Person aus.
	 * 
	 * @param personId
	 * @return ArrayList aller Projekte einer Person
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Project> getProjectsByPerson(int personId) throws IllegalArgumentException;

	// ------------------------------------------ RATING

	/**
	 * Erstellt ein neues Rating-Objekt
	 * 
	 * @param rating
	 * @throws IllegalArgumentException
	 */
	public Rating addRating(String statement, float score, int applicationId) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Rating-Objekt.
	 * 
	 * @return aktualisiertes Rating-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateRating(Rating rating) throws IllegalArgumentException;

	/**
	 * Löscht ein Rating-Objekt und alle eventuell darauf basierenden Objekte.
	 * 
	 * @param rating
	 * @throws IllegalArgumentException
	 */
	public void deleteRating(Rating rating) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Ratings aus.
	 * 
	 * @return ArrayList aller Rating-Objekte
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Rating> getRatings() throws IllegalArgumentException;

	/**
	 * Gibt die Bewertung anhand ihrer Id aus.
	 * 
	 * @param id
	 * @return Rating
	 * @throws IllegalArgumentException
	 */
	public Rating getRatingByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt die Bewertung einer spezischen Bewerbung aus.
	 * 
	 * @param applicationId
	 * @return Rating einer Bewerbung
	 * @throws IllegalArgumentException
	 */
	public Rating getRatingByApplicationId(int applicationId) throws IllegalArgumentException;

	/**
	 * Bewertung einer Bewerbung. Wenn die Bewertung einen "score" von 1
	 * aufweist wird automatisch eine Beteiligung erschaffen. Dadurch �ndert
	 * sich auch automatisch der Status der Bewerbung und der Ausschreibung.
	 * 
	 * @param score
	 * @param statement
	 * @param applicationId
	 * @param personId
	 * @param projectId
	 * @param jobPostingId
	 */
	void rateApplication(float score, String statement, int applicationId, int personId, int projectId,
			int jobPostingId);

	// ---------------------------------------- TEAM

	/**
	 * Erstellt ein neues Team-Objekt.
	 * 
	 * @return neu erstelltes Team-Objekt
	 * @throws IllegalArgumentException
	 */
	public Team addTeam(String name, String description, int teamSize) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Team-Objekt.
	 * 
	 * @return aktualisiertes Team-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateTeam(Team team) throws IllegalArgumentException;

	/**
	 * Löscht ein Team-Objekt und alle eventuell darauf basierenden Objekte.
	 * 
	 * @param team
	 * @throws IllegalArgumentException
	 */
	public void deleteTeam(Team team) throws IllegalArgumentException;

	/**
	 * Gibt das Team anhand seiner Id aus.
	 * 
	 * @param id
	 * @return PartnerProfile
	 * @throws IllegalArgumentException
	 */
	public Team getTeamByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Teams aus.
	 * 
	 * @return ArrayList aller Team-Objekte
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Team> getTeams() throws IllegalArgumentException;

	// ------------------------------------------ TRAIT

	/**
	 * Erstellt ein neues Trait-Objekt.
	 * 
	 * @return neu erstelltes Trait-Objekt
	 * @throws IllegalArgumentException
	 */
	public Trait addTrait(String name, String value, int partnerProfileId) throws IllegalArgumentException;

	/**
	 * Aktuallisiert ein Trait-Objekt.
	 * 
	 * @return aktualisiertes Trait-Objekt
	 * @throws IllegalArgumentException
	 */
	public void updateTrait(Trait trait) throws IllegalArgumentException;

	/**
	 * Löscht ein Trait-Objekt und alle eventuell darauf basierenden Objekte.
	 * 
	 * @param trait
	 * @throws IllegalArgumentException
	 */
	public void deleteTrait(Trait trait) throws IllegalArgumentException;

	/**
	 * Gibt alle vorhandenen Traits aus.
	 * 
	 * @return ArrayList aller Trait-Objekte
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Trait> getTraits() throws IllegalArgumentException;
	
	/**
	 * Gibt die Eigenschaften der Tabelle Eigenschaften aus, welche einem JobPosting zugeordnet sind
	 * 
	 * 
	 * @return PartnerProfile
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Trait> getTraitsFromJobPostings() throws IllegalArgumentException;

	/**
	 * Gibt die Eigenschaft anhand seiner Id aus.
	 * 
	 * @param id
	 * @return ArrayList aller Trait-Objekte
	 * @throws IllegalArgumentException
	 */
	public Trait getTraitByID(int id) throws IllegalArgumentException;

	/**
	 * Gibt alle Eigenschaften eines spezischen Partnerprofiles aus.
	 * 
	 * @param partnerProfileId
	 * @return ArrayList aller Trait-Objekte eines PartnerProfile-Objekts
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Trait> getTraitsByPartnerProfileId(int partnerProfileId) throws IllegalArgumentException;

	public ArrayList<JobPosting> getJobPostingsMatchingTraits(PartnerProfile pp) throws IllegalArgumentException;

	// --------------------------- LOGIN

	public Person login(String requestUri);




}