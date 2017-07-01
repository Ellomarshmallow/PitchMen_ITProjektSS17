package de.pitchMen.client.elements;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

/**
 * Formular ist die Superklasse aller Formulare. Wichtig ist hier die hasPermission() Methoden
 * @author Leon Schelle
 *
 */
public class Formular extends VerticalPanel {

	/** Wenn die PersonId gleich der aktuellen UserId ist, dann ist der User auch der Ersteller des
	 * Projektmarktplatzes bzw. des Projektes und hat die Buttons Löschen und Bearbeiten zur Verfügung
	 * (vgl. MarketplaceForm,ProjectForm) 
	 * Überlagerung von Methoden: 
	 * 
	 * @return boolean
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

	
}


