package de.pitchMen.client.elements;

import java.lang.Object;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;

public class Formular extends VerticalPanel {

	
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
	
	/* Wenn die PersonId gleich der aktuellen UserId ist, dann ist der User auch der Ersteller des
	 * Projektmarktplatzes bzw. des Projektes und hat die Buttons Löschen und Bearbeiten zur verfügung
	 * (vgl. MarketplaceForm,ProjectForm) 
	 * Überlagerung von Methoden: 
	 */
	public boolean hasPermission(Marketplace selectedMarketplace){
		if(selectedMarketplace.getPersonId() == ClientsideSettings.getCurrentUser().getId()){
			return true;
		}
		else {
			return false; 
		}
	}
	
	public boolean hasPermission(Project selectedProject){
		if(selectedProject.getPersonId() == ClientsideSettings.getCurrentUser().getId()){
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


