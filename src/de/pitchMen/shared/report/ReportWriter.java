package de.pitchMen.shared.report;



/**
 * Überführt Report-Objekte in ein menschenlesbares Format von Server zu
 * Client. Ist Superklasse von HTMLReportWriter und PlainTextReportWriter.
 * 
 * @author
 */
public abstract class ReportWriter {

	
	
	
	public abstract void process(AllApplicationsOfUser a); 
	
	public abstract void process(AllJobPostings a); 
	
	public abstract void process(AllJobPostingsMatchingPartnerProfileOfUser a); 
	
	public abstract void process(ApplicationsRelatedToJobPostingsOfUser a); 
	
}
