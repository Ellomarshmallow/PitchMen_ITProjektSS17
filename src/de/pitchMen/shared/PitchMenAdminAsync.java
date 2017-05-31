package de.pitchMen.shared;

import java.util.ArrayList;
import java.util.Date;

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

	// ---------- MARKETPLACE

	void addMarketplace(String title, String description, ArrayList<OrganisationUnit> organisationUnits,
			ArrayList<Project> projects, AsyncCallback<Marketplace> callback);

	void updateMarketplace(Marketplace m, AsyncCallback<Void> callback);

	void deleteMarketplace(Marketplace m, AsyncCallback<Void> callback);

	void getMarketplaces(AsyncCallback<ArrayList<Marketplace>> callback);

	void getMarketplaceByID(int id, AsyncCallback<Marketplace> callback);

	void setMarketplaces(Marketplace m, AsyncCallback<Void> callback);

	// ---------- PROJECT

	void addProject(Date dateOpened, Date dateClosed, String title, String description, Person manager,
			ArrayList<JobPosting> jobPostings, ArrayList<Participation> participation, AsyncCallback<Project> callback);

	void updateProject(Project p, AsyncCallback<Void> callback);

	void deleteProject(Project p, AsyncCallback<Void> callback);

	void getProject(AsyncCallback<ArrayList<Project>> callback);

	void setProject(Project p, AsyncCallback<Void> callback);

	void getProjectByID(int id, AsyncCallback<Project> callback);

	// ---------- JOBPOSTING

	void addJobPosting(String title, String text, Date deadline, PartnerProfile partnerprofile, Person recruiter,
			ArrayList<Application> applications, AsyncCallback<JobPosting> callback) throws IllegalArgumentException;

	void updateJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	void deleteJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	void getJobPostings(AsyncCallback<ArrayList<JobPosting>> callback);

	void getJobPostingByID(int id, AsyncCallback<JobPosting> callback);

	void setJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	// ---------- PARTNERPROFILE

	void addPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit, Date dateCreated,
			Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting,
			AsyncCallback<PartnerProfile> callback);

	void updatePartnerProfile(PartnerProfile PartnerProfile, AsyncCallback<Void> callback);
	
	void deletePartnerProfile(PartnerProfile partnerProfile, AsyncCallback<Void> callback);
	
	void getPartnerProfiles(AsyncCallback<ArrayList<PartnerProfile>> callback);

	void getPartnerProfileByID(int id, AsyncCallback<PartnerProfile> callback);

	// ---------- TRAIT

	void addTrait(String name, String value, AsyncCallback<Trait> callback);

	void updateTrait(Trait Trait, AsyncCallback<Void> callback);

	void deleteTrait(Trait trait, AsyncCallback<Void> callback);

	void getTraits(AsyncCallback<ArrayList<Trait>> callback);

	void getTraitByID(int id, AsyncCallback<Trait> callback);

	// ---------- RATING

	void deleteRating(Rating rating, AsyncCallback<Void> callback);

	// ---------- PARTICIPATION

	void deleteParticipation(Participation participation, AsyncCallback<Void> callback);

}