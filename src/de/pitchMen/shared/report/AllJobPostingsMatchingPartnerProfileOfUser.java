package de.pitchMen.shared.report;

	/**
	 * Report zur Abfrage sämtlicher Ausschreibungen, die dem Partnerprofil des Benutzers entsprechen.  
	 * Subklasse von SimpleReport. Enthält keine eigenen Attribute und Methoden.
	 * Dient der Objektorientierten-Programmierung. 
	 * 
	 * @author
	 */
	public class AllJobPostingsMatchingPartnerProfileOfUser extends SimpleReport {

	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;
	    
	    
	       
	    private int jobPostingId;
	    
	    private int traitId;


	

		public int getJobPostingId() {
			return jobPostingId;
		}


		public void setJobPostingId(int jobPostingId) {
			this.jobPostingId = jobPostingId;
		}
		
		public int getTraitId() {
			return traitId;
		}


		public void setTraitId(int traitId) {
			this.traitId = traitId;
		}

		
		
	    

	}
