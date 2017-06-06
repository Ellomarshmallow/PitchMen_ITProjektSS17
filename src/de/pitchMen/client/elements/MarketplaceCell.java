package de.pitchMen.client.elements;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.pitchMen.shared.bo.Marketplace;

/**
 * Die Klasse <code>MarketplaceCell</code> ist dafür 
 * zuständig, Objekte der Businessobjekt-Klasse 
 * {@link Marketplace} für die Webansicht in der GUI
 * aufzubereiten.
 * 
 * @author Simon
 */

public class MarketplaceCell extends AbstractCell<Marketplace> {

	/**
	 * Die <code>render()</code>-Methode ist die einzige
	 * von der abstrakten Klasse {@link AbstractCell} 
	 * vorgeschriebene Methode und enthält Anweisungen,
	 * wie das Marketplace-Objekt in der HTML-Struktur
	 * präsentiert wird.
	 */
	@Override
	public void render(Context context, Marketplace value, SafeHtmlBuilder sb) {
		
		/*
		 * Der null-Check stellt sicher, dass auch wirklich
		 * nur echte Objekte gerendert werden, theoretisch
		 * kann der value-Parameter nämlich auch null sein.
		 */
		if(value == null) {
			return;
		}
		
		/*
		 * Der Titel und die Beschreibung eines Projektmarktplatzes
		 * werden in ein div-Element gerendert. Dabei befindet sich 
		 * der Titel in einem h3-, die Beschreibung in einem p-Element.
		 * Spezifische Klassen für das umschließenede div-Element
		 * ermöglichen später ein einheitliches Design mittels CSS.
		 */
		sb.appendHtmlConstant("<div class='cell marketplaceCell'><p>");
		sb.appendEscaped(value.getTitle());
		sb.appendHtmlConstant("</p></div>");
		
	}

}
