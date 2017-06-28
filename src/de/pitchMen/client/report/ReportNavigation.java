package de.pitchMen.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.Person;
import de.pitchMen.shared.report.AllApplicationsOfUser;
import de.pitchMen.shared.report.AllJobPostings;
import de.pitchMen.shared.report.AllJobPostingsMatchingPartnerProfileOfUser;
import de.pitchMen.shared.report.ApplicationsRelatedToJobPostingsOfUser;
import de.pitchMen.shared.report.FanInAndOutReport;
import de.pitchMen.shared.report.HTMLReportWriter;
import de.pitchMen.shared.report.ProjectInterweavingsWithParticipationsAndApplications;

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
	
	private HTML reportContent = null;

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
			RootPanel.get("content").clear();
			switch (reportNo) {
				case 1: RootPanel.get("content").add(new HTML("<h3>Switch Case this.getAllJobPostings() ausgewählt</h3>"));
						reportContent = this.getAllJobPostings();
						break;
				case 2: RootPanel.get("content").add(new HTML("<h3>Switch Case this.getAllJobPostingsMathcingPartnerProfile() ausgewählt</h3>"));
						reportContent = this.getAllJobPostingsMatchingPartnerProfileOfUser();
						break;
				case 3: RootPanel.get("content").add(new HTML("<h3>Switch Case this.getApplicationsRelatedToJobPostingOfUser() ausgewählt</h3>"));
						reportContent = this.getApplicationsRelatedToJobPostingsOfUser();
						break;
				case 4: RootPanel.get("content").add(new HTML("<h3>Switch Case this.getAllApplicationsOfUserWithJobPostings() ausgewählt</h3>"));
						reportContent = this.getAllApplicationsOfUserWithJobPostings();
						break;
//				case 5: RootPanel.get("content").add(new HTML("<h3>Switch Case this.getProjectInterveawings() ausgewählt</h3>"));
//						reportContent = this.getProjectInterveawings();
//						break;
				case 6: RootPanel.get("content").add(new HTML("<h3>Switch Case this.getFanInFanOutAnalysis() ausgewaehlt</h3>"));
						reportContent = this.getFanInFanOutAnalysis();
						break;
			}
			RootPanel.get("content").add(new HTML("<h3>Button geklickt: " + reportNo + "</h3>"));
			RootPanel.get("content").add(reportContent);
		}

		private HTML getFanInFanOutAnalysis() {
			final HTMLReportWriter writer = new HTMLReportWriter();
			ClientsideSettings.getReportGenerator().showFanInAndOutReport(new AsyncCallback<FanInAndOutReport>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(FanInAndOutReport result) {
							writer.process(result);
							reportContent = new HTML(writer.getReportText());
						}
			});
			return reportContent;
		}

/**		
 * FIXME: Report �berarbeiten und fertig machen
 * 
 */
		
//		private HTML getProjectInterveawings() {
//			final HTMLReportWriter writer = new HTMLReportWriter();
//			
//			ClientsideSettings.getReportGenerator().showProjectInterweavingsWithParticipationsAndApplications(
//													ClientsideSettings.getCurrentUser(),
//													new AsyncCallback<ProjectInterweavingsWithParticipationsAndApplications>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void onSuccess(ProjectInterweavingsWithParticipationsAndApplications result) {
//					writer.process(result);
//					reportContent = new HTML(writer.getReportText());
//				}
//			});
//			return reportContent;
//		}
//
		private HTML getAllApplicationsOfUserWithJobPostings() {
			final HTMLReportWriter writer = new HTMLReportWriter();
			
			ClientsideSettings.getReportGenerator().showAllApplicationsOfUser(ClientsideSettings.getCurrentUser(),new AsyncCallback<AllApplicationsOfUser>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe("Upsi iwas hat nicht geklappt :()");
				}

				@Override
				public void onSuccess(AllApplicationsOfUser result) {
					writer.process(result);
					reportContent = new HTML(writer.getReportText());
				}
			});							
			return reportContent;
		}

		private HTML getApplicationsRelatedToJobPostingsOfUser() {
			final HTMLReportWriter writer = new HTMLReportWriter();
			ClientsideSettings.getPitchMenAdmin().getPersonByID(ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<Person>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Person result) {
					ClientsideSettings.getReportGenerator().showApplicationsRelatedToJobPostingsOfUser(result, new AsyncCallback<ApplicationsRelatedToJobPostingsOfUser>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(ApplicationsRelatedToJobPostingsOfUser result) {
							writer.process(result);
							reportContent = new HTML(writer.getReportText());
						}
					});
				}				
			});
			return reportContent;
		}
		
		
		private HTML getAllJobPostingsMatchingPartnerProfileOfUser() {
			final HTMLReportWriter writer = new HTMLReportWriter();
			ClientsideSettings.getPitchMenAdmin().getPartnerProfileByPersonId(ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<PartnerProfile>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(PartnerProfile result) {
					ClientsideSettings.getReportGenerator().showAllJobPostingsMatchingPartnerProfileOfUser(result, new AsyncCallback<AllJobPostingsMatchingPartnerProfileOfUser>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(AllJobPostingsMatchingPartnerProfileOfUser result) {
							writer.process(result);
							reportContent = new HTML(writer.getReportText());
						}
						
					});
				}
				
			});
			return reportContent;
		}

		private HTML getAllJobPostings() {
			final HTMLReportWriter writer = new HTMLReportWriter();
			ClientsideSettings.getReportGenerator().showAllJobPostings(new AsyncCallback<AllJobPostings>() {
					
						@Override
						public void onSuccess(AllJobPostings result) {
						writer.process(result);
						reportContent = new HTML(writer.getReportText());
						
						}

						@Override
						public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Upsi, da ist was schief gelaufen");
						
						}
			});
			return reportContent;
		}
	}
	
}
