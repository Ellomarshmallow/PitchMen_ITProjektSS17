package de.pitchMen.shared;

public interface PitchMenAdminAsync extends RemoteService {


    /**
     * Fügt ein Project-Objekt zur ArrayList projects hinzu.
     * 
     *  @param das hinzuzufügende Project-Objekt
     * @param project  
     * @return
     */
    public void addProject(Project project );

    /**
     * Fügt ein PartnerProfile-Objekt zur ArrayList partnerprofiles hinzu.
     * 
     *  @param das hinzuzufügende PartnerProfile-Objekt
     * @param trait 
     * @return
     */
    public void addTrait(Trait trait);

    /**
     * Fügt ein JobPosting-Objekt zur ArrayList jobPostings hinzu.
     * 
     *  @param das hinzuzufügende JobPosting-Objekt
     * @param jobPosting  
     * @return
     */
    public void addJobPosting(JobPosting jobPosting );

    /**
     * Fügt ein Marketplace-Objekt zur ArrayList marketplaces hinzu.
     * 
     *  @param das hinzuzufügende Marketplace-Objekt
     * @param marketplace 
     * @return
     */
    public void addMarketplace(Marketplace marketplace);

    /**
     * Erstellt ein neues Marketplace-Objekt.
     * 
     *  @return das neu erstellte Marketplace-Objekt
     * @return Erstellt einen neuen marketplace.
     * 
     * @return neues marketplace Objekt
     */
    public Marketplace createMarketplace();

    /**
     * Erstellt ein neues Project-Objekt.
     * 
     *  @return das neu erstellte Project-Objekt
     * @return
     */
    public void createProject();

    /**
     * Erstellt ein neues Trait-Objekt.
     * 
     *  @return das neu erstellte Trait-Objekt
     * @return
     */
    public void createTrait();

    /**
     * Löscht ein Trait-Objekt aus der ArrayList traits.
     * 
     *  @param das zu löschende Trait-Objekt
     * @param trait 
     * @return
     */
    public void deleteTrait(Trait trait);

    /**
     * Löscht das PartnerProfile-Objekt.
     * 
     *  @param das zu löschende PartnerProfil-Objekt
     * @return
     */
    public void deletePartnerProfile();

    /**
     * Löscht das Rating-Objekt.
     * 
     *  @param das zu löschende Rating-Objekt
     * @return
     */
    public void deleteRating();

    /**
     * Löscht das JobPosting-Objekt.
     * 
     *  @param das zu löschende JobPosting-Objekt
     * @param jobPosting  
     * @return
     */
    public void deleteJobPosting(JobPosting jobPosting );

    /**
     * Löscht das Participation-Objekt.
     * 
     *  @param das zu löschende Participation-Objekt
     * @param participation  
     * @return
     */
    public void deleteParticipation(Participation participation );

    /**
     * Löscht das Project-Objekt.
     * 
     *  @param das zu löschende Project-Objekt
     * @param project 
     * @return
     */
    public void deleteProject(Project project);

    /**
     * Löscht ein Marketplace-Objekt aus der ArrayList marketplaces.
     * 
     *  @param das zu löschende Marketplace-Objekt
     * @param marketplace 
     * @return
     */
    public void deleteMarketplace(Marketplace marketplace);

    /**
     * @return
     */
    public void getMarketplaces();

    /**
     * @param value 
     * @return
     */
    public void setMarketplaces(ArrayList<Marketplace> value);

}