package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Person;

/**
 * Das FirstLoginForm wird instanziiert, wenn bei einer
 * Nutzeranmeldung festgestellt wird, dass ein Nutzer noch
 * nicht in der Datenbank der PitchMen-Applikation vorhanden
 * ist.
 * 
 * @author Leon & Simon
 */

public class FirstLoginForm extends Formular  {

	Label firstNameLabel = new Label("Vorname: "); 
	TextBox firstNameBox = new TextBox(); 
	Label lastNameLabel = new Label("Nachname: ");
	TextBox lastNameBox = new TextBox();
	
	/**
	 * Das Attribut <code>newUser</code> wird Instanzen
	 * der Klasse {@link FirstLoginForm} beim 
	 * Konstruktor-Aufruf übergeben und enthält das
	 * {@link Person}-Objekt, das von der Applikationsschicht
	 * bei der Anmeldung an die GUI zurückübergeben wird.
	 * Über dieses Attribut sind die bereits bekannten
	 * User-Daten abfragbar.
	 */
	private Person newUser = null;
	
	public FirstLoginForm(){		
		
		// Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel();
		this.add(labelsPanel);
				
		// labels und Boxen dem Vertical Panel hinzufügen
				
		labelsPanel.add(firstNameLabel);
		labelsPanel.add(firstNameBox);
		labelsPanel.add(lastNameLabel);
		labelsPanel.add(lastNameBox);
				
		// HorizontalPanel für den Button erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		this.add(buttonsPanel);
						
		// ---------- Speichern Button, ClickHandler hinzufügen und dem
		// HorizontalPanel hinzufügen
		Button saveButton = new Button("Speichern");
		saveButton.addClickHandler(new ClickHandler(){
			 public void onClick(ClickEvent event) {
				 save(); 
			 }
		}); 				
				
		buttonsPanel.add(saveButton);
				
	}
	
	//FIXME rest ausfüllen
	public void save(){
		super.getPitchMenAdmin().addPerson(firstNameBox.getText(), lastNameBox.getText(),newUser.getEmailAdress(), newUser.getLoginUrl(), newUser.getLogoutUrl(), true, true, new AsyncCallback<Person>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().severe("Neue Person konnte nicht gespeichert werden.");				
			}

			@Override
			public void onSuccess(Person result) {
				ClientsideSettings.getLogger().info("Neue Person erfolgreich gespeichert: " 
													+ result.getFirstName() 
													+ " "
													+ result.getName()
													+ " ("
													+ result.getEmailAdress()
													+ ")");		
				// TODO Was passiert nach erfolgreicher Speicherung?
			}
			
		});			
	}

	
}
