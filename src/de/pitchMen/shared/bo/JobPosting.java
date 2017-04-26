package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Ausschreibung.
 * 
 * @author
 */
public class JobPosting extends BusinessObject {

	
    private ArrayList<Application> applications;

    /**
     * 
     */
    private Person recruiter;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String text;

    /**
     * 
     */
    private Date deadline;

    /**
     * 
     */
    private PartnerProfile partnerProfile;

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
    public Set<Application> Application;

    /**
     * @return
     */
    public ArrayList<Application> getApplications() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Person getRecruiter() {
        // TODO implement here
        return null;
    }

    /**
     * @param recruiter 
     * @return
     */
    public void setRecruiter(Person recruiter) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getTitle() {
        // TODO implement here
        return "";
    }

    /**
     * @param title 
     * @return
     */
    public void setTitle(String title) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getText() {
        // TODO implement here
        return "";
    }

    /**
     * @param text 
     * @return
     */
    public void setText(String text) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Date getDeadline() {
        // TODO implement here
        return null;
    }

    /**
     * @param deadline 
     * @return
     */
    public void setDeadline(Date deadline) {
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
     */
    public void setPartnerProfile(PartnerProfile partnerProfile) {
        // TODO implement here
    }

}