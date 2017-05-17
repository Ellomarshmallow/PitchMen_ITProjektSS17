package de.pitchMen.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.pitchMen.shared.ReportGeneratorAsync;
import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.HTMLReportWriter;

public class ShowAllJobPostings extends VerticalPanel {
	
	public void onLoad() {
		super.onLoad();
		this.add(new HTML("<h2>Alle Ausschreibungen</h2>"));
		
		ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
		
		reportGenerator.showAllJobPostings(new AllJobPostingsCallback(this));
	}
	
	class AllJobPostingsCallback implements AsyncCallback<AllJobPostings> {
		
		private ShowAllJobPostings parent = null;
		
		public AllJobPostingsCallback(ShowAllJobPostings parent) {
			this.parent = parent;
		}

		@Override
		public void onFailure(Throwable caught) {
			parent.add(new HTML("<p class='error'>Konnte Ausschreibungen nicht laden.</p>"));
			
		}

		@Override
		public void onSuccess(AllJobPostings report) {
			HTMLReportWriter writer = new HTMLReportWriter();
			writer.process(report);
	        parent.add(new HTML(writer.getReportText()));
		}
		
	}
	
	public void add(Widget w) {
		super.add(w);
	}

}
