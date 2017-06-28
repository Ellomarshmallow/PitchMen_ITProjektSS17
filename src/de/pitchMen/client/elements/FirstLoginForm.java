package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.client.PitchMen;
import de.pitchMen.client.report.ReportDisplay;
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
	Label descriptionLabel = new Label("Kurzbeschreibung: ");
	TextBox descriptionBox = new TextBox();
	
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
	
	private PitchMen pitchMen = null;
	private ReportDisplay reportDisplay = null;
	
	public FirstLoginForm(PitchMen pitchMen){		
		
		this.pitchMen = pitchMen; 
		this.newUser = ClientsideSettings.getCurrentUser();
		
		// Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel();
		this.add(labelsPanel);
				
		// labels und Boxen dem Vertical Panel hinzufügen
				
		labelsPanel.add(firstNameLabel);
		labelsPanel.add(firstNameBox);
		labelsPanel.add(lastNameLabel);
		labelsPanel.add(lastNameBox);
		labelsPanel.add(descriptionLabel);
		labelsPanel.add(descriptionBox);
				
		// HorizontalPanel für den Button erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();
						
		// ---------- Speichern Button, ClickHandler hinzufügen und dem
		// HorizontalPanel hinzufügen
		Button saveButton = new Button("Speichern");
		saveButton.addClickHandler(new ClickHandler(){
			 public void onClick(ClickEvent event) {
				 save(); 
			 }
		}); 				
				
		buttonsPanel.add(saveButton);
		
		this.add(buttonsPanel);
				
	}
	
	public void save(){		
		ClientsideSettings.getPitchMenAdmin().addPerson(firstNameBox.getText(), 
											  lastNameBox.getText(),
											  newUser.getEmailAdress(), 
											  newUser.getLoginUrl(), 
											  newUser.getLogoutUrl(), 
											  descriptionBox.getText(),
											  true, 
											  true, 
											  new NewPersonCallback());	
	}
	
	private class NewPersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Neue Person konnte nicht gespeichert werden.");
			RootPanel.get("content").clear();
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
			ClientsideSettings.getCurrentUser().setFirstName(result.getFirstName());
			ClientsideSettings.getCurrentUser().setName(result.getName());
			ClientsideSettings.getCurrentUser().setIsExisting(true);
			RootPanel.get("usermenu").clear();
			RootPanel.get("content").clear();
			pitchMen.onModuleLoad();
		}
		
	}

	
	public FirstLoginForm(ReportDisplay reportDisplay){		
		
		this.reportDisplay  = reportDisplay; 
		this.newUser = ClientsideSettings.getCurrentUser();
		
		// Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel();
		this.add(labelsPanel);
				
		// labels und Boxen dem Vertical Panel hinzufügen
				
		labelsPanel.add(firstNameLabel);
		labelsPanel.add(firstNameBox);
		labelsPanel.add(lastNameLabel);
		labelsPanel.add(lastNameBox);
		labelsPanel.add(descriptionLabel);
		labelsPanel.add(descriptionBox);
				
		// HorizontalPanel für den Button erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();
						
		// ---------- Speichern Button, ClickHandler hinzufügen und dem
		// HorizontalPanel hinzufügen
		Button saveButton = new Button("Speichern");
		saveButton.addClickHandler(new ClickHandler(){
			 public void onClick(ClickEvent event) {
				 saveForRepGen(); 
			 }
		}); 				
				
		buttonsPanel.add(saveButton);
		
		this.add(buttonsPanel);
				
	}
	
	public void saveForRepGen() {		
		ClientsideSettings.getPitchMenAdmin().addPerson(firstNameBox.getText(), 
											  lastNameBox.getText(),
											  newUser.getEmailAdress(), 
											  newUser.getLoginUrl(), 
											  newUser.getLogoutUrl(), 
											  descriptionBox.getText(),
											  true, 
											  true, 
											  new NewPersonOnRepGenCallback());	
	}
	
	private class NewPersonOnRepGenCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Neue Person konnte nicht gespeichert werden.");
			RootPanel.get("content").clear();
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
			ClientsideSettings.getCurrentUser().setFirstName(result.getFirstName());
			ClientsideSettings.getCurrentUser().setName(result.getName());
			ClientsideSettings.getCurrentUser().setIsExisting(true);
			RootPanel.get("usermenu").clear();
			RootPanel.get("content").clear();
			reportDisplay.onModuleLoad();
		}
		
	}
	
}
