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

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.PitchMenAdmin;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class MarketplaceForm extends Formular {

	/*
	 * titleBox und descBox sind hier labels, erst beim Erstellen oder
	 * Bearbeiten ist titleBox eine Textbox und descBox eine TextArea
	 */
	private int currentUserId;
	PitchMenAdminAsync pitchMenAdmin = ClientsideSettings.getPitchMenAdmin();
	
	private Marketplace selectedMarketplace = null;
	private Project selectedProject = null;
	
	private ArrayList<Marketplace> marketplaces = null;
	PitchMenTreeViewModel pitchMenTreeViewModel = null;
	
	Label idLabel = new Label();
	Label titleLabel = new Label("Name des Marktplatzes:");
	TextBox titleBox = new TextBox();
	Label descLabel = new Label("Beschreibung des Marktplatzes:");
	TextArea descBox = new TextArea();

	/**
	 * Konstruktor kommt zum Einsatz, wenn der Marktplatz bereits existiert.
	 * @param marketplace
	 */
	public MarketplaceForm(Marketplace marketplace) {

		this.selectedMarketplace = marketplace;

		RootPanel.get("content").clear();
		RootPanel.get("content").add(new HTML("<div class='lds-dual-ring'><div></div></div>"));

		this.currentUserId = ClientsideSettings.getCurrentUser().getId();

		ClientsideSettings.getPitchMenAdmin().getMarketplaceByID(marketplace.getId(), new MarketplaceCallback());
	}
	
	/**
	 * Konstruktor kommt zum Einsatz, wenn ein neuer Marktplatz angelegt wird.
	 */
	public MarketplaceForm() {
		
		RootPanel.get("content").clear();
		
		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.addStyleName("headline");

		topPanel.add(new HTML("<h2>Neuen Projektmarktplatz anlegen</h2>"));
		
		Button cancelButton = new Button("Neuanlage abbrechen");
		cancelButton.addStyleName("delete");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.Location.reload();
			}
		});
		
		
		topPanel.add(cancelButton);

		Button saveButton = new Button("Projektmarktplatz anlegen");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(Window.confirm("Möchten Sie den Projektmarktplatz " + titleBox.getText() + " wirklich anlegen?")) {
					ClientsideSettings.getPitchMenAdmin().addMarketplaceByPerson(titleBox.getText(),
																				descBox.getText(),
																				ClientsideSettings.getCurrentUser().getId(),
																				new AsyncCallback<Marketplace>(){

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().severe("Konnte Projektmarktplatz nicht anlegen");
						}
								
						public void onSuccess(Marketplace result) {
							RootPanel.get("content").add(new MarketplaceForm(result));
						}		
					});
				}	
			}
		});
		
		topPanel.add(saveButton);
		
		RootPanel.get("content").add(topPanel);

		RootPanel.get("content").add(new HTML("<h3>Titel des Marktplatzes</h3>"));

		RootPanel.get("content").add(titleBox);
		
		RootPanel.get("content").add(new HTML("<h3>Marktplatzsbeschreibung</h3>"));

		RootPanel.get("content").add(descBox);
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
			
			if (hasPermission(selectedMarketplace)) {

				RootPanel.get("content").add(new HTML("<div class='info'><p><span class='fa fa-info-circle'></span>"
						+ " Sie sind Besitzer des Projektmarktplatzes. </p></div>"));

				// ---------- Marktplatz löschen, ClickHandler hinzufügen und dem
				// HorizontalPanel hinzufügen
				Button deleteMarketplaceBtn = new Button("- Projektmarktplatz löschen");
				deleteMarketplaceBtn.addClickHandler(new deleteMarketplaceClickHandler());
				deleteMarketplaceBtn.setStyleName("delete");
				topPanel.add(deleteMarketplaceBtn);

				// ---------- Marktplatz bearbeiten, ClickHandler hinzufügen und
				// dem
				// HorizontalPanel hinzufügen

				Button updateMarketplaceBtn = new Button("Projektmarktplatz bearbeiten");
				updateMarketplaceBtn.addClickHandler(new updateMarketplaceClickHandler());
				topPanel.add(updateMarketplaceBtn);

			}
			
			
			
			RootPanel.get("content").add(topPanel);



			RootPanel.get("content")
					.add(new HTML("<h3>Beschreibung: </h3><p>" + selectedMarketplace.getDescription() + "</p>"));

		
		
			// ---------- Neues	 Projekt Button, ClickHandler
			// hinzufügen und dem
			// HorizontalPanel hinzufügen
			Button addProjectBtn = new Button("+ Neues Projekt hinzufügen");
			addProjectBtn.addClickHandler(new addProjectClickHandler());
			topPanel.add(addProjectBtn);
			
		
		}
	}

		

		
		// ---------- ClickHandler
		
		// ---------- addProjectClickHandler
		private class addProjectClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				/*
				 * Das Anlegen eines Projekts ist Sache des ProjectForms.
				 * Da es das anzulegende Objekt noch nicht gibt, übergeben
				 * wir ihm null.
				 */
				ProjectForm addProjectForm = new ProjectForm(selectedMarketplace);
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
			
			private String titleBoxContent;
			public void onClick(ClickEvent event) {

				// bei Click wird die update() Methode aufgerufen

				RootPanel.get("content").clear();
				RootPanel.get("content").add(new HTML(
						"<div class='info'><p><span class='fa fa-info-circle'></span> Sie bearbeiten diesen Marktplatz.</p></div>"));
				HorizontalPanel topPanel = new HorizontalPanel();
				topPanel.addStyleName("headline");

				topPanel.add(new HTML("<h2>Projektmarktplatz <em>" + selectedMarketplace.getTitle() + "</em> bearbeiten</h2>"));
				
				Button cancelButton = new Button("Bearbeitung abbrechen");
				cancelButton.addStyleName("delete");
				cancelButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new MarketplaceForm(selectedMarketplace));
					}
				});
				
				
				topPanel.add(cancelButton);

				Button saveButton = new Button("Änderungen speichern");
				saveButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
						selectedMarketplace.setTitle(titleBox.getText());
						selectedMarketplace.setDescription(descBox.getText());

						ClientsideSettings.getPitchMenAdmin().updateMarketplace(selectedMarketplace, new AsyncCallback<Void>(){
							
							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().severe("Konnte Projektmarktplatz nicht bearbeiten");
							}
							
							public void onSuccess(Void result) {
								
								Window.alert("Projektmarktplatz erfolgreich bearbeitet");
								RootPanel.get("content").add(new MarketplaceForm(selectedMarketplace));
							}
							
						});
					
					}
				});
				
				topPanel.add(saveButton);
				
				RootPanel.get("content").add(topPanel);

				RootPanel.get("content").add(new HTML("<h3>Titel des Marktplatzes</h3>"));

				titleBox.setText(selectedMarketplace.getTitle());
				RootPanel.get("content").add(titleBox);
				
				RootPanel.get("content").add(new HTML("<h3>Marktplatzsbeschreibung</h3>"));

				descBox.setText(selectedMarketplace.getDescription());
				RootPanel.get("content").add(descBox);
				selectedMarketplace.setDescription(descBox.getValue());
				
			}
		}

	// ---------- delete Methode

	public void delete() {
		ClientsideSettings.getPitchMenAdmin().deleteMarketplace(selectedMarketplace, new DeleteMarketplaceCallback(selectedMarketplace));
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
