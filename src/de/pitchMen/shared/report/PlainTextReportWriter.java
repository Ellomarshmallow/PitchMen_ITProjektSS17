package de.pitchMen.shared.report;

import java.util.ArrayList;

/**
 * Subklasse von ReportWriter.
 * 
 * "Ein <code>ReportWriter</code>, der Reports mittels Plain Text formatiert.
 * Das im Zielformat vorliegende Ergebnis wird in der Variable
 * <code>reportText</code> abgelegt und kann nach Aufruf der entsprechenden
 * Prozessierungsmethode mit <code>getReportText()</code> ausgelesen werden.
 * 
 * @quelle Thies."
 * 
 * 
 * @author
 */
public class PlainTextReportWriter extends ReportWriter {

	/**
	 * Default constructor
	 */
	public PlainTextReportWriter() {
	}

	/**
	 * 
	 */
	private String reportText = "";

	/**
	 * @return
	 */
	public void resetReportText() {
		this.reportText = "";
	}

	/**
	 * @return
	 */
	public String getReportText() {
		// TODO implement here
		return "";
	}

	
	
	
	
	public void process(AllApplicationsOfUser a) {
		
		this.resetReportText();
		
		StringBuffer buff = new StringBuffer(); 
		
		buff.append(a.getTitle()); 
		buff.append(a.getDatecreated().toString());
		
		ArrayList<Row> row = a.getRows(); 
		
		this.reportText = buff.toString(); 
	}
	
	public void process(AllJobPostings a){
		//TODO implement here
	}
	
	
	
	public void process(AllJobPostingsMatchingPartnerProfileOfUser a){
		//TODO implement here
	}
	
	public void process(ApplicationsRelatedToJobPostingsOfUser a){
		//TODO implement here
	}
	
	
	
	
}
