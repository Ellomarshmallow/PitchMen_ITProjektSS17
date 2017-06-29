package de.pitchMen.client.elements;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.pitchMen.client.ClientsideSettings;
import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.JobPosting;
import de.pitchMen.shared.bo.PartnerProfile;

/**
 * Klasse, deren Objekte ein Formular
 * zur Bewerbung auf eine Ausschreibung 
 * bereitstellen.
 * 
 * @author Simon und Leon
 */

public class ApplicationForm extends Formular {
	
	/**
	 * Um die Zugehörigkeit der mit dem Formular
	 * zu erstellenden Bewerbung zu einem Projekt
	 * feststellen zu können, benötigen Objekte der
	 * Klasse {@link ApplicationForm} ein Attribut
	 * <code>referredJobPosting</code> vom Typ
	 * {@link JobPosting}. 
	 */
	private JobPosting referredJobPosting = null;
	private int partnerProfileId;
	


	Label infoLabel = new Label("Hiermit bewerben Sie sich auf die ausgewählte Ausschreibung,"
							+ "bedenken Sie, dass Ihr AKTUELLES Partnerprofil automatisch beigefügt wird. ");
	
	TextArea textArea = new TextArea(); 
	/**
	 * Beim Anlegen eines neuen <code>ApplicationForm</code>
	 * Objekts wird das JobPosting-Objekt übergeben, auf das
	 * sich die anzulegende Bewerbung beziehen soll.
	 */
	public ApplicationForm(JobPosting jobPosting) {
		
		this.referredJobPosting = jobPosting; 
		
		RootPanel.get("content").clear();

		ClientsideSettings.getPitchMenAdmin().getPartnerProfileByPersonId(ClientsideSettings.getCurrentUser().getId(), new PartnerProfileCallback());
			
	}
		
		private class PartnerProfileCallback implements AsyncCallback<PartnerProfile> {
		
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().severe("Empfangen des Partnerprofils fehlgeschlagen");
			}

			public void onSuccess(PartnerProfile result) {
				
				partnerProfileId = result.getId(); 
				RootPanel.get("content").add(infoLabel);
				RootPanel.get("content").add(new HTML("</p>"));
				
				
				RootPanel.get("content").add(textArea);
				RootPanel.get("content").add(new HTML("</p>"));
				
				
				
				
				
				Button sendBtn = new Button("Absenden");
				sendBtn.addClickHandler(new BtnClickHandler());									
				RootPanel.get("content").add(sendBtn);
				
			}	}
				
	private class BtnClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			
			java.util.Date date = new Date();
			java.sql.Date convertedDate = new java.sql.Date(date.getTime());
			
			ClientsideSettings.getPitchMenAdmin().addApplication(convertedDate, textArea.getText() , "laufend", referredJobPosting.getId(), partnerProfileId, new ApplicationCallback());				
		}		
		
}
		 private class ApplicationCallback implements AsyncCallback<Application>{
			 
		 
		 
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().severe("Speichern fehlgeschlagen");
			}
			
			public void onSuccess(Application result) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new JobPostingForm(referredJobPosting));
					Window.alert("erfolgreich beworben");
			}
		 
		 }
		 
		 
}

		
		/**
		
		
		
	// GetPartnerProfilCallback
	public class GetPartnerProfileCallback implements AsyncCallback<PartnerProfile>{
		
		private ApplicationForm applicationForm = null; 
		
		public GetPartnerProfileCallback(ApplicationForm applicationForm){
			this.applicationForm = applicationForm; 
		}
		
		public void onFailure(Throwable caught) {

		}

		public void onSuccess(PartnerProfile partnerprofile) {

			this.applicationForm.partnerProfileId = partnerprofile.getId(); 
		}
		
	}
	
	
	
	

**/
		
