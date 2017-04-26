package de.pitchMen.shared.report;

/**
 * Superklasse der Klassen AllJobPostings und AllApplicationsOfUser und
 * AllJobPostingsMatchingPartnerProfileOfUser Subklasse von Report.
 * 
 * @author
 */
public abstract class SimpleReport extends Report {

	/**
	 * Default constructor
	 */
	public SimpleReport() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID;

	/**
	 * 
	 */
	private ArrayList<Report> tables;

	/**
	 * @param row
	 * @return
	 */
	public void addRow(Row row) {
		// TODO implement here
		return null;
	}

	/**
	 * @param row
	 * @return
	 */
	public void removeRow(Row row) {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public ArrayList<Report> getRows() {
		// TODO implement here
		return null;
	}

}
