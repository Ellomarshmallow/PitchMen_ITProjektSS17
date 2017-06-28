package de.pitchMen.client.elements;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.pitchMen.shared.bo.JobPosting;

/**
 * Die Klasse <code>ProjectCell</code> ist dafür 
 * zuständig, Objekte der Businessobjekt-Klasse 
 * {@link Project} für die Webansicht in der GUI
 * aufzubereiten.
 * 
 * @author Simon
 */

public class JobPostingCell extends AbstractCell<JobPosting> {

	@Override
	public void render(Context context, JobPosting value, SafeHtmlBuilder sb) {	
		/*
		 * Der null-Check stellt sicher, dass auch wirklich
		 * nur echte Objekte gerendert werden, theoretisch
		 * kann der value-Parameter nämlich auch null sein.
		 */
		if(value == null) {
			return;
		}
		
		/*
		 * Der Titel und die Beschreibung einer Ausschreibung werden
		 * in ein div-Element gerendert. Dabei befindet sich 
		 * der Titel in einem h5-Element. Spezifische Klassen für 
		 * das umschließenede div-Element ermöglichen später ein 
		 * einheitliches Design mittels CSS.
		 */
		sb.appendHtmlConstant("<div class='cell jobPostingCell'><p>");
		sb.appendEscaped(value.getTitle());
		sb.appendHtmlConstant("</p></div>");
		
	}

}
