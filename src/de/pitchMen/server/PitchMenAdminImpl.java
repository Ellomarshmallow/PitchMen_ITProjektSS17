package de.pitchMen.server;

import java.util.*;

/**
 * Implemetierungsklasse des Interface PitchMenAdmin.  
 * Sie enthält die Applikationslogik, stellt die Zusammenhänge konstistent dar 
 * und ist zuständig für einen geordneten Ablauf.
 * 
 * @author
 */
private class PitchMenAdminImpl extends RemoteServiceServlet {

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
        return null;
    }

    /**
     * @param trait 
     * @return
     */
    public void addTrait(Trait trait) {
        // TODO implement here
        return null;
    }

    /**
     * @param jobPosting 
     * @return
     */
    public void addJobPosting(JobPosting jobPosting) {
        // TODO implement here
        return null;
    }

    /**
     * @param marketplace 
     * @return
     */
    public void addMarketplace(Marketplace marketplace) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Marketplace createMarketplace() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Project createProject() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Trait createTrait() {
        // TODO implement here
        return null;
    }

    /**
     * @param trait 
     * @return
     */
    public void deleteTrait(Trait trait) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void deletePartnerProfile() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void deleteRating() {
        // TODO implement here
        return null;
    }

    /**
     * @param jobPosting 
     * @return
     */
    public void deleteJobPosting(JobPosting jobPosting) {
        // TODO implement here
        return null;
    }

    /**
     * @param participation 
     * @return
     */
    public void deleteParticipation(Participation participation) {
        // TODO implement here
        return null;
    }

    /**
     * @param project 
     * @return
     */
    public void deleteProject(Project project) {
        // TODO implement here
        return null;
    }

    /**
     * @param marketplace 
     * @return
     */
    public void deleteMarketplace(Marketplace marketplace) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Marketplace> getMarketplaces() {
        // TODO implement here
        return null;
    }

    /**
     * @param value 
     * @return
     */
    public void setMarketplaces(ArrayList<Marketplace> value) {
        // TODO implement here
        return null;
    }

}