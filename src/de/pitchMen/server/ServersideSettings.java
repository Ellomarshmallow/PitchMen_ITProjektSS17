package de.pitchMen.server;

import java.util.logging.Logger;
import de.pitchMen.shared.CommonSettings;

/**
 * Beschreibung aller Einstellungen der Klassen auf dem Server. Subklasse von
 * CommonSettings.
 * 
 * @author
 */
public class ServersideSettings extends CommonSettings {
	private static final String LOGGER_NAME = "PitchMen Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	/**
	 * Default constructor
	 */
	public ServersideSettings() {
	}

	/**
	 * @return
	 */
	public Logger getLogger() {
		return log;
	}

}