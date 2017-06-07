package de.pitchMen.client.elements;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class AddProjectForm extends Formular{

	private int mpID;
	Label titleLabel = new Label("Name des Projektes:");
	TextBox titleBox = new TextBox();		
	Label descLabel = new Label("Beschreibung des Projektes:");
	TextBox descBox = new TextBox();
	Label dateOpenedLabel = new Label("Startdatum des Projektes:");
	DateBox dateOpenedBox = new DateBox();		
	Label dateClosedLabel = new Label("Enddatum des Projektes:");
	DateBox dateClosedBox = new DateBox();

	protected void onLoad(int marketplaceID){

		this.mpID = marketplaceID; 

		Button cancelButton = new Button("Abbrechen" + new ClickHandler(){
			public void onClick(ClickEvent event){
				/* Wenn man auf den Cancel Button drückt, wird man auf den angewählten Projektmarktplatz
				 * zurückgeführt
				 * */
				MarketplaceForm mpf = new MarketplaceForm(mpID); 

			}
		}); 

		Button saveButton = new Button("Speichern" + new ClickHandler(){

			public void onClick(ClickEvent event) {

				AddProjectForm f1 = new AddProjectForm(); 
				f1.save(mpID);


			}
		});

	}

	public void save(int MarketplaceID){

		super.getPitchMenAdmin().addProject(dateOpenedBox.getValue(), dateClosedBox.getValue(), titleBox.getText(), descBox.getText(),
				ClientsideSettings.getCurrentUser().getId(),MarketplaceID, new AddProjectFormCallback(this));
		super.setCreator(ClientsideSettings.getCurrentUser().getId());
	}


	public class AddProjectFormCallback implements AsyncCallback<Project>{

		private AddProjectForm addProjectForm = null; 

		public AddProjectFormCallback(AddProjectForm addProjectForm){
			this.addProjectForm = addProjectForm; 
		}

		public void onFailure(Throwable caught){

			this.addProjectForm.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));

		}
		public void onSuccess(Project project){

			Window.alert("erfolgreich gespeichert"); 

		}

	}	

}

