package de.pitchMen.shared.bo;

import java.io.Serializable;

/**
 * Alle Klassen des packages bo erben von dieser Superklasse.
 */
public abstract class BusinessObject implements Serializable {

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return
     */
    public int getId() {
        // TODO implement here
        return 0;
    }

    /**
     * @param id 
     * @return
     */
    public void setId(int id) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String toString() {
        // TODO implement here
        return "";
    }

    /**
     * @param object 
     * @return
     */
    public boolean equals(Object object) {
        // TODO implement here
        return false;
    }

}