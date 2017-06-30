package de.pitchMen.client.report;

import java.util.ArrayList;

import org.apache.http.entity.ContentProducer;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.ReportGeneratorAsync;
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
 * Die Klasse <code>ReportNavigation</code> erweitert die von GWT angebotene
 * Klasse VerticalPanel und dient der Darstellung von Buttons, mit deren hilfe
 * der Nutzer zwischen verschiedenen Ansichten des Report-Generators wechseln
 * kann.
 * 
 * @author Simon, Lars
 */

public class ReportNavigation extends VerticalPanel {

	// private HTML reportContent = null;

	public void onLoad() {
		/*
		 * Die folgenden Buttons gibt es in der PitchMen-ReportNavigation:
		 */
		Button report1Btn = new Button("Alle Ausschreibungen");
		Button report2Btn = new Button("Alle Ausschreibungen passend zum Partnerprofil des Nutzers");
		Button report3Btn = new Button("Alle Bewerbungen auf Ausschreibungen des Nutzers");
		Button report4Btn = new Button("Alle Bewerbungen des Nutzers und dazu gehoerende Ausschreibungen");
		Button report5Btn = new Button("Projektverflechtungen von Bewerbern");
		Button report6Btn = new Button("Fan-In- und Fan-Out-Analyse");

		report1Btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final HTMLReportWriter writer = new HTMLReportWriter();
				ClientsideSettings.getReportGenerator().showAllJobPostings(new AsyncCallback<AllJobPostings>() {

					@Override
					public void onSuccess(AllJobPostings result) {
						writer.process(result);
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new HTML(writer.getReportText()));

					}

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Upsi, da ist was schief gelaufen");

					}
				});

			}
		});
		
		report2Btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final HTMLReportWriter writer = new HTMLReportWriter();
				ClientsideSettings.getPitchMenAdmin().getPartnerProfileByPersonId(ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<PartnerProfile>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Upsi, da ist wohl etwas schief gelaufen");

					}

					@Override
					public void onSuccess(PartnerProfile result) {
						ClientsideSettings.getReportGenerator().showAllJobPostingsMatchingPartnerProfileOfUser(
								result, new AsyncCallback<AllJobPostingsMatchingPartnerProfileOfUser>() {

									@Override
									public void onFailure(Throwable caught) {
										ClientsideSettings.getLogger()
										.severe("Upsi, da ist wohl etwas schief gelaufen");

									}

									@Override
									public void onSuccess(AllJobPostingsMatchingPartnerProfileOfUser result) {
										writer.process(result);
										RootPanel.get("content").clear();
										RootPanel.get("content").add(new HTML(writer.getReportText()));
									}
								});
					}
				});
			}
		});

