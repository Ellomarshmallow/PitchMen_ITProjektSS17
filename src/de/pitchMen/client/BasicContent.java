package de.pitchMen.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Basis-Klasse zur Darstellung der Inhalts-Elemente. 
 * Erweitert das von GWT angebotene VerticalPanel.
 * 
 * @author Simon
 */

public abstract class BasicContent extends VerticalPanel {
	/**
	 * Alle GWT-Widgets und damit auch alle von Widgets
	 * erbenden Klassen müssen die <code>onLoad()</code>
	 * Methode implementieren. SIe definiert, was geschieht
	 * wenn das Widget angezeigt wird.
	 */
	@Override
	public void onLoad() {
		/*
		 * Die Suplerklasse aufrufen, um die korrekte
		 * Funktionsweise von BasicContent als VerticalPanel
		 * zu gewährleisten.
		 */
		super.onLoad();
		
		/*
		 * Dem Inhalt die Kopfzeile hinzufügen. Deren Inhalt
		 * wird in den Subklassen von BasicContent festgelegt,
		 * aber hier, in der Superklasse, hinzugefügt, damit
		 * dieser Schritt nicht in jeder Subklasse extra
		 * ausgeführt werden muss.
		 */
		this.add(this.getContentHeader(this.getHeadline(), this.getDescription()));
		
		/*
		 * Der Aufruf der Methode run() erfolgt hier in dem 
		 * Wissen, dass die (in dieser Klasse abstrakte) 
		 * Methode in den Subklassen implementiert wird.
		 */
		run();
	}

	/**
	 * Die Methode <code>getHeadline()</code> wird in
	 * BasicContent abstrakt angebeben und in Subklassen um
	 * einen Methodenkörper ergänzt. Dieser enthält dann die 
	 * konkrete Überschrift der instanzierten Klasse.
	 * 
	 * @return Überschrift-String, der zur Anzeige gebracht wird
	 */
	protected abstract String getHeadline();
	
	/**
	 * Die Methode <code>getDescription()</code> wird in
	 * BasicContent abstrakt angegeben und in Subklassen um
	 * einen Methodenkörper ergänzt. Dieser enthält dann die 
	 * konkrete Beschreibung der instanzierten Klasse.
	 * 
	 * @return Beschreibungs-String, der zur Anzeige gebracht wird
	 */
	protected abstract String getDescription();
	
	/**
	 * Im Gegensatz zu den Methoden {@link #getHeadline()} und 
	 * {@link #getDescription()} wird <code>getContentHeader()</code>
	 * in der abstrakten Superklasse BasicContent implementiert.
	 * Damit wird erreicht, dass nicht in jeder Subklasse die 
	 * Ausgabe der dort angegebenen Überschriften und Beschreibungen
	 * implementiert werden muss. 
	 * 
	 * @param String headline
	 * @param String desc
	 * @return Das HTML-Widget, das den Header des Contents darstellt
	 */
	protected HTML getContentHeader(String headline, String desc) {
		/*
		 * Erstellung eines HTML-Widgets, das die übergebenen Strings
		 * headline und desc in ein einfaches HTML-Konstrukt einbaut. 
		 */
		HTML header = new HTML("<h2>" + headline + "</h2>" + "<p class='desc'>" + desc + "</p>");
		return header;
	}
	
	/**
	 * Die Methode <code>run()</code> wird in
	 * BasicContent abstrakt angegeben und in Subklassen um
	 * einen Methodenkörper ergänzt. Dort werden die
	 * Befehle implementiert, die die konkret für den 
	 * Anwendungsfall der Subklasse nötig sind. Vorlage für
	 * dieses Vorgehen war die Realisierung der <code>run()
	 * </code>-Methode in der Klasse <code>Showcase</code>
	 * im BankProjket von P. Thies & C. Rathke.
	 */
	protected abstract void run();
}