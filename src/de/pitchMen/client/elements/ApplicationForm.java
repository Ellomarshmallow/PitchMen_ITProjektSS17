package de.pitchMen.client.elements;

import de.pitchMen.shared.bo.JobPosting;

/**
 * Klasse, deren Objekte ein Formular
 * zur Bewerbung auf eine Ausschreibung 
 * dienen.
 * 
 * @author Simon
 */

public class ApplicationForm extends Formular {
	
	/**
	 * Um die Zugehörigkeit der mit dem Formular
	 * zu erstellenden Bewerbung zu einem Projekt
	 * feststellen zu können, benötigen Objekte der
	 * Klasse {@link ApplicationForm} ein Attribut
	 * <code>referredJobPosting</code> vom Typ
	 * {@link JobPosting}. 
	 */
	private JobPosting referredJobPosting = null;
	
	/**
	 * Beim Anlegen eines neuen <code>ApplicationForm</code>
	 * Objekts wird das JobPosting-Objekt übergeben, auf das
	 * sich die anzulegende Bewerbung beziehen soll.
	 */
	public ApplicationForm(JobPosting jobPosting) {
		this.referredJobPosting = jobPosting;
	}
}
