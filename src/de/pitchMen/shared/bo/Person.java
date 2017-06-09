package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Person und erbt von der Superklasse OrganisationUnit.
 * 
 * @author JuliusDigel
 */
public class Person extends OrganisationUnit {

	private String firstName = "";

	private static final long serialVersionUID = 1L;

	private boolean loggedIn = false;
	private String emailAdress = "";
	private String nickname = "";
	private String loginUrl = "";
	private String logoutUrl = "";

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}