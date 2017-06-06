package de.pitchMen.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

import de.pitchMen.shared.bo.Marketplace;
import de.pitchMen.shared.bo.Project;

public class ProjectForm extends Formular{
	
	//TODO 
	
	private int prID; 
	
	public ProjectForm(int ProjectID){
		
		super(); 
		this.prID = ProjectID; 
		
		super.getPitchMenAdmin().getProjectByID(this.prID, new ProjectFormCallback(this));
			
	}

	class ProjectFormCallback implements AsyncCallback<Project>{
		
		private ProjectForm projectform = null; 
		
		public ProjectFormCallback(ProjectForm projectForm){
			this.projectform = projectForm; 
		}
		
		public void onFailure(Throwable caught){
			
			this.projectform.add(new HTML("Fehler bei RPC Aufruf:" + caught.getMessage()));
			
		}
		
		public void onSuccess(Project project){
			if (project != null){
				
							this.projectform.add(new HTML(project.getTitle()));
							this.projectform.add(new HTML(project.getDescription()));
							
							Button addJobPostingBtn = new Button("+ Neue Ausschreibung", new ClickHandler(){
								
																
								public void onClick(ClickEvent event) {
									
								AddJobPostingForm addJobPosting = new AddJobPostingForm(); 
								
								addJobPosting.run(); 
									//TODO Klasse AddJobPostingForm mit der Methode run() erstellen.Vgl AddProjectForm
							}}); 
	
	
}
}}}