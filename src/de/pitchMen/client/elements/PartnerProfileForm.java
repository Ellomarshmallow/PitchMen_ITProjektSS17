package de.pitchMen.client.elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.PartnerProfile;
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
		
		// RPC-Abfrage des Partnerprofils
		this.pitchMenAdmin.getPartnerProfileByPersonId(currentUserId, new PartnerProfileCallback());
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
				RootPanel.get("content").add(new HTML("<h2>Sie haben noch kein Partnerprofil.</h2>"));
				Button createButton = new Button("Partnerprofil anlegen");
				createButton.addClickHandler(new CreatePartnerProfileClickHandler());
				RootPanel.get("content").add(createButton);			
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
				RootPanel.get("content").add(new HTML("<h2>Für Ihr Partnerprofil gibt es noch keine Eigenschaften.</h2>"));
				ClientsideSettings.getLogger().info("RPC gibt null zurück - das Partnerprofil mit der id " + userPartnerProfile.getId() + " hat noch keine Traits.");
			} else {
				RootPanel.get("content").clear();
				ClientsideSettings.getLogger().info("Traits von RPC empfangen");
				/*
				 *  Das Attribut traits enthält nach dieser Zuweisung
				 *  die Traits des PartnerProfils des aktuell angemeldeten Benutzers.
				 */
				traits = result;
				RootPanel.get("content").add(new HTML("<h2>Bearbeiten Sie Ihr Partner-Profil</h2>"));
				
				RootPanel.get("content").add(new HTML("<p><strong>Erstellt:</strong> "
														+ userPartnerProfile.getDateCreated()
														+ " | <strong>Zuletzt geändert:</strong> "
														+ userPartnerProfile.getDateChanged()
														+ "</p>"));
				
				FlexTable traitTable = new FlexTable();
				traitTable.setStyleName("traits");
				
				for(final Trait trait : traits) {
					Button deleteTraitBtn = new Button("Eigenschaft entfernen");
					deleteTraitBtn.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							pitchMenAdmin.deleteTrait(trait, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									RootPanel.get("content").clear();
									RootPanel.get("content").add(new HTML("<p>Fehler beim Entfernen der Eigenschaft.</p>"));
								}

								@Override
								public void onSuccess(Void result) {
									PartnerProfileForm updatedForm = new PartnerProfileForm();
									RootPanel.get("content").clear();
									RootPanel.get("content").add(updatedForm);
								}
								
							});
						}
						
					});
					int rowCount = traitTable.getRowCount();
					traitTable.setWidget(rowCount, 0, new HTML("<p><strong>" + trait.getName() + "</strong></p>"));
					traitTable.setWidget(rowCount, 1, new HTML("<p>" + trait.getValue() + "</p>"));
					traitTable.setWidget(rowCount, 2, deleteTraitBtn);
				}
				
				// Der addTraitBtn erhält einen Clickhandler
				addTraitBtn.addClickHandler(new AddTraitClickHandler());
				
				int rowCount = traitTable.getRowCount();
				
				traitTable.getFlexCellFormatter().setColSpan(rowCount, 0, 3);
				traitTable.setWidget(rowCount, 0, new HTML("<h3>Neue Eigenschaft hinzufügen</h3>"));
				
				rowCount = traitTable.getRowCount();
				
				traitNameBox = new TextBox();
				traitNameBox.getElement().setPropertyString("placeholder", "Name der Eigenschaft");
				traitValueBox = new TextBox();
				traitValueBox.getElement().setPropertyString("placeholder", "Wert der Eigenschaft");
				traitTable.setWidget(rowCount, 0, traitNameBox);
				traitTable.setWidget(rowCount, 1, traitValueBox);
				traitTable.setWidget(rowCount, 2, addTraitBtn);
				
				RootPanel.get("content").add(traitTable);
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
					// TODO getDateChanged() von userPartnerProfile updaten 
					pitchMenAdmin.updatePartnerProfile(userPartnerProfile, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							
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
	 * Die genestete Klasse <code>CreatePartnerProfileClickHandler</code>
	 * behandelt das Drücken des Buttons <code>createButton</code>.
	 */
	private class CreatePartnerProfileClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			/*
			 *  wird der Button geklickt, muss ein 
			 *  neues partnerProfile erstellt werden.
			 */ 
			Date initialDate = new Date();
			pitchMenAdmin.addPartnerProfileForPerson(initialDate, initialDate, currentUserId, new AsyncCallback<PartnerProfile>() {

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
	 * Die Methode <code>onLoad()</code> wird von GWT für alle Widgets
	 * vorgeschrieben und wird ausgeführt, wenn das Widget zur Anzeige 
	 * gebracht wird.
	 */
	public void onLoad() {
		super.onLoad();
	}
}
