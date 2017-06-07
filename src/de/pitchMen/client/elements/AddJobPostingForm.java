package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.client.elements.AddProjectForm.AddProjectFormCallback;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Project;

public class AddJobPostingForm extends Formular {

	private int prID;

	Label titleLabel = new Label("Name der Ausschreibung:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung der Ausschreibung:");
	TextBox descBox = new TextBox();
	Label deadlineLabel = new Label("Bewerbungsdeadline angeben: ");
	DateBox deadlineBox = new DateBox();

	protected void onLoad(int projectID) {

		this.prID = projectID;

		Button cancelButton = new Button("Abbrechen" + new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*
				 * Wenn man auf den Cancel Button drückt, wird man auf das
				 * angewählten Projekt zurückgeführt
				 */
				ProjectForm prf = new ProjectForm(prID);

			}
		});
		// ---------- Speichern-Button
		Button saveButton = new Button("Speichern" + new ClickHandler() {

			public void onClick(ClickEvent event) {
				// TODO: erstellen eines anonymen Partnerprofils für die
				// Ausschreibung fehlt noch!

				AddJobPostingForm f1 = new AddJobPostingForm();
				f1.save(prID);

			}
		});

	}

	// ---------- speichern
	public void save(int projektID) {

		super.getPitchMenAdmin().addJobPosting(titleBox.getText(), descBox.getText(), deadlineBox.getValue(), projektID,
				new AddJobPostingCallback(this));

		super.setCreator(ClientsideSettings.getCurrentUser().getId());

	}

	public class AddJobPostingCallback implements AsyncCallback<JobPosting> {

		private AddJobPostingForm addJobPostingForm = null;

		public AddJobPostingCallback(AddJobPostingForm addJobPostingForm) {
			this.addJobPostingForm = addJobPostingForm;
		}

		public void onFailure(Throwable caught) {

			this.addJobPostingForm.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}

		public void onSuccess(JobPosting jobposting) {

			Window.alert("erfolgreich gespeichert");

		}

	}
}
