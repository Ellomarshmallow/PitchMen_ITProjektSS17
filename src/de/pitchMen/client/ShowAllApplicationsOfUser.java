package de.pitchMen.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.pitchMen.shared.ReportGeneratorAsync;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.HTMLReportWriter;


public class ShowAllApplicationsOfUser extends VerticalPanel {

	public void onLoad() {
		super.onLoad();
		this.add(new HTML("<h2>Alle Bewerbungen</h2>"));
		
	ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
	//TODO : Person P wie festlegen? 
		reportGenerator.showAllApplicationsOfUser(p, new AllApplicationsOfUserCallback(this));	
}

class AllApplicationsOfUserCallback implements AsyncCallback<AllApplicationsOfUser> {
		
		private ShowAllApplicationsOfUser parent = null;
		
		public AllApplicationsOfUserCallback(ShowAllApplicationsOfUser parent) {
			this.parent = parent;
		}

		@Override
		public void onFailure(Throwable caught) {
			parent.add(new HTML("<p class='error'>Konnte Bewerbungen nicht laden.</p>"));
			
		}

		@Override
		public void onSuccess(AllApplicationsOfUser report) {
			HTMLReportWriter writer = new HTMLReportWriter();
			writer.process(report);
	        parent.add(new HTML(writer.getReportText()));
		}
		
	}
	
	public void add(Widget w) {
		super.add(w);
	}

}
