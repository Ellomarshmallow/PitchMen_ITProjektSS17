package de.pitchMen.client.elements;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.Company;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Team;
import de.pitchMen.shared.bo.Trait;

/**
 * Die Klasse <code>PartnerProfileForm</code> stellt
 * Funktionalitäten für die Erstellung eines Formulars
 * zur Verfügung, mit dessen Hilfe Partnerprofile 
 * bearbeitet werden können. Sie erbt von der Klasse
 * {@link Formular}.
 * 
 * @author Simon
 */
public class PartnerProfileForm extends Formular {
	
	/**
	 * Der angemeldete Nutzer muss dem Formular bekannt sein,
	 * um ihm das richtige PartnerProfile zur Bearbeitung
	 * ausgeben zu können.
	 */
	private int currentUserId = 0;
	
	/**
	 * Der angemeldete Nutzer muss dem Formular bekannt sein,
	 * um ihm das richtige PartnerProfile zur Bearbeitung
	 * ausgeben zu können.
	 */
	private int currentTeamId = 0;
	
	/**
	 * Der angemeldete Nutzer muss dem Formular bekannt sein,
	 * um ihm das richtige PartnerProfile zur Bearbeitung
	 * ausgeben zu können.
	 */
	private int currentCompanyId = 0;
	
	/**
	 * Auch in dieser Klasse werden die Funktionalitäten der
	 * {@link de.pitchMen.server.PitchMenAdminImpl} benötigt.
	 */
	PitchMenAdminAsync pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();
	
	/**
	 * Der angemeldete Nutzer hat i. d. R. bereits ein Partnerprofil, das
	 * in der Variable <code>userPartnerProfile</code> hinterlegt wird. Hat
	 * der Nutzer noch kein Partnerprofil angelegt, bleibt dieses Element 
	 * <code>null</code> und dem Nutzer wird ein leeres Formular angezeigt.
	 */
	private PartnerProfile userPartnerProfile = null;
	
	/**
	 * Die {@link Trait}-Objekte eines Partnerprofils werden in Objekten der 
	 * <code>PartnerProfileForm</code>-Klasse gesondert als Attribut geführt,
	 * um Veränderungen an den Traits vornehmen zu können.
	 */
	private ArrayList<Trait> traits = new ArrayList<Trait>();
	
	
	/**
	 * Der Button <code>addTraitButton</code> dient dem Anlegen einer neuen
	 * Eigenschaft für das Partnerprofil.
	 */
	private Button addTraitBtn = new Button("Eigenschaft hinzufügen");
	
	TextBox traitNameBox = null;
	
	TextBox traitValueBox = null;
	
	public PartnerProfileForm() {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
		// Abfrage der id des aktuell angemeldeten Nutzers
		this.currentUserId = ClientsideSettings.getCurrentUser().getId();
		
		// RPC-Abfrage des Partnerprofils nach Person
		this.pitchMenAdmin.getPartnerProfileByPersonId(currentUserId, new PartnerProfileCallback());
			
		//RPC-Abfrage des Partnerprofils nach Team
		this.pitchMenAdmin.getPartnerProfileByTeamId(currentTeamId, new PartnerProfileCallback());
		
		//RPC-Abfrage des Partnerprofils nach Unternehmen
		this.pitchMenAdmin.getPartnerProfileByCompanyId(currentCompanyId, new PartnerProfileCallback());
	}
	
	/**
	 * Die genestete Klasse <code>PartnerProfileCallback</code>
	 * behandelt die zurückkehrende Server-Abfrage eines
	 * Partnerprofils.
	 */
	private class PartnerProfileCallback implements AsyncCallback<PartnerProfile> {

		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Konnte Partnerprofil nicht laden");	
		}

		@Override
		public void onSuccess(PartnerProfile result) {
			if(result == null) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML("<h2>Sie haben noch keine Partnerprofile.</h2>"));
				Button createButton = new Button("Persönliches Partnerprofil anlegen");
				Button createTeamButton = new Button("Team anlegen");
				Button createCompanyButton = new Button("Unternehmen anlegen");
				createButton.addClickHandler(new CreatePartnerProfileClickHandler());
				createTeamButton.addClickHandler(new CreateTeamPartnerProfileClickHandler());
				createCompanyButton.addClickHandler(new CreateCompanyPartnerProfileClickHandler());
				RootPanel.get("content").add(createButton);	
				RootPanel.get("content").add(new HTML("<br>"));
				RootPanel.get("content").add(createTeamButton);
				RootPanel.get("content").add(new HTML("<br>"));
				RootPanel.get("content").add(createCompanyButton);			

				
			} else {
				ClientsideSettings.getLogger().info("PartnerProfil von RPC empfangen");
				/*
				 *  Das Attribut userPartnerProfile enthält nach dieser Zuweisung
				 *  das PartnerProfil des aktuell angemeldeten Benutzers.
				 */
				userPartnerProfile = result;
				
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));
				RootPanel.get("content").add(new HTML("<p class='load-msg'>Eigenschaften des Partnerprofils werden abgefragt</p>"));
				
				pitchMenAdmin.getTraitsByPartnerProfileId(userPartnerProfile.getId(), new TraitsCallback());
			}
		}
		
	}
	
	/**
	 * Die genestete Klasse <code>PartnerProfileCallback</code>
	 * behandelt die zurückkehrende Server-Abfrage eines
	 * Partnerprofils.
	 */
	private class TraitsCallback implements AsyncCallback<ArrayList<Trait>> {
		
		@Override
		public void onFailure(Throwable caught) {
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new HTML("Konnte Traits nicht laden"));	
		}

		@Override
		public void onSuccess(ArrayList<Trait> result) {
			if(result.isEmpty()) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML("<h2>Für Ihr Partnerprofil gibt es noch keine Eigenschaften.</h2></br></br><p>Nutzen Sie das Partnerprofil "
						+ "um Ihre persönlichen Fähigkeiten anzulegen und hierdurch passende Ausschreibungen zu finden. Bitte geben Sie ihren Eigenschaften Werte aus dem Bereich: sehr gut, gut, mittel.</p>"));
				ClientsideSettings.getLogger().info("RPC gibt null zurück - das Partnerprofil mit der id " + userPartnerProfile.getId() + " hat noch keine Traits.");
				
				FlexTable traitTable = new FlexTable();
				traitTable.setStyleName("traits");
				
				// Der addTraitBtn erhält einen Clickhandler
				addTraitBtn.addClickHandler(new AddTraitClickHandler());
				
				int rowCount = traitTable.getRowCount();
				
				traitTable.getFlexCellFormatter().setColSpan(rowCount, 0, 3);
				traitTable.setWidget(rowCount, 0, new HTML("<h3>Neue Eigenschaften hinzufügen</h3>"));
				
				rowCount = traitTable.getRowCount();
				
				traitNameBox = new TextBox();
				traitNameBox.getElement().setPropertyString("placeholder", "Name der Eigenschaften");
				traitValueBox = new TextBox();
				traitValueBox.getElement().setPropertyString("placeholder", "Wert der Eigenschaften");
				traitTable.setWidget(rowCount, 0, traitNameBox);
				traitTable.setWidget(rowCount, 1, traitValueBox);
				traitTable.setWidget(rowCount, 2, addTraitBtn);
				
				RootPanel.get("content").add(traitTable);
			
			} else {
				RootPanel.get("content").clear();
				ClientsideSettings.getLogger().info("Traits von RPC empfangen");
				/*
				 *  Das Attribut traits enthält nach dieser Zuweisung
				 *  die Traits des PartnerProfils des aktuell angemeldeten Benutzers.
				 */
				traits = result;
				
				//Buttons zum Anlegen von Team oder Unternehmen
				
				HorizontalPanel topPanel = new HorizontalPanel();
				topPanel.addStyleName("headline");
				
				Button createTeamButton = new Button("Team anlegen");
				Button createCompanyButton = new Button("Unternehmen anlegen");
				createTeamButton.addClickHandler(new CreateTeamPartnerProfileClickHandler());
				createCompanyButton.addClickHandler(new CreateCompanyPartnerProfileClickHandler());
				
				topPanel.add(new HTML("<h2>Verwalten Sie Ihre Partnerprofile</h2>"));
				topPanel.add(createTeamButton);			
				topPanel.add(createCompanyButton);
				
				RootPanel.get("content").add(topPanel);
				
				RootPanel.get("content").add(new HTML("<h3>Eigenes, personenbezogenes Partnerprofil</h3>"));
				
				RootPanel.get("content").add(new HTML("<p><strong>Erstellt:</strong> "
														+ userPartnerProfile.getDateCreated()
														+ " | <strong>Zuletzt geändert:</strong> "
														+ userPartnerProfile.getDateChanged()
														+ "</p>"));
				
				FlexTable traitTable = new FlexTable();
				traitTable.setStyleName("traits");
				
				for(final Trait trait : traits) {
					Button deleteTraitBtn = new Button("Eigenschaft entfernen");
					deleteTraitBtn.setStyleName("delete");
					deleteTraitBtn.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							if(Window.confirm("Möchten Sie diese Eigenschaft wirklich löschen?")) {
								pitchMenAdmin.deleteTrait(trait, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										RootPanel.get("content").clear();
										RootPanel.get("content").add(new HTML("<p>Fehler beim Entfernen der Eigenschaft.</p>"));
									}

									@Override
									public void onSuccess(Void result) {
										java.util.Date currentDate = new java.util.Date();
										userPartnerProfile.setDateChanged(new Date(currentDate.getTime()));
										ClientsideSettings.getPitchMenAdmin().updatePartnerProfile(userPartnerProfile, new AsyncCallback<Void>() {

											@Override
											public void onFailure(Throwable caught) {
												ClientsideSettings.getLogger().severe("Konnte PartnerProfile nicht aktualisieren");
											}

											@Override
											public void onSuccess(Void result) {
												ClientsideSettings.getLogger().info("PartnerProfile aktualisiert");
											}
										});	
										PartnerProfileForm updatedForm = new PartnerProfileForm();
										RootPanel.get("content").clear();
										RootPanel.get("content").add(updatedForm);
									}
									
								});
							}
						}
						
					});
					
					Button updateTraitBtn = new Button("Eigenschaft bearbeiten");
					
					updateTraitBtn.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							RootPanel.get("content").clear();
							RootPanel.get("content").add(new HTML("<h2>Eigenschaft bearbeiten</h2>"));
							
							Button saveUpdateTraitBtn = new Button("Änderungen speichern");
							
							saveUpdateTraitBtn.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									Trait updatedTrait = trait;
									updatedTrait.setName(traitNameBox.getText());
									updatedTrait.setValue(traitValueBox.getText());
									ClientsideSettings.getPitchMenAdmin().updateTrait(updatedTrait, new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											ClientsideSettings.getLogger().severe("Updaten der Eigenschaft fehlgeschlagen");
										}

										@Override
										public void onSuccess(Void result) {
											java.util.Date currentDate = new java.util.Date();
											userPartnerProfile.setDateChanged(new Date(currentDate.getTime()));
											ClientsideSettings.getPitchMenAdmin().updatePartnerProfile(userPartnerProfile, new AsyncCallback<Void>() {

												@Override
												public void onFailure(Throwable caught) {
													ClientsideSettings.getLogger().severe("Konnte PartnerProfile nicht aktualisieren");
												}

												@Override
												public void onSuccess(Void result) {
													ClientsideSettings.getLogger().info("PartnerProfile aktualisiert");
												}
											});	
											PartnerProfileForm updatedPartnerProfileForm = new PartnerProfileForm();
											RootPanel.get("content").clear();
											RootPanel.get("content").add(updatedPartnerProfileForm);
										}
										
									});
									
								}
								
							});
							
							FlexTable traitTable = new FlexTable();
							traitTable.setStyleName("traits");
							
							int rowCount = traitTable.getRowCount();
							
							Button cancelButton = new Button("Abbrechen");
							cancelButton.setStyleName("delete");
							cancelButton.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									PartnerProfileForm updatedForm = new PartnerProfileForm();
									RootPanel.get("content").add(updatedForm);
								}
								
							});
							
							traitNameBox = new TextBox();
							traitNameBox.getElement().setPropertyString("placeholder", "Name der Eigenschaft");
							traitNameBox.setText(trait.getName());
							traitValueBox = new TextBox();
							traitValueBox.getElement().setPropertyString("placeholder", "Wert der Eigenschaft");
							traitValueBox.setText(trait.getValue());
							traitTable.setWidget(rowCount, 0, traitNameBox);
							traitTable.setWidget(rowCount, 1, traitValueBox);
							traitTable.setWidget(rowCount, 2, saveUpdateTraitBtn);
							traitTable.setWidget(rowCount, 3, cancelButton);
							
							RootPanel.get("content").add(traitTable);
							
						}
						
					});
					
					int rowCount = traitTable.getRowCount();
					traitTable.setWidget(rowCount, 0, new HTML("<p><strong>" + trait.getName() + "</strong></p>"));
					traitTable.setWidget(rowCount, 1, new HTML("<p>" + trait.getValue() + "</p>"));
					traitTable.setWidget(rowCount, 2, updateTraitBtn);
					traitTable.setWidget(rowCount, 3, deleteTraitBtn);
				}
				
				// Der addTraitBtn erhält einen Clickhandler
				addTraitBtn.addClickHandler(new AddTraitClickHandler());
				
				int rowCount = traitTable.getRowCount();
				
				traitTable.getFlexCellFormatter().setColSpan(rowCount, 0, 4);
				traitTable.setWidget(rowCount, 0, new HTML("<h3>Neue Eigenschaft hinzufügen</h3>"
						+ "</br></br><p>Bitte geben Sie ihrer Eigenschaft Werte aus dem Bereich: sehr gut, gut, mittel.</p>"));
				
				rowCount = traitTable.getRowCount();
				
				traitNameBox = new TextBox();
				traitNameBox.getElement().setPropertyString("placeholder", "Name der Eigenschaft");
				traitValueBox = new TextBox();
				traitValueBox.getElement().setPropertyString("placeholder", "Wert der Eigenschaft");
				traitTable.setWidget(rowCount, 0, traitNameBox);
				traitTable.setWidget(rowCount, 1, traitValueBox);
				traitTable.setWidget(rowCount, 2, addTraitBtn);
				traitTable.setWidget(rowCount, 3, new HTML(""));
				
				RootPanel.get("content").add(traitTable);
				
				//FIXME If-Abfrage, wenn schon ein Partnerprofil von Team oder Person angelegt ist, dann sollte das zum öffenen angezeigt werden.
				
			}
			
			
		}
		
	}
	
	/**
	 * Die genestete Klasse <code>AddTraitClickHandler</code>
	 * behandelt das Drücken des Buttons <code>addTraitButton</code>.
	 */
	private class AddTraitClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			pitchMenAdmin.addTrait(traitNameBox.getText(), traitValueBox.getText(), userPartnerProfile.getId(), new AsyncCallback<Trait>() {

				@Override
				public void onFailure(Throwable caught) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new HTML("<p>Fehler beim Speichern der Eigenschaft</p>"));
				}

				@Override
				public void onSuccess(Trait result) {
					java.util.Date currentDate = new java.util.Date();
					userPartnerProfile.setDateChanged(new Date(currentDate.getTime()));
					ClientsideSettings.getPitchMenAdmin().updatePartnerProfile(userPartnerProfile, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().severe("Konnte PartnerProfile nicht aktualisieren");
						}

						@Override
						public void onSuccess(Void result) {
							ClientsideSettings.getLogger().info("PartnerProfile aktualisiert");
						}
						
					});
					PartnerProfileForm updatedForm = new PartnerProfileForm();
					RootPanel.get("content").clear();
					RootPanel.get("content").add(updatedForm);
				}
				
			});
		}
		
	}
	
	/**
	 * Die genestete Klasse <code>CreateTeamPartnerProfileClickHandler</code>
	 * behandelt das Drücken des Buttons <code>createButton</code>.
	 */
	private class CreatePartnerProfileClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			/*
			 *  wird der Button geklickt, muss ein 
			 *  neues partnerProfile erstellt werden.
			 */ 
			java.util.Date initialDate = new java.util.Date();
			java.sql.Date convertedInitialDate = new java.sql.Date(initialDate.getTime());
			pitchMenAdmin.addPartnerProfileForPerson(convertedInitialDate, convertedInitialDate, currentUserId, new AsyncCallback<PartnerProfile>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("PartnerProfile konnte nicht gespeichert werden");				
				}

				@Override
				public void onSuccess(PartnerProfile result) {
					PartnerProfileForm updatedForm = new PartnerProfileForm();
					RootPanel.get("content").add(updatedForm);
				}
				
			});
		}
		
	}
	
	
	/**
	 * Die genestete Klasse <code>CreatePartnerProfileClickHandler</code>
	 * behandelt das Drücken des Buttons <code>createButton</code>.
	 */
	private class CreateTeamPartnerProfileClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			/*
			 *  wird der Button geklickt, muss ein 
			 *  neues Team erstellt werden.
			 */
			final TextBox nameBox = new TextBox(); 
			final TextArea descArea = new TextArea();
			final TextBox teamSizeBox = new TextBox();
			
			HorizontalPanel topPanel = new HorizontalPanel();
			topPanel.addStyleName("headline");
					
			RootPanel.get("content").clear(); 
			topPanel.add(new HTML("<h2>Neues Team anlegen</h2>"));
			Button save = new Button("Speichern");
			topPanel.add(save);
			RootPanel.get("content").add(topPanel);
			RootPanel.get("content").add(new HTML("<p>Geben Sie in der Teambeschreibung bitte Vor- und Nachname aller Teammitglieder an.</p>"));
			RootPanel.get("content").add(new HTML("<p>Teamname:</p>"));
			RootPanel.get("content").add(nameBox);
			RootPanel.get("content").add(new HTML("<p>Teambeschreibung:</p>"));
			RootPanel.get("content").add(descArea);
			RootPanel.get("content").add(new HTML("<p>Teamgröße:</p>"));
			RootPanel.get("content").add(teamSizeBox);
			
			save.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					ClientsideSettings.getPitchMenAdmin().addTeam(nameBox.getText(), descArea.getText(), Integer.parseInt(teamSizeBox.getText()), new AsyncCallback<Team>(){
					
						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().severe("Neues Team konnte nicht gespeichert werden.");
						}
							
							public void onSuccess(Team result) {
							
								java.util.Date currentDate = new java.util.Date();
								java.sql.Date convertedDate = new java.sql.Date(currentDate.getTime());
								ClientsideSettings.getPitchMenAdmin().addPartnerProfileForTeam(convertedDate, convertedDate, result.getId(), ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<PartnerProfile>() {

									@Override
									public void onFailure(Throwable caught) {
										ClientsideSettings.getLogger().severe("Konnte Partnerprofil nicht speichern.");
									}

									@Override
									public void onSuccess(PartnerProfile result) {
										PartnerProfileForm updatedForm = new PartnerProfileForm();
									}
									
								});
								
								
							}
					});
				}
			}); 
			RootPanel.get("content").add(save);
		}
			
					
			
		
			
			
				
				
		
			}
		
	
	
	/**
	 * Die genestete Klasse <code>CreateTeamPartnerProfileClickHandler</code>
	 * behandelt das Drücken des Buttons <code>createButton</code>.
	 */
	private class CreateCompanyPartnerProfileClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			/*
			 *  wird der Button geklickt, muss ein 
			 *  neues Unternehmen erstellt werden.
			 */ 
			final TextBox nameBox = new TextBox(); 
			final TextArea descArea = new TextArea();
			
			HorizontalPanel topPanel = new HorizontalPanel();
			topPanel.addStyleName("headline");
					
			RootPanel.get("content").clear(); 
			topPanel.add(new HTML("<h2>Neues Unternehmen anlegen</h2>"));
			Button save = new Button("Speichern");
			topPanel.add(save);
			RootPanel.get("content").add(topPanel);
			RootPanel.get("content").add(new HTML("<p>Unternehmensname:</p>"));
			RootPanel.get("content").add(nameBox);
			RootPanel.get("content").add(new HTML("<p>Unternehmensbeschreibung:</p>"));
			RootPanel.get("content").add(descArea);
			
			save.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent event){
					ClientsideSettings.getPitchMenAdmin().addCompany(nameBox.getText(), descArea.getText(), new AsyncCallback<Company>(){
					
						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().severe("Neues Unternehmen konnte nicht gespeichert werden.");
						}
							
							public void onSuccess(Company result) {
							
								java.util.Date currentDate = new java.util.Date();
								java.sql.Date convertedDate = new java.sql.Date(currentDate.getTime());
								ClientsideSettings.getPitchMenAdmin().addPartnerProfileForCompany(convertedDate, convertedDate, result.getId(), ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<PartnerProfile>() {

									@Override
									public void onFailure(Throwable caught) {
										ClientsideSettings.getLogger().severe("Konnte Partnerprofil nicht speichern.");
									}

									@Override
									public void onSuccess(PartnerProfile result) {
										PartnerProfileForm updatedForm = new PartnerProfileForm();
									}
									
								});
								
								
							}
					});
				}
			}); 
			RootPanel.get("content").add(new HTML("<br>"));
			RootPanel.get("content").add(save);
				
			}
	}
	
	/**
	 * Die Methode <code>onLoad()</code> wird von GWT für alle Widgets
	 * vorgeschrieben und wird ausgeführt, wenn das Widget zur Anzeige 
	 * gebracht wird.
	 */
	public void onLoad() {
		super.onLoad();
	}
}
