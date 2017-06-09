package de.pitchMen.shared.bo;

/**
 * ReprÃ¤sentiert eine Marktplatz.
 * 
 * @author EleonoraRenz
 */
public class Marketplace extends BusinessObject {

	/**
	 * Der default Wert von Strings ist "null", jedoch wird hier ein leerer
	 * String verwendet, damit man das Werfen einer NullPointException
	 * vermeidet.
	 */
	private String title = "";

	/**
	 * Der default Wert von Strings ist "null", jedoch wird hier ein leerer
	 * String verwendet, damit man das Werfen einer NullPointException
	 * vermeidet.
	 */
	private String description = "";

	/**
	 * Realisierung der Beziehung zu einer Person durch einen Fremdschlüssel.
	 */
	private int personId = 0;
	/**
	 * Realisierung der Beziehung zu einem Team durch einen Fremdschlüssel.
	 */
	private int teamId = 0;
	/**
	 * Realisierung der Beziehung zu eine Unternehmen durch einen
	 * Fremdschlüssel.
	 */
	private int companyId = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * @return teamId
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}