//		report2Btn.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				final HTMLReportWriter writer = new HTMLReportWriter();
//				ClientsideSettings.getPitchMenAdmin().getPartnerProfileByPersonId(
//						ClientsideSettings.getCurrentUser().getId(), new AsyncCallback<PartnerProfile>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								ClientsideSettings.getLogger().severe("Upsi, da ist wohl was schiefgegangen");
//
//							}
//
//							@Override
//							public void onSuccess(PartnerProfile result) {
//								ClientsideSettings.getReportGenerator().showAllJobPostingsMatchingPartnerProfileOfUser(
//										result, new AsyncCallback<AllJobPostingsMatchingPartnerProfileOfUser>() {
//
//											@Override
//											public void onFailure(Throwable caught) {
//												ClientsideSettings.getLogger()
//												.severe("Upsi, da ist wohl was schiefgegangen");
//
//											}
//
//											@Override
//											public void onSuccess(AllJobPostingsMatchingPartnerProfileOfUser result) {
//												writer.process(result);
//												RootPanel.get("content").clear();
//												RootPanel.get("content").add(new HTML(writer.getReportText()));
//											}
//
//										});
//							}
//						});
//			}
//		});

		report3Btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final HTMLReportWriter writer = new HTMLReportWriter();
				ClientsideSettings.getPitchMenAdmin().getPersonByID(ClientsideSettings.getCurrentUser().getId(),
						new AsyncCallback<Person>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Upsi, da ist wohl etwas schief gelaufen");

					}

					@Override
					public void onSuccess(Person result) {
						ClientsideSettings.getReportGenerator().showApplicationsRelatedToJobPostingsOfUser(
								result, new AsyncCallback<ApplicationsRelatedToJobPostingsOfUser>() {

									@Override
									public void onFailure(Throwable caught) {
										ClientsideSettings.getLogger()
										.severe("Upsi, da ist wohl etwas schief gelaufen");

									}

									@Override
									public void onSuccess(ApplicationsRelatedToJobPostingsOfUser result) {
										writer.process(result);
										RootPanel.get("content").clear();
										RootPanel.get("content").add(new HTML(writer.getReportText()));
									}
								});
					}
				});
			}
		});

		report4Btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final HTMLReportWriter writer = new HTMLReportWriter();

				ClientsideSettings.getReportGenerator().showAllApplicationsOfUser(ClientsideSettings.getCurrentUser(),
						new AsyncCallback<AllApplicationsOfUser>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Upsi iwas hat nicht geklappt");
					}

					@Override
					public void onSuccess(AllApplicationsOfUser result) {
						writer.process(result);
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new HTML(writer.getReportText()));
					}
				});

			}
		});

		report5Btn.addClickHandler(new ClickHandler() {
				
			@Override
			public void onClick(ClickEvent event) {
			
				
				final ListBox applicantBox = new ListBox();
				
				
				ReportGeneratorAsync repGen = ClientsideSettings.getReportGenerator();
				applicantBox.clear();
				applicantBox.addItem("Bitte wähle einen Bewerber aus");
				RootPanel.get("content").clear();
				repGen.getApplicatorsOnOwnJobPostings(ClientsideSettings.getCurrentUser(), new AsyncCallback<ArrayList<Person>>(){

							@Override
							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().severe("Upsi iwas hat nicht geklappt");

							}

							@Override
							public void onSuccess(ArrayList<Person> result) {
								
								for(Person p : result){
									
									applicantBox.addItem(p.getFirstName() + " " + p.getName() + ", ID: " + p.getId());
									
								}
							}
				});
				RootPanel.get("content").add(applicantBox);
				
				
				applicantBox.addChangeHandler(new ChangeHandler(){
					
				
				public void onChange(ChangeEvent event){
					
					ReportGeneratorAsync repGenA = ClientsideSettings.getReportGenerator();
			
					RootPanel.get("content").add(new HTML("<h2>i bims ein test lul</h2>"));;
					String s = applicantBox.getValue(applicantBox.getSelectedIndex());
					String last = s.substring(s.indexOf(':')+2, s.length());
					int chosenId = Integer.valueOf(last);
					RootPanel.get("content").add(new HTML("Ausgewählte ID: " + chosenId));
					
					repGenA.showProjectInterweavingsWithParticipationsAndApplications(chosenId,
						new AsyncCallback<ProjectInterweavingsWithParticipationsAndApplications>() {

							@Override
							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().severe("Upsi iwas hat nicht geklappt");
								RootPanel.get("content").add(new HTML("FAIL"));
							}

							@Override
							public void onSuccess(ProjectInterweavingsWithParticipationsAndApplications result) {
								if(result != null){
								HTMLReportWriter writer = new HTMLReportWriter();
								writer.process(result);
								RootPanel.get("content").add(new HTML(writer.getReportText()));
								}
								else{
								RootPanel.get("content").add(new HTML("Bei der OnSucess Methode kam kein valides Objekt zurück"));
								}
							}
						});
					}
				});
			}
		});
			
		
		report6Btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final HTMLReportWriter writer = new HTMLReportWriter();
				ClientsideSettings.getReportGenerator().showFanInAndOutReport(new AsyncCallback<FanInAndOutReport>() {

					@Override
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().severe("Upsi iwas hat nicht geklappt");

					}

					@Override
					public void onSuccess(FanInAndOutReport result) {
						writer.process(result);
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new HTML(writer.getReportText()));
					}
				});

			}
		});
	
		/*
		 * Sie werden der Navigation hinzugefÃ¼gt
		 */
		this.add(report1Btn);
		this.add(report2Btn);
		this.add(report3Btn);
		this.add(report4Btn);
		this.add(report5Btn);
		this.add(report6Btn);

	}

}
