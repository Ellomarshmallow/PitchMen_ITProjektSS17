package de.pitchMen.shared.bo;


/**
 * Abstrakte Superklasse der Klassen Person, Team, Company. 
 * 
 * @author
 */
public abstract class OrganisationUnit extends BusinessObject {

     /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private PartnerProfile partnerProfile;

    /**
     * 
     */
    private ArrayList<Participation> participations;

    /**
     * 
     */
    private static final long serialVersionUID;

    /**
     * 
     */
    public PartnerProfile PartnerProfile;

    /**
     * 
     */
    public Set<Marketplace> Marketplace;

    /**
     * 
     */
    public Set<Participation> Participation;

    /**
     * @return
     */
    public String getDescription() {
        // TODO implement here
        return "";
    }

    /**
     * @param description 
     * @return
     */
    public void setDescription(String description) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return "";
    }

    /**
     * @param name 
     * @return
     */
    public void setName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public PartnerProfile getPartnerProfile() {
        // TODO implement here
        return null;
    }

    /**
     * @param partnerProfile 
     * @return
     */
    public void setPartnerProfile(PartnerProfile partnerProfile) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Participation> getParticipations() {
        // TODO implement here
        return null;
    }

    /**
     * @param participition 
     * @return
     */
    public void setParticipations(ArrayList<Participation> participition) {
        // TODO implement here
        return null;
    }

}