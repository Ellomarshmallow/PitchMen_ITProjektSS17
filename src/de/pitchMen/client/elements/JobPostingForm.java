package de.pitchMen.client.elements;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.client.Navigation;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Participation;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.bo.Project;
import de.pitchMen.shared.bo.Rating;
import de.pitchMen.shared.bo.Trait;

/**
 * Klasse für die Bereitstellung eines Formulars
 * zum Anzeigen und Bearbeiten von Ausschreibungen.
 * 
 * @author Leon
 */

public class JobPostingForm extends Formular{
 
	/**
	 * Das Projekt, zu dem das anzuzeigende und zu
	 * bearbeitende JobPosting-Objekt dieses Formular-Objekts
	 * gehört.
	 */
	private Project parentProject = null;
	
	/**
	 * Das JobPosting-Objekt, welches in diesem Formular angezeigt
	 * bzw. bearbeitet werden soll.
	 */
	private JobPosting selectedJobPosting = null; 
	
	/**
	 * Zu jedem JobPosting-Objekt gibt es ein PartnerProfile-Objekt.
	 * Dieses muss dem JobPostingForm ebenfalls bekannt sein.
	 */
	private PartnerProfile partnerProfileOfJobPosting = null;
	
	/**
	 * Jedes JobPosting "gehört" einer Person. Anhand dieses Attributs
	 * wird überprüft, ob der aktuell angemeldete Nutzer berechtigt ist,
	 * das JobPosting zu bearbeiten oder sich nur darauf bewerben kann.
	 */
	private Person owningPerson = null;
	
	/**
	 * Die Traits des zugehörigen PartnerProfils werden aufgrund der 
	 * RPC-Abfrage-Struktur separat abgefragt und gespeichert.
	 */
	private ArrayList<Trait> jobPostingTraits = null;
	
	/**
	 * Ist der Nutzer Besitzer des aktuell anzuzeigenden Ausschreibungs-Objekts,
	 * bekommt er in einer Tabelle die auf diese Ausschreibung eingegangene 
	 * Bewerbungen angezeigt.
	 */
	private FlexTable applicationTable  = null;
	
	/**
	 * Zum Bearbeiten des Titels der Ausschreibung.
	 */
	TextBox titleBox = new TextBox();
	
	/**
	 * Zum Bearbeiten des Ausschreibungstextes.
	 */
	TextArea jobPostingText = new TextArea();
	
	/**
	 * Zum Bearbeiten der Deadline der Ausschreibung.
	 */
	DateBox deadlineBox = new DateBox();
	
	/**
	 * Zur Eingabe des numerischen Bewertungs-Werts.
	 */
	TextBox scoreBox = new TextBox();
	
	/**
	 * Zur Eingabe der textuellen Bewertung.
	 */
	TextBox ratingTextBox = new TextBox();
	
	/**
	 * Konstruktor zur Ausgabe eines bestehenden JobPostings in der GUI mithilfe
	 * des JobPostingForms. 
	 * 
	 * @param existieredndes jobPosting-Objekt
	 */
	public JobPostingForm(JobPosting jobPosting) {
		
		// Das RootPanel leeren und den Loading-Screen ausgeben
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
		
		ClientsideSettings.getPitchMenAdmin().getJobPostingByID(jobPosting.getId(), new JobPostingCallback());
	}
	
	/**
	 * Konstruktor zur Anlage eines neuen JobPostings in der GUI mithilfe
	 * des JobPostingForms. 
	 * 
	 * @param übergeordnetes Projekt
	 */
	public JobPostingForm(Project parentProject) {
		
		this.parentProject = parentProject;
		
		// Das RootPanel leeren und den Loading-Screen ausgeben
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
		
		createNewJobPosting();
	}

	/**
	 * In dieser lokalen Klasse findet die Abarbeitung der Anzeige und Bearbeitung
	 * eines bestehenden Ausschreibungs-Objekts statt.
	 */
	private class JobPostingCallback implements AsyncCallback<JobPosting> {

		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Empfangen der Ausschreibung fehlgeschlagen");
		}

		@Override
		public void onSuccess(JobPosting result) {
			
			selectedJobPosting = result;
			
			ClientsideSettings.getPitchMenAdmin().getPartnerProfilesByJobPostingId(selectedJobPosting.getId(), new AsyncCallback<PartnerProfile>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Empfangen des Partner-Profils fehlgeschlagen");
				}

				@Override
				public void onSuccess(PartnerProfile result) {
					
					partnerProfileOfJobPosting = result;
					
					ClientsideSettings.getPitchMenAdmin().getTraitsByPartnerProfileId(partnerProfileOfJobPosting.getId(), new AsyncCallback<ArrayList<Trait>>() {

						@Override
						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().severe("Empfangen der Traits fehlgeschlagen");
						}

						@Override
						public void onSuccess(ArrayList<Trait> result) {
							
							jobPostingTraits = result;
							
							ClientsideSettings.getPitchMenAdmin().getProjectByID(selectedJobPosting.getProjectId(), new AsyncCallback<Project>() {

								@Override
								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().severe("Fehler beim Empfangen der Daten");
								}

								@Override
								public void onSuccess(Project result) {
									
									parentProject = result;
									
									ClientsideSettings.getPitchMenAdmin().getPersonByID(parentProject.getPersonId(), new AsyncCallback<Person>() {

										@Override
										public void onFailure(Throwable caught) {
											ClientsideSettings.getLogger().severe("Abfrage der Person fehlgeschlagen");
										}

										@Override
										public void onSuccess(Person result) {
											owningPerson = result;
											
											RootPanel.get("content").clear();
											
											HorizontalPanel topPanel = new HorizontalPanel();
											topPanel.addStyleName("headline");
											
											topPanel.add(new HTML("<h2>Ausschreibung: <em>" + selectedJobPosting.getTitle() + "</em></h2>"));
											
											if (hasPermission(parentProject)) {
												RootPanel.get("content").add(new HTML("<div class='info'><p><span class='fa fa-info-circle'></span> Sie sind Besitzer dieser Ausschreibung. "
														+ "Sie können sich daher nicht bewerben. Nur Sie sehen die unten aufgeführten Bewerbungen.</p></div>"));
												
												/* ---------- Ausschreibung bearbeiten, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen */
												Button updateJobPostingBtn = new Button("Ausschreibung bearbeiten");
												updateJobPostingBtn.addClickHandler(new UpdateJobPostingClickHandler());
												topPanel.add(updateJobPostingBtn);
												
												/* ---------- Ausschreibung löschen, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen */
												Button deleteJobPostingBtn = new Button("Ausschreibung löschen");
												deleteJobPostingBtn.setStyleName("delete");
												deleteJobPostingBtn.addClickHandler(new deleteJobPostingClickHandler());
												topPanel.add(deleteJobPostingBtn);
											
											} else {
												/* ---------- Bewerben-Button, ClickHandler hinzufügen und dem HorizontalPanel hinzufügen */
												Button applicateButton = new Button ("Auf diese Ausschreibung bewerben"); 
												applicateButton.addClickHandler(new applicateClickHandler());
												topPanel.add(applicateButton);
											}
											
											RootPanel.get("content").add(topPanel);
											
											RootPanel.get("content").add(new HTML("<h3>Übergeordnetes Projekt</h3><p>" + parentProject.getTitle() + "</p>"));
											
											RootPanel.get("content").add(new HTML("<h3>Ausgeschrieben durch</h3><p>" + owningPerson.getFirstName() + " " + owningPerson.getName() + "</p>"));
											
											RootPanel.get("content").add(new HTML("<h3>Deadline</h3><p>" + selectedJobPosting.getDeadline() + "</p>"));
											
											java.sql.Date convertedDate = new java.sql.Date(new java.util.Date().getTime());
											int dateDifference = Math.round((selectedJobPosting.getDeadline().getTime() - convertedDate.getTime()) / 86400000);
											if(dateDifference > 0) {
												RootPanel.get("content").add(new HTML("<p><strong>Diese Ausschreibung läuft noch " + dateDifference + " Tage.</strong></p>"));
											} else if(dateDifference < 0) {
												RootPanel.get("content").add(new HTML("<p><strong>Diese Ausschreibung läuft seit " + (-dateDifference) + " Tagen nicht mehr.</strong></p>"));
											} else {
												RootPanel.get("content").add(new HTML("<p><strong>Diese Ausschreibung läuft nur noch heute!</strong></p>"));
											}
											
											RootPanel.get("content").add(new HTML("<h3>Ausschreibungstext</h3><p> " + selectedJobPosting.getText() + "</p>"));
											
											RootPanel.get("content").add(new HTML("<h3>In dieser Bewerbung verlangte Eigenschaften</h3>"));
											
											FlexTable traitTable = new FlexTable();
											traitTable.setStyleName("traits");
											
											for(final Trait trait : jobPostingTraits) {
												int rowCount = traitTable.getRowCount();
												traitTable.setWidget(rowCount, 0, new HTML("<p><strong>" + trait.getName() + "</strong></p>"));
												traitTable.setWidget(rowCount, 1, new HTML("<p>" + trait.getValue() + "</p>"));
											}
											
											RootPanel.get("content").add(traitTable);
											
											if(hasPermission(parentProject)) {
												RootPanel.get("content").add(new HTML("<h3>Bewerbungen </h3>"));
												ClientsideSettings.getPitchMenAdmin().getApplicationsByJobPostingId(selectedJobPosting.getId(), new AsyncCallback<ArrayList<Application>>() {

													@Override
													public void onFailure(Throwable caught) {
														ClientsideSettings.getLogger().severe("Konnte Ausschreibungen nicht empfangen");
													}

													@Override
													public void onSuccess(ArrayList<Application> applications) {
														
														applicationTable = new FlexTable();
														
														applicationTable.setWidget(0, 0, new HTML("<p><strong>BewerberIn</strong></p>"));
														applicationTable.setWidget(0, 1, new HTML("<p><strong>Anschreiben</strong></p>"));
														applicationTable.setWidget(0, 2, new HTML("<p><strong>Numerische Bewertung</strong></p>"));
														applicationTable.setWidget(0, 3, new HTML("<p><strong>Text-Bewertung</strong></p>"));
														applicationTable.setWidget(0, 4, new HTML("<p><strong>Ablehnen</strong></p>"));
														applicationTable.setWidget(0, 5, new HTML("<p><strong>Annehmen</strong></p>"));
														
														for(final Application app : applications) {
															ClientsideSettings.getPitchMenAdmin().getPartnerProfileByID(app.getPartnerProfileId(), new AsyncCallback<PartnerProfile>() {

																@Override
																public void onFailure(Throwable caught) {
																	ClientsideSettings.getLogger().severe("Konnte PartnerProfil nicht empfangen");
																}

																@Override
																public void onSuccess(PartnerProfile partnerProfile) {
																	ClientsideSettings.getPitchMenAdmin().getPersonByID(partnerProfile.getPersonId(), new AsyncCallback<Person>() {

																		@Override
																		public void onFailure(Throwable caught) {
																			ClientsideSettings.getLogger().severe("Konnte Person nicht empfangen");
																		}

																		@Override
																		public void onSuccess(final Person person) {
																			
																			applicationTable.setStyleName("applications");
																			
																			ClientsideSettings.getPitchMenAdmin().getRatingByApplicationId(app.getId(), new AsyncCallback<Rating>() {

																				@Override
																				public void onFailure(Throwable caught) {
																					ClientsideSettings.getLogger().severe("Konnte Rating nicht empfangen");
																				}

																				@Override
																				public void onSuccess(final Rating result) {
																					if(result != null) {																			
																						Button rejectButton = new Button("Ablehnen");
																						rejectButton.addStyleName("delete");
																						rejectButton.addClickHandler(new ClickHandler() {

																							@Override
																							public void onClick(
																									ClickEvent event) {
																								if(Window.confirm("Möchten Sie diese Bewerbung wirklich ablehnen?")) {
																									app.setStatus("abgelehnt");
																									ClientsideSettings.getPitchMenAdmin().updateApplication(app, new AsyncCallback<Void>() {

																										@Override
																										public void onFailure(
																												Throwable caught) {
																											ClientsideSettings.getLogger().severe("Update des Ratings fehlgeschlagen");
																										}

																										@Override
																										public void onSuccess(
																												Void result) {
																											JobPostingForm updatedForm = new JobPostingForm(selectedJobPosting);
																										}
																										
																									});
																								}
																							}
																							
																						});
																						Button acceptButton = new Button("Annehmen");
																						
																						acceptButton.addClickHandler(new ClickHandler() {

																							@Override
																							public void onClick(
																									ClickEvent event) {
																								if(Window.confirm("Möchten Sie diese Bewerbung wirklich annehmen?")) {
																									app.setStatus("angenommen");
																									ClientsideSettings.getPitchMenAdmin().updateApplication(app, new AsyncCallback<Void>() {

																										@Override
																										public void onFailure(
																												Throwable caught) {
																											ClientsideSettings.getLogger().severe("Update des Ratings fehlgeschlagen");
																										}

																										@Override
																										public void onSuccess(
																												Void result) {
																											RootPanel.get("content").clear();
																											Button saveButton = new Button("Speichern");
																											HorizontalPanel topPanel = new HorizontalPanel();
																											topPanel.addStyleDependentName("headline");
																											topPanel.add(new HTML("<h2>Legen Sie eine Beteilgung für den gerade angenommenen Bewerber an.</h2>"));
																											topPanel.add(saveButton);
																											RootPanel.get("content").add(topPanel);
																											final DateBox dateOpenedBox = new DateBox();
																											final DateBox dateClosedBox = new DateBox();
																											final TextBox workloadBox = new TextBox();
																											
																											RootPanel.get("content").add(new HTML("<p>Beginn der Beteiligung</p>"));
																											RootPanel.get("content").add(dateOpenedBox);
																											RootPanel.get("content").add(new HTML("<p>Ende der Beteiligung</p>"));
																											RootPanel.get("content").add(dateClosedBox);
																											RootPanel.get("content").add(new HTML("<p>Workload in Tagen</p>"));
																											RootPanel.get("content").add(workloadBox);
																											
																											saveButton.addClickHandler(new ClickHandler() {

																												@Override
																												public void onClick(
																														ClickEvent event) {
																													java.sql.Date convertedDateOpened = new java.sql.Date(dateOpenedBox.getValue().getTime());
																													java.sql.Date convertedDateClosed = new java.sql.Date(dateClosedBox.getValue().getTime());
																													ClientsideSettings.getPitchMenAdmin().addParticipation(convertedDateOpened, 
																																										convertedDateClosed, 
																																										Integer.parseInt(workloadBox.getText()), 
																																										parentProject.getId(), 
																																										person.getId(), new AsyncCallback<Participation>() {

																																											@Override
																																											public void onFailure(
																																													Throwable caught) {
																																												ClientsideSettings.getLogger().severe("Fehler beim Anlegen der Beteiligung");
																																											}

																																											@Override
																																											public void onSuccess(
																																													Participation result) {
																																												JobPostingForm updatedForm = new JobPostingForm(selectedJobPosting);
																																											}
																														
																													});
																												}
																												
																											});
																										}
																										
																									});
																								}
																							}
																							
																						});
																						
																						int scorePercentage = Math.round(result.getScore()*100);
																						
																						int rowCount = applicationTable.getRowCount();
																						applicationTable.setWidget(rowCount, 0, new HTML("<p><strong>" + person.getFirstName() + " " + person.getName() + "</strong></p>"));
																						applicationTable.setWidget(rowCount, 1, new HTML(app.getText()));
																						applicationTable.setWidget(rowCount, 2, new HTML("<div class='scoreContainer'><div class='scoreBar' style='width: " + scorePercentage + "%;'></div></div>"));
																						applicationTable.setWidget(rowCount, 3, new HTML("<p>" + result.getStatement() + "</p>"));
																						if(app.getStatus().equals("laufend")) {	
																							applicationTable.setWidget(rowCount, 4, rejectButton);
																							applicationTable.setWidget(rowCount, 5, acceptButton);
																						} else if(app.getStatus().equals("angenommen")) {
																							applicationTable.getFlexCellFormatter().setColSpan(rowCount, 4, 2);
																							applicationTable.setWidget(rowCount, 4, new HTML("<p class='app-status accepted'>angenommen</p>"));
																						} else if(app.getStatus().equals("abgelehnt")) {
																							applicationTable.getFlexCellFormatter().setColSpan(rowCount, 4, 2);
																							applicationTable.setWidget(rowCount, 4, new HTML("<p class='app-status denied'>abgelehnt</p>"));
																						}
																					} else {
																						
																						scoreBox = new TextBox();
																						ratingTextBox = new TextBox();
																						
																						Button rateButton = new Button("Bewerten");
																						rateButton.addClickHandler(new ClickHandler() {

																							@Override
																							public void onClick(ClickEvent event) {
																								if(Window.confirm("Diese Bewerbung jetzt bewerten?")) {
																									if(Float.parseFloat(scoreBox.getText())/100 == 1) {
																										if(Window.confirm("Eine mit 100% bewertete Bewerbung wird automatisch angenommen. Möchten Sie fortfahren?")) {
																											ClientsideSettings.getPitchMenAdmin().addRating(ratingTextBox.getText(), (Float.parseFloat(scoreBox.getText())/100), app.getId(), new AsyncCallback<Rating>() {

																												@Override
																												public void onFailure(
																														Throwable caught) {
																													ClientsideSettings.getLogger().severe("Anlegen der Bewertung fehlgeschlagen");
																												}

																												@Override
																												public void onSuccess(
																														Rating result) {
																													app.setStatus("angenommen");
																													ClientsideSettings.getPitchMenAdmin().updateApplication(app, new AsyncCallback<Void>() {

																														@Override
																														public void onFailure(
																																Throwable caught) {
																															ClientsideSettings.getLogger().severe("Update des Ratings fehlgeschlagen");
																														}

																														@Override
																														public void onSuccess(
																																Void result) {
																															RootPanel.get("content").clear();
																															Button saveButton = new Button("Speichern");
																															HorizontalPanel topPanel = new HorizontalPanel();
																															topPanel.addStyleDependentName("headline");
																															topPanel.add(new HTML("<h2>Legen Sie eine Beteilgung für den gerade angenommenen Bewerber an.</h2>"));
																															topPanel.add(saveButton);
																															RootPanel.get("content").add(topPanel);
																															final DateBox dateOpenedBox = new DateBox();
																															final DateBox dateClosedBox = new DateBox();
																															final TextBox workloadBox = new TextBox();
																															
																															RootPanel.get("content").add(new HTML("<p>Beginn der Beteiligung</p>"));
																															RootPanel.get("content").add(dateOpenedBox);
																															RootPanel.get("content").add(new HTML("<p>Ende der Beteiligung</p>"));
																															RootPanel.get("content").add(dateClosedBox);
																															RootPanel.get("content").add(new HTML("<p>Workload in Tagen</p>"));
																															RootPanel.get("content").add(workloadBox);
																															
																															saveButton.addClickHandler(new ClickHandler() {

																																@Override
																																public void onClick(
																																		ClickEvent event) {
																																	java.sql.Date convertedDateOpened = new java.sql.Date(dateOpenedBox.getValue().getTime());
																																	java.sql.Date convertedDateClosed = new java.sql.Date(dateClosedBox.getValue().getTime());
																																	ClientsideSettings.getPitchMenAdmin().addParticipation(convertedDateOpened, 
																																														convertedDateClosed, 
																																														Integer.parseInt(workloadBox.getText()), 
																																														parentProject.getId(), 
																																														person.getId(), new AsyncCallback<Participation>() {

																																															@Override
																																															public void onFailure(
																																																	Throwable caught) {
																																																ClientsideSettings.getLogger().severe("Fehler beim Anlegen der Beteiligung");
																																															}

																																															@Override
																																															public void onSuccess(
																																																	Participation result) {
																																																JobPostingForm updatedForm = new JobPostingForm(selectedJobPosting);
																																															}
																																		
																																	});
																																}
																																
																															});
																														}
																														
																													});
																												}
																												
																											});
																										}
																									} else {
																										ClientsideSettings.getPitchMenAdmin().addRating(ratingTextBox.getText(), (Float.parseFloat(scoreBox.getText())/100), app.getId(), new AsyncCallback<Rating>() {

																											@Override
																											public void onFailure(
																													Throwable caught) {
																												ClientsideSettings.getLogger().severe("Anlegen der Bewertung fehlgeschlagen");
																											}

																											@Override
																											public void onSuccess(
																													Rating result) {
																												JobPostingForm updatedForm = new JobPostingForm(selectedJobPosting);
																											}
																											
																										});
																									}
																								}
																							}
																							
																						});
																						
																						scoreBox.getElement().setPropertyString("placeholder", "Bewertung in Prozent");
																						ratingTextBox.getElement().setPropertyString("placeholder", "Textuelle Bewertung");
																						
																						int rowCount = applicationTable.getRowCount();
																						applicationTable.setWidget(rowCount, 0, new HTML("<p><strong>" + person.getFirstName() + " " + person.getName() + "</strong></p>"));
																						applicationTable.setWidget(rowCount, 1, new HTML(app.getText()));
																						applicationTable.setWidget(rowCount, 2, scoreBox);
																						applicationTable.getFlexCellFormatter().setColSpan(rowCount, 3, 2);
																						applicationTable.setWidget(rowCount, 3, ratingTextBox);
																						applicationTable.setWidget(rowCount, 4, rateButton);
																					}
																				}
																				
																			});
																			
																		}
																		
																	});
																}
																
															});
														}
														
														RootPanel.get("content").add(applicationTable);
														
													}
													
												});
											}
										}
										
									});					
								}
								
							});
						}
						
					});
				}
				
			});
		}	
	}


		// ---------- ClickHandler
	
		// ---------- deleteJobPostingClickHandler
		private class deleteJobPostingClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {

				// bei Click wird die delete() Methode aufgerufen

				if (Window.confirm("Sind Sie sich sicher, dass Sie die Ausschreibung unwiderruflich löschen wollen?")) {
					delete();
				}

			}
		}

		
		// ---------- updateJobPostingClickHandler
		private class UpdateJobPostingClickHandler implements ClickHandler {
			
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML("<div class='info'><p><span class='fa fa-info-circle'></span> Sie bearbeiten diese Ausschreibung.</p></div>"));
				HorizontalPanel topPanel = new HorizontalPanel();
				topPanel.addStyleName("headline");
				
				Button cancelButton = new Button("Bearbeitung abbrechen");
				cancelButton.addStyleName("delete");
				cancelButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new JobPostingForm(selectedJobPosting));
					}
				});
				
				Button saveButton = new Button("Änderungen speichern");
				saveButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
						selectedJobPosting.setTitle(titleBox.getText());
						selectedJobPosting.setText(jobPostingText.getText());
						java.sql.Date convertedDate = new java.sql.Date(deadlineBox.getValue().getTime());
						selectedJobPosting.setDeadline(convertedDate);
						
						ClientsideSettings.getPitchMenAdmin().updateJobPosting(selectedJobPosting, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().severe("PartnerProfile konnte nicht gespeichert werden.");
							}

							@Override
							public void onSuccess(Void result) {
								JobPostingForm updatedForm = new JobPostingForm(selectedJobPosting);
								RootPanel.get("content").add(updatedForm);
								
								/* 
								 * Beim Updaten einer Ausschreibung wird der Baum im Nav Panel neu geladen.
								 */
							
									RootPanel.get("nav").clear();
									Navigation updatedNavigation = new Navigation();  
									RootPanel.get("nav").add(updatedNavigation);
							}
							
						});
					}
				});
				
				topPanel.add(new HTML("<h2>Ausschreibung: <em>" + selectedJobPosting.getTitle() + "</em></h2>"));
				topPanel.add(saveButton);
				topPanel.add(cancelButton);
				RootPanel.get("content").add(topPanel);
				
				RootPanel.get("content").add(new HTML("<h3>Titel der Ausschreibung</h3>"));
				
				titleBox = new TextBox();
				titleBox.setText(selectedJobPosting.getTitle());
				RootPanel.get("content").add(titleBox);
				
				RootPanel.get("content").add(new HTML("<h3>Deadline</h3>"));
				
				deadlineBox = new DateBox();
				deadlineBox.setValue(selectedJobPosting.getDeadline());
				RootPanel.get("content").add(deadlineBox);
				
				RootPanel.get("content").add(new HTML("<h3>Ausschreibungstext</h3>"));
				
				jobPostingText = new TextArea();
				jobPostingText.setText(selectedJobPosting.getText());
				RootPanel.get("content").add(jobPostingText);
				
				RootPanel.get("content").add(new HTML("<h3>Benötigte Bewerber-Eigenschaften</h3>"));
				
				final FlexTable traitTable = new FlexTable();
				traitTable.setStyleName("traits");
				
				printJobPostingTraitTable(traitTable);
			
				RootPanel.get("content").add(traitTable);
			}
		} 
		private class applicateClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				/** Es wird überprüft ob der Nutzer ein PartnerProfil erstellt hat, bevor dieser sich
				 * auf eine Ausschreibung bewerben kann
				 */
				ClientsideSettings.getPitchMenAdmin().getPartnerProfileByPersonId(ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<PartnerProfile>(){
					
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Konnte PartnerProfile nicht laden");
					}
					
					public void onSuccess(PartnerProfile result) {
						
						
						if(result != null){
							
							RootPanel.get("content").add(new ApplicationForm(selectedJobPosting));
						}
						else{
							Window.alert("Sie haben noch kein Partnerprofil erstellt! Klicken Sie dazu auf Ihren Namen oben rechts");
						}
						
					}
					
				});
				

			}
		} 
		
		

		public void delete() {
			
			ClientsideSettings.getPitchMenAdmin().deleteJobPosting(selectedJobPosting, new DeleteJobPostingCallback(parentProject));


		}

		class DeleteJobPostingCallback implements AsyncCallback<Void> {

			Project project = null;

			public DeleteJobPostingCallback(Project parentProject) {
				this.project = parentProject;
			}

			public void onFailure(Throwable caught) {
				Window.alert("Das Löschen der Ausschreibung ist fehlgeschlagen!");

			}

			public void onSuccess(Void result) {
				Window.alert("Die Ausschreibung wurde erfolgreich gelöscht.");
				ProjectForm projectForm = new ProjectForm(project); 
				/* 
				 * Beim löschen einer Ausschreibung wird der Baum im Nav Panel neu geladen.
				 */
			
					RootPanel.get("nav").clear();
					Navigation updatedNavigation = new Navigation();  
					RootPanel.get("nav").add(updatedNavigation);
			}
		}
				
	public void printJobPostingTraitTable(final FlexTable traitTable) {
		
		ClientsideSettings.getPitchMenAdmin().getTraitsByPartnerProfileId(partnerProfileOfJobPosting.getId(), new AsyncCallback<ArrayList<Trait>>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().severe("Empfangen der Traits fehlgeschlagen");
			}

			@Override
			public void onSuccess(ArrayList<Trait> result) {
				
				jobPostingTraits = result;
			}
		});
		
		
		for(final Trait trait : jobPostingTraits) {
			int rowCount = traitTable.getRowCount();
			
			rowCount = traitTable.getRowCount();
			
			Button removeButton = null;
			
			removeButton = new Button("Eigenschaft entfernen");
			removeButton.setStyleName("delete");
			removeButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if(Window.confirm("Möchten Sie die Eigenschaft wirklich löschen?")) {
						jobPostingTraits.remove(trait);
						ClientsideSettings.getPitchMenAdmin().deleteTrait(trait, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().severe("Löschen der Eigenschaft fehlgeschlagen");
							}

							@Override
							public void onSuccess(Void result) {
								traitTable.removeAllRows();
								printJobPostingTraitTable(traitTable);
							}
							
						});
					}
				}
				
			});
			
			TextBox traitNameBox = new TextBox();
			traitNameBox.setText(trait.getName());
			traitNameBox.getElement().setPropertyString("placeholder", "Name der Eigenschaft");
			TextBox traitValueBox = new TextBox();
			traitValueBox.setText(trait.getValue());
			traitValueBox.getElement().setPropertyString("placeholder", "Wert der Eigenschaft");
			traitTable.setWidget(rowCount, 0, traitNameBox);
			traitTable.setWidget(rowCount, 1, traitValueBox);
			traitTable.setWidget(rowCount, 2, removeButton);
		}
		
		int rowCount = traitTable.getRowCount();
		
		final TextBox newTraitNameBox = new TextBox();
		newTraitNameBox.getElement().setPropertyString("placeholder", "Name der Eigenschaft");
		final TextBox newTraitValueBox = new TextBox();
		newTraitValueBox.getElement().setPropertyString("placeholder", "Wert der Eigenschaft");
		
		Button addTraitButton = new Button("Eigenschaft hinzufügen");
		addTraitButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ClientsideSettings.getPitchMenAdmin().addTrait(newTraitNameBox.getText(), 
															newTraitValueBox.getText(), 
															partnerProfileOfJobPosting.getId(), 
															new AsyncCallback<Trait>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Konnte Trait nicht speichern");
					}

					@Override
					public void onSuccess(Trait result) {
						jobPostingTraits.add(result);
						traitTable.removeAllRows();
						printJobPostingTraitTable(traitTable);
					}
					
				});
			}
			
		});
		
		traitTable.setWidget(rowCount, 0, newTraitNameBox);
		traitTable.setWidget(rowCount, 1, newTraitValueBox);
		traitTable.setWidget(rowCount, 2, addTraitButton);
	}
	
	public void setSelectedJobPosting(JobPosting jp) {
		
	}
	
	/**
	 * Mit dieser Methode wird ein Formular zur Neuanlage
	 * einer Ausschreibung angezeigt.
	 */
	private void createNewJobPosting() {		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='info'><p><span class='fa fa-info-circle'></span> Sie erstellen gerade eine neue Ausschreibung innerhalb des Projekts <em>" + parentProject.getTitle() + "</em>. In einem zweiten Schritt können Sie dafür dann ein Partner-Profil definieren.</p></div>"));
		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.addStyleName("headline");
		
		Button cancelButton = new Button("Bearbeitung abbrechen");
		cancelButton.addStyleName("delete");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new ProjectForm(parentProject));
			}
		});
		
		Button saveButton = new Button("Ausschreibung speichern");
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				java.sql.Date convertedDate = new java.sql.Date(deadlineBox.getValue().getTime());
				
				ClientsideSettings.getPitchMenAdmin().addJobPosting(titleBox.getText(), 
																jobPostingText.getText(), 
																"laufend", 
																convertedDate, 
																parentProject.getId(), 
																new AsyncCallback<JobPosting>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("JobPosting konnte nicht gespeichert werden.");
					}
	
					@Override
					public void onSuccess(JobPosting result) {						
						
						/* 
						 * Beim Anlegen einer Ausschreibung wird der Baum im Nav Panel neu geladen.
						 */
					
							RootPanel.get("nav").clear();
							Navigation updatedNavigation = new Navigation();  
							RootPanel.get("nav").add(updatedNavigation);
						
						
						selectedJobPosting = result;
						java.util.Date currentDate = new java.util.Date();
						java.sql.Date convertedDate = new java.sql.Date(currentDate.getTime());
						
						ClientsideSettings.getPitchMenAdmin().addPartnerProfileForJobPosting(convertedDate, convertedDate, result.getId(), new AsyncCallback<PartnerProfile>() {

							@Override
							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().severe("PartnerProfile konnte nicht gespeichert werden.");
							}

							@Override
							public void onSuccess(PartnerProfile result) {
								JobPostingForm updatedForm = new JobPostingForm(selectedJobPosting);
								RootPanel.get("content").add(updatedForm);
							}
							
						});
					}				
				});
			}
		});
		
		topPanel.add(new HTML("<h2>Neue Ausschreibung anlegen</h2>"));
		topPanel.add(saveButton);
		topPanel.add(cancelButton);
		RootPanel.get("content").add(topPanel);
		
		RootPanel.get("content").add(new HTML("<h3>Titel der Ausschreibung</h3>"));
		
		titleBox = new TextBox();
		RootPanel.get("content").add(titleBox);
		
		RootPanel.get("content").add(new HTML("<h3>Deadline</h3>"));
		
		deadlineBox = new DateBox();
		deadlineBox.setValue(new java.util.Date());
		RootPanel.get("content").add(deadlineBox);
		
		RootPanel.get("content").add(new HTML("<h3>Ausschreibungstext</h3>"));
		
		jobPostingText = new TextArea();
		jobPostingText.setText("Beschreiben Sie die Stelle genauer. Sie können auch HTML-Tags verwenden.");
		RootPanel.get("content").add(jobPostingText);	
	}	
}
