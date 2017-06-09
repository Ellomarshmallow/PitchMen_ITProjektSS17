package de.pitchMen.shared.bo;

/**
 * ReprÃ¤sentiert eine Bewertung.
 * 
 * @author JuliusDigel
 */
public class Rating extends BusinessObject {

	private String statement = "";

	private float score = 0.0f;

	private static final long serialVersionUID = 1L;
	/**
	 * Realisierung der Beziehung zu einer Bewerbung durch einen Fremdschlüssel.
	 */
	private int applicationId = 0;

	/**
	 * Leere Konstruktor
	 */
	public Rating() {

	}

	public Rating(float score, String statement, int applicationId) {
		this.score = score;
		this.statement = statement;
	}

	/**
	 * @return statement
	 */
	public String getStatement() {
		return this.statement;
	}

	/**
	 * @param statement
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}

	/**
	 * @return score
	 */
	public float getScore() {
		return this.score;
	}

	/**
	 * @param score
	 */
	public void setScore(float score) {
		this.score = score;
	}

	/**
	 * @return applicationId
	 */
	public int getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 */
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

}