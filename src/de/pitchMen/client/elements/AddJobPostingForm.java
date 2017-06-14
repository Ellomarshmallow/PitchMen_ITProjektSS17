package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Project;

public class AddJobPostingForm extends Formular {


	private Project selectedProject = null;
	private JobPosting selectedJobPosting = null; 
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Projektes:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung des Projektes:");
	TextBox descBox = new TextBox();
	Label statusLabel = new Label("aktueller Status: "); 
	TextBox statusBox = new TextBox(); 
	Label deadlineLabel = new Label("Deadline: "); 
	DatePicker deadlineBox = new DatePicker(); 
	private boolean isSave = false; 
	
	AddJobPostingForm(PitchMenTreeViewModel pitchMenTreeViewModel, boolean isSave) {
		this(null, pitchMenTreeViewModel, isSave);
	}
	
	AddJobPostingForm(JobPosting selectedJobPosting,PitchMenTreeViewModel pitchMenTreeViewModel,boolean isSave) {
		
		this.selectedJobPosting = selectedJobPosting;
		this.pitchMenTreeViewModel = pitchMenTreeViewModel; 
		this.isSave = isSave; 
		this.selectedJobPosting = pitchMenTreeViewModel.getSelectedJobPosting(); 
		
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
		
		
		Button cancelButton = new Button("Abbrechen" + new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*
				 * Wenn man auf den Cancel Button drückt, wird man auf die
				 * angewählten Ausschreibung zurückgeführt
				 */
				JobPostingForm jpf = new JobPostingForm(getSelectedJobPosting());

			}
		});
		// ---------- Speichern-Button
		Button saveButton = new Button("Speichern" + new ClickHandler() {

			public void onClick(ClickEvent event) {
				// TODO: erstellen eines anonymen Partnerprofils für die
				// Ausschreibung fehlt noch!

				if (Window.confirm("Sind alle Angaben korrekt?")) {

					if(getIsSave()){

						/* bei Click wird die unten implementierte Methode save()
						 * aufgerufen.
						 */
						save();
						JobPostingForm jf = new JobPostingForm(getSelectedJobPosting());
					}
					else{
						update();
						JobPostingForm jf = new JobPostingForm(getSelectedJobPosting());
					}

				}
			}
		});

	}

	// ---------- speichern
	public void save() {
		
		super.getPitchMenAdmin().addJobPosting(titleBox.getText(), descBox.getText(), statusBox.getText(),
				deadlineBox.getValue(), this.selectedProject.getId(), new AddJobPostingCallback(this));

	}

	public class AddJobPostingCallback implements AsyncCallback<JobPosting> {

		private AddJobPostingForm addJobPostingForm = null;

		public AddJobPostingCallback(AddJobPostingForm addJobPostingForm) {
			this.addJobPostingForm = addJobPostingForm;
		}

		public void onFailure(Throwable caught) {
			//FIXME andere onFailure Message? 
			this.addJobPostingForm.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}

		public void onSuccess(JobPosting jobposting) {

			Window.alert("erfolgreich gespeichert");
			pitchMenTreeViewModel.addJobPosting(selectedJobPosting, selectedProject);

		}

	}
	
	// ---------- update Methode

		public void update() {
			if (selectedJobPosting != null) {
				selectedJobPosting.setTitle(titleBox.getText());
				selectedJobPosting.setText(descBox.getText());
				selectedJobPosting.setDeadline(deadlineBox.getValue());
				super.getPitchMenAdmin().updateJobPosting(selectedJobPosting, new UpdateJobPostingCallback());
			}}


		// ---------- update Callback
		class UpdateJobPostingCallback implements AsyncCallback<Void> {


			public void onFailure(Throwable caught) {
				Window.alert("Das Bearbeiten der Ausschreibung ist fehlgeschlagen!");

			}

			public void onSuccess(Void result) {
				pitchMenTreeViewModel.updateJobPosting(selectedJobPosting);
			}
		}


	// ---------- getIsSave

	public boolean getIsSave(){
		return this.isSave; 
	}
	
	public JobPosting getSelectedJobPosting() {
		return this.selectedJobPosting; 
	}
}
