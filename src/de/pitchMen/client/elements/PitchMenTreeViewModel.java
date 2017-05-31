package de.pitchMen.client.elements;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

/**
 * Anstatt der ursprünglich vorgesehenen Navigation,
 * einem VerticalPanel gefüllt mit Buttons, wurde nach 
 * dem Gespräch mit Herrn Rathke am 30.05.2017 
 * überlegt, die Auswahl von Projektmarktplätzen,
 * Projekten und Ausschreibungen durch eine navigierbare
 * Baumstruktur zu realisieren. Hierfür bietet GWT Cell-
 * Widgets an, die in Baumstrukturen angelegt werden. Die
 * vorliegende Klasse <code>PitchMenTreeViewModel</code>
 * steuert das Verhalten der Baumstruktur. Die Ausgabe 
 * der einzelnen Elemente wird durch die entsprechenden
 * Cell-Klassen gerendert. 
 * 
 * Die Hierarchie des Baumes ist Projektmarktplatz > Projekt
 * > Ausschreibung.
 * 
 * @author Simon
 */

public class PitchMenTreeViewModel implements TreeViewModel {
	
	/**
	 * Um in der Lage zu sein, Abfragen an die Applikations-
	 * schicht stellen zu können, ebnötigt PitchMenTreeViewModel
	 * Zugriff auf die PitchMenAdmin.
	 */
	private PitchMenAdminAsync pitchMenAdmin = null;
	
	/**
	 * ListDataProvider-Objekte verwalten das Datenmodell
	 * auf Client-Seite. Dies ist der ListDataProvider für
	 * die Projektmarktplätze der PitchMen-Applikation.
	 */
	private ListDataProvider<Marketplace> marketplaceLDP = null;
	
	/**
	 * Diese Map speichert die ListDataProviders für die 
	 * Projektmarktplätze, die im Navigationsbaum expandiert
	 * wurden.
	 */
	private Map<Marketplace, ListDataProvider<Project>> projectDataProviders = null;
	
	/**
	 * Diese Map speichert die ListDataProviders für die 
	 * Projekte, die im Navigationsbaum expandiert
	 * wurden.
	 */
	private Map<Project, ListDataProvider<JobPosting>> jobPostingDataProviders = null;
	
	/**
	 * Konstruktor für Instanzen der Klasse.Hier wird z. B.
	 * die Verbindung zur PitchMenAdministration hergestellt. 
	 */
	public PitchMenTreeViewModel() {
		this.pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();
	}

	/**
	 * Die Methode <code>getNodeInfo()</code> stellt Informationen
	 * über den aktuell angefragten Knoten der Baumstruktur zur
	 * Verfügung.
	 */
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		
		/*
		 * Wenn value == null gilt, bedeutet das, dass die
		 * Wurzel der Baumstruktur abgefragt wird. In diesem
		 * Fall soll eine Ausgabe von MarketplaceCells 
		 * erfolgen.
		 */
		if(value == null) {
			// Erzeugen eines neuen ListDataProviders für Marketplaces
			this.marketplaceLDP = new ListDataProvider<Marketplace>();
			
			// Abfrage aller Marketplaces über die Applikationsschicht
			this.pitchMenAdmin.getMarketplaces(new AsyncCallback<ArrayList<Marketplace>>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
				}

				@Override
				public void onSuccess(ArrayList<Marketplace> result) {
					// Übertragen aller Marketplace-Objekte in den ListDataProvider
					for(Marketplace marketplace : result) {
						marketplaceLDP.getList().add(marketplace);
					}
				}
				
			});
			
			/*
			 * TODO Abklären was SelectionModel ist
			 * In der Vorlage beim Bankprojekt wird an dieser Stelle ein 
			 * anderer Konstruktor mit mehr Parametern verwendet, unter anderem
			 * mit dem selectionModel, das auch in dieser Klasse definiert ist.
			 * Die Funktion dieses SelesctionModels ist mir noch nicht klar.
			 * 
			 * Simon, 31.05.2017, 17:40
			 */
			return new DefaultNodeInfo<Marketplace>(marketplaceLDP, new MarketplaceCell());
		}
		
		/*
		 * Ist der value-Parameter vom Typ Marketplace, wird die 
		 * darunterliegende Hierarchie-Ebene der zu diesem 
		 * Marktplatz gehörenden Projekt in einen ListDataProvider
		 * geschrieben.
		 */
		if(value instanceof Marketplace) {
			ListDataProvider<Project> projectLDP = new ListDataProvider<Project>();
			this.projectDataProviders.put((Marketplace) value, projectLDP); 
			
			/*
			 * FIXME Die Methode getProjectsOf(Marketplace m) ist aktuell noch nicht 
			 * in der PitchMenAdmin bzw. der PitchMenAdminImpl vorhanden. Sie muss 
			 * hinzugefügt werden, und wenn anders benannt, muss ihr Aufruf an dieser
			 * Stelle angepasst werden. Wenn die Methode implementiert wurde, in der 
			 * nächsten Zeile also kein Fehler ausgegeben wird, ist dieses Fixme 
			 * überflüssig geworden und kann gelöscht werden.
			 * 
			 * Simon, 31.05.2017 18:10
			 */
			this.pitchMenAdmin.getProjectsOf((Marketplace) value, new AsyncCallback<ArrayList<Project>>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
				}

				@Override
				public void onSuccess(ArrayList<Project> result) {
					// TODO Methode nach Vorbild BankProjekt (CustomerAccountsTreeViewModel) schreiben
					
				}
				
			});
		}
		return null;
	}
	
	/**
	 * Die Methode <code>isLeaf()</code> überprüft, ob es sich
	 * bei dem aufgerufenen Baumknoten um ein Blatt handelt (d. h.
	 * der Knoten hat keine Kindknoten). Im Falle der PitchMen-
	 * Applikation ist das der Fall, wenn der Knoten vom Typ 
	 * Ausschreibung ({@link JobPosting}) ist. 
	 */

	@Override
	public boolean isLeaf(Object value) {
		// Prüfe ob der aufgerufene Knoten ein jobPosting-Objekt ist
		return (value instanceof JobPosting);
	}

}
