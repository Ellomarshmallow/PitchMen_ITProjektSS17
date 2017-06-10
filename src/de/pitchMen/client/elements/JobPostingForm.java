package de.pitchMen.client.elements;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

/**
 * Klasse für die Bereitstellung eines Formulars
 * zum Anzeigen und Bearbeiten von Ausschreibungen.
 * 
 * @author Leon
 */

public class JobPostingForm extends Formular{
	
	private Marketplace selectedMarketplace = null; 
	private Project selectedProject = null;
	private JobPosting selectedJobPosting = null; 
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Projektes:");
	Label titleBox = new Label();
	Label descLabel = new Label("Beschreibung des Projektes:");
	Label descBox = new Label();
	Label statusLabel = new Label("aktueller Status: "); 
	Label statusBox = new Label(); 
	Label deadlineLabel = new Label("Deadline: "); 
	Label deadlineBox = new Label(); 
	
	public JobPostingForm(JobPosting jobposting) {
		
		
		// Vertical Panel erstellen
				VerticalPanel labelsPanel = new VerticalPanel();
				this.add(labelsPanel);

				// labels und Boxen dem Vertical Panel hinzufügen
				labelsPanel.add(idLabel);
				labelsPanel.add(titleLabel);
				labelsPanel.add(titleBox);
				labelsPanel.add(descLabel);
				labelsPanel.add(descBox);
				labelsPanel.add(statusLabel);
				labelsPanel.add(statusBox);
				labelsPanel.add(deadlineLabel);
				labelsPanel.add(deadlineBox);

				// HorizontalPanel für die Buttons erstellen
				HorizontalPanel buttonsPanel = new HorizontalPanel();
				this.add(buttonsPanel);
		
		
	}
	
	
	
	

	public void setSelectedJobPosting(JobPosting jobPosting) {
		this.selectedJobPosting = jobPosting;
	}
}
