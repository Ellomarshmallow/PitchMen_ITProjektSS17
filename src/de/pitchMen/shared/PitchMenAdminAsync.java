package de.pitchMen.shared;

import java.util.ArrayList;
import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.pitchMen.shared.bo.*;

/**
 * Das asynchrone Gegenstück des Interface PitchMenAdmin. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt.
 * 
 * @author Eleonora Renz
 *
 */
public interface PitchMenAdminAsync {

	void init(AsyncCallback<Void> callback);

	// ---------- APPLICATION

	void addApplication(Date dateCreated, String text, String status, int jobPostingId,
			int partnerProfileId, AsyncCallback<Application> callback);

	void updateApplication(Application application, AsyncCallback<Void> callback);

	void deleteApplication(Application application, AsyncCallback<Void> callback);

	void getApplications(AsyncCallback<ArrayList<Application>> callback);

	void getApplicationByID(int id, AsyncCallback<Application> callback);

	void getApplicationsByPerson(int personId, AsyncCallback<ArrayList<Application>> callback);

	void getApplicationsByJobPostingId(int jobPostingId, AsyncCallback<ArrayList<Application>> callback);

	// ---------- COMPANY

	void addCompany(String name, String description, AsyncCallback<Company> callback);

	void updateCompany(Company company, AsyncCallback<Void> callback);

	void deleteCompany(Company company, AsyncCallback<Void> callback);

	void getCompanyByID(int id, AsyncCallback<Company> callback);

	void getCompanies(AsyncCallback<ArrayList<Company>> callback);

	// ---------- JOBPOSTING

	void addJobPosting(String title, String text, String status, Date deadline, int projectId,
			AsyncCallback<JobPosting> callback) throws IllegalArgumentException;

	void updateJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	void deleteJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	void getJobPostings(AsyncCallback<ArrayList<JobPosting>> callback);

	void getJobPostingByID(int id, AsyncCallback<JobPosting> callback);

	void getJobPostingsByProjectId(int projectId, AsyncCallback<ArrayList<JobPosting>> callback);
	
	void getJobPostingsByPersonId(int personId, AsyncCallback<ArrayList<JobPosting>> callback); 

	// ---------- MARKETPLACE

	void addMarketplaceByPerson(String title, String description, int personId, AsyncCallback<Marketplace> callback);

	void addMarketplaceByTeam(String title, String description, int teamId, AsyncCallback<Marketplace> callback);

	void addMarketplaceByCompany(String title, String description, int companyId, AsyncCallback<Marketplace> callback);

	void updateMarketplace(Marketplace m, AsyncCallback<Void> callback);

	void deleteMarketplace(Marketplace m, AsyncCallback<Void> callback);

	void getMarketplaces(AsyncCallback<ArrayList<Marketplace>> callback);

	void getMarketplaceByID(int id, AsyncCallback<Marketplace> callback);
	
	void getMarketplacesByPersonId(int personId, AsyncCallback<ArrayList<Marketplace>> callback);

	// ---------- PARTICIPATION

	void addParticipation(Date dateOpened, Date dateClosed, float workload, int projectId, int personId,
			AsyncCallback<Participation> callback);

	void updateParticipation(Participation participation, AsyncCallback<Void> callback);

	void deleteParticipation(Participation participation, AsyncCallback<Void> callback);

	void getParticipations(AsyncCallback<ArrayList<Participation>> callback);

	void getParticipationByID(int id, AsyncCallback<Participation> callback);

	void getParticipationsByPersonId(int personId, AsyncCallback<ArrayList<Participation>> callback);

	void getParticipationsByTeamId(int teamId, AsyncCallback<ArrayList<Participation>> callback);

	void getParticipationsByCompanyId(int companyId, AsyncCallback<ArrayList<Participation>> callback);

	// ---------- PARTNERPROFILE

	void addPartnerProfileForPerson(Date dateCreated, Date dateChanged, int personId,
			AsyncCallback<PartnerProfile> callback);

	void addPartnerProfileForTeam(Date dateCreated, Date dateChanged, int teamId, int personId,
			AsyncCallback<PartnerProfile> callback);

