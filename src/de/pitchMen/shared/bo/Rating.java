package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Bewertung.
 * 
 * @author JuliusDigel
 */
public class Rating extends BusinessObject {


	private String statement = "";


	private float score = 0.0f;


	private static final long serialVersionUID = 1L;


	public Application Application = null;

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

}