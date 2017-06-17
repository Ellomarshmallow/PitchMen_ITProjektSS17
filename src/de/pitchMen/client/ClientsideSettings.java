package de.pitchMen.client;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.pitchMen.shared.CommonSettings;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.ReportGenerator;
import de.pitchMen.shared.ReportGeneratorAsync;
import de.pitchMen.shared.bo.Person;

/**
 * Diese Klasse enthält Dienste und Eigenschaften, die für Klassen
 * des client-Packages relevant sind.
 * 
 * @author Simon
 * 
 */

public class ClientsideSettings extends CommonSettings {
	
	 /**
	   * currentUser wird gesetzt.
	   */
	private static Person currentUser = null;
	
	  /**
	   * Remote Service Proxy. Hiermit wird eine Verbindung mit dem Server-seitgen Dienst
	   * <code>PitchMenAdmin</code> hergestellt.
	   */
	  private static PitchMenAdminAsync pitchMenAdmin = null;

	  /**
	   * Remote Service Proxy. Hiermit wird eine Verbindung mit dem Server-seitgen Dienst
	   * <code>ReportGenerator</code> hergestellt.
	   */
	  
	  private static ReportGeneratorAsync reportGenerator = null;

	  /**
	   * Name des Client-Loggers.
	   */
	  private static final String LOGGER_NAME = "PitchMen Client";
	  
	  /**
	   * Instanz des Client-seitigen Loggers.
	   */
	  private static final Logger log = Logger.getLogger(LOGGER_NAME);
	  
	  /**
	   * 
	   */
	  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");

	  /**
	   * <p>
	   * Auslesen des Client-Loggers.
	   * </p>
	   * 
	   * @return die Logger-Instanz für die Server-Seite
	   */
	  public static Logger getLogger() {
	    return log;
	  }

	  /**
	   * <p>
	   * Anlegen und Auslesen der applikationsweit eindeutigen PitchMenAdmin. Diese
	   * Methode erstellt die PitchMenAdmin, wenn sie noch nicht existiert. Ansonsten 
	   * wird das bereits angelegte Objekt übergeben.
	   * </p>
	   * 
	   * @return eindeutige Instanz des Typs <code>PitchMenAdminAsync</code>
	   * @author Simon
	   */
	  public static PitchMenAdminAsync getPitchMenAdmin() {
	    if (pitchMenAdmin == null) {
	      // falls noch nicht geschehen, per GWT.create ein Objekt von PitchMenAdmin erzeugen
	      pitchMenAdmin = GWT.create(PitchMenAdmin.class);
	    }

	    // Zurückgeben des Objekts
	    return pitchMenAdmin;
	  }

	  /**
	   * <p>
	   * Anlegen und Auslesen dey applikationsweit eindeutigen ReportGenerator. Diese
	   * Methode erstellt den ReportGenerator, wenn er noch nicht existiert. Ansonsten 
	   * wird das bereits angelegte Objekt übergeben.
	   * </p>
	   * 
	   * @return eindeutige Instanz des Typs <code>ReportGeneratorAsync</code>
	   * @author Simon
	   */
	  public static ReportGeneratorAsync getReportGenerator() {
	    if (reportGenerator == null) {
	      // Instantiierung von ReportGenerator
	      reportGenerator = GWT.create(ReportGenerator.class);

	      final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {
	        @Override
			public void onFailure(Throwable caught) {
	          ClientsideSettings.getLogger().severe(
	              "Der ReportGenerator konnte nicht initialisiert werden!");
	        }

	        @Override
			public void onSuccess(Void result) {
	          ClientsideSettings.getLogger().info(
	              "Der ReportGenerator wurde initialisiert.");
	        }
	      };

	      reportGenerator.init(initReportGeneratorCallback);
	    }

	    // Rückgabe des ReportGenerator
	    return reportGenerator;
	  }

	public static Person getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(Person currentUser) {
		ClientsideSettings.currentUser = currentUser;
	}
	
	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

}