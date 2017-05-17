package de.pitchMen.server;

import java.util.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.server.db.MarketplaceMapper;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Trait;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.OrganisationUnit;
import de.pitchMen.shared.bo.Participation;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Rating;

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
	public PitchMenAdminImpl() throws IllegalArgumentException{
	}

	
	private MarketplaceMapper marketplaceMapper = null;
	private static final long serialVersionUID = 1L;
	
	  @Override
	  public void init() throws IllegalArgumentException {
	      /*
	       * Ganz wesentlich ist, dass die PitchMenAdministration einen vollständigen Satz
	       * von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank
	       * kommunizieren kann.
	       */
	      this.marketplaceMapper = MarketplaceMapper.marketplaceMapper();
	      
	    }
	  
	/**
	 * Referenz auf den DatenbankMapper, der Marketplaceobjekte mit der
	 * Datenbank abgleicht.
	 */


	/**
	 * @return
	 */
	public void deletePartnerProfile() {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public void deleteRating() {
		// TODO implement here
	}

	@Override
	public void addJobPosting(JobPosting jobPosting) {
		// TODO Auto-generated method stub

	}

	@Override
	public Marketplace createMarketplace(String title, String describtion) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void deleteJobPosting(JobPosting jobPosting) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteParticipation(Participation participation) {
		// TODO Auto-generated method stub

	}

	/*@Override
	public void setMarketplaces(ArrayList<Marketplace> value) {
		
	// TODO Auto-generated method stub

	}*/ 

	@Override
	public ArrayList<Marketplace> getMarketplaces() throws IllegalArgumentException{
		try { 
			return this.marketplaceMapper.findAll();
		}
		catch (ClassNotFoundException e){
			
			e.printStackTrace();
			
		}
		return marketplaces;
		
	}

	@Override
	public Project createProject(Marketplace marketplace)
			throws IllegalArgumentException {
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
	public PartnerProfile createPartnerProfile(ArrayList<Trait> traits,
			OrganisationUnit organisationUnit, Date dateCreated, Date dateChanged, PartnerProfile partnerprofile,
			JobPosting jobPosting) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRating(Rating rating) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

}