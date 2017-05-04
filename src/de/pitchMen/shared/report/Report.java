package de.pitchMen.shared.report;

import java.io.Serializable;
import java.util.Date;


/**
 * Implemetierungsklasse des Interface Serializable. Ist die Superklasse von
 * SimpleReport und CompositeReport.
 * 
 * @author
 */
public abstract class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String title = "";

	/**
	 * 
	 */
	private Date dateCreated = new Date();
		
	/**
	 * 
	 */
	private Paragraph headerData = null;
	
	/**
	 * @return
	 */
	public Paragraph getHeaderData() {
		return this.headerData;
	}
	
	/**
	 * @return
	 */
	public void setHeaderData(Paragraph h){
		this.headerData = h; 
	}
	/**
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param value
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * @return
	 */
	public Date getDatecreated() {
		
		return this.dateCreated;
	}

	/**
	 * @param value
	 */
	public void setDatecreated(Date value) {
		this.dateCreated = value; 
	}

}
