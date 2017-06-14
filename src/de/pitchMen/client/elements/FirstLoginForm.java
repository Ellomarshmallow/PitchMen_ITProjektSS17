package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class FirstLoginForm extends Formular  {

	Label firstNameLabel = new Label("Vorname: "); 
	TextBox firstNameBox = new TextBox(); 
	Label lastNameLabel = new Label("Nachname: ");
	TextBox lastNameBox = new TextBox(); 
	
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
		super.getPitchMenAdmin().addPerson(firstNameBox.getText(), true, emailAdress, lastNameBox.getText(), loginUrl, logoutUrl, callback);			
	}

	
}
