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


