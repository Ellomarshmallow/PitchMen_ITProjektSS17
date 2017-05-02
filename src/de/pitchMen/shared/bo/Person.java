package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Person und erbt von der Superklasse OrganisationUnit.
 * 
 * @author JuliusDigel
 */
public class Person extends OrganisationUnit {

	
    private String firstName = "";

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    
    public Set<Project> Project;

    /**
     * @return
     */
    public String getFirstName() {
        // TODO implement here
        return "";
    }

    /**
     * @param firstName 
     * @return
     */
    public void setFirstName(String firstName) {
        // TODO implement here
        return null;
    }

}