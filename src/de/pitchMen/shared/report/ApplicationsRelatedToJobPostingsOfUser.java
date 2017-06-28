package de.pitchMen.shared.report;

/**
 * Report zur Abfrage der Bewerbungen und den zugehörigen Ausschreibungen des
 * Benutzers. Subklasse von CompositeReport. Enthält keine eigenen Attribute
 * und Methoden. Dient der Objektorientierten-Programmierung.
 * 
 * @author
 */
public class ApplicationsRelatedToJobPostingsOfUser extends CompositeReport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int jobPostingId;
	
	public int projectId;
	
	public int personId;
	
	public int getJobPostingId() {
		return jobPostingId;
	}
	public void setJobPostingId(int jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
	
	



}
