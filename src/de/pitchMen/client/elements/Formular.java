package de.pitchMen.client.elements;

import java.lang.Object;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;

public class Formular extends SimplePanel {

	// TODO: Die Methode hasPermission() implementieren. Rückgabe eines booleanschen Wertes.
	// 		 Da der Ersteller des Projektmarktplatzes mehr Buttons hat (löschen bearbeiten)

	private int creator;


	private PitchMenAdminAsync pitchMenAdmin = null;

	private String title = " " ; 

	private String description = " "; 

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PitchMenAdminAsync getPitchMenAdmin() {
		return pitchMenAdmin;
	}

	public void setCreator(int creator){
		this.creator = creator; 
	}

	public int getCreator(){
		return this.creator; 
	}

	public boolean hasPermission(){
		if(this.creator == ClientsideSettings.getCurrentUser().getId()){
			return true;
		}
		else {
			return false; 
		}
	}

	public Formular(){
		/*
		 * Verbindung zur PitchMenAdministration
		 */
		PitchMenAdminAsync pitchmenadmin = ClientsideSettings.getPitchMenAdmin();

		/*
		 * Erstellen eines FormPanels
		 */
		FormPanel form = new FormPanel(); 

		/*
		 * Erstellen und hinzufügen eines VerticalPanels zu dem FormPanel
		 */
		VerticalPanel ver = new VerticalPanel(); 
		form.setWidget(ver);




	}

}


