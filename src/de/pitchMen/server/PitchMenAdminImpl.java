package de.pitchMen.server;

import java.util.ArrayList;
import java.sql.Date;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.server.db.*;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.bo.*;

/**
 * Implemetierungsklasse des Interface PitchMenAdmin. Sie enth�lt die
 * Applikationslogik, stellt die Zusammenh�nge konstistent dar und ist
 * zust�ndig f�r einen geordneten Ablauf.
 * 
 * @author Eleonora Renz
 */
public class PitchMenAdminImpl extends RemoteServiceServlet implements PitchMenAdmin {

	public PitchMenAdminImpl() throws IllegalArgumentException {
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Referenzen auf die DatenbankMapper, die Objekte mit der Datenbank
	 * abgleicht.
	 */
	private MarketplaceMapper marketplaceMapper = null;

	private ApplicationMapper applicationMapper = null;

	private CompanyMapper companyMapper = null;

	private JobPostingMapper jobPostingMapper = null;

	private ParticipationMapper participationMapper = null;

	private PartnerProfileMapper partnerProfileMapper = null;

	private PersonMapper personMapper = null;

	private ProjectMapper projectMapper = null;

	private RatingMapper ratingMapper = null;

	private TeamMapper teamMapper = null;

	private TraitMapper traitMapper = null;

	@Override
	public void init() throws IllegalArgumentException {

		/*
		 * Vollständiger Satz von Mappern mit deren Hilfe PitchMenAdminImpl
		 * dann mit der Datenbank kommunizieren kann.
		 */

		this.marketplaceMapper = MarketplaceMapper.marketplaceMapper();
		this.applicationMapper = ApplicationMapper.applicationMapper();
		this.companyMapper = CompanyMapper.companyMapper();
		this.jobPostingMapper = JobPostingMapper.jobPostingMapper();
		this.partnerProfileMapper = PartnerProfileMapper.partnerProfileMapper();
		this.personMapper = PersonMapper.personMapper();
		this.projectMapper = ProjectMapper.projectMapper();
		this.ratingMapper = RatingMapper.ratingMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.traitMapper = TraitMapper.traitMapper();

	}

	// -------------------------- APPLICATION

	@Override
	public Application addApplication(Date dateCreated, String text, Rating rating, String status, int jobPostingId,
			int partnerProfileId) {
		Application application = new Application();
		application.setDateCreated(dateCreated);
		application.setText(text);
		application.setStatus(status);
		application.setJobPostingId(jobPostingId);
		application.setPartnerProfileId(partnerProfileId);
		application.setRating(rating);

		return this.applicationMapper.insert(application);

	}

	@Override
	public void updateApplication(Application application) throws IllegalArgumentException {
		applicationMapper.update(application);
	}

	@Override
	public void deleteApplication(Application application) throws IllegalArgumentException {
		Rating rating = this.getRatingByApplicationId(application.getId());

		if (rating != null) {
			this.ratingMapper.delete(rating);
		}

		this.applicationMapper.delete(application);
	}

	@Override
	public ArrayList<Application> getApplications() throws IllegalArgumentException {
		return this.applicationMapper.findAll();
	}

	@Override
	public ArrayList<Application> getApplicationsByPerson(int personId) throws IllegalArgumentException {
		return this.applicationMapper.findApplicationsByPersonId(personId);
	}

	@Override
	public Application getApplicationByID(int id) throws IllegalArgumentException {
		return this.applicationMapper.findById(id);
	}

	@Override
	public ArrayList<Application> getApplicationsByJobPostingId(int jobPostingId) throws IllegalArgumentException {
		return this.applicationMapper.findApplicationsByJobPostingId(jobPostingId);
	}

	// --------------------------- COMPANY

	@Override
	public Company addCompany() throws IllegalArgumentException {
		Company company = new Company();

		return this.companyMapper.insert(company);
	}

	@Override
	public void updateCompany(Company company) throws IllegalArgumentException {
		companyMapper.update(company);
	}

	@Override
	public void deleteCompany(Company company) throws IllegalArgumentException {
		ArrayList<Participation> participations = this.getParticipationsByCompanyId(company.getId());
		PartnerProfile partnerProfile = this.getPartnerProfileByPersonId(company.getId());

		if (participations != null) {
			for (Participation participation : participations) {
				this.participationMapper.delete(participation);
			}
		}

		if (partnerProfile != null) {
			this.partnerProfileMapper.delete(partnerProfile);
		}

		this.companyMapper.delete(company);
	}

	@Override
	public Company getCompanyByID(int id) throws IllegalArgumentException {
		return this.companyMapper.findById(id);
	}

	@Override
	public ArrayList<Company> getCompanies() throws IllegalArgumentException {
		return this.companyMapper.findAll();

	}

	// --------------------------- JOBPOSTNG

	@Override
	public JobPosting addJobPosting(String title, String text, String status, Date deadline, int projectId)
			throws IllegalArgumentException {
		JobPosting jobPosting = new JobPosting();
		jobPosting.setTitle(title);
		jobPosting.setText(text);
		jobPosting.setStatus(status);
		jobPosting.setDeadline(deadline);
		jobPosting.setProjectId(projectId);

		return this.jobPostingMapper.insert(jobPosting);

	}

	@Override
	public void updateJobPosting(JobPosting jobPosting) throws IllegalArgumentException {

		jobPostingMapper.update(jobPosting);

	}

	@Override
	public void deleteJobPosting(JobPosting jobPosting) throws IllegalArgumentException {
		ArrayList<Application> applications = this.getApplicationsByJobPostingId(jobPosting.getId());
		PartnerProfile partnerProfile = this.getPartnerProfilesByJobPostingId(jobPosting.getId());

		if (applications != null) {
			for (Application application : applications) {
				this.applicationMapper.delete(application);
			}
		}

		if (partnerProfile != null) {
			this.partnerProfileMapper.delete(partnerProfile);
		}

		this.jobPostingMapper.delete(jobPosting);
	}

	@Override
	public ArrayList<JobPosting> getJobPostings() throws IllegalArgumentException {

		return this.jobPostingMapper.findAll();

	}

	@Override
	public JobPosting getJobPostingByID(int id) throws IllegalArgumentException {
		return this.jobPostingMapper.findById(id);
	}

	@Override
	public ArrayList<JobPosting> getJobPostingsByProjectId(int projectId) throws IllegalArgumentException {
		return this.jobPostingMapper.findJobPostingsByProjectId(projectId);
	}

	// --------------------------- MARKETPLACE

	// TODO
	@Override
	public Marketplace addMarketplaceByPerson(String title, String description, int personId)
			throws IllegalArgumentException {
		Marketplace marketplace = new Marketplace();
		marketplace.setTitle(title);
		marketplace.setDescription(description);
		marketplace.setPersonId(personId);

		return this.marketplaceMapper.insert(marketplace);
	}

	@Override
	public Marketplace addMarketplaceByTeam(String title, String description, int teamId)
			throws IllegalArgumentException {
		Marketplace marketplace = new Marketplace();
		marketplace.setTitle(title);
		marketplace.setDescription(description);
		marketplace.setTeamId(teamId);

		return this.marketplaceMapper.insert(marketplace);
	}

	@Override
	public Marketplace addMarketplaceByCompany(String title, String description, int companyId)
			throws IllegalArgumentException {
		Marketplace marketplace = new Marketplace();
		marketplace.setTitle(title);
		marketplace.setDescription(description);
		marketplace.setCompanyId(companyId);

		return this.marketplaceMapper.insert(marketplace);
	}

	@Override
	public void updateMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		marketplaceMapper.update(marketplace);

	}

