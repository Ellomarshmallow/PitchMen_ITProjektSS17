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
	    private String value;

	    /**
	     * @return
	     */
	    public String toString() {
	        // TODO implement here
	        return "";
	    }

	    /**
	     * @return
	     */
	    public String getValue() {
	        // TODO implement here
	        return "";
	    }

	    /**
	     * @param value
	     */
	    public void setValue(String value) {
	        // TODO implement here
	    }

	}
	
}
