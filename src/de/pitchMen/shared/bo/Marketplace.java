package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Marktplatz.
 * 
 * @author
 */
public class Marketplace extends BusinessObject {

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
    private ArrayList<Project> projects;

    /**
     * 
     */
    private static final long serialVersionUID;

    /**
     * 
     */
    public Set<Project> Project;

    /**
     * 
     */
    public Set<OrganisationUnit> Participant;

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
    public ArrayList<Project> getProjects() {
        // TODO implement here
        return null;
    }

}