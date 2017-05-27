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

	void createMarketplace(String title, String description, ArrayList<OrganisationUnit> organisationUnits,
			ArrayList<Project> projects, AsyncCallback<Marketplace> callback);

	void addMarketplace(Marketplace marketplace, AsyncCallback<Void> callback);

	void deleteMarketplace(Marketplace m, AsyncCallback<Void> callback);

	void getMarketplaces(AsyncCallback<ArrayList<Marketplace>> callback);

	void setMarketplaces(Marketplace m, AsyncCallback<Void> callback);

	// ---------- PROJECT

	void createProject(Date dateOpened, Date dateClosed, String title, String description, Person manager,
			ArrayList<JobPosting> jobPostings, ArrayList<Participation> participation, AsyncCallback<Project> callback);

	void addProject(Project p, AsyncCallback<Void> callback);

	void deleteProject(Project p, AsyncCallback<Void> callback);

	void getProject(AsyncCallback<ArrayList<Project>> callback);

	void setProject(Project p, AsyncCallback<Void> callback);

	// ---------- JOBPOSTING

	void createJobPosting(String title, String text, Date deadline, PartnerProfile partnerprofile, Person recruiter,
			ArrayList<Application> applications, AsyncCallback<JobPosting> callback) throws IllegalArgumentException;

	void addJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	void deleteJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	void getJobPosting(AsyncCallback<ArrayList<JobPosting>> callback);

	void setJobPosting(JobPosting jobPosting, AsyncCallback<Void> callback);

	// ---------- PARTNERPROFILE

	void createPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit, Date dateCreated,
			Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting,
			AsyncCallback<PartnerProfile> callback);

	void deletePartnerProfile(PartnerProfile partnerProfile, AsyncCallback<Void> callback);

	// ---------- TRAIT

	void createTrait(String name, String value, AsyncCallback<Trait> callback);

	void addTrait(Trait Trait, AsyncCallback<Void> callback);

	void deleteTrait(Trait trait, AsyncCallback<Void> callback);

	// ---------- RATING

	void deleteRating(Rating rating, AsyncCallback<Void> callback);

	// ---------- PARTICIPATION

	void deleteParticipation(Participation participation, AsyncCallback<Void> callback);

}