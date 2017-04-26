package de.pitchMen.shared.bo;

/**
 * Jedes PartnerProfile-Objekt gehört zu genau einem Teilnehmer des Projektmarktplatzes bzw. einer Ausschreibung.
 * 
 * @author
 */
public class PartnerProfile extends BusinessObject {

    /**
     * 
     */
    private ArrayList<Trait> traits;

    /**
     * 
     */
    private Date dateCreated;

    /**
     * 
     */
    private Date dateChanged;

    /**
     * 
     */
    private static final long serialVersionUID;

    /**
     * 
     */
    public OrganisationUnit OrganisationUnit;

    /**
     * 
     */
    public JobPosting JobPosting;

    /**
     * 
     */
    public Set<Trait> Trait;

    /**
     * @return
     */
    public ArrayList<Trait> getTraits() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Date getDateCreated() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Date getDateChanged() {
        // TODO implement here
        return null;
    }

    /**
     * Vergleicht das aufrufende PartnerProfile-Objekt mit dem übergebenen PartnerProfil-Objekt und ermittelt einen Übereinstimmungswert.
     * 
     * @param partnerProfile 
     * @return
     */
    public float compareWith(PartnerProfile partnerProfile) {
        // TODO implement here
        return 0.0f;
    }

}