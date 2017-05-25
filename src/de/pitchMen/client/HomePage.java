package de.pitchMen.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

/**
 * Die Klasse <code>HomePage</code> erweitert die abstrakte
 * Klasse {@link BasicContent}. Sie dient der Darstellung
 * einer Übersichtsseite, auf die der Nutzer nach erfolgreicher
 * Anmeldung gelangt.
 * 
 * @author Simon
 */

public class HomePage extends BasicContent {

	@Override
	protected String getHeadline() {
		/*
		 * Die Überschrift der Homepage festlegen.
		 */
		return "Herzlich willkommen bei PitchMen.";
	}

	@Override
	protected String getDescription() {
		/*
		 * Den Beschreibungstext der Homepage festlegen.
		 */
		return "Lesen Sie auf dieser Seite, wie Sie PitchMen nutzen können"
				+ "und welche Möglichkeiten Ihnen die tolle Projektmarktplatz-"
				+ "Applikation bietet. Wir freuen uns, dass Sie PitchMen"
				+ "nutzen!";
	}

	@Override
	protected void run() {
		/*
		 * Da es sich bei HomePage nicht um eine Klasse
		 * verwendet, die RP-Calls ausführt, sondern nur
		 * statisches HTML ausgeben wird, kann die Implementierung
		 * der run()-Methode stark vereinfacht im Vergleich zu
		 * anderen Klassen erfolgen.
		 */
		this.add(new HTML("<h3>Was sind Projektmarktplätze?</h3>"));
		
		// createBtn anlegen
		Button createBtn = new Button("Anlegen");
		
		// füge neues Grid-Element hinzu, wenn createBtn geklickt wird
		createBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				/*
				 * Mit dem Ausdruck HomePage.this wird auf das aktuell
				 * aufrufende Objekt der äußeren Klasse referenziert, dem
				 * der Text hinzugefügt weden soll.
				 */
				HomePage.this.add(
						new HTML("<div class='item'><h3>Test-Item</h3><p>Beschreibender Text, "
								+ "der etwas länger ist und hoffentlich schön aussieht.</p></div>"));
			}
			
		});

	}

}
