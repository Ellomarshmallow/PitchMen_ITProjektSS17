package de.pitchMen.client.elements;

import com.google.gwt.user.client.ui.Grid;

/**
 * Die Klasse <code>PitchMenGrid</code> erweitert die von
 * GWT bereitgestellte Klasse {@link Grid} und erweitert 
 * sie um spezielle, für die PitchMen-Applikation benötigte 
 * Funktionalitäten.
 * 
 * @author Simon
 */
public class PitchMenGrid extends Grid {
	
	/**
	 * Aktuell freie Grid-Position (Reihe)
	 */
	private int rowCount = 0;
	
	/**
	 * Aktuell freie Grid-Position (Spalte)
	 */
	private int colCount = 0;
	
	/**
	 * Der Konstruktor legt das Grid an.
	 */
	public PitchMenGrid(int numOfRows) {
		super(numOfRows,3);
	}
	
	public void addGridElement(GridElement ge) {
		if(this.colCount == 3) {
			this.colCount = 0;
			this.rowCount += 1;
		}
		this.setWidget(this.rowCount, this.colCount++, ge);
	}

}
