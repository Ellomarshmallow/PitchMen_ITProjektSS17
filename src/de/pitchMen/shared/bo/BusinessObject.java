package de.pitchMen.shared.bo;

import java.io.Serializable;

/**
 * Alle Klassen des Paket bo erben von dieser Superklasse.
 * 
 * @author EleonoraRenz
 */
public abstract class BusinessObject implements Serializable {

	private int id = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gibt den Klassennamen + jeweilige
	 * 
	 * @return id wieder.
	 */
	public String toString() {
		return this.getClass().getName() + " #" + this.id;
	}

	/**
	 * Vergleicht zwei Objekte auf ihre inhaltliche Gleichheit. Momentan nur auf
	 * eine ID.
	 * 
	 * @param object
	 */
	public boolean equals(Object o) {
		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * @return id
	 */
	public int hashCode() {
		return this.id;
	}

}