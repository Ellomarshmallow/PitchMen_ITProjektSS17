package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class ProjectForm extends Formular {

	private int prID;

	public ProjectForm(int ProjectID) {

		super();
		this.prID = ProjectID;

		super.getPitchMenAdmin().getProjectByID(this.prID, new ProjectFormCallback(this));

	}

	class ProjectFormCallback implements AsyncCallback<Project> {

		private ProjectForm projectform = null;

		public ProjectFormCallback(ProjectForm projectForm) {
			this.projectform = projectForm;
		}

		public void onFailure(Throwable caught) {

			this.projectform.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}

		public void onSuccess(Project project){
			if (project != null){

				this.projectform.add(new HTML(project.getTitle()));
				this.projectform.add(new HTML(project.getDescription()));

				Button addJobPostingBtn = new Button("+ Neue Ausschreibung", new ClickHandler(){


					public void onClick(ClickEvent event) {

						AddJobPostingForm addJobPosting = new AddJobPostingForm(); 

						addJobPosting.onLoad(prID); 
						//TODO Klasse AddJobPostingForm mit der Methode onLoad() erstellen.Vgl AddProjectForm
					}}); 

				//DAS FOLGENDE brauchen wir nur wenn man ein neues Projekt
				//auch von anderen Projekten aus erstellen möchte, Fehlend: mpID,
				//							// Neues Projekt Button:
				//
				//							Button addProjectBtn = new Button("+ Neues Projekt hinzufügen", new ClickHandler() {
				//
				//								public void onClick(ClickEvent event) {
				//
				//									AddProjectForm addProject = new AddProjectForm();
				//
				//									addProject.onLoad(mpID);
				//
				//								}
				//							});				
				//					


				if(hasPermission()){

					// Projekt löschen

					Button deleteProjectBtn = new Button("- Ausgewähltes Projekt löschen", new ClickHandler(){


						public void onClick(ClickEvent event) {

							//bei Click wird die Methode delete()  aufgerufen

							if(Window.confirm("Sind Sie sich sicher, dass Sie das Projekt löschen wollen?")){
								delete(); 
								//TODO implement delete() 

							}

						}}); 

					Button updateProjectBtn = new Button("Bearbeiten", new ClickHandler(){


						public void onClick(ClickEvent event) {

							//bei Click wird die Methode update()  aufgerufen


							update(); 
							//TODO implement update() 



						}}); 

				}
			}}
	}