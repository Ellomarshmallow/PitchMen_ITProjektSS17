package de.pitchMen.server.report;

import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.ReportGenerator;

/**
 * Implemetierungsklasse des Interface ReportGenerator.  Sie enth‰lt die Applikationslogik, stellt die Zusammenh‰nge konstistent dar und ist zust‰ndig f¸r einen geordneten Ablauf.
 * 
 * @author JuliusDigel
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private static final long serialVersionUID = 1L;
	private PitchMenAdminImpl administration = null;

	public ReportGeneratorImpl() throws IllegalArgumentException{}


	@Override
	public void init() throws IllegalArgumentException{
		/**	
		 Ein ReportGeneratorImpl-Objekt instantiiert f¸r seinen Eigenbedarf eine
		 * PitchMenAdministration-Instanz.
		 */
		PitchMenAdminImpl a = new PitchMenAdminImpl();
		a.init();
		administration = a;	
	}

	/**
	 * Auslesen der zugeh√∂rigen BankAdministration (interner Gebrauch).
	 * 
	 * @return das BankVerwaltungsobjekt
	 */
	protected PitchMenAdmin getPitchMenAdmin() {
		return this.administration;
	}

	 /**
	   * Hinzuf√ºgen des Report-Impressums. Diese Methode ist aus den
	   * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	   * diese T√§tigkeiten redundant auszuf√ºhren h√§tte. Stattdessen rufen die
	   * <code>create...</code>-Methoden diese Methode auf.
	   * 
	   * @param r der um das Impressum zu erweiternde Report.
	   */
	
	 protected void addImprint(Report r) {
		    /*
		     * Das Impressum soll wesentliche Informationen √ºber die Bank enthalten.
		     */
		    Bank bank = this.administration.getBank();

		    /*
		     * Das Imressum soll mehrzeilig sein.
		     */
		    CompositeParagraph imprint = new CompositeParagraph();

		    imprint.addSubParagraph(new SimpleParagraph(bank.getName()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getStreet()));
		    imprint.addSubParagraph(new SimpleParagraph(bank.getZip() + " "
		        + bank.getCity()));

		    // Das eigentliche Hinzuf√ºgen des Impressums zum Report.
		    r.setImprint(imprint);

		  }

	/**
	 * Default constructor
	 */

}