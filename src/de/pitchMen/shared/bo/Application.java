package de.pitchMen.shared.bo;


/**
 * Repräsentiert eine Bewerbung.
 * 
 * @author
 */

public class Application extends BusinessObject {

    /**
     * 
     */
    private Date dateCreated;

    /**
     * 
     */
    private OrganisationUnit applicant;

    /**
     * 
     */
    private String text;

    /**
     * 
     */
    private Rating rating;

    /**
     * 
     */
    private static final long serialVersionUID;

    /**
     * 
     */
    public Rating Rating;

    /**
     * 
     */
    public JobPosting JobPosting;

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
    public OrganisationUnit getApplicant() {
        // TODO implement here
        return null;
    }

    /**
     * @param applicant 
     * @return
     */
    public void setApplicant(OrganisationUnit applicant) {
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
    public Rating getRating() {
        // TODO implement here
        return null;
    }

    /**
     * @param rating 
     * @return
     */
    public void setRating(Rating rating) {
        // TODO implement here
        return null;
    }

    /**
     * Bewertet das aufrufende Application-Objekt. Hierfür werden ein Bewertungswert und eine Stellungnahme übergeben. Erzeugt ein Rating-Objekt.
     * 
     * @param score 
     * @param statement 
     * @return
     */
    public void rate(float score, String statement) {
        // TODO implement here
        return null;
    }

    /**
     * Überprüft ob die Bewerbung eine Bewertung hat. Ist eine Bewertung vorhanden, wird true ausgegeben, wenn nicht, false.
     * 
     * @return
     */
    public boolean isRated() {
        // TODO implement here
        return false;
    }

}

}
