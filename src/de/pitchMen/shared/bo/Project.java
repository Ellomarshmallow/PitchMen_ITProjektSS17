package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Projekt.
 * 
 * @author
 */
public class Project extends BusinessObject {

     /**
     * 
     */
    private Date dateOpened;

    /**
     * 
     */
    private Date dateClosed;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Person manager;

    /**
     * 
     */
    private ArrayList<JobPosting> jobPostings;

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
    public Set<Participation> Participation;

    /**
     * 
     */
    public Person Manager;

    /**
     * 
     */
    public Set<JobPosting> JobPosting;

    /**
     * @return
     */
    public Date getDateOpened() {
        // TODO implement here
        return null;
    }

    /**
     * @param dateOpened 
     * @return
     */
    public void setDateOpened(Date dateOpened) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Date getDateClosed() {
        // TODO implement here
        return null;
    }

    /**
     * @param dateClosed 
     * @return
     */
    public void setDateClosed(Date dateClosed) {
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
     * @param description 
     * @return
     */
    public void setTitle(String description) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getDescription() {
        // TODO implement here
        return "";
    }

    /**
     * @param value 
     * @return
     */
    public void setDescription(String value) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Person getManager() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<JobPosting> getJobPostings() {
        // TODO implement here
        return null;
    }

}