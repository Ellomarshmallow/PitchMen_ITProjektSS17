package de.pitchMen.client.elements;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	
	public PartnerProfileForm() {
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
				ClientsideSettings.getLogger().info("RPC gibt null zurück - der Nutzer mit der id " + currentUserId + " hat noch kein Partnerprofil.");
			} else {
				ClientsideSettings.getLogger().info("PartnerProfil von RPC empfangen");
				/*
				 *  Das Attribut userPartnerProfile enthält nach dieser Zuweisung
				 *  das PartnerProfil des aktuell angemeldeten Benutzers.
				 */
				userPartnerProfile = result;
				
				// nach dem Aufruf des RPCs ist entweder das userPartnerProfile gesetzt, oder der Nutzer hatte noch keins
				if(userPartnerProfile == null) {
					add(new HTML("<h2>Sie haben noch kein Partner-Profil angelegt. Beginnen Sie jetzt!</h2>"));
					Button createButton = new Button("Partnerprofil anlegen");
					createButton.addClickHandler(new CreatePartnerProfileClickHandler());
					add(createButton);
					return;
				} else {
					// Der Nutzer hatte schon ein PartnerProfile
					
					// RPC-Abfrage des Partnerprofils
					pitchMenAdmin.getTraitsByPartnerProfileId(userPartnerProfile.getId(), new TraitsCallback());
				}
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
			ClientsideSettings.getLogger().severe("Konnte Partnerprofil nicht laden");	
		}

		@Override
		public void onSuccess(ArrayList<Trait> result) {
			if(result == null) {
				ClientsideSettings.getLogger().info("RPC gibt null zurück - das Partnerprofil mit der id " + userPartnerProfile.getId() + " hat noch keine Traits.");
			} else {
				ClientsideSettings.getLogger().info("Traits von RPC empfangen");
				/*
				 *  Das Attribut traits enthält nach dieser Zuweisung
				 *  die Traits des PartnerProfils des aktuell angemeldeten Benutzers.
				 */
				traits = result;
				add(new HTML("<h2>Bearbeiten Sie Ihr Partner-Profil</h2>"));
				
				// Überprüfen, ob Traits hinterlegt wurden
				if(traits == null) {
					add(new HTML("<p>Sie haben bisher keine Eigenschaften angelegt </p>"));
				}
				
				// Der addTraitBtn erhält einen Clickhandler
				addTraitBtn.addClickHandler(new AddTraitClickHandler());
				
				add(addTraitBtn);
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
			/*
			 *  wird der Button geklickt, muss ein neues Paar von
			 *  Name- & Wert-Eingabefeldern erzeugt werden.
			 */
			HorizontalPanel traitFormRow = new HorizontalPanel();
			TextBox nameBox = new TextBox();
			TextBox valueBox = new TextBox();
			traitFormRow.add(nameBox);
			traitFormRow.add(valueBox);
			add(traitFormRow);
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
			 *  wird der Button geklickt, muss ein neues Paar von
			 *  Name- & Wert-Eingabefeldern erzeugt werden.
			 */
			pitchMenAdmin.addPartnerProfileForPerson(new Date(), new Date(), currentUserId, new AsyncCallback<PartnerProfile>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("PartnerProfile konnte nicht gespeichert werden");				
				}

				@Override
				public void onSuccess(PartnerProfile result) {
					userPartnerProfile = result;
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
