package de.pitchMen.server;

import java.util.*;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.client.LoginInfo;
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
	public Application addApplication(Date dateCreated, OrganisationUnit applicant, String text, Rating rating)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
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
	public Application getApplicationByID(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	// --------------------------- COMPANY
	
	@Override
	public Company addCompany(AsyncCallback<Application> callback) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCompany(Company company, AsyncCallback<Void> callback) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCompany(Company company, AsyncCallback<Void> callback) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCompanyByID(int id, AsyncCallback<Application> callback) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	// --------------------------- PROJECT

	@Override
	public Project addProject(Date dateOpened, Date dateClosed, String title, String description, Person manager,
			ArrayList<JobPosting> jobPostings, ArrayList<Participation> participation) throws IllegalArgumentException {
		Project project = new Project();
		project.setDateOpened(dateOpened);
		project.setDateClosed(dateClosed);
		project.setTitle(title);
		project.setDescription(description);
		project.setManager(manager);
		project.setJobPostings(jobPostings);
		project.setParticipations(participation);

		/*
		 * Setzen einer vorläufigen Kundennr. Der insert-Aufruf liefert dann
		 * ein Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		project.setId(1);

		return this.projectMapper.insert(project);
	}

	/**
	 * Speichert ein Projekt
	 */
	@Override
	public void updateProject(Project project) throws IllegalArgumentException {
		try {
			projectMapper.update(project);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
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
		try {
			return this.projectMapper.findAll();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

	}

	/**
	 * Auslesen eines Projekts anhand seiner ID
	 */
	@Override
	public Project getProjectByID(int id) throws IllegalArgumentException {
		try {
			return this.projectMapper.findById(id);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

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
	public Marketplace addMarketplace(String title, String description, ArrayList<OrganisationUnit> organisationUnits,
			ArrayList<Project> projects) throws IllegalArgumentException {
		Marketplace marketplace = new Marketplace();
		marketplace.setTitle(title);
		marketplace.setDescription(description);
		marketplace.setOrganisationUnits(organisationUnits);
		marketplace.setProject(projects);

		/*
		 * Setzen einer vorläufigen Kundennr. Der insert-Aufruf liefert dann
		 * ein Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		marketplace.setId(1);

		// Objekt in der DB speichern
		return this.marketplaceMapper.insert(marketplace, null, null, null);
	}

	/**
	 * Speichert einen Marktplatz
	 */
	@Override
	public void updateMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		try {
			marketplaceMapper.update(marketplace);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

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

		try {
			return this.marketplaceMapper.findAll();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

	}

	/**
	 * Auslesen eines Marketplatzes anhand seiner ID
	 */
	@Override
	public Marketplace getMarketplaceByID(int id) throws IllegalArgumentException {
		try {
			return this.marketplaceMapper.findById(id);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

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
		try {
			traitMapper.update(trait);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

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

		try {
			return this.traitMapper.findAll();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

	}

	/**
	 * Auslesen einer Eigenschaft anhand seiner ID
	 */
	@Override
	public Trait getTraitByID(int id) throws IllegalArgumentException {
		try {
			return this.traitMapper.findById(id);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

	}
	// --------------------------- JOBPOSTNG

	@Override
	public JobPosting addJobPosting(String title, String text, Date deadline, PartnerProfile partnerProfile,
			Person recruiter, ArrayList<Application> applications) throws IllegalArgumentException {
		JobPosting jobPosting = new JobPosting();
		jobPosting.setTitle(title);
		jobPosting.setText(text);
		jobPosting.setDeadline(deadline);
		jobPosting.setPartnerProfile(partnerProfile);
		jobPosting.setRecruiter(recruiter);
		jobPosting.setApplications(applications);

		/*
		 * Setzen einer vorläufigen Kundennr. Der insert-Aufruf liefert dann
		 * ein Objekt, dessen Nummer mit der Datenbank konsistent ist.
		 */
		jobPosting.setId(1);

		// Objekt in der DB speichern.
		return this.jobPostingMapper.insert(jobPosting);
	}

	@Override
	public void updateJobPosting(JobPosting jobPosting) throws IllegalArgumentException {
		try {
			jobPostingMapper.update(jobPosting);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

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
		try {
			return this.jobPostingMapper.findAll();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

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
		partnerProfile.setTraits(traits);
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
		try {
			this.partnerProfileMapper.update(partnerProfile);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

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
		try {
			return this.partnerProfileMapper.findAll();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

	}

	/**
	 * Auslesen eines Partnerprofiles anhand seiner ID
	 */
	@Override
	public PartnerProfile getPartnerProfileByID(int id) throws IllegalArgumentException {
		try {
			return this.partnerProfileMapper.findById(id);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
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
	
	// --------------------------- LOGIN
	
	public Person login (String requestUri){
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser(); 
		Person logInf = new Person();
		
		if(user != null){
			
			Person existingPerson = null;
			try {
				existingPerson = PersonMapper.personMapper().findByEmail(user.getEmail());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			if(existingPerson != null){
				ClientsideSettings.getLogger().severe("Userobjekt E-Mail = " + user.getEmail()
				+ "  Bestehender User: E-Mail  =" + existingPerson.getEmail());
				
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

}