package de.pitchMen.server;

import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.shared.bo.Marketplace;
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
private class PitchMenAdminImpl extends RemoteServiceServlet implements PitchMenAdmin {

	/**
	 * Default constructor
	 */
	private PitchMenAdminImpl() {
	}

	private ArrayList<Marketplace> marketplaces = null;

	private Project project = null;

	private Trait trait = null;

	/**
	 * @param project
	 * @return
	 */
	public void addProject(Project project) {
		// TODO implement here
	}

	/**
	 * @param trait
	 * @return
	 */
	public void addTrait(Trait trait) {
		// TODO implement here
	}

	/**
	 * @param marketplace
	 * @return
	 */
	public void addMarketplace(Marketplace marketplace) {
		// TODO implement here
	}

	/**
	 * @param trait
	 * @return
	 */
	public void deleteTrait(Trait trait) {
		// TODO implement here
	}

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

	/**
	 * @param project
	 * @return
	 */
	public void deleteProject(Project project) {
		// TODO implement here
	}

	/**
	 * @param marketplace
	 * @return
	 */
	public void deleteMarketplace(Marketplace marketplace) {
		// TODO implement here
	}

	@Override
	public void addJobPosting(JobPosting jobPosting) {
		// TODO Auto-generated method stub

	}

	@Override
	public Marketplace createMarketplace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project createProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trait createTrait() {
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

	@Override
	public ArrayList<Marketplace> getMarketplaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMarketplaces(ArrayList<Marketplace> value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Marketplace createMarketplace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project createProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trait createTrait() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Marketplace> getMarketplaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMarketplaces(ArrayList<Marketplace> value) {
		// TODO Auto-generated method stub

	}

	@Override
	public de.pitchMen.shared.bo.Project createProject(de.pitchMen.shared.bo.Marketplace marketplace)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProject(de.pitchMen.shared.bo.Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProject(de.pitchMen.shared.bo.Project project) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMarketplace(de.pitchMen.shared.bo.Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public de.pitchMen.shared.bo.Marketplace createMarketplace(String title, String description)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMarketplace(de.pitchMen.shared.bo.Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMarketplaces(de.pitchMen.shared.bo.Marketplace marketplace) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTrait(de.pitchMen.shared.bo.Trait trait) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public de.pitchMen.shared.bo.Trait createTrait(String name, String value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTrait(de.pitchMen.shared.bo.Trait trait) throws IllegalArgumentException {
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
	public PartnerProfile createPartnerProfile(java.util.ArrayList<de.pitchMen.shared.bo.Trait> traits,
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