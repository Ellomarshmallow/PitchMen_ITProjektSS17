package de.pitchMen.server;

import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Trait;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Participation;

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

}