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
	
	private int Creator;

	
	private PitchMenAdminAsync pitchMenAdmin = null;
	
	private String Title = " " ; 
	
	private String Description = " "; 
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public PitchMenAdminAsync getPitchMenAdmin() {
		return pitchMenAdmin;
	}
	
	public void setCreator(int creator){
		this.Creator = creator; 
	}
	
	public int getCreator(){
		return this.Creator; 
	}
	
	public boolean hasPermission(){
		if(this.Creator == ClientsideSettings.getCurrentUser().getId()){
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


