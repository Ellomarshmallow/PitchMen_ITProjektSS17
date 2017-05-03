package de.pitchMen.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Klasse zur Darstellung der Inhalts-Elemente. Erweitert das von GWT angebotene VerticalPanel.
 * 
 * @author Simon
 *
 */

public class Content extends VerticalPanel {
	
	/**
	 * Der Titel dieses Inhaltselements.
	 */
	private HTML title = new HTML("");
	
	/**
	 * Eine Beschreibung des aktuellen Inhalts. Kann leer bleiben.
	 */
	private HTML description = new HTML("");
	
	/**
	 * Das <code>DockPanel</code> topBar wird im Konstruktor 
	 * zum ersten Element des Content-Objekts und ethält den Titel
	 * sowie situationsbedingt unterschiedliche Buttons.
	 */
	private DockPanel topBar = new DockPanel();
	
	/**
	 * Das <code>HorizontalPanel</code> buttons wird in der GUI rechts 
	 * neben dem Content-Titel ausgerichtet und beinhaltet die Interaktions-
	 * Buttons, die dem Nutzer in der jeweiligen Ansicht zur Verfügung stehen.
	 */
	private HorizontalPanel buttons = new HorizontalPanel();
	
	/**
	 * Das <code>Grid</code> grid enthält Hauptinhlatselemente.
	 */
	private Grid grid = new Grid();
	
	/**
	 * Zähler zum Iterieren der Grid-Spalten
	 */
	private int gridColCounter = 0;
	
	/**
	 * Zähler zum Iterieren der Grid-Zeilen
	 */
	private int gridRowCounter = 0;
	
	public Content(String title, String description, int rows) {
		this.setWidth("100%");
		this.topBar.setWidth("100%");
		// TODO Lösung ohne absolute Pixelangaben finden
		this.title.setWidth("500px");
		this.buttons.setWidth("100%");
		// Button-Panel: align Buttons rechts
		this.buttons.setHorizontalAlignment(ALIGN_RIGHT);
		// Der übergebene String title wird zur Überschrift des Content-Elements
		this.title.setHTML("<h2>" + title + "</h2>");
		// Der String description wird in ein Paragraph-Tag (fett) eingebettet
		this.description.setHTML("<p><strong>" + description + "</p><strong>");
		this.description.addStyleName("desc");
		// Das HTML-Widget des Titels wird der topBar hinzugefügt
		this.topBar.add(this.title,DockPanel.WEST);
		// Die topBar wird dem Content-Element hinzugefügt
		this.add(this.topBar);
		// Das HTML-Widget wird dem Content-Element hinzugefügt
		this.add(this.description);
		// Das Button-Panel wird dem Content-Element hinzugefügt
		this.topBar.add(buttons,DockPanel.EAST);
		// Grid hinzufügen und Dessen Dimensionen festlegen
		this.grid.resize(1, 3);
		this.grid.setWidth("100%");
		this.add(grid);
	}

	/**
	 * Methode zum Hinzufügen von Buttons zum HorizontalPanel buttons.
	 * Dabei wird das Styling für jeden Button automatisch festgelegt.
	 */
	public void addTopBarButton(Button btn) {
		btn.addStyleName("topbar-btn");
		this.buttons.add(btn);
	}
	
	/**
	 * Methode zum Hinzufügen von Widgets zum Grid des Contens.
	 * @param das hinzuzufügende Widget-Objekt
	 */
	public void addToGrid(Widget w) {
		if(this.gridColCounter == 3) {
			this.gridColCounter = 0;
			this.gridRowCounter++;
			this.grid.resize((this.gridRowCounter+1), 3);
		}
		this.grid.setWidget(this.gridRowCounter, this.gridColCounter++, w);
	}

}
