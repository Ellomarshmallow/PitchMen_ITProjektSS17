package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
				
				/* ---------- Bewerben-Button, ClickHandler hinzufügen und 
	 						* dem HorizontalPanel hinzufügen */
				Button applicateButton = new Button ("Hier Bewerben"); 
				applicateButton.addClickHandler(new applicateClickHandler());
				buttonsPanel.add(applicateButton);
				
				/*
				 * Wenn die aktuelle UserId gleich der PersonId ist, dann hat dieser die
				 * Buttons Ausschreibung hinzufügen, Löschen und Bearbeiten zur verfügung.
				 * Vgl. hasPermission() in Formular.java
				 */
				if (hasPermission(this.selectedProject)) {
					/* ---------- Neue Ausschreibung-Button, ClickHandler hinzufügen und 
					 			* dem HorizontalPanel hinzufügen */
					Button addJobPostingBtn = new Button("+ Neue Ausschreibung hinzufügen");
					addJobPostingBtn.addClickHandler(new addJobPostingClickHandler());
					buttonsPanel.add(addJobPostingBtn);

					/* ---------- Ausschreibung löschen, ClickHandler hinzufügen und dem
								* HorizontalPanel hinzufügen */
					Button deleteJobPostingBtn = new Button("- Ausschreibung löschen");
					deleteJobPostingBtn.addClickHandler(new deleteJobPostingClickHandler());
					buttonsPanel.add(deleteJobPostingBtn);

					/* ---------- Ausschreibung bearbeiten, ClickHandler hinzufügen und dem
					 			* HorizontalPanel hinzufügen */

					Button updateJobPostingBtn = new Button("Bearbeiten");
					updateJobPostingBtn.addClickHandler(new updateJobPostingClickHandler());
					buttonsPanel.add(updateJobPostingBtn);


				}


	}


		// ---------- ClickHandler
	
	// ---------- addJobPostingClickHandler
		private class addJobPostingClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {

				AddJobPostingForm addJobPosting = new AddJobPostingForm(selectedJobPosting,pitchMenTreeViewModel,true);

			}
		}
	
		// ---------- deleteJobPostingClickHandler
		private class deleteJobPostingClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {

				// bei Click wird die delete() Methode aufgerufen

				if (Window.confirm("Sind Sie sich sicher, dass Sie das löschen wollen?")) {
					delete();
				}

			}
		}

		
		// ---------- updateJobPostingClickHandler
		private class updateJobPostingClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {

				// bei Click wird die update() Methode aufgerufen
				AddJobPostingForm updateJobPosting = new AddJobPostingForm(selectedJobPosting,pitchMenTreeViewModel,false);

			}
		} 
		private class applicateClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {

				ApplicationForm applicationForm = new ApplicationForm(selectedJobPosting);

			}
		} 
		
		

		public void delete() {
			
			super.getPitchMenAdmin().deleteJobPosting(selectedJobPosting, new DeleteJobPostingCallback(selectedJobPosting));


		}

		class DeleteJobPostingCallback implements AsyncCallback<Void> {

			JobPosting j = null;

			public DeleteJobPostingCallback(JobPosting j) {
				this.j = j;
			}

			public void onFailure(Throwable caught) {
				Window.alert("Das Löschen der Ausschreibung ist fehlgeschlagen!");

			}

			public void onSuccess(Void result) {
				if (j != null) {
					setSelectedJobPosting(null);
				
					pitchMenTreeViewModel.deleteJobPosting(selectedJobPosting, selectedProject);
				}
			}
		}
		
		// ----------pitchMenTreeViewModelsetter
		public void setPitchMenTreeViewModel(PitchMenTreeViewModel pitchMenTreeViewModel) {
			this.pitchMenTreeViewModel = pitchMenTreeViewModel;
		}

		
		
		// ---------- selectedProject setter
				public void setSelectedJobPosting(JobPosting j) {
					if (j != null) {
						this.selectedJobPosting = j;
						titleBox.setText(selectedJobPosting.getTitle());
						descBox.setText(selectedJobPosting.getText()); 						
						statusBox.setText(selectedJobPosting.getStatus());
						deadlineBox.setText(selectedJobPosting.getStatus());
						idLabel.setText(Integer.toString(selectedProject.getId()));
					} 
					else {
						idLabel.setText("");
						titleBox.setText("");
						descBox.setText("");
						statusBox.setText("");
						deadlineBox.setText("");
						
					}
				}
	
}
