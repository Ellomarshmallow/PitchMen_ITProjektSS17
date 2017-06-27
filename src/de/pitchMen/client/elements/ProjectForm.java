package de.pitchMen.client.elements;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Trait;

public class ProjectForm extends Formular {

	/*
	 * titleBox und descBox sind hier labels, erst beim Erstellen oder
	 * Bearbeiten ist titleBox eine Textbox und descBox eine TextArea
	 */
	private Marketplace parentMarketplace = null;
	private Project selectedProject = null;
	private ArrayList<JobPosting> jobPostings = null;
	private Person projectManager = null;
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Projektes:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung des Projektes:");
	TextArea descBox = new TextArea();
	Label fromLabel = new Label("Von:");
	Label fromBox = new Label();
	Label toLabel = new Label("Bis:");
	Label toBox = new Label();

	/**
	 * Konstruktor, der bei bestehenden Projekten verwendet wird.
	 * @param project
	 */
	public ProjectForm(Project project) {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));

		ClientsideSettings.getPitchMenAdmin().getProjectByID(project.getId(), new ProjectCallback());
	}
	
	/**
	 * Soll ein neues Projekt angelegt werden, kommt dieser Konstruktor zum Einsatz.
	 * 
	 * @param übergeordneter Projektmarktplatz
	 */
	public ProjectForm(final Marketplace parentMarketplace) {
		RootPanel.get("content").clear();
		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.addStyleName("headline");

		Button cancelButton = new Button("Neuanlage abbrechen");
		cancelButton.addStyleName("delete");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new MarketplaceForm(parentMarketplace));
			}
		});

		Button saveButton = new Button("Projekt anlegen");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				java.util.Date currentDate = new java.util.Date();
				java.sql.Date convertedDate = new java.sql.Date(currentDate.getTime());
				ClientsideSettings.getPitchMenAdmin().addProject(convertedDate, 
																convertedDate, 
																titleBox.getText(), 
																descBox.getText(), 
																ClientsideSettings.getCurrentUser().getId(),
																parentMarketplace.getId(),
																new AsyncCallback<Project>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Fehler beim Updaten des Projekts");
					}

					@Override
					public void onSuccess(Project result) {
						ProjectForm udpatedForm = new ProjectForm(result);
						
					}
					
				});
			}
		});

		topPanel.add(new HTML("<h2>Neues Projekt im Marktplatz <em>" + parentMarketplace.getTitle() + "</em> anlegen</em></h2>"));
		topPanel.add(saveButton);
		topPanel.add(cancelButton);
		RootPanel.get("content").add(topPanel);

		RootPanel.get("content").add(new HTML("<h3>Titel des Projekts</h3>"));
		
		RootPanel.get("content").add(titleBox);

		RootPanel.get("content").add(new HTML("<h3>Projektbeschreibung</h3>"));
		
		RootPanel.get("content").add(descBox);

	}

	private class ProjectCallback implements AsyncCallback<Project> {

		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Empfangen der Projekte fehlgeschlagen");
		}

		public void onSuccess(Project result) {

			selectedProject = result; 

				ClientsideSettings.getPitchMenAdmin().getMarketplaceByID(result.getMarketplaceId(), new AsyncCallback<Marketplace>(){

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Empfangen des Marktplatzes fehlgeschlagen");
					}

						public void onSuccess(Marketplace result){

							parentMarketplace = result; 

							ClientsideSettings.getPitchMenAdmin().getJobPostingsByProjectId(selectedProject.getId(), new AsyncCallback<ArrayList<JobPosting>>(){

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().severe("Empfangen der Ausschreibungen fehlgeschlagen");
								}
								
								public void onSuccess(ArrayList<JobPosting> result){

										jobPostings = result; 

										ClientsideSettings.getPitchMenAdmin().getPersonByID(selectedProject.getPersonId(), new AsyncCallback<Person>(){
										
											public void onFailure(Throwable caught) {
												ClientsideSettings.getLogger().severe("Empfangen des Projektleiters fehlgeschlagen");
											}

											public void onSuccess(Person result){
												projectManager = result;
												
												HorizontalPanel topPanel = new HorizontalPanel();
												topPanel.addStyleName("headline");
												
												RootPanel.get("content").clear();
												
												topPanel.add(new HTML("<h2>Projekt: <em>" + selectedProject.getTitle() + "</em></h2>"));
												
												if (hasPermission(selectedProject)) {
			
													RootPanel.get("content").add(new HTML("<div class='info'><p><span class='fa fa-info-circle'></span>"
															+ " Sie sind Besitzer dieses Projekts. </p></div>"));
			
													// ---------- Projekt löschen, ClickHandler hinzufügen und dem
													// HorizontalPanel hinzufügen
													Button deleteProjectBtn = new Button("Projekt löschen");
													deleteProjectBtn.addStyleName("delete");
													deleteProjectBtn.addClickHandler(new deleteProjectClickHandler());
													topPanel.add(deleteProjectBtn);
			
													// ---------- Projekt bearbeiten, ClickHandler hinzufügen und dem
													// HorizontalPanel hinzufügen
			
													Button updateProjectBtn = new Button("Bearbeiten");
													updateProjectBtn.addClickHandler(new updateProjectClickHandler());
													topPanel.add(updateProjectBtn);
			
													// ---------- Neue Ausschreibung Button, ClickHandler hinzufügen und dem
													// HorizontalPanel hinzufügen
													Button addJobPostingBtn = new Button("Neue Ausschreibung hinzufügen");
													addJobPostingBtn.addClickHandler(new addJobPostingClickHandler());
													topPanel.add(addJobPostingBtn);
			
												}
			
												RootPanel.get("content").add(topPanel);
			
												RootPanel.get("content").add(new HTML("<h3>Übergeordneter Marktplatz: </h3><p>" + parentMarketplace.getTitle() + "</p>"));
			
												RootPanel.get("content").add(new HTML("<h3>Projektbeschreibung</h3><p> " + selectedProject.getDescription() + "</p>"));
			
												RootPanel.get("content").add(new HTML("<h3> Von: </h3> <p> " + selectedProject.getDateOpened() + "</p>"));
			
												RootPanel.get("content").add(new HTML("<h3> Bis: </h3> <p> " + selectedProject.getDateClosed() + "</p>"));
			
												RootPanel.get("content").add(new HTML("<h3> Projektleiter: </h3> <p> " + projectManager.getFirstName() + " " + projectManager.getName() + "</p>"));
			
												RootPanel.get("content").add(new HTML("<h3> Anzahl der enthaltenen Ausschreibungen: </h3> <p>" + jobPostings.size() + "</p>"));
												

								}});
						}});
				}});
		}

	}

	// ---------- ClickHandler

	// ---------- addProjectClickHandler
	private class addProjectClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			AddProjectForm addProject = new AddProjectForm(selectedProject, pitchMenTreeViewModel, true);

		}
	}

	// ---------- addJobPostingClickHandler()
	private class addJobPostingClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			JobPostingForm addJobPosting = new JobPostingForm(selectedProject);
			RootPanel.get("content").add(addJobPosting);

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
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new HTML(
					"<div class='info'><p><span class='fa fa-info-circle'></span> Sie bearbeiten dieses Projekt.</p></div>"));
			HorizontalPanel topPanel = new HorizontalPanel();
			topPanel.addStyleName("headline");

			Button cancelButton = new Button("Bearbeitung abbrechen");
			cancelButton.addStyleName("delete");
			cancelButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new ProjectForm(selectedProject));
				}
			});

			Button saveButton = new Button("Änderungen speichern");
			saveButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					selectedProject.setTitle(titleBox.getText());
					selectedProject.setDescription(descBox.getText());
					ClientsideSettings.getPitchMenAdmin().updateProject(selectedProject, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().severe("Fehler beim Updaten des Projekts");
						}

						@Override
						public void onSuccess(Void result) {
							ProjectForm udpatedForm = new ProjectForm(selectedProject);
						}
						
					});
				}
			});

			topPanel.add(new HTML("<h2>Projekt: <em>" + selectedProject.getTitle() + "</em></h2>"));
			topPanel.add(saveButton);
			topPanel.add(cancelButton);
			RootPanel.get("content").add(topPanel);

			RootPanel.get("content").add(new HTML("<h3>Titel des Projekts</h3>"));
			
			titleBox.setText(selectedProject.getTitle());
			RootPanel.get("content").add(titleBox);

			RootPanel.get("content").add(new HTML("<h3>Projektbeschreibung</h3>"));
			
			descBox.setText(selectedProject.getDescription());
			RootPanel.get("content").add(descBox);

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

				pitchMenTreeViewModel.deleteProject(p, parentMarketplace);
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
		} else {
			idLabel.setText("");
			titleBox.setText("");
			descBox.setText("");
			fromBox.setText("");
			toBox.setText("");

		}
	}

	// ---------- selectedMarketplace setter
	public void setSelectedMarketplace(Marketplace m) {
		this.parentMarketplace = m;
	}
}