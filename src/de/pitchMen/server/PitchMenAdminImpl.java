package de.pitchMen.server;

import java.util.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.server.db.*;
import de.pitchMen.shared.*;
import de.pitchMen.shared.bo.*;

/**
 * Implemetierungsklasse des Interface PitchMenAdmin. Sie enth�lt die
 * Applikationslogik, stellt die Zusammenh�nge konstistent dar und ist
 * zust�ndig f�r einen geordneten Ablauf.
 * 
 * @author Eleonora Renz
 */
public class PitchMenAdminImpl extends RemoteServiceServlet implements PitchMenAdmin {

	/**
	 * Default constructor
	 */
	public PitchMenAdminImpl() throws IllegalArgumentException {
	}

	private ArrayList<Marketplace> marketplaces = null;

	private Project project = null;

	private Trait trait = null;

	private static final long serialVersionUID = 1L;

	/**
	 * Referenz auf den DatenbankMapper, der Marketplaceobjekte mit der
	 * Datenbank abgleicht.
	 */
	private MarketplaceMapper marketplaceMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Applicationobjekte mit der
	 * Datenbank abgleicht.
	 */
	private ApplicationMapper applicationMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Companyobjekte mit der Datenbank
	 * abgleicht.
	 */
	private CompanyMapper companyMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der JobPostingobjekte mit der Datenbank
	 * abgleicht.
	 */
	private JobPostingMapper jobPostingMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der PartnerProfileobjekte mit der
	 * Datenbank abgleicht.
	 */
	private PartnerProfileMapper partnerProfileMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Personobjekte mit der Datenbank
	 * abgleicht.
	 */
	private PersonMapper personMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Projectobjekte mit der Datenbank
	 * abgleicht.
	 */
	private ProjectMapper projectMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Ratingobjekte mit der Datenbank
	 * abgleicht.
	 */
	private RatingMapper ratingMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Teamobjekte mit der Datenbank
	 * abgleicht.
	 */
	private TeamMapper teamMapper = null;

	/**
	 * Referenz auf den DatenbankMapper, der Traitobjekte mit der Datenbank
	 * abgleicht.
	 */
	private TraitMapper traitMapper = null;

	@Override
	public void init() throws IllegalArgumentException {
		/*
		 * Ganz wesentlich ist, dass die PitchMenAdmin einen vollständigen Satz
		 * von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank
		 * kommunizieren kann.
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
	public Application addApplication(Date dateCreated, String text, int jobPostingId, int partnerProfileId) {
		Application application = new Application();
		application.setDateCreated(dateCreated);
		application.setText(text);
		application.setJobPostingId(jobPostingId);
		application.setPartnerProfileId(partnerProfileId);

		return this.applicationMapper.insert(application);

	}

	@Override
	public void updateApplication(Application application) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteApplication(Application application) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Application> getApplications() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Application> getApplicationsByPerson(Person p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return applicationMapper.findByPerson(p);
		
	
		}
		
			
	@Override
	public Application getApplicationByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------- COMPANY

	@Override
	public Company addCompany() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCompany(Company company) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCompany(Company company) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Company getCompanyByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
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

	/**
	 * Speichert ein Projekt
	 */
	@Override
	public void updateProject(Project project) throws IllegalArgumentException {

		projectMapper.update(project);

	}

	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {

		// ArrayList<Projects> projects = this.get
	}

	/**
	 * Auslesen aller Projekte
	 */
	@Override
	public ArrayList<Project> getProject() throws IllegalArgumentException {
		return this.projectMapper.findAll();

	}

	/**
	 * Auslesen eines Projekts anhand seiner ID
	 */
	@Override
	public Project getProjectByID(int id) throws IllegalArgumentException {
		return this.projectMapper.findById(id);

	}

	/**
	 * Auslesen aller Projekte der übergeben Person
	 * 
	 * @Override public Project getProjectsOf(Person person) throws
	 *           IllegalArgumentException { try { return this.projectMapper. }
	 *           catch (ClassNotFoundException e) {
	 * 
	 *           e.printStackTrace();
	 * 
	 *           } return null;
	 * 
	 *           }
	 */

	@Override
	public void setProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	// --------------------------- MARKETPLACE

	@Override
	public Marketplace addMarketplace(String title, String description, int personId, int teamId, int companyId ) throws IllegalArgumentException {
		Marketplace marketplace = new Marketplace();
		marketplace.setTitle(title);
		marketplace.setDescription(description);

		// Objekt in der DB speichern
		return this.marketplaceMapper.insert(marketplace);
	}

	/**
	 * Speichert einen Marktplatz
	 */
	@Override
	public void updateMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		marketplaceMapper.update(marketplace);

	}

