package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.*;
import de.pitchMen.client.report.*;
import de.pitchMen.shared.bo.*;

/**
 * Das CreateTeamForm ist wie das FirstLoginForm. Es wird instanziiert, wenn bei
 * einer Nutzeranmeldung festgestellt wird, dass ein Team einer bestimmten
 * Person noch nicht in der Datenbank der PitchMen-Applikation vorhanden ist.
 * 
 * @author Eleonora Renz
 */
public class CreateTeamForm extends Formular {

	Label nameLabel = new Label("Team-Name: ");
	TextBox nameBox = new TextBox();
	Label descriptionLabel = new Label("Kurzbeschreibung: ");
	TextBox descriptionBox = new TextBox();
	Label teamSizeLabel = new Label("Team-Gr��e: ");
	TextBox teamSizeBox = new TextBox();

	private Team newTeam = null;
	private PitchMen pitchMen = null;
	private ReportDisplay reportDisplay = null;

	public CreateTeamForm(PitchMen pitchMen) {

		this.pitchMen = pitchMen;
		// FIXME
		// this.newTeam = ClientsideSettings.getCurrentUser();

		// Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel();
		this.add(labelsPanel);

		// Labels und Boxen dem Vertical Panel hinzuf端gen

		labelsPanel.add(nameLabel);
		labelsPanel.add(nameBox);
		labelsPanel.add(descriptionLabel);
		labelsPanel.add(descriptionBox);
		labelsPanel.add(teamSizeLabel);
		labelsPanel.add(teamSizeBox);

		// HorizontalPanel f端r den Button erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		// ---------- Speichern Button, ClickHandler hinzuf端gen und dem
		// HorizontalPanel hinzuf端gen
		Button saveButton = new Button("Speichern");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				save();
			}
		});
		buttonsPanel.add(saveButton);

		this.add(buttonsPanel);
	}

	public void save() {
		ClientsideSettings.getPitchMenAdmin().addTeam(nameBox.getText(), descriptionBox.getText(),
				Integer.parseInt(teamSizeBox.getText()), new NewTeamCallback());
	}

	private class NewTeamCallback implements AsyncCallback<Team> {

		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Neues Team konnte nicht gespeichert werden.");
			RootPanel.get("content").clear();
		}

		// TODO
		@Override
		public void onSuccess(Team result) {
			// ClientsideSettings.getLogger().info("Neue Person erfolgreich
			// gespeichert: " + result.getFirstName() + " "
			// + result.getName() + " (" + result.getEmailAdress() + ")");
			// ClientsideSettings.getCurrentUser().setFirstName(result.getFirstName());
			// ClientsideSettings.getCurrentUser().setName(result.getName());
			// ClientsideSettings.getCurrentUser().setIsExisting(true);

			RootPanel.get("usermenu").clear();
			RootPanel.get("content").clear();
			pitchMen.onModuleLoad();
		}

	}

	public CreateTeamForm(ReportDisplay reportDisplay) {
		this.reportDisplay = reportDisplay;
		// FIXME
		// this.newTeam = ClientsideSettings.getCurrentUser();

		// Vertical Panel erstellen
		VerticalPanel labelsPanel = new VerticalPanel();
		this.add(labelsPanel);

		// Labels und Boxen dem Vertical Panel hinzuf端gen

		labelsPanel.add(nameLabel);
		labelsPanel.add(nameBox);
		labelsPanel.add(descriptionLabel);
		labelsPanel.add(descriptionBox);
		labelsPanel.add(teamSizeLabel);
		labelsPanel.add(teamSizeBox);

		// HorizontalPanel f端r den Button erstellen
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		// ---------- Speichern Button, ClickHandler hinzuf端gen und dem
		// HorizontalPanel hinzuf端gen
		Button saveButton = new Button("Speichern");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				saveForRepGen();
			}
		});
		buttonsPanel.add(saveButton);

		this.add(buttonsPanel);
	}

	public void saveForRepGen() {
		ClientsideSettings.getPitchMenAdmin().addTeam(nameBox.getText(), descriptionBox.getText(),
				Integer.parseInt(teamSizeBox.getText()), new NewTeamOnRepGenCallback());
	}

	private class NewTeamOnRepGenCallback implements AsyncCallback<Team> {

		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Neues Team konnte nicht gespeichert werden.");
			RootPanel.get("content").clear();
		}

		// TODO
		@Override
		public void onSuccess(Team result) {
			// ClientsideSettings.getLogger().info("Neue Person erfolgreich
			// gespeichert: " + result.getFirstName() + " "
			// + result.getName() + " (" + result.getEmailAdress() + ")");
			// ClientsideSettings.getCurrentUser().setFirstName(result.getFirstName());
			// ClientsideSettings.getCurrentUser().setName(result.getName());
			// ClientsideSettings.getCurrentUser().setIsExisting(true);

			RootPanel.get("usermenu").clear();
			RootPanel.get("content").clear();
			reportDisplay.onModuleLoad();
		}

	}
}