	void addPartnerProfileForCompany(Date dateCreated, Date dateChanged, int companyId, int personId,
			AsyncCallback<PartnerProfile> callback);

	void addPartnerProfileForJobPosting(Date dateCreated, Date dateChanged, int jobPostingId,
			AsyncCallback<PartnerProfile> callback);

	void updatePartnerProfile(PartnerProfile PartnerProfile, AsyncCallback<Void> callback);

	void deletePartnerProfile(PartnerProfile partnerProfile, AsyncCallback<Void> callback);

	void getPartnerProfiles(AsyncCallback<ArrayList<PartnerProfile>> callback);

	void getPartnerProfileByID(int id, AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileByCompanyId(int companyId, AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileByTeamId(int teamId, AsyncCallback<PartnerProfile> callback);

	void getPartnerProfileByPersonId(int personId, AsyncCallback<PartnerProfile> callback);

	void getPartnerProfilesByJobPostingId(int jobPostingId, AsyncCallback<PartnerProfile> callback);

	// ---------- PERSON

	void addPerson(String firstName, String name, String description, String emailAdress, String loginUrl, String logoutUrl,
			boolean loggedIn, boolean isExisting, AsyncCallback<Person> callback);

	void updatePerson(Person person, AsyncCallback<Void> callback);

	void deletePerson(Person person, AsyncCallback<Void> callback);

	void getPersonByID(int id, AsyncCallback<Person> callback);

	void getAllPeople(AsyncCallback<ArrayList<Person>> callback);
	
	void getPersonByApplicationId(int applicationId, AsyncCallback<Person> callback);

	// ---------- PROJECT

	void addProject(Date dateOpened, Date dateClosed, String title, String description, int personId, int marketplaceId,
			AsyncCallback<Project> callback);

	void updateProject(Project p, AsyncCallback<Void> callback);

	void deleteProject(Project project, AsyncCallback<Void> callback);

	void getProjects(AsyncCallback<ArrayList<Project>> callback);

	void getProjectByID(int id, AsyncCallback<Project> callback);

	void getProjectsByMarketplaceId(int marketplaceId, AsyncCallback<ArrayList<Project>> callback);

	void getProjectsByPerson(int personId, AsyncCallback<ArrayList<Project>> callback);

	// ---------- RATING

	void addRating(String statement, float scrore, int applicationId, AsyncCallback<Rating> callback);

	void updateRating(Rating rating, AsyncCallback<Void> callback);

	void deleteRating(Rating rating, AsyncCallback<Void> callback);

	void getRatings(AsyncCallback<ArrayList<Rating>> callback);

	void getRatingByID(int id, AsyncCallback<Rating> callback);

	void getRatingByApplicationId(int applicationId, AsyncCallback<Rating> callback);

	void rateApplication(float score, String statement, int applicationId, int personId, int projectId,
			int jobPostingId, AsyncCallback<Void> callback);

	// ---------- TEAM

	void addTeam(String name, String description, int teamSize, AsyncCallback<Team> callback);

	void updateTeam(Team team, AsyncCallback<Void> callback);

	void deleteTeam(Team team, AsyncCallback<Void> callback);

	void getTeamByID(int id, AsyncCallback<Team> callback);

	void getTeams(AsyncCallback<ArrayList<Team>> callback);

	// ---------- TRAIT

	void addTrait(String name, String value, int partnerProfileId, AsyncCallback<Trait> callback);

	void updateTrait(Trait Trait, AsyncCallback<Void> callback);

	void deleteTrait(Trait trait, AsyncCallback<Void> callback);

	void getTraits(AsyncCallback<ArrayList<Trait>> callback);

	void getTraitByID(int id, AsyncCallback<Trait> callback);

	void getTraitsByPartnerProfileId(int partnerProfileId, AsyncCallback<ArrayList<Trait>> callback);

	void getJobPostingsMatchingTraits(PartnerProfile pp, AsyncCallback<ArrayList<JobPosting>> callback);

	// --------------------------- LOGIN

	void login(String requestUri, AsyncCallback<Person> callback);

}