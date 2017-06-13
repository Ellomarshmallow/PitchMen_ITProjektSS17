package de.pitchMen.shared.bo;

/**
 * Repräsentiert eine Person und erbt von der Superklasse OrganisationUnit.
 * 
 * @author JuliusDigel
 */
public class Person extends OrganisationUnit {


	private static final long serialVersionUID = 1L;

	private String firstName = "";
	private boolean loggedIn = false;
	private String emailAdress = "";
	private String name = "";
	private String loginUrl = "";
	private String logoutUrl = "";
	private boolean isExisting = false; 

	public boolean getIsExisting() {
		return isExisting;
	}

	public void setIsExisting(boolean isExisting) {
		this.isExisting = isExisting;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getName() {
		return name;
	}

	public void setName(String nickname) {
		this.name = nickname;
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