package de.pitchMen.client.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.BusinessObject;
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
	 * schicht stellen zu können, benötigt PitchMenTreeViewModel
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
	
	/*
	 * Im Baum kann es einen ausgewählten Marktplatz, ein 
	 * ausgewähltes Projekt und eine ausgewählte Ausschreibung
	 * geben. 
	 */
	private Marketplace selectedMarketplace = null;
	private Project selectedProject = null;
	private JobPosting selectedJobPosting = null;
	
	/*
	 * Entsprechend gibt es Formulare, mit deren Hilfe die zuvor
	 * selektierten Business-Objekte manipuliert werden können.
	 */
	private MarketplaceForm marketplaceForm = null;
	private ProjectForm projectForm = null;
	private JobPostingForm jobPostingForm = null;
	
	/**
	 * Die genestete Klasse <code>BusinessObjectKeyProvider</code>
	 * dient dazu, Baumknoten des TreeViewModels eindeutige Schlüssel
	 * auf Basis der in ihnen enthaltenen BusinessObjects 
	 * zuzuweisen. Auf Basis dieser Schlüssel arbeitet das 
	 * SelectionModel. Diese Vorgehensweise wurde aus der gleichnamigen
	 * Klasse im bankProjek (Rathke C., 2016) übernommen. Allerdings
	 * konnte die Vorgehensweise mit positiven und negativen Integers
	 * nicht übernommen werden, da es sich nicht um zwei, sondern drei
	 * Hierarchieebenen handelt. Deshalb wird mit Strings gearbeitet, die 
	 * sich aus einem einzelnen Buchstaben als Identifier, einem
	 * Bindestrich und der id des Business-Objekts zusammensetzen.
	 * 
	 * @author Simon
	 */
	private class BusinessObjectKeyProvider implements ProvidesKey<BusinessObject> {

		@Override
		public String getKey(BusinessObject bo) {
			if (bo == null) {
				/*
				 *  Wofür dieser Sonderfall gebraucht wird, ist mir aktuell 
				 *  nicht klar. Übernommen. Simon, 07.06.2017, 20:10 Uhr
				 */
				return null;
			} else if (bo instanceof Marketplace) {
				// Es handelt sich um ein Marktplatz-Objekt. Präfix M
				return "M-" + bo.getId();
			} else if (bo instanceof Project) {
				// Es handelt sich um ein Projekt-Objekt. Präfix P
				return "P-" + bo.getId();
			} else {
				// Es handelt sich um ein Ausschriebungs-Objekt. Präfix J
				return "J-" + bo.getId();
			}
		}
		
	}
	
	/**
	 * Die zuvor lokal definierte Klasse {@link BusinessObjectKeyProvider}
	 * wird nun als Variable der Klasse {@link PitchMenTreeViewModel} 
	 * hinzugefügt.
	 */
	private BusinessObjectKeyProvider boKeyProvider = null;
	
	/**
	 * Das <code>SingleSelectionObject</code> wird von GWT vordefiniert
	 * und beschreibt die Auswahl von Objekten im Baum.
	 */
	private SingleSelectionModel<BusinessObject> selectionModel = null;
	
	/**
	 * Die genestete Klasse <code>SelectionChangeEventHandler</code>
	 * dient dazu, Events in Bezug auf den Wechsel einer Auswahl im
	 * Baum zu bearbeiten. Diese Vorgehensweise wurde aus der gleichnamigen
	 * Klasse im bankProjek (Rathke C., 2016) übernommen. 
	 * 
	 * @author Simon
	 */
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			
			// Beim Wechsel der Auswahl wird zunächst das ausgewählte Objekt abgefragt
			BusinessObject selection = selectionModel.getSelectedObject();
			
			if (selection instanceof Marketplace) {
				// Handelt es sich dabei um ein Marktplatz-Objekt, wird dieses gesetzt
				setSelectedMarketplace((Marketplace) selection);
			} else if (selection instanceof Project) {
				// Handelt es sich stattdessen um ein Projekt-Objekt, wird dieses gesetzt
				setSelectedProject((Project) selection);
			} else {
				// Handelt es sich um ein Ausschreibungs-Objekt, wird dieses gesetzt
				setSelectedJobPosting((JobPosting) selection);
			}
			
		}
		
	}
	
	/**
	 * Konstruktor für Instanzen der Klasse. Hier wird z. B.
	 * die Verbindung zur PitchMenAdministration hergestellt. 
	 */
	public PitchMenTreeViewModel() {
		this.pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();
		boKeyProvider = new BusinessObjectKeyProvider();
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		projectDataProviders = new HashMap<Marketplace, ListDataProvider<Project>>();
		jobPostingDataProviders = new HashMap<Project, ListDataProvider<JobPosting>>();
	}
	
	/*
	 * Setter für die Formulare
	 */
	public void setMarketplaceForm(MarketplaceForm marketplaceForm) {
		this.marketplaceForm = marketplaceForm;
	}

	public void setProjectForm(ProjectForm projectForm) {
		this.projectForm = projectForm;
	}

	public void setJobPostingForm(JobPostingForm jobPostingForm) {
		this.jobPostingForm = jobPostingForm;
	}
	
	/**
	 * Methode zum Abfragen des aktuell selektierten Projekt-Marktplatzes.
	 * @return den aktuell selektierten Marktplatz
	 */
	public Marketplace getSelectedMarketplace() {
		return selectedMarketplace;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Nutzer im Baum einen 
	 * Projektmarktplatz selektiert. Der Aufruf geschieht dabei durch
	 * die genestete Klasse {@link SelectionChangeEventHandler}. Da 
	 * damit die höchste Hierarchie-Stufe angesprochen wird, werden 
	 * die darunter liegenden Instanzen von Projekten und Ausschreibungen 
	 * in dieser Methode <code>null</code> gesetzt.
	 * 
	 * @param selectedMarketplace, der neu selektierte Projektmarktplatz
	 */
	public void setSelectedMarketplace(Marketplace selectedMarketplace) {
		/*
		 *  Wird ein Marktplatz ausgewählt, wird das sowohl
		 *  dem TreeViewModel als auch dem MarketplaceForm
		 *  mitgeteilt.
		 */
		this.selectedMarketplace = selectedMarketplace;
		this.marketplaceForm.setSelected(selectedMarketplace);
		
		/*
		 * Gleichzeitig werden die evt. noch vorhandenen Projekte
		 * und Ausschreibungen, die zuvor angezeigt wurden, entfernt.
		 */
		this.selectedProject = null;
		this.projectForm.setSelected(null);
		this.selectedJobPosting = null;
		this.jobPostingForm.setSelected(null);	
		
	}

	/**
	 * Methode zum Abfragen des aktuell selektierten Projekts.
	 * @return das aktuell selektierte Projekt
	 */
	public Project getSelectedProject() {
		return selectedProject;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Nutzer im Baum ein 
	 * Projekt selektiert. Der Aufruf geschieht dabei durch
	 * die genestete Klasse {@link SelectionChangeEventHandler}. Da 
	 * damit die zweithöchste Hierarchie-Stufe angesprochen wird, wird 
	 * die evt. darunter liegenden Instanz der selektierten Ausschreibung 
	 * in dieser Methode <code>null</code> gesetzt. Da Marktplätze sich
	 * hierarchisch allerdings <em>über</em> Projekten befinden, muss
	 * u. U. der selektierte Projektmarktplatz angepasst werden. Hierfür
	 * erfolgt in dieser Methode ein <strong>RPC</strong>, um den zum
	 * Projekt gehörenden Projekt-Marktplatz abzufragen.
	 * 
	 * @param selectedProject, das neu selektierte Projekt
	 */
	public void setSelectedProject(Project selectedProject) {
		/*
		 * Die Asuwahl eines Projekts hat die folgenden
		 * Updates zur Folge:
		 */
		this.selectedProject = selectedProject;
		this.projectForm.setSelected(selectedProject);
		
		/*
		 * Ausschreibungen liegen hierarchisch unter Projekten,
		 * deshalb wird hier erst einmal kein Element mehr 
		 * selektiert.
		 */
		this.selectedJobPosting = null;
		this.jobPostingForm.setSelected(null);
		
		/*
		 * Projektmarktplätze liegen hierarchisch über Projekten.
		 * Deshalb sollte der selektierte Projektmarktplatz 
		 * auf den aktuellen Stand gebracht werden.
		 */
		if(selectedProject != null) {
			/*
			 *  Auf Basis des selektierten Projekts soll der entsprechende,
			 *  darüber liegende Projektmarktplatz selektiert werden.
			 *  Dies soll nur geschehen, wenn es auch wirklich ein 
			 *  Projekt-Objekt gibt.
			 */
			this.pitchMenAdmin.getMarketplaceByID(this.selectedProject.getMarketplaceId(), new AsyncCallback<Marketplace>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Ein Fehler bei der Abfrage des Marktplatzes ist aufgetreten");
				}

				@Override
				public void onSuccess(Marketplace result) {
					selectedMarketplace = result;
					marketplaceForm.setSelected(result);
				}
				
			});
		}
	}

	/**
	 * Methode zum Abfragen der aktuell selektierten Ausschreibung.
	 * @return die aktuell selektierte Ausschreibung
	 */
	public JobPosting getSelectedJobPosting() {
		return selectedJobPosting;
	}

	/**
	 * Diese Methode wird aufgerufen, wenn der Nutzer im Baum eine 
	 * Auschreibung selektiert. Der Aufruf geschieht dabei durch
	 * die genestete Klasse {@link SelectionChangeEventHandler}. Da 
	 * damit die unterste Hierarchie-Stufe angesprochen wird, muss
	 * u. U. das selektierte Projekt sowie, darüber liegend, der 
	 * selektierte Projektmarktplatz angepasst werden. Hierfür
	 * erfolgt in dieser Methode ein <strong>RPC</strong>, um das zur
	 * Ausschreibung gehörende Projekt und dann, geschachtelt, den zum
	 * Projekt gehörenden Projekt-Marktplatz abzufragen.
	 * 
	 * @param selectedProject, das neu selektierte Projekt
	 */
	public void setSelectedJobPosting(JobPosting selectedJobPosting) {
		/*
		 * Der Aufruf dieser Methode ändert das aktuell
		 * selektierte Ausschreibungs-Objekt.
		 */
		this.selectedJobPosting = selectedJobPosting;
		this.jobPostingForm.setSelected(selectedJobPosting);
		
		/*
		 * Gleichzeitig muss das darüberliegende Projekt 
		 * aktualisiert werden.
		 */
		this.pitchMenAdmin.getProjectByID(selectedJobPosting.getProjectId(), new AsyncCallback<Project>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().severe("Fehler bei der Abfrage des Projekts");
			}

			@Override
			public void onSuccess(Project result) {
				selectedProject = result;
				projectForm.setSelected(result);
				
				/*
				 * Mitunter muss auch der Marktplatz des Projekts
				 * aktualisiert werden.
				 */
				pitchMenAdmin.getMarketplaceByID(result.getMarketplaceId(), new AsyncCallback<Marketplace>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Ein Fehler bei der Abfrage des Marktplatzes ist aufgetreten");
					}

					@Override
					public void onSuccess(Marketplace result) {
						selectedMarketplace = result;
						marketplaceForm.setSelected(result);
					}
					
				});
			}
			
		});
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
			
			return new DefaultNodeInfo<Marketplace>(marketplaceLDP, new MarketplaceCell(), selectionModel, null);
		}
		
		/*
		 * Ist der value-Parameter vom Typ Marketplace, wird die 
		 * darunterliegende Hierarchie-Ebene der zu diesem 
		 * Marktplatz gehörenden Projekte in einen ListDataProvider
		 * geschrieben.
		 */
		if(value instanceof Marketplace) {
			final ListDataProvider<Project> projectLDP = new ListDataProvider<Project>();
			this.projectDataProviders.put((Marketplace) value, projectLDP); 			
			int marketplaceId = ((Marketplace) value).getId();
			
			this.pitchMenAdmin.getProjectsByMarketplaceId(marketplaceId, new AsyncCallback<ArrayList<Project>>()  {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Projekte konnten nicht abgefragt werden");
				}

				@Override
				public void onSuccess(ArrayList<Project> result) {
					for(Project project : result) {
						projectLDP.getList().add(project);
					}
				}
			 	
			});
			
			return new DefaultNodeInfo<Project>(projectLDP, new ProjectCell(), selectionModel, null);
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
