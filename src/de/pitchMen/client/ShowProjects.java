package de.pitchMen.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.Project;

public class ShowProjects extends BasicContent {
	@Override
	protected String getHeadline() {

		return "Alle Projekte in diesem Marktplatz";
	}

	protected String getDescription() {
		return "Wählen Sie ein Projekt aus und sehen Sie sich die darin enthaltenen Ausschreibungen an";
	}

	protected void run() {

		PitchMenAdminAsync pitchmenadmin = ClientsideSettings.getPitchMenAdmin();
		// TODO: getProjects muss noch geschrieben werden
		pitchmenadmin.getProjects(new GetProjectsCallback(this));

		class GetProjectsCallback implements AsyncCallback<ArrayList<Project>> {

			private BasicContent content = null;

			public GetProjectsCallback(BasicContent content) {
				this.content = content;
			}

			public void onFailure(Throwable caught) {
				this.content.add(new HTML("Fehler: " + caught.getMessage()));
			}

			public void onSuccess(ArrayList<Project> projects) {

				if (projects != null) {

					for (Project p : projects) {

						this.content.add(new HTML("Projekt" + p.getId() + ": " + p.getTitle() + p.getDescription()
								+ "\n Von: " + p.getDateOpened() + " Bis: " + p.getDateClosed() + "\n Manager:"
								+ p.getManager()));
					}
				}
			}
		}
	}
}
