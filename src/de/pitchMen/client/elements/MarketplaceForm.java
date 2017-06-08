package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

//TODO noch Buttons (löschen bearbeiten) hinzufügen falls hasPermission() is true, bei false nur + Btn
public class MarketplaceForm extends Formular {

	private int mpID;

	public MarketplaceForm(int marketplaceID) {
		super();

		this.mpID = marketplaceID;

		super.getPitchMenAdmin().getMarketplaceByID(this.mpID, new MarketplaceFormCallback(this));

	}

	class MarketplaceFormCallback implements AsyncCallback<Marketplace> {

		private MarketplaceForm marketplaceform = null;

		public MarketplaceFormCallback(MarketplaceForm marketplaceForm) {
			this.marketplaceform = marketplaceForm;
		}

		public void onFailure(Throwable caught) {

			this.marketplaceform.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}

		public void onSuccess(Marketplace marketplace) {
			if (marketplace != null) {

				this.marketplaceform.add(new HTML(marketplace.getTitle()));
				this.marketplaceform.add(new HTML(marketplace.getDescription()));

				// ---------- Neuer Projektmarktplatz Button:
				Button addMarketplaceBtn = new Button("+ Neuer Projektmarktplatz", new ClickHandler() {

					public void onClick(ClickEvent event) {

						// bei Click wird die Methode onLoad() aus der Klasse
						// AddMarketplaceForm aufgerufen
						AddMarketplaceForm addMarketplace = new AddMarketplaceForm();

						addMarketplace.onLoad();

					}
				});

				this.marketplaceform.add(addMarketplaceBtn);

				// ---------- Neues Projekt Button:

				Button addProjectBtn = new Button("+ Neues Projekt hinzufügen", new ClickHandler() {

					public void onClick(ClickEvent event) {

						AddProjectForm addProject = new AddProjectForm();

						addProject.onLoad(mpID);

					}
				});

				this.marketplaceform.add(addProjectBtn);

				/*
				 * Wenn der aktuelle User gleich dem Ersteller ist, dann hat
				 * dieser die Buttons Löschen und Bearbeiten zur verfügung.
				 */
				if (hasPermission()) {

					// ---------- Projektmarktplatz löschen

					Button deleteMarketplaceBtn = new Button("- Ausgewählter Projektmarktplatz löschen",
							new ClickHandler() {

						public void onClick(ClickEvent event) {

							// bei Click wird die Methode delete()
							// aufgerufen

							if (Window.confirm("Sind Sie sich sicher, dass Sie das löschen wollen?")) {
								delete();

							}

						}
					});
					this.marketplaceform.add(deleteMarketplaceBtn);

					// ---------- Projektmarktplatz bearbeiten

					Button updateMarketplaceBtn = new Button("Bearbeiten", new ClickHandler() {

						public void onClick(ClickEvent event) {

							// bei Click wird die Methode update() aufgerufen
							update();

						}
					});
					this.marketplaceform.add(updateMarketplaceBtn);
				}

			}
		}
	}

	public void delete(){
		Marketplace m1 = super.getPitchMenAdmin().getMarketplaceByID(mpID, new GetMarketplaceCallback());

		super.getPitchMenAdmin().deleteMarketplace(m1, new DeleteAsyncCallback());
	}

	class DeleteAsyncCallback implements AsyncCallback<Void> {

		public DeleteAsyncCallback() {

		}

		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Projektmarktplatzes ist fehlgeschlagen!");

		}

		public void onSuccess(Void result) {
		}
	}

	public void update() {
		Marketplace m1 = super.getPitchMenAdmin().getMarketplaceByID(mpID, new GetMarketplaceCallback(this);

		super.getPitchMenAdmin().updateMarketplace(m1, callback);
	}

	//Folgendes vllt unnötig,
	//	class GetMarketplaceCallback implements AsyncCallback<Marketplace> {
	//		
	//		private MarketplaceForm marketplace;
	//
	//		public GetMarketplaceCallback(MarketplaceForm marketplace) {
	//			this.marketplace = marketplace; 
	//		}
	//
	//		public void onFailure(Throwable caught) {
	//			Window.alert("Das Löschen des Projektmarktplatzes ist fehlgeschlagen!");
	//
	//		}
	//
	//		public void onSuccess(Marketplace marketplace) {
	//		}
	//	}

}
