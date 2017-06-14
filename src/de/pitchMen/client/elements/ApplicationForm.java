package de.pitchMen.client.elements;

import de.pitchMen.shared.bo.JobPosting;

public class ApplicationForm extends Formular{


	private JobPosting selectedJobPosting;
	
ApplicationForm(JobPosting jobPosting){
	
	this.selectedJobPosting = jobPosting; 
	
	
	
	
	
	
	
	
	super.getPitchMenAdmin().addApplication(dateCreated, text, rating, status, selectedJobPosting.getId(), partnerProfileId, callback);
}
}
