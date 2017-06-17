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

	    
	    public String paragraphToHtml(Paragraph p){
	    	if (p instanceof SimpleParagraph){
	    		return this.paragraphToHtml((SimpleParagraph) p); 
	    	}
	    	else return ""; 
	    	
	    }
	    
	    
	    public String paragraphToHtml(SimpleParagraph s) {
	        return "<p>" + s.toString() + "</p>";
	      }
	    
	    
	    /**
	     * HTML Header schreiben
	     * 
	     * @return HTML
	     */
	    public String getHead() {
	      StringBuffer buff = new StringBuffer();

	      buff.append("<html><head><title></title></head><body>");
	      return buff.toString();
	    }
	    
	    
	    /**
	     * HTML Trailer schreiben
	     * 
	     * @return HTML
	     */
	    public String getTrailer() {
	      return "</body></html>";
	    }
	    
	
	    // ---------- process(AllApplicationsOfUser)
	    
	    public void process(AllApplicationsOfUser a) {
			// zurücksetzen des Ausgabe-Strings
			this.resetReportText();
			
			//StringBuffer erzeugen
			StringBuffer buff = new StringBuffer(); 
			
			buff.append("<h2>" + a.getTitle() + "</h2>"); 
			buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
			//table und tr und td öffnen			
			buff.append("<table><tr>");
			buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
			buff.append("</tr>");
					
			ArrayList<Row> rows = a.getRows();
			
			for(int i=0; i<rows.size();i++){
				Row row = rows.get(i);
				buff.append("<tr>"); 
			for(int x=0; x<row.getNumberOfColumns();x++){
				buff.append("<td>" + row.getColumnAt(x) + "</td");
			}
				buff.append("</tr"); 
			}
			
				buff.append("</table"); 
			this.reportText = buff.toString(); 
		}
	    
	    // ---------- process(AllJobPostings)

	    public void process(AllJobPostings a){
	    	// zurücksetzen des Ausgabe-Strings
	    	this.resetReportText();

	    	//StringBuffer erzeugen
	    	StringBuffer buff = new StringBuffer(); 

	    	buff.append("<h2>" + a.getTitle() + "</h2>"); 
	    	buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
	    	//table und tr und td öffnen			
	    	buff.append("<table><tr>");
	    	buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
	    	buff.append("</tr>");

	    	ArrayList<Row> rows = a.getRows();

	    	for(int i=0; i<rows.size();i++){
	    		Row row = rows.get(i);
	    		buff.append("<tr>"); 
	    		for(int x=0; x<row.getNumberOfColumns();x++){
	    			buff.append("<td>" + row.getColumnAt(x) + "</td");
	    		}
	    		buff.append("</tr"); 
	    	}

	    	buff.append("</table"); 
	    	this.reportText = buff.toString(); 
	    }

	     // ---------- process(ApplicationsRelatedToJobPostingsOfUser)
	    
		public void process(AllJobPostingsMatchingPartnerProfileOfUser a){
			// zurücksetzen des Ausgabe-Strings
			this.resetReportText();

			//StringBuffer erzeugen
			StringBuffer buff = new StringBuffer(); 

			buff.append("<h2>" + a.getTitle() + "</h2>"); 
			buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
			//table und tr und td öffnen			
			buff.append("<table><tr>");
			buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
			buff.append("</tr>");

			ArrayList<Row> rows = a.getRows();

			for(int i=0; i<rows.size();i++){
				Row row = rows.get(i);
				buff.append("<tr>"); 
				for(int x=0; x<row.getNumberOfColumns();x++){
					buff.append("<td>" + row.getColumnAt(x) + "</td");
				}
				buff.append("</tr"); 
			}

			buff.append("</table"); 
			this.reportText = buff.toString(); 

		}
		
		// ---------- process(ApplicationsRelatedToJobPostingsOfUser)
		
		public void process(ApplicationsRelatedToJobPostingsOfUser a){
			// TODO Composite Report? 
		}
		
		//---------- process(ProjectInterweavingsWithParticipationsAndApplications)

		public void process(ProjectInterweavingsWithParticipationsAndApplications a	){
			//FIXME
			// zurücksetzen des Ausgabe-Strings
			this.resetReportText();

			//StringBuffer erzeugen
			StringBuffer buff = new StringBuffer(); 
			buff.append("<h2>" + a.getTitle() + "</h2>");
			buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
			//table und tr und td öffnen			
			buff.append("<table><tr>");
			buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
			buff.append("</tr>");
			
		}

		//---------- process(FanInJobPostingsOfUser)
		
		public void process(FanInJobPostingsOfUser a){
			// zurücksetzen des Ausgabe-Strings
			this.resetReportText();

			//StringBuffer erzeugen
			StringBuffer buff = new StringBuffer(); 
			buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
			//table und tr und td öffnen			
			buff.append("<table><tr>");
			buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
			buff.append("</tr>");
			
			ArrayList<Row> rows = a.getRows();
			
			for(int i=0; i<rows.size();i++){
				Row row = rows.get(i);
				buff.append("<tr>"); 
				for(int x=0; x<row.getNumberOfColumns();x++){
					buff.append("<td>" + row.getColumnAt(x) + "</td");
				}
				buff.append("</tr"); 
			}

			buff.append("</table"); 
			this.reportText = buff.toString(); 
		}

		
		// ---------- process(FanOutApplicationsOfUser)
		
		public void process(FanOutApplicationsOfUser a){
			// zurücksetzen des Ausgabe-Strings
			this.resetReportText();

			//StringBuffer erzeugen
			StringBuffer buff = new StringBuffer(); 
			buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
			//table und tr und td öffnen			
			buff.append("<table><tr>");
			buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
			buff.append("</tr>");
			
			ArrayList<Row> rows = a.getRows();
			
			for(int i=0; i<rows.size();i++){
				Row row = rows.get(i);
				buff.append("<tr>"); 
				for(int x=0; x<row.getNumberOfColumns();x++){
					buff.append("<td>" + row.getColumnAt(x) + "</td");
				}
				buff.append("</tr"); 
			}

			buff.append("</table"); 
			this.reportText = buff.toString(); 
		}
	
			//TODO Beschreibung
		public String getReportText(){
			return this.getHead() + this.reportText + this.getTrailer(); 
		}

		public void process(FanInAndOutReport a) {
			// zurücksetzen des Ausgabe-Strings
	    	this.resetReportText();

	    	//StringBuffer erzeugen
	    	StringBuffer buff = new StringBuffer(); 

	    	buff.append("<h2>" + a.getTitle() + "</h2>"); 
	    	buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
	    	//table und tr und td öffnen			
	    	buff.append("<table><tr>");
	    	buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
	    	buff.append("</tr>");

	    	ArrayList<Row> rows = a.getRows();

	    	for(int i=0; i<rows.size();i++){
	    		Row row = rows.get(i);
	    		buff.append("<tr>"); 
	    		for(int x=0; x<row.getNumberOfColumns();x++){
	    			buff.append("<td>" + row.getColumnAt(x) + "</td");
	    		}
	    		buff.append("</tr"); 
	    	}

	    	buff.append("</table"); 
	    	this.reportText = buff.toString(); 
					}
}
