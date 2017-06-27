package de.pitchMen.shared.report;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

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
		if (p instanceof CompositeParagraph){
			return this.paragraphToHtml((CompositeParagraph) p);
		}	else
			return this.paragraphToHtml((SimpleParagraph) p); 

	}

	public String paragraphToHtml(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getNumParagraphs(); i++) {
			result.append("<p>" + p.getParagraphAt(i) + "</p>");
		}

		return result.toString();
	}

	public String paragraphToHtml(SimpleParagraph s) {
		return "<p>" + s.toString() + "</p>";
	}




	/**
	 * HTML Header schreiben
	 * 
	 * @return HTML
	 */
	public String getHeaderData() {
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

	//TODO Beschreibung
	public String getReportText(){
		return this.getHeaderData() + this.reportText + this.getTrailer(); 
	}

	// ---------- process(AllApplicationsOfUser)

	public void process(AllApplicationsOfUser a) {
		// zurücksetzen des Ausgabe-Strings
		this.resetReportText();

		//StringBuffer erzeugen
		StringBuffer buff = new StringBuffer(); 

		buff.append("<h3>" + a.getTitle() + "</h3>"); 
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
		//		// zurücksetzen des Ausgabe-Strings
		//		this.resetReportText();
		//
		//		//StringBuffer erzeugen
		//		StringBuffer buff = new StringBuffer(); 
		//
		//		buff.append("<h2>" + a.getTitle() + "</h2>"); 
		//		buff.append("<p><strong>" + a.getDatecreated().toString() + "</strong></p>");			
		//		//table und tr und td öffnen			
		//		buff.append("<table><tr>");
		//		buff.append("<td>" + paragraphToHtml(a.getHeaderData()) + "</td>"); 
		//		buff.append("</tr>");
		//
		//		ArrayList<Row> rows = a.getRows();
		//
		//		for(int i=0; i<rows.size();i++){
		//			Row row = rows.get(i);
		//			buff.append("<tr>"); 
		//			for(int x=0; x<row.getNumberOfColumns();x++){
		//				buff.append("<td>" + row.getColumnAt(x) + "</td");
		//			}
		//			buff.append("</tr"); 
		//		}
		//
		//		buff.append("</table"); 
		//		this.reportText = buff.toString(); 
		//	}

		// zurücksetzen des Ausgabe-Strings
		this.resetReportText();

		//StringBuffer erzeugen
		StringBuffer buff = new StringBuffer(); 

		buff.append("<H2>" + a.getTitle() + "</H2>");
		buff.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		buff.append("</tr><tr><td></td><td>" + a.getDatecreated().toString()
				+ "</td></tr></table>");


		ArrayList<Row> rows = a.getRows();

		buff.append("<table style=\"width:400px\">");

		for(int i=0; i<rows.size();i++){
			Row row = rows.get(i);
			buff.append("<tr>"); 
			for(int x=0; x<row.getNumberOfColumns();x++){

				if (i == 0) {

					buff.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(i)
					+ "</td>");

				}	else {
					if (i > 1) {
						buff.append("<td style=\"border-top:1px solid silver\">"
								+ row.getColumnAt(i) + "</td>");
					}	
					else
						buff.append("<td valign=\"top\">" + row.getColumnAt(i) + "</td>");
				}
			}
		}
		buff.append("</tr"); 



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



	public void process(FanInAndOutReport a) {
		// zurücksetzen des Ausgabe-Strings
		this.resetReportText();

		//StringBuffer erzeugen
		StringBuffer buff = new StringBuffer(); 

		HTML titel = new HTML("<h1>" + a.getTitle() + "</h1>");
		//titel.setStyleName("header");
		RootPanel.get("content").add(titel);

		for(int i=0; i< a.getNumSubReports();i++){
			FanInJobPostingsOfUser subReportone = (FanInJobPostingsOfUser) a.getSubReportAt(i);

			this.process(subReportone);

			buff.append(reportText + "\n");

			resetReportText();
		}
		reportText = buff.toString();
	}

	public void processSimpleReport(Report report){

		SimpleReport r = (SimpleReport)report;

		this.resetReportText();

		StringBuffer result = new StringBuffer();

		result.append("<H2>" + r.getTitle() + "</H2>");

		result.append("<table style=\"width:400px;border:1px solidsilver\"><tr>");

		result.append("</tr><tr><td></td><td>" + r.getDatecreated().toString()
				+ "</td></tr></table>");

		ArrayList<Row> rows = r.getRows();
		result.append("<table style=\"width:400px\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.get(i);
			result.append("<tr>");
			for (int k = 0; k < row.getNumberOfColumns(); k++) {
				if (i == 0) {
					result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k)
					+ "</td>");
				}
				else {
					if (i > 1) {
						result.append("<td style=\"border-top:1px solid silver\">"
								+ row.getColumnAt(k) + "</td>");
					}
					else {
						result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		/*
		 * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		 * reportText-Variable zugewiesen. Dadurch wird es m�glich, anschlie�end das
		 * Ergebnis mittels getReportText() auszulesen.
		 */
		this.reportText = result.toString();
	}




}





