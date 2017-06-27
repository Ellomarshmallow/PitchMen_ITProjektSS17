package de.pitchMen.client.elements;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class MarketplaceForm extends Formular {

	/*
	 * titleBox und descBox sind hier labels, erst beim Erstellen oder
	 * Bearbeiten ist titleBox eine Textbox und descBox eine TextArea
	 */
	private int currentUserId = 0;
	PitchMenAdminAsync pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();
	
	private Marketplace selectedMarketplace = null;
	private Project selectedProject = null;
	
	private ArrayList<Marketplace> marketplaces = null;
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Marktplatzes:");
	Label titleBox = new Label();
	Label descLabel = new Label("Beschreibung des Marktplatzes:");
	Label descBox = new Label();

	public MarketplaceForm(Marketplace marketplace) {

		this.selectedMarketplace = marketplace;

		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));

		this.currentUserId = ClientsideSettings.getCurrentUser().getId();

		ClientsideSettings.getPitchMenAdmin().getMarketplaceByID(marketplace.getId(), new MarketplaceCallback());
	}

	private class MarketplaceCallback implements AsyncCallback<Marketplace> {

		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Empfangen der Prjektmarktpl�tze fehlgeschlagen");
		}

		public void onSuccess(Marketplace result) {
			selectedMarketplace = result;

			RootPanel.get("content").clear();

			HorizontalPanel topPanel = new HorizontalPanel();
			topPanel.addStyleName("headline");

			topPanel.add(new HTML("<h2>Projektmarktplatz: <em>" + selectedMarketplace.getTitle() + "</em></h2>"));

			RootPanel.get("content").add(topPanel);

			RootPanel.get("content")
					.add(new HTML("<h3>Beschreibung: </h3><p>" + selectedMarketplace.getDescription() + "</p>"));

		
			// ---------- Neue Ausschreibung Button, ClickHandler
			// hinzufügen und dem
			// HorizontalPanel hinzufügen
			Button addMarketplaceBtn = new Button("+ Neuer Projektmarktplatz hinzufügen");
			addMarketplaceBtn.addClickHandler(new addMarketplaceClickHandler());
			topPanel.add(addMarketplaceBtn);
		
		
			// ---------- Neue Projekt Button, ClickHandler
			// hinzufügen und dem
			// HorizontalPanel hinzufügen
			Button addProjectBtn = new Button("+ Neues Projekt hinzufügen");
			addProjectBtn.addClickHandler(new addProjectClickHandler());
			topPanel.add(addProjectBtn);
			
			if (hasPermission(selectedMarketplace)) {

				RootPanel.get("content").add(new HTML("<div class='info'><p><span class='fa fa-info-circle'></span>"
						+ " Sie sind Besitzer des Projektmarktplatzes. </p></div>"));

				// ---------- Projekt löschen, ClickHandler hinzufügen und dem
				// HorizontalPanel hinzufügen
				Button deleteMarketplaceBtn = new Button("- Projektmarktplatz löschen");
				deleteMarketplaceBtn.addClickHandler(new deleteMarketplaceClickHandler());
				topPanel.add(deleteMarketplaceBtn);

				// ---------- Projekt bearbeiten, ClickHandler hinzufügen und
				// dem
				// HorizontalPanel hinzufügen

				Button updateMarketplaceBtn = new Button("Bearbeiten");
				updateMarketplaceBtn.addClickHandler(new updateMarketplaceClickHandler());
				topPanel.add(updateMarketplaceBtn);

			}
		}
	}

		//
		// //Vertical Panel erstellen
		// VerticalPanel labelsPanel = new VerticalPanel();
		// this.add(labelsPanel);
		//
		// //labels und Boxen dem Vertical Panel hinzufügen
		// labelsPanel.add(idLabel);
		// labelsPanel.add(titleLabel);
		// labelsPanel.add(titleBox);
		// labelsPanel.add(descLabel);
		// labelsPanel.add(descBox);
		//
		// //HorizontalPanel für die Buttons erstellen
		// HorizontalPanel buttonsPanel = new HorizontalPanel();
		// this.add(buttonsPanel);
		//
		// //Die zwei VerticalPanels hinzufügen
		// RootPanel.get("content").clear();
		// RootPanel.get("content").add(labelsPanel);
		// RootPanel.get("content").add(buttonsPanel);
		//
		//
		//
		// //Projektmarktplatz ausgeben
		// HorizontalPanel topPanel = new HorizontalPanel();
		// topPanel.addStyleName("headline");
		//
		//
		//
		//
		// // ---------- Neuer Projektmarktplatz Button, ClickHandler
		// hinzufügen und dem HorizontalPanel hinzufügen
		// Button addMarketplaceBtn = new Button("+ Neuer Projektmarktplatz");
		// addMarketplaceBtn.addClickHandler(new addMarketplaceClickHandler());
		// buttonsPanel.add(addMarketplaceBtn);
		//
		//
		// // ---------- Neues Projekt Button, ClickHandler hinzufügen und dem
		// HorizontalPanel hinzufügen
		// Button addProjectBtn = new Button("+ Neues Projekt hinzufügen");
		// addProjectBtn.addClickHandler(new addProjectClickHandler());
		// buttonsPanel.add(addProjectBtn);
		//
		// /*
		// * Wenn der aktuelle User gleich der PersonId ist, dann hat
		// * dieser die Buttons Löschen und Bearbeiten zur verfügung. Vgl.
		// hasPermission() in Formular.java
		// */
		// if (hasPermission(this.selectedMarketplace)) {
		//
		//
		// // ---------- Projektmarktplatz löschen, ClickHandler hinzufügen
		// und dem HorizontalPanel hinzufügen
		// Button deleteMarketplaceBtn = new Button("- Projektmarktplatz
		// löschen");
		// deleteMarketplaceBtn.addClickHandler(new
		// deleteMarketplaceClickHandler());
		// buttonsPanel.add(deleteMarketplaceBtn);
		//
		//
		// // ---------- Projektmarktplatz bearbeiten, ClickHandler hinzufügen
		// und dem HorizontalPanel hinzufügen
		//
		// Button updateMarketplaceBtn = new Button("Bearbeiten");
		// updateMarketplaceBtn.addClickHandler(new
		// updateMarketplaceClickHandler());
		// buttonsPanel.add(updateMarketplaceBtn);

		// }
		//
		//
		//
		// public Marketplace getSelectedMarketplace() {
		// return selectedMarketplace;
		// }
		//
		// public Project getSelectedProject(){
		// return selectedProject;
		// }

		
		// ---------- ClickHandler
		// ---------- addMarketplaceClickHandler
		private class addMarketplaceClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {

				/*
				 * bei Click wird ein Objekt vom Typ AddMarketplaceForm erzeugt
				 * und der aktuell gewählte Marktplatz, den
				 * pitchMenTreeViewModel und einen booleanschen Wert übergeben.
				 * Dieser Wert sagt aus, ob der addMarketplaceBtn oder der
				 * updateMarketplaceBtn gedrückt wurde, da beide ClickHandler
				 * ein Objekt vom Typ AddMarketplaceForm erzeugen. Der
				 * booleansche Wert wird benötigt um festzulegen ob bei dem
				 * Click auf den saveButton in AddMarketplaceForm.java die
				 * save() oder die update() Methode verwendet wird.
				 * addMarketplaceBtn = true updateMarketplaceBtn = false
				 */
				AddMarketplaceForm addMarketplace = new AddMarketplaceForm(selectedMarketplace, pitchMenTreeViewModel,
						true);
			}
		}

		// ---------- addProjectClickHandler
		private class addProjectClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				// FIXME selectedProject steht im Konstruktor von
				// AddProjectForm, geht hier aber nicht
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML("<h2> Ich wurde geclickt <h2>"));

				AddProjectForm addProject = new AddProjectForm(selectedProject, pitchMenTreeViewModel, true);

			}
		}

		// ---------- deleteMarketplaceClickHandler
		private class deleteMarketplaceClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {

				// bei Click wird die delete() Methode aufgerufen

				if (Window.confirm("Sind Sie sich sicher, dass Sie das löschen wollen?")) {
					delete();

				}
			}
		}

		// ---------- updateMarketplaceClickHandler
		private class updateMarketplaceClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {

				// bei Click wird die update() Methode aufgerufen

				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML(
						"<div class='info'><p><span class='fa fa-info-circle'></span> Sie bearbeiten diese Ausschreibung.</p></div>"));
				HorizontalPanel topPanel = new HorizontalPanel();
				topPanel.addStyleName("headline");

				Button cancelButton = new Button("Bearbeitung abbrechen");
				cancelButton.addStyleName("delete");
				cancelButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new ProjectForm(selectedProject));
					}
				});

				Button saveButton = new Button("Änderungen speichern");
				saveButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO
					}
				});

				topPanel.add(new HTML("<h2>Projekt: <em>" + selectedProject.getTitle() + "</em></h2>"));
				topPanel.add(saveButton);
				topPanel.add(cancelButton);
				RootPanel.get("content").add(topPanel);

				RootPanel.get("content").add(new HTML("<h3>Titel des Projekts</h3>"));

				TextBox titleBox = new TextBox();
				titleBox.setText(selectedProject.getTitle());
				RootPanel.get("content").add(titleBox);

				RootPanel.get("content").add(new HTML("<h3>Projektbeschreibung</h3>"));

				TextArea projectDesc = new TextArea();
				projectDesc.setText(selectedProject.getDescription());
				RootPanel.get("content").add(projectDesc);

				// AddMarketplaceForm updateMarketplace = new
				// AddMarketplaceForm(getSelectedMarketplace(),MarketplaceForm.this.pitchMenTreeViewModel,
				// false);
			}
		}

	// ---------- delete Methode

	public void delete() {
		super.getPitchMenAdmin().deleteMarketplace(selectedMarketplace, new DeleteMarketplaceCallback(selectedMarketplace));
	}
	
	class DeleteMarketplaceCallback implements AsyncCallback<Void> {

		Marketplace m = null;

		public DeleteMarketplaceCallback(Marketplace m) {
			this.m = m;
		}

		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Projektmarktplatzes ist fehlgeschlagen!");

		}

		public void onSuccess(Void result) {
			if (m != null) {
				setSelectedMarketplace(null);
				pitchMenTreeViewModel.deleteMarketplace(m);
			}
		}
	}

	// ----------pitchMenTreeViewModelsetter
	public void setPitchMenTreeViewModel(PitchMenTreeViewModel pitchMenTreeViewModel) {
		this.pitchMenTreeViewModel = pitchMenTreeViewModel;
	}

	// ---------- selectedMarketplace setter
	public void setSelectedMarketplace(Marketplace m) {
		if (m != null) {
			this.selectedMarketplace = m;
			titleBox.setText(selectedMarketplace.getTitle());
			descBox.setText(selectedMarketplace.getDescription());
			idLabel.setText(Integer.toString(selectedMarketplace.getId()));
		} else {
			idLabel.setText("");
			titleBox.setText("");
			descBox.setText("");
		}
	}
}
