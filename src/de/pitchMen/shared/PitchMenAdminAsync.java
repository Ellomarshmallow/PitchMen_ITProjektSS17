package de.pitchMen.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Participation;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Rating;
import de.pitchMen.shared.bo.Trait;

public interface PitchMenAdminAsync extends RemoteService {

	void init(AsyncCallback<Void> callback);
    /**
     * Fügt ein Project-Objekt zur ArrayList projects hinzu.
     * 
     *  @param das hinzuzufügende Project-Objekt
     * @param project  
     * @return
     */
    void createProject(Marketplace marketplace, AsyncCallback<Project> callback);

    /**
     * Fügt ein PartnerProfile-Objekt zur ArrayList partnerprofiles hinzu.
     * 
     *  @param das hinzuzufügende PartnerProfile-Objekt
     * @param trait 
     * @return
     */
    public void addTrait(PartnerProfile partnerprofile, AsyncCallback<Trait> callback);

    /**
     * Fügt ein JobPosting-Objekt zur ArrayList jobPostings hinzu.
     * 
     *  @param das hinzuzufügende JobPosting-Objekt
     * @param jobPosting  
     * @return
     */
    public void addJobPosting(JobPosting j, AsyncCallback<JobPosting> callback);

    /**
     * Fügt ein Marketplace-Objekt zur ArrayList marketplaces hinzu.
     * 
     *  @param das hinzuzufügende Marketplace-Objekt
     * @param marketplace 
     * @return
     */
    public void addMarketplace(Marketplace m, AsyncCallback<Marketplace> callback);

    /**
     * Erstellt ein neues Marketplace-Objekt.
     * 
     *  @return das neu erstellte Marketplace-Objekt
     * @return Erstellt einen neuen marketplace.
     * 
     * @return neues marketplace Objekt
     */
    public Marketplace createMarketplace(Marketplace m, AsyncCallback<Marketplace> callback);

    /**
     * Erstellt ein neues Project-Objekt.
     * 
     *  @return das neu erstellte Project-Objekt
     * @return
     */
    public void addProject(Project p,  AsyncCallback<Void> callback);

    /**
     * Erstellt ein neues Trait-Objekt.
     * 
     *  @return das neu erstellte Trait-Objekt
     * @return
     */
    public void createTrait(Trait t,  AsyncCallback<Trait> callback);

    /**
     * Löscht ein Trait-Objekt aus der ArrayList traits.
     * 
     *  @param das zu löschende Trait-Objekt
     * @param trait 
     * @return
     */
    public void deleteTrait(Trait trait,  AsyncCallback<Trait> callback);

    /**
     * Löscht das PartnerProfile-Objekt.
     * 
     *  @param das zu löschende PartnerProfil-Objekt
     * @return
     */
    public void deletePartnerProfile(PartnerProfile pp,  AsyncCallback<PartnerProfile> callback);

    /**
     * Löscht das Rating-Objekt.
     * 
     *  @param das zu löschende Rating-Objekt
     * @return
     */
    public void deleteRating(Rating r,  AsyncCallback<Rating> callback);

    /**
     * Löscht das JobPosting-Objekt.
     * 
     *  @param das zu löschende JobPosting-Objekt
     * @param jobPosting  
     * @return
     */
    public void deleteJobPosting(JobPosting j, AsyncCallback<JobPosting> callback );

    /**
     * Löscht das Participation-Objekt.
     * 
     *  @param das zu löschende Participation-Objekt
     * @param participation  
     * @return
     */
    public void deleteParticipation(Participation pa,  AsyncCallback<Participation> callback);

    /**
     * Löscht das Project-Objekt.
     * 
     *  @param das zu löschende Project-Objekt
     * @param project 
     * @return
     */
    public void deleteProject(Project p,  AsyncCallback<Void> callback);

    /**
     * Löscht ein Marketplace-Objekt aus der ArrayList marketplaces.
     * 
     *  @param das zu löschende Marketplace-Objekt
     * @param marketplace 
     * @return
     */
    public void deleteMarketplace(Marketplace m,  AsyncCallback<Marketplace> callback);

    /**
     * @return
     */
    public void getMarketplaces(Marketplace m,  AsyncCallback<Marketplace> callback);

    /**
     * @param value 
     * @return
     */
    public void setMarketplaces(Marketplace m,  AsyncCallback<Marketplace> callback);

}