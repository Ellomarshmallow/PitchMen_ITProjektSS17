package de.pitchMen.client.elements;

import de.pitchMen.shared.bo.JobPosting;

/**
 * Klasse für die Bereitstellung eines Formulars
 * zum Anzeigen und Bearbeiten von Ausschreibungen.
 * 
 * @author Simon
 */

public class JobPostingForm {
	
	private JobPosting selectedJobPosting = null;

	public void setSelectedJobPosting(JobPosting jobPosting) {
		this.selectedJobPosting = jobPosting;
	}
}
