package de.pitchMen.client.elements;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.pitchMen.shared.bo.Project;

/**
 * Die Klasse <code>ProjectCell</code> ist dafür 
 * zuständig, Objekte der Businessobjekt-Klasse 
 * {@link Project} für die Webansicht in der GUI
 * aufzubereiten.
 * 
 * @author Simon
 */

public class ProjectCell extends AbstractCell<Project> {

	@Override
	public void render(Context context, Project value, SafeHtmlBuilder sb) {	
		/*
		 * Der null-Check stellt sicher, dass auch wirklich
		 * nur echte Objekte gerendert werden, theoretisch
		 * kann der value-Parameter nämlich auch null sein.
		 */
		if(value == null) {
			return;
		}
		
		/*
		 * Der Titel und die Beschreibung eines Projekts werden
		 * in ein div-Element gerendert. Dabei befindet sich 
		 * der Titel in einem h4--Element. Spezifische Klassen für 
		 * das umschließenede div-Element ermöglichen später ein 
		 * einheitliches Design mittels CSS.
		 */
		sb.appendHtmlConstant("<div class='cell projectCell'><p>");
		sb.appendEscaped(value.getTitle());
		sb.appendHtmlConstant("</p></div>");
		
	}

}
