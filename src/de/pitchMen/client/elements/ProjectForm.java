package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class ProjectForm extends Formular {

	/*
	 * titleBox und descBox sind hier labels, erst beim Erstellen oder
	 * Bearbeiten ist titleBox eine Textbox und descBox eine TextArea
	 */
	private Marketplace selectedMarketplace = null; 
	private Project selectedProject = null;
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Projektes:");
	Label titleBox = new Label();
	Label descLabel = new Label("Beschreibung des Projektes:");
	Label descBox = new Label();
	Label fromLabel = new Label("Von:");
	Label fromBox = new Label();
	Label toLabel = new Label("Bis:");
	Label toBox = new Label();

	public ProjectForm(Project project) {
		
		
		super();
	 
		
		// Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel();
		this.add(labelsPanel);

		// labels und Boxen dem Vertical Panel hinzufügen
		labelsPanel.add(idLabel);
		labelsPanel.add(titleLabel);
		labelsPanel.add(titleBox);
		labelsPanel.add(descLabel);
		labelsPanel.add(descBox);
		labelsPanel.add(fromLabel);
		labelsPanel.add(fromBox);
		labelsPanel.add(toLabel);
		labelsPanel.add(toBox);

		// HorizontalPanel für die Buttons erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		this.add(buttonsPanel);

		// ---------- Neues Projekt Button, ClickHandler hinzufügen und dem
		// HorizontalPanel hinzufügen
		Button addProjectBtn = new Button("+ Neues Projekt hinzufügen");
		addProjectBtn.addClickHandler(new addProjectClickHandler());
		buttonsPanel.add(addProjectBtn);


		/*
		 * Wenn die aktuelle UserId gleich der PersonId ist, dann hat dieser die
		 * Buttons Projekt Löschen, Bearbeiten und Ausschreibung hinzufügen
		 * zur verfügung. Vgl. hasPermission() in Formular.java
		 */
		if (hasPermission(this.selectedProject)) {

			// ---------- Projekt löschen, ClickHandler hinzufügen und dem
			// HorizontalPanel hinzufügen
			Button deleteProjectBtn = new Button("- Projekt löschen");
			deleteProjectBtn.addClickHandler(new deleteProjectClickHandler());
			buttonsPanel.add(deleteProjectBtn);

			// ---------- Projekt bearbeiten, ClickHandler hinzufügen und dem
			// HorizontalPanel hinzufügen

			Button updateProjectBtn = new Button("Bearbeiten");
			updateProjectBtn.addClickHandler(new updateProjectClickHandler());
			buttonsPanel.add(updateProjectBtn);

			// ---------- Neue Ausschreibung Button, ClickHandler hinzufügen und dem
			// HorizontalPanel hinzufügen
			Button addJobPostingBtn = new Button("+ Neue Ausschreibung hinzufügen");
			addJobPostingBtn.addClickHandler(new addJobPostingClickHandler());
			buttonsPanel.add(addJobPostingBtn);
		}
		
		
	}


	

	
	// ---------- ClickHandler



	// ---------- addProjectClickHandler
	private class addProjectClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			AddProjectForm addProject = new AddProjectForm(selectedProject,pitchMenTreeViewModel,true);

		}
	}
	
	//---------- addJobPostingClickHandler()
	private class addJobPostingClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			AddJobPostingForm addJobposting = new AddJobPostingForm();

		}
	}
	// ---------- deleteProjectClickHandler
	private class deleteProjectClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// bei Click wird die delete() Methode aufgerufen

			if (Window.confirm("Sind Sie sich sicher, dass Sie das löschen wollen?")) {
				delete();
			}

		}
	}

	// ---------- updateProjectClickHandler
	private class updateProjectClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			// bei Click wird die update() Methode aufgerufen
			AddProjectForm updateProject = new AddProjectForm(selectedProject,pitchMenTreeViewModel,false);

		}
	}

	public void delete() {

		super.getPitchMenAdmin().deleteProject(selectedProject, new DeleteProjectCallback(selectedProject));

	}

	class DeleteProjectCallback implements AsyncCallback<Void> {

		Project p = null;

		public DeleteProjectCallback(Project p) {
			this.p = p;
		}

		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Projekts ist fehlgeschlagen!");

		}

		public void onSuccess(Void result) {
			if (p != null) {
				setSelectedProject(null);
			
				pitchMenTreeViewModel.deleteProject(p, selectedMarketplace);
			}
		}
	}

	// ----------pitchMenTreeViewModelsetter
	public void setPitchMenTreeViewModel(PitchMenTreeViewModel pitchMenTreeViewModel) {
		this.pitchMenTreeViewModel = pitchMenTreeViewModel;
	}

	
	
	// ---------- selectedProject setter
			public void setSelectedProject(Project p) {
				if (p != null) {
					this.selectedProject = p;
					titleBox.setText(selectedProject.getTitle());
					descBox.setText(selectedProject.getDescription());
					fromBox.setText(selectedProject.getDateOpened().toString());
					toBox.setText(selectedProject.getDateClosed().toString());
					idLabel.setText(Integer.toString(selectedProject.getId()));
				} 
				else {
					idLabel.setText("");
					titleBox.setText("");
					descBox.setText("");
					fromBox.setText("");
					toBox.setText("");
					
				}
			}
			
			// ---------- selectedMarketplace setter
			public void setSelectedMarketplace(Marketplace m) {
				this.selectedMarketplace = m; 
			}
}