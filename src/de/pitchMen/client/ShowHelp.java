package de.pitchMen.client;

import com.google.gwt.user.client.ui.HTML;

/** Die Klasse <code> ShowHelp </code> erweitert die abstrakte Klasse 
 * {@link BasicContent}. Die Klasse gibt Hilfestellungen in HTML aus.
 * 
 *    @author Leon Schelle
 *    **/


public class ShowHelp extends BasicContent{

	@Override
	/** Die abstrakten Methoden getHeadLine und getDescription
	 * aus der Superklasse BasicContent werden hier überschrieben.**/

	protected String getHeadline(){
		return "Hilfe" ;
	}

	protected String getDescription(){
		return "Hier finden Sie häufig gestellte Fragen:" ; 
	}

	/** Implementierung der Methode run(), welche in der Superklasse BasicContent 
	 *  in der onLoad() Methode aufgerufen wird**/

	protected void run(){
		this.add(new HTML("<div style = 'border: 1px solid black;width:400px; height:100px;'>"
				+ " <h3> 1. Was ist der Report Generator? <br> </h3>" 
				+ "<small>Der Report Generator zeigt Ihnen ..... </small>"
				+"</div>"
				+ "<br> <br>"
				+ "<div style = 'border: 1px solid black;width:400px; height:100px;'>"
				+"<h3> 2. Noch eine Frage...? <br> </h3>"
				+ "<small> hier kommt dann die Antwort hin...</small>"
				+"</div>"));
	}



}
