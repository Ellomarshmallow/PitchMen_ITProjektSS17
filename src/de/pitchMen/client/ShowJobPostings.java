package de.pitchMen.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.pitchMen.shared.PitchMenAdminAsync;
import de.pitchMen.shared.bo.JobPosting;


/** Die Klasse <code> ShowJobPostings </code> erweitert die abstrakte Klasse 
 * {@link BasicContent}. Die Klasse dient der Darstellung der Ausschreibungen.
 * 
 *    @author Leon Schelle
 *    **/

public class ShowJobPostings extends BasicContent {

	
	@Override
	
	/** Die abstrakten Methoden getHeadLine und getDescription
	aus der Superklasse BasicContent werden hier überschrieben.**/
	
	protected String getHeadline(){
		return "Alle Ausschreibungen in diesem Projekt"; 
	}
	
	
	protected String getDescription(){
		return "Wählen Sie eine Ausschreibung aus und bewerben Sie sich jetzt"; 
	}
	
	/** Implementierung der Methode run(), welche in der Superklasse BasicContent 
    in der onLoad() Methode aufgerufen wird**/
	
	protected void run(){
		
		
		PitchMenAdminAsync pitchmenadmin = ClientsideSettings.getPitchMenAdmin();
		
		pitchmenadmin.getJobPosting(new GetJobPostingCallback(this));		
		
			}
	
	
	class GetJobPostingCallback implements AsyncCallback<ArrayList<JobPosting>>{
		
		private BasicContent content = null; 
		
		
		public GetJobPostingCallback(BasicContent content){
			this.content = content; 
		}
		
		public void onFailure(Throwable caught){
			
			this.content.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));
			
		}
		
		public void onSuccess(ArrayList<JobPosting> jobpostings){
			if (jobpostings != null){
				
				
				for (JobPosting j : jobpostings){
					this.content.add(new HTML("Ausschreibung" + j.getTitle()));				}
				
				
			}
		}
		
		
	}
	
	
}