	@Override
	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMarketplaces(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	/**
	 * Auslesen aller Marktpl�tze
	 */
	@Override
	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException {

		return this.marketplaceMapper.findAll();

	}

	/**
	 * Auslesen eines Marketplatzes anhand seiner ID
	 */
	@Override
	public Marketplace getMarketplaceByID(int id) throws IllegalArgumentException {
		return this.marketplaceMapper.findById(id);

	}

	// --------------------------- TRAIT

	@Override
	public Trait addTrait(String name, String value) throws IllegalArgumentException {
		Trait trait = new Trait();
		trait.setName(name);
		trait.setValue(value);

		/*
		 * Setzen einer vorläufigen Kundennr. Der insert-Aufruf liefert dann
		 * ein Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		trait.setId(1);

		// Objekt in der DB speichern.
		return this.traitMapper.insert(trait);
	}

	@Override
	public void updateTrait(Trait trait) throws IllegalArgumentException {
		traitMapper.update(trait);

	}

	@Override
	public void deleteTrait(Trait trait) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	/**
	 * Auslesen aller Eigenschaften
	 */
	@Override
	public ArrayList<Trait> getTraits() throws IllegalArgumentException {

		return this.traitMapper.findAll();

	}

	/**
	 * Auslesen einer Eigenschaft anhand seiner ID
	 */
	@Override
	public Trait getTraitByID(int id) throws IllegalArgumentException {

		return this.traitMapper.findById(id);

	}
	// --------------------------- JOBPOSTNG

	@Override
	public JobPosting addJobPosting(String title, String text, Date deadline, int projectId)
			throws IllegalArgumentException {
		JobPosting jobPosting = new JobPosting();
		jobPosting.setTitle(title);
		jobPosting.setText(text);
		jobPosting.setDeadline(deadline);
		jobPosting.setProjectId(projectId);

		// Objekt in der DB speichern.

		return this.jobPostingMapper.insert(jobPosting);

	}

	@Override
	public void updateJobPosting(JobPosting jobPosting) throws IllegalArgumentException {

		jobPostingMapper.update(jobPosting);

	}

	@Override
	public void deleteJobPosting(JobPosting jobPosting) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	/**
	 * Auslesen aller Ausschreibungen
	 */
	@Override
	public ArrayList<JobPosting> getJobPostings() throws IllegalArgumentException {

		return this.jobPostingMapper.findAll();

	}

	/**
	 * Auslesen einer Ausschreibung anhand seiner ID
	 */
	@Override
	public JobPosting getJobPostingByID(int id) throws IllegalArgumentException {
		return this.getJobPostingByID(id);
	}

	@Override
	public void setJobPosting(JobPosting jobPosting) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	// --------------------------- PARTNERPROFILE

	@Override
	public PartnerProfile addPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit,
			Date dateCreated, Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting)
			throws IllegalArgumentException {
		PartnerProfile partnerProfile = new PartnerProfile();
		partnerProfile.setDateCreated(dateCreated);
		partnerProfile.setDateChanged(dateChanged);
		// weitere ergaenzen

		/*
		 * Setzen einer vorläufigen Kundennr. Der insert-Aufruf liefert dann
		 * ein Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		partnerProfile.setId(1);

		// Objekt in der DB speichern.
		return this.partnerProfileMapper.insert(partnerProfile);
	}

	@Override
	public void updatePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException {

		this.partnerProfileMapper.update(partnerProfile);

	}

	@Override
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	/**
	 * Auslesen aller Partnerprofile
	 */
	@Override
	public ArrayList<PartnerProfile> getPartnerProfiles() throws IllegalArgumentException {

		return this.partnerProfileMapper.findAll();

	}

	/**
	 * Auslesen eines Partnerprofiles anhand seiner ID
	 */
	@Override
	public PartnerProfile getPartnerProfileByID(int id) throws IllegalArgumentException {

		return this.partnerProfileMapper.findById(id);

	}

	// --------------------------- RATING

	@Override
	public Rating addRating(String statement, float score) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Rating> getRatings() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating getRatingByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------- PARTICIPATION

	@Override
	public Participation addParticipation(Date dateOpened, Date dateClosed, float workload, Rating rating,
			OrganisationUnit associatedApplicant, Project associatedProject) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateParticipation(Participation participation) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteParticipation(Participation participation) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Participation> getParticipations() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Participation getParticipationByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// ------------------------ PERSON

	@Override
	public Person addPerson(String firstName, ArrayList<Project> projetcs, String eMail)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePerson(Person person) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePerson(Person person) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Person getPersonByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------- TEAM

	@Override
	public Team addTeam() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTeam(Team team) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTeam(Team team) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Team getTeamByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------- LOGIN

	public Person login(String requestUri) {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		Person logInf = new Person();

		if (user != null) {

			Person existingPerson = null;
			existingPerson = PersonMapper.personMapper().findByEmail(user.getEmail());

			if (existingPerson != null) {
				ClientsideSettings.getLogger().severe("Userobjekt E-Mail = " + user.getEmail()
						+ "  Bestehender User: E-Mail  =" + existingPerson.getEmailAdress());

				existingPerson.setLoggedIn(true);
				existingPerson.setLogoutUrl(userService.createLogoutURL(requestUri));

				return existingPerson;

			}

			logInf.setEmailAdress(user.getEmail());
			logInf.setLoggedIn(true);
			logInf.setNickname(user.getNickname());
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			logInf.setLoggedIn(false);
			logInf.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return logInf;
	}

}