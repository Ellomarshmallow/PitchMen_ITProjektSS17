package de.pitchMen.server;

import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.server.db.*;
import de.pitchMen.shared.*;
import de.pitchMen.shared.bo.*;

/**
 * Implemetierungsklasse des Interface PitchMenAdmin. Sie enth�lt die
 * Applikationslogik, stellt die Zusammenh�nge konstistent dar und ist
 * zust�ndig f�r einen geordneten Ablauf.
 * 
 * @author
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

	@Override
	public void addJobPosting(JobPosting jobPosting) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteJobPosting(JobPosting jobPosting) {
		// TODO Auto-generated method stub

	}

	@Override
	public Project createProject(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProject(Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Marketplace createMarketplace(String title, String describtion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMarketplace(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMarketplaces(Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException {

		try {
			return this.marketplaceMapper.findAll();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}
		return null;

	}

	@Override
	public void addTrait(Trait trait) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Trait createTrait(String name, String value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTrait(Trait trait) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public JobPosting createJobPosting(String title, String text, Date deadline, PartnerProfile partnerprofile)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePartnerProfile(PartnerProfile partnerProfile) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public PartnerProfile createPartnerProfile(ArrayList<Trait> traits, OrganisationUnit organisationUnit,
			Date dateCreated, Date dateChanged, PartnerProfile partnerprofile, JobPosting jobPosting)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteParticipation(Participation participation) {
		// TODO Auto-generated method stub

	}

}