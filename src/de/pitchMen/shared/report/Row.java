package de.pitchMen.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implemetierungsklasse des Interface Serializable. Ist die Zeile einer Tabelle
 * eines SimpleReports-Objekts. Row-Objekt kann als Kopie z.B. vom Server an den
 * Client �bertragen werden.
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
	private ArrayList<Column> columns = new ArrayList<Column>();

	/**
	 * @param column
	 * @return
	 */
	public void addColumn(Column column) {
		this.columns.add(column); 
	}

	/**
	 * @param column
	 * @return
	 */
	public void deleteColumn(Column column) {
		this.columns.remove(column);
	}

	/**
	 * @return
	 */
	public ArrayList<Column> getColumns() {
		return this.columns;
	}
	
	
	/** Anzahl aller Spalten  
	 * @return Spaltenanzahl 
	 *  */
	//Benötigen wir die Gesamtanzahl der Spalten?
	public int getNumberOfColumns(){
		return this.columns.size(); 
	}
	
	/** Column Objekt an der Stelle i wird ausgelesen
	 * @return column Objekt an der Stelle i
	 */
	 public Column getColumnAt(int i) {
		    return this.columns.get(i); 
		  }
	
	
}
