package de.pitchMen.shared.report;

import java.io.Serializable;

/**
 * Implemetierungsklasse des Interface Serializable. Ist die Zeile einer Tabelle
 * eines SimpleReports-Objekts. Row-Objekt kann als Kopie z.B. vom Server an den
 * Client übertragen werden.
 * 
 * @author
 */
public class Row implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private ArrayList<Column> columns;

	/**
	 * @param column
	 * @return
	 */
	public void addColumn(Column column) {
		// TODO implement here
		return null;
	}

	/**
	 * @param column
	 * @return
	 */
	public void deleteColumn(Column column) {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public ArraList<Column> getColunms() {
		// TODO implement here
		return null;
	}

}
