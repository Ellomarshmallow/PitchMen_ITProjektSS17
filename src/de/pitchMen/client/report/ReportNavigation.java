package de.pitchMen.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.HTMLReportWriter;

/**
 * Die Klasse <code>ReportNavigation</code> erweitert die
 * von GWT angebotene Klasse VerticalPanel und dient
 * der Darstellung von Buttons, mit deren hilfe der
 * Nutzer zwischen verschiedenen Ansichten des 
 * Report-Generators wechseln kann.
 * 
 * @author Simon
 */

public class ReportNavigation extends VerticalPanel {
	
	

	public void onLoad() {
		/*
		 * Die folgenden Buttons gibt es in der 
		 * PitchMen-ReportNavigation:
		 */
		Button report1Btn = new Button("Alle Ausschreibungen");
		Button report2Btn = new Button("Alle Ausschreibungen passend zum Partnerprofil des Nutzers");
		Button report3Btn = new Button("Alle Bewerbungen auf Ausschreibungen des Nutzers");
		Button report4Btn = new Button("Alle Bewerbungen des Nutzers und dazu gehörende Ausschreibungen");
		Button report5Btn = new Button("Projektverflechtungen von Bewerbern");
		Button report6Btn = new Button("Fan-In- und Fan-Out-Analyse");
		
		/*
		 * Sie werden der Navigation hinzugefügt 
		 */
		this.add(report1Btn);
		this.add(report2Btn);
		this.add(report3Btn);
		this.add(report4Btn);
		this.add(report5Btn);
		this.add(report6Btn);
		
		/*
		 * Jeder Button benötigt einen ClickHandler,
		 * um auf Interaktionen des Nutzers entsprechend
		 * reagieren zu können.
		 */
		report1Btn.addClickHandler(new ReportClickHandler(1));
		report2Btn.addClickHandler(new ReportClickHandler(2));
		report3Btn.addClickHandler(new ReportClickHandler(3));
		report4Btn.addClickHandler(new ReportClickHandler(4));
		report5Btn.addClickHandler(new ReportClickHandler(5));
		report6Btn.addClickHandler(new ReportClickHandler(6));
	}
	
	/**
	 *  Die geschachtelte Klasse <code>ReportClickHandler</code>
	 *  implementiert das von GWT vorgegebene Interface
	 *  ClickHandler und behandelt alle Fälle, in denen der 
	 *  Nutzer auf einen der Buttons klickt.
	 *  
	 * @author Simon
	 */
	private class ReportClickHandler implements ClickHandler {
		
		/**
		 * Die Report-Nummer wird als Instanzvariable deklariert
		 * und im Konstruktor gesetzt. Somit ist über den 
		 * später verwendeten Switch-Case eine einfache Handhabung
		 * der verschiedenen Fälle möglich.
		 */
		private int reportNo = 0;
		
		public ReportClickHandler(int i) {
			this.reportNo = i;
		}

		/**
		 * Die onClick-Methode ist hier eine verzweigende
		 * Weitergabe-Methode, die je nach gewähltem 
		 * Report-Szenario die entsprechende Methode
		 * ansteuert.
		 */
		@Override
		public void onClick(ClickEvent event) {
			HTML reportContent = null;
			switch (reportNo) {
				case 1: reportContent = this.getAllApplications();
						break;
				case 2: reportContent = this.getAllApplicationsMatchingPartnerProfileOfUser();
						break;
				case 3: reportContent = this.getAllApplicationOnJobPostingsOfUser();
						break;
				case 4: reportContent = this.getAllApplicationsOfUserWithJobPostings();
						break;
				case 5: reportContent = this.getProjectInterveawings();
						break;
				case 6: reportContent = this.getFanInFanOutAnalysis();
						break;
			}
			RootPanel.get("content").clear();
			RootPanel.get("content").add(reportContent);
		}

		private HTML getFanInFanOutAnalysis() {
			return null;
			// TODO Auto-generated method stub
			
		}

		private HTML getProjectInterveawings() {
			return null;
			// TODO Auto-generated method stub
			
		}

		private HTML getAllApplicationsOfUserWithJobPostings() {
			return null;
			// TODO Auto-generated method stub
			
		}

		private HTML getAllApplicationOnJobPostingsOfUser() {
			return null;
			// TODO Auto-generated method stub
			
		}

		private HTML getAllApplicationsMatchingPartnerProfileOfUser() {
			return null;
			// TODO Auto-generated method stub
			
		}

		private HTML getAllApplications() {
			
//			class ShowAllJobPostings extends VerticalPanel {
//				
//				public void onLoad() {
//					super.onLoad();
//					this.add(new HTML("<h2>Alle Ausschreibungen</h2>"));
//					
//					ReportGeneratorAsync reportGenerator = ClientsideSettings.getReportGenerator();
//					
//					reportGenerator.showAllJobPostings(new AllJobPostingsCallback(this));
//				}
//				
//				
//			}
			return null;
		}
		class AllJobPostingsCallback implements AsyncCallback<AllJobPostings> {
			
			private ClickHandler parent = null;
			
			public AllJobPostingsCallback(ClickHandler parent) {
				this.parent = parent;
			}

			@Override
			public void onFailure(Throwable caught) {
				((AbsolutePanel) parent).add(new HTML("<p class='error'>Konnte Ausschreibungen nicht laden.</p>"));
				
			}

			@Override
			public void onSuccess(AllJobPostings report) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(report);
		        ((AbsolutePanel) parent).add(new HTML(writer.getReportText()));
			}
		}
	}
}
