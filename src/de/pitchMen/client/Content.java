package de.pitchMen.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	private HorizontalPanel buttons = new HorizontalPanel();
	
	public Content(String title, String description) {
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
		// Das HTML-Widget des Titels wird der topBar hinzugefügt
		this.topBar.add(this.title,DockPanel.WEST);
		// Die topBar wird dem Content-Element hinzugefügt
		this.add(this.topBar);
		// Das HTML-Widget wird dem Content-Element hinzugefügt
		this.add(this.description);
		// Das Button-Panel wird dem Content-Element hinzugefügt
		this.topBar.add(buttons,DockPanel.EAST);
	}

	/**
	 * 
	 */
	public void addTopBarButton(Button btn) {
		btn.addStyleName("topbar-btn");
		this.buttons.add(btn);
	}

}
