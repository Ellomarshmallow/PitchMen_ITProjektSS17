package de.pitchMen.shared.report;

import java.io.Serializable;

/**
	 * Superklasse der Klassen ApplicationsRelatedJobPostingsOfUser, ProjectInterweavingsWithParticipationsAndApplications, FanInJobPostingsOfUser, FanOutApplicationsOfUser.
	 * Zusammensetzung von mehreren Teil-Reports (SubReports Attribut).
	 * Implementiert das Interface Serializable.
	 * Subklasse von Report.
	 * 
	 * @author
	 */
	public abstract class CompositeReport extends Report implements Serializable{

	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;

	    /**
	     * 
	     */
	    private ArrayList<Report> subReports;

	    /**
	     * @param report 
	     * @return
	     */
	    public void addSubReport(Report report) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param report 
	     * @return
	     */
	    public void removeSubReport(Report report) {
	        // TODO implement here
	        return null;
	    }

	}
}
