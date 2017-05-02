package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Eigenschaft, bestehend aus einem Attribut-Wert-Paar. 
 * 
 * @author JuliusDigel
 */
public class Trait extends BusinessObject {


	private String name = "";


	private String value = "";
	

	private static final long serialVersionUID = 1L;

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value 
	 */
	public void setValue(String value) {
		this.value = value;
	}

}