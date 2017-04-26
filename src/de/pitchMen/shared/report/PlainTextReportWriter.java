package de.pitchMen.shared.report;

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
	private String reportText;

	/**
	 * @return
	 */
	public void resetReportText() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public String getReportText() {
		// TODO implement here
		return "";
	}

}
