package de.pitchMen.client.elements;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.pitchMen.shared.bo.BusinessObject;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

/**
 * Die Klasse <code>GridElement</code> erweitert die von
 * GWT zur Verfügung gestellte Klasse {@link VerticalPanel}
 * und dient der Darstellung von Objekten der Subklassen der
 * Klasse {@link BusinessObject}. Sie enthält als einziges
 * Attribut eine <code>id</code> vom Typ <code>int</code>,
 * das beim Konstuktor-Aufruf durch die Show-Klassen im 
 * dieser Klasse übergeordneten client-Package gesetzt wird.
 * Damit wird erreicht, dass die GUI intelligente Grid-Elemente
 * zur Verfügung hat, die die ID der durch sie repräsentierten
 * Business-Objekte kennen.
 * 
 * @author Simon
 */

public class GridElement extends VerticalPanel {
	
	/**
	 * Die Variable <code>id</code> enthält nach dem 
	 * Konstruktor-Aufruf durch die Show-Klassen die
	 * <code>id</code> des durch das aktuelle Objekt
	 * repräsentierten Business-Objekts.
	 * 
	 */
	private int id = 0;
	
	/**
	 * Beim Konstruktor-Aufruf wird durch die Show-Klasse
	 * eine Instanz einer Subklasse von {@link BusinessObject}
	 * an das GridElement-Objekt übergeben. In diesem 
	 * Konstruktor wird für all diese Aufrufe die <code>id</code>
	 * des Business-Objekts auf das Grid-Element übertragen. Nun 
	 * kann auch nach dem RPC-Call und der Ausgabe der Elemente
	 * festgestellt werden, welches Objekt der Nutzer später wählt.
	 * 
	 * @param die ID des zu repräsentierenden Business-Objekts
	 */
	private GridElement(int id) {
		/*
		 * Der Aufruf des Super-Konstruktors sorgt dafür, dass sich 
		 * Objekte der Klasse Grid-Element bei der Anzeige verhalten
		 * wie ein VerticalPanel.
		 */
		super();
		
		// Zuweisen der id
		this.id = id;
		
		/*
		 * Durch das Hinzufügen eines CSS-Styles können Grid-Elemente
		 * später einheitlich gestaltet werden.
		 */
		this.addStyleName("grid-element");
	}
	
	/**
	 * Der Konstruktor erzeugt ein GridElement-Objekts zur
	 * Repräsentation von Objekten der Klasse {@link Marketplace}.
	 * 
	 * @param Das anzuzeigende Marketplace-Objekt
	 */
	public GridElement(Marketplace marketplace) {
		// Aufruf des allgemeinen GridElement-Konstruktors zum Setzen der id
		this(marketplace.getId());
		
		// Ausgabe des Titels
		this.add(new HTML("<h4>" + marketplace.getTitle() + "</h4>"));
		
		// Ausgabe der Beschreibung
		this.add(new HTML("<p>" + marketplace.getDescription() + "</p>"));
	}
	
	/**
	 * Der Konstruktor erzeugt ein GridElement-Objekts zur
	 * Repräsentation von Objekten der Klasse {@link Project}.
	 * 
	 * @param Das anzuzeigende Project-Objekt
	 */
	public GridElement(Project project) {
		// Aufruf des allgemeinen GridElement-Konstruktors zum Setzen der id
		this(project.getId());
		
		// Ausgabe des Titels
		this.add(new HTML("<h4>" + project.getTitle() + "</h4>"));
		
		// Ausgabe der Beschreibung
		this.add(new HTML("<p>" + project.getDescription() + "</p>"));
		
		// Ausgabe von Start- und End-Datum des Projekt
		this.add(new HTML("<p><small>Projekt läuft von "
						  + project.getDateOpened().toString()
						  + " bis "
						  + project.getDateClosed().toString()
						  + "</small></p>"));
	}
	
	/**
	 * Der Konstruktor erzeugt ein GridElement-Objekts zur
	 * Repräsentation von Objekten der Klasse {@link JobPosting}.
	 * 
	 * @param Das anzuzeigende Project-Objekt
	 */
	public GridElement(JobPosting jobPosting) {
		// Aufruf des allgemeinen GridElement-Konstruktors zum Setzen der id
		this(jobPosting.getId());
		
		// Ausgabe des Titels
		this.add(new HTML("<h4>" + jobPosting.getTitle() + "</h4>"));
		
		// Ausgabe der Bewerbungsfrist
		this.add(new HTML("<p><small>Bewerbungsfrist: "
						  + jobPosting.getDeadline().toString()
						  + "</small></p>"));
	}

	/**
	 * Gibt die ID des durch das Grid-Element
	 * repräsentierten Business-Objekts zurück.
	 * 
	 * @return die ID des Grid-Elements
	 */
	public int getId() {
		return id;
	}

	/**
	 * Mit dieser Methode kann die ID des Grid-Elements
	 * nachträglich verändert werden, dies sollte im 
	 * Normfall nicht notwenig sein.
	 * 
	 * @param die neue ID des Grid-Elements
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}