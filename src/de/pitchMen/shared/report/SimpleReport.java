package de.pitchMen.shared.report;
import java.util.ArrayList;


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
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ArrayList<Row> tables = new ArrayList<Row>();

	/**
	 * @param row
	 * @return
	 */
	public void addRow(Row row) {
		
		this.tables.add(row);
	}

	/**
	 * @param row
	 * @return
	 */
	public void removeRow(Row row) {
		this.tables.remove(row);
	}

	/**
	 * @return
	 */
	public ArrayList<Row> getRows() {
		return this.tables;
	}
	

	
	
	
	

}
