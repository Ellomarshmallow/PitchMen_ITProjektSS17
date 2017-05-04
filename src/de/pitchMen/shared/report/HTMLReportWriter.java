	package de.pitchMen.shared.report;

import java.util.ArrayList;

/**
	 * Subklasse von ReportWriter.
	 * 
	 * "Ein <code>ReportWriter</code>, der Reports mittels HTML formatiert. Das im
	 *  Zielformat vorliegende Ergebnis wird in der Variable <code>reportText</code>
	 *  abgelegt und kann nach Aufruf der entsprechenden Prozessierungsmethode mit
	 *  <code>getReportText()</code> ausgelesen werden. 
	 * @quelle Thies."
	 *  
	 * 
	 * @author
	 */
	public class HTMLReportWriter extends ReportWriter {

	    /**
	     * Dieser String wird später die Ausgabe des ReportWriters
	     * enthalten. Er wird leer deklariert.
	     */
	    private String reportText = "";

	    /**
	     * Setzt die Ausgabe des ReportWriters zurück.
	     */
	    public void resetReportText() {
	     this.reportText = ""; 
	    }

	    /**
	     * Gibt die Ausgabe des ReportWriters zurück.
	     */
	    public String getReportText() {
	        
	        return this.reportText;
	    }
	    
	    
	    public void process(AllApplicationsOfUser a) {
			// zurücksetzen des Ausgabe-Strings
			this.resetReportText();
			
			StringBuffer buff = new StringBuffer(); 
			
			buff.append("<h2>" + a.getTitle() + "</h2>"); 
			buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");
			
			ArrayList<Row> row = a.getRows();
			
			// TODO finish method
			
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
