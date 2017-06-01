package de.pitchMen.shared.report;

import java.io.Serializable;

/**
	 * Implemetierungsklasse des Interface Serializable. Ist die Spalte eines Row-Objekts.  Column-Objekt kann als Kopie z.B. vom Server an den Client übertragen werden.
	 * 
	 * @author
	 */
	public class Column implements Serializable {

	    /**
	     * 
	     */
	    private static final long serialVersionUID=1L;

	    /**
	     * 
	     */
	    private String value = "";
	    
	    private int intvalue = 0;
	    
	    
	    
	    public Column() {
	    }
	    
	      /**
	     * @return
	     */
	    
	    public Column(String s){
	    	this.value = s;
	    }
	    
	    public Column(int intvalue){
	    	this.intvalue = intvalue;
	    }
	    
	    public String toString() {
	        return this.value;
	    }
	    
	 
	    /**
	     * @return
	     */
	    public String getValue() {
	        return this.value;
	    }

	    /**
	     * @param value
	     */
	    public void setValue(String v) {
	    	this.value = v; 
	    }
		public int getIntvalue() {
			return this.intvalue;
		}
		public void setIntvalue(int intvalue) {
			this.intvalue = intvalue;
		}

	}
	