	@Override
	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		ArrayList<Project> projects = this.getProjectsByMarketplaceId(marketplace.getId());

		if (projects != null) {
			for (Project project : projects) {
				this.projectMapper.delete(project);
			}
		}

		this.marketplaceMapper.delete(marketplace);
	}

	@Override
	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException {

		return this.marketplaceMapper.findAll();

	}

	@Override
	public Marketplace getMarketplaceByID(int id) throws IllegalArgumentException {
		return this.marketplaceMapper.findById(id);

	}

	// --------------------------- PARTICIPATION

	@Override
	public Participation addParticipation(Date dateOpened, Date dateClosed, float workload, int projectId, int personId)
			throws IllegalArgumentException {
		Participation participation = new Participation();
		participation.setDateClosed(dateClosed);
		participation.setDateOpened(dateOpened);
		participation.setWorkload(workload);
		participation.setPersonId(personId);
		participation.setProjectId(projectId);

		return this.participationMapper.insert(participation);

	}

	@Override
	public void updateParticipation(Participation participation) throws IllegalArgumentException {
		this.participationMapper.update(participation);
	}

	@Override
	public void deleteParticipation(Participation participation) throws IllegalArgumentException {
		this.participationMapper.delete(participation);
	}

	@Override
	public ArrayList<Participation> getParticipations() throws IllegalArgumentException {
		return this.participationMapper.findAll();
	}

	@Override
	public Participation getParticipationByID(int id) throws IllegalArgumentException {
		return this.participationMapper.findById(id);
	}

	@Override
	public ArrayList<Participation> getParticipationsByPersonId(int personId) throws IllegalArgumentException {
		return this.participationMapper.findParticipationsByPersonId(personId);
	}

	@Override
	public ArrayList<Participation> getParticipationsByTeamId(int teamId) throws IllegalArgumentException {
		return this.participationMapper.findParticipationsByTeamId(teamId);
	}

	@Override
	public ArrayList<Participation> getParticipationsByCompanyId(int companyId) throws IllegalArgumentException {
		return this.participationMapper.findParticipationsByCompanyId(companyId);
	}

	// --------------------------- PARTNERPROFILE

	@Override
	public PartnerProfile addPartnerProfileForJobPosting(Date dateCreated, Date dateChanged, int jobPostingId)
			throws IllegalArgumentException {
		PartnerProfile partnerProfile = new PartnerProfile();
		partnerProfile.setDateCreated(dateCreated);
		partnerProfile.setDateChanged(dateChanged);
		partnerProfile.setJobPostingId(jobPostingId);

		return this.partnerProfileMapper.insert(partnerProfile);
	}

	@Override
	public PartnerProfile addPartnerProfileForPerson(Date dateCreated, Date dateChanged, int personId)
			throws IllegalArgumentException {
		PartnerProfile partnerProfile = new PartnerProfile();
		partnerProfile.setDateCreated(dateCreated);
		partnerProfile.setDateChanged(dateChanged);
		partnerProfile.setPersonId(personId);

		return this.partnerProfileMapper.insert(partnerProfile);
	}

	@Override
	public PartnerProfile addPartnerProfileForTeam(Date dateCreated, Date dateChanged, int teamId)
			throws IllegalArgumentException {
		PartnerProfile partnerProfile = new PartnerProfile();
		partnerProfile.setDateCreated(dateCreated);
		partnerProfile.setDateChanged(dateChanged);
		partnerProfile.setTeamId(teamId);

		return this.partnerProfileMapper.insert(partnerProfile);
	}

	@Override
	public PartnerProfile addPartnerProfileForCompany(Date dateCreated, Date dateChanged, int companyId)
			throws IllegalArgumentException {
		PartnerProfile partnerProfile = new PartnerProfile();
		partnerProfile.setDateCreated(dateCreated);
		partnerProfile.setDateChanged(dateChanged);
		partnerProfile.setCompanyId(companyId);

		return this.partnerProfileMapper.insert(partnerProfile);
	}

	@Override
	public void updatePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException {

		this.partnerProfileMapper.update(partnerProfile);

	}

	@Override
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException {
		ArrayList<Trait> traits = this.getTraitsByPartnerProfileId(partnerProfile.getId());

		if (traits != null) {
			for (Trait trait : traits) {
				this.traitMapper.delete(trait);
			}
		}

		this.partnerProfileMapper.delete(partnerProfile);

	}

	@Override
	public ArrayList<PartnerProfile> getPartnerProfiles() throws IllegalArgumentException {

		return this.partnerProfileMapper.findAll();

	}

	@Override
	public PartnerProfile getPartnerProfileByID(int id) throws IllegalArgumentException {

		return this.partnerProfileMapper.findById(id);

	}

	@Override
	public PartnerProfile getPartnerProfilesByJobPostingId(int jobPostingId) throws IllegalArgumentException {
		return this.partnerProfileMapper.findPartnerProfileByJobPostingId(jobPostingId);
	}

	@Override
	public PartnerProfile getPartnerProfileByPersonId(int personId) throws IllegalArgumentException {
		return this.partnerProfileMapper.findPartnerProfileByPersonId(personId);
	}

	@Override
	public PartnerProfile getPartnerProfileByTeamId(int teamId) throws IllegalArgumentException {
		return this.partnerProfileMapper.findPartnerProfileByTeamId(teamId);
	}

	@Override
	public PartnerProfile getPartnerProfileByCompanyId(int companyId) throws IllegalArgumentException {
		return this.partnerProfileMapper.findPartnerProfileByCompanyId(companyId);
	}

	// -------------------------------- PERSON

	@Override
	public Person addPerson(String firstName, String name, String emailAdress, String loginUrl, String logoutUrl,
			boolean loggedIn, boolean isExisting) throws IllegalArgumentException {
		Person person = new Person();

		person.setFirstName(firstName);
		person.setName(name);
		person.setEmailAdress(emailAdress);
		person.setLoginUrl(loginUrl);
		person.setLogoutUrl(logoutUrl);
		person.setLoggedIn(loggedIn);
		person.setIsExisting(isExisting);

		return this.personMapper.insert(person);

	}

	@Override
	public void updatePerson(Person person) throws IllegalArgumentException {
		personMapper.update(person);
	}

	@Override
	public void deletePerson(Person person) throws IllegalArgumentException {
		ArrayList<Participation> participations = this.getParticipationsByPersonId(person.getId());
		PartnerProfile partnerProfile = this.getPartnerProfileByPersonId(person.getId());

		if (participations != null) {
			for (Participation participation : participations) {
				this.participationMapper.delete(participation);
			}
		}

		if (partnerProfile != null) {
			this.partnerProfileMapper.delete(partnerProfile);
		}

		this.personMapper.delete(person);
	}

	@Override
	public Person getPersonByID(int id) throws IllegalArgumentException {
		return this.personMapper.findById(id);
	}

	@Override
	public ArrayList<Person> getAllPeople() throws IllegalArgumentException {
		return this.personMapper.findAll();
	}

	// --------------------------- PROJECT

	@Override
	public Project addProject(Date dateOpened, Date dateClosed, String title, String description, int personId,
			int marketplaceId) throws IllegalArgumentException {
		Project project = new Project();
		project.setDateOpened(dateOpened);
		project.setDateClosed(dateClosed);
		project.setTitle(title);
		project.setDescription(description);
		project.setPersonId(personId);
		project.setMarketplaceId(marketplaceId);

		return this.projectMapper.insert(project);
	}

	@Override
	public void updateProject(Project project) throws IllegalArgumentException {

		projectMapper.update(project);

	}

	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {
		ArrayList<JobPosting> jobPostings = this.getJobPostingsByProjectId(project.getId());

		if (jobPostings != null) {
			for (JobPosting jobPosting : jobPostings) {
				this.jobPostingMapper.delete(jobPosting);
			}
		}

		this.projectMapper.delete(project);
	}

	@Override
	public ArrayList<Project> getProjects() throws IllegalArgumentException {
		return this.projectMapper.findAll();

	}

	@Override
	public Project getProjectByID(int id) throws IllegalArgumentException {
		return this.projectMapper.findById(id);

	}

	@Override
	public ArrayList<Project> getProjectsByMarketplaceId(int marketplaceId) throws IllegalArgumentException {
		return this.projectMapper.findProjectsByMarketplaceId(marketplaceId);
	}

	@Override
	public ArrayList<Project> getProjectsByPerson(int personId) throws IllegalArgumentException {
		return this.projectMapper.findProjectsByPersonId(personId);
	}
	// --------------------------- RATING

	@Override
	public Rating addRating(String statement, float score, int applicationId) throws IllegalArgumentException {
		Rating rating = new Rating();
		rating.setStatement(statement);
		rating.setScore(score);
		rating.setApplicationId(applicationId);

		return this.ratingMapper.insert(rating);
	}

	@Override
	public void updateRating(Rating rating) throws IllegalArgumentException {
		ratingMapper.update(rating);
	}

	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		this.ratingMapper.delete(rating);
	}

	@Override
	public ArrayList<Rating> getRatings() throws IllegalArgumentException {
		return this.ratingMapper.findAll();
	}

	@Override
	public Rating getRatingByID(int id) throws IllegalArgumentException {
		return this.ratingMapper.findById(id);
	}

	@Override
	public Rating getRatingByApplicationId(int applicationId) throws IllegalArgumentException {
		return this.ratingMapper.findRatingByApplicationId(applicationId);
	}

	@Override
	public void rateApplication(float score, String statement, int applicationId, int personId, int projectId,
			int jobPostingId) throws IllegalArgumentException {
		// FIXME nicht sicher ob die Methode funktioniert
		Rating rating = new Rating(score, statement, applicationId);
		this.ratingMapper.insert(rating);

		if (score == 1) {
			Participation participation = new Participation(projectId, personId);
			Application application = this.getApplicationByID(applicationId);
			JobPosting jobPosting = this.getJobPostingByID(jobPostingId);
			this.participationMapper.insert(participation);
			this.applicationMapper.update(application);
			this.jobPostingMapper.update(jobPosting);
		}

	}

	// --------------------------- TEAM

	@Override
	public Team addTeam() throws IllegalArgumentException {
		Team team = new Team();

		return this.teamMapper.insert(team);
	}

	@Override
	public void updateTeam(Team team) throws IllegalArgumentException {
		teamMapper.update(team);
	}

	@Override
	public void deleteTeam(Team team) throws IllegalArgumentException {
		ArrayList<Participation> participations = this.getParticipationsByTeamId(team.getId());
		PartnerProfile partnerProfile = this.getPartnerProfileByTeamId(team.getId());

		if (participations != null) {
			for (Participation participation : participations) {
				this.participationMapper.delete(participation);
			}
		}

		if (partnerProfile != null) {
			this.partnerProfileMapper.delete(partnerProfile);
		}

		this.teamMapper.delete(team);
	}

	@Override
	public Team getTeamByID(int id) throws IllegalArgumentException {
		return this.teamMapper.findById(id);
	}

	@Override
	public ArrayList<Team> getTeams() throws IllegalArgumentException {
		return this.teamMapper.findAll();
	}

	// ------------------------------ TRAIT

	@Override
	public Trait addTrait(String name, String value, int partnerProfileId) throws IllegalArgumentException {
		Trait trait = new Trait();
		trait.setName(name);
		trait.setValue(value);
		trait.setPartnerProfileId(partnerProfileId);

		return this.traitMapper.insert(trait);
	}

	@Override
	public void updateTrait(Trait trait) throws IllegalArgumentException {
		traitMapper.update(trait);

	}

	@Override
	public void deleteTrait(Trait trait) throws IllegalArgumentException {
		this.traitMapper.delete(trait);
	}

	@Override
	public ArrayList<Trait> getTraits() throws IllegalArgumentException {

		return this.traitMapper.findAll();

	}

	@Override
	public Trait getTraitByID(int id) throws IllegalArgumentException {

		return this.traitMapper.findById(id);

	}

	@Override
	public ArrayList<Trait> getTraitsByPartnerProfileId(int partnerProfileId) throws IllegalArgumentException {
		return this.traitMapper.findTraitByPartnerProfileId(partnerProfileId);
	}

	// --------------------------- LOGIN

	public Person login(String requestUri) {

		ClientsideSettings.getLogger().info("login()-Methode wurde aufgerufen.");

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Person logInf = new Person();

		/*
		 * Ist der User angemeldet, hat der Methodenaufruf von
		 * userService.getCurrentUser() ein vollwertiges User-Objekt
		 * zurückgegeben. Ist der Nutzer nicht angemeldet, gibt die Methode
		 * null zurück. Wir springen dann in den else-Block.
		 */
		if (user != null) {
			// Der Nutzer hat die erste Hürde genommen und sich angemeldet

			ClientsideSettings.getLogger().info("User-Objekt ist nicht null.");

			/*
			 * Wir fragen zunächst bei der Datenbank an, ob der Nutzer, der
			 * sich gerade angemeldet hat, bereits vorhanden ist.
			 */
			Person existingPerson = personMapper.findByEmail(user.getEmail());

			/*
			 * Hat der Mapper ein passendes Person-Objekt gefunden, gibt er
			 * dieses zurück. Ansonsten returnt er null. Darauf basierend
			 * können wir folgende Fallunterscheidung vornehemen:
			 */
			if (existingPerson != null) {
				// Der Nutzer ist dem System bereits bekannt.
				ClientsideSettings.getLogger().info("User mit der E-Mai-Adresse [" + user.getEmail() + "]  existiert.");

				/*
				 * Hier werden nun noch alle Attribute gesetzt, die in der
				 * Datenbank nicht gespeichert sind.
				 */

				// der Nutzer ist eingeloggt
				existingPerson.setLoggedIn(true);
				// über diese URL kann er sich ausloggen
				existingPerson.setLogoutUrl(userService.createLogoutURL(requestUri));
				// außerdem existiert er bereits. Dieser Wert sagt der GUI:
				// lade die eigentliche Applikation
				existingPerson.setIsExisting(true);

				return existingPerson;

			}

			// Hier landet das Programm, wenn der Nutzer angemeldet, aber noch
			// unbekannt ist
			logInf.setLoggedIn(true);
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
			logInf.setEmailAdress(user.getEmail());
			// Der GUI wird mit diesem Wert mitgeteilt, dass der Nutzer erst
			// seine Daten eingeben muss
			logInf.setIsExisting(false);
		} else {
			// Hier landen wir wenn der Nutzer nicht angemeldet ist

			ClientsideSettings.getLogger().info("User-Objekt ist null.");

			/*
			 * Mit dem setzen dieses Wertes auf false teilen wir der GUI mit,
			 * dass der Nutzer sich erst anmelden muss. Diese erzeugt daraufhin
			 * ein Overlay mit einem Link, der den Nutzer zum Anmeldeformular
			 * weiterleitet.
			 */
			logInf.setLoggedIn(false);

			/*
			 * Dem per Callback an die GUI weitergereichten Person-Obbjekt
			 * logInf wird eine LoginURL mitgegeben. Diese wird zum Ziel des
			 * Links im Overlay. Darüber meldet sich der Nutzer an und kehrt
			 * dann auf die Seite zurück. Wieder wird diese login()- Methode
			 * aufgerufen, nun ist der User aber nicht mehr null. Jetzt geht es
			 * im if-Block "weiter".
			 */
			logInf.setLoginUrl(userService.createLoginURL(requestUri));
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
		}
		return logInf;
	}

	// -------------------------TRAITMATCHING METHODE

	public ArrayList<JobPosting> getJobPostingsMatchingTraits(PartnerProfile pp) {

		ArrayList<Trait> personTraits = traitMapper.findTraitByPartnerProfileId(pp.getId());

		ArrayList<PartnerProfile> allpps = partnerProfileMapper.findAll();

		ArrayList<JobPosting> matchingTraits = new ArrayList<JobPosting>();

		for (PartnerProfile pprofile : allpps) {
			ArrayList<Trait> jPTraits = traitMapper.findTraitByPartnerProfileId(pprofile.getId());

			for (Trait trait : jPTraits) {
				String traitJp = trait.getName();

				for (Trait pTrait : personTraits) {
					String traitPerson = pTrait.getName();

					if (traitJp == traitPerson) {
						matchingTraits.add(this.getJobPostingByID(pprofile.getJobPostingId()));
					}
				}
			}
		}

		return matchingTraits;

	};

}