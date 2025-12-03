package it.vige.ws.bean;

/**
 * Bean representing a signer with credentials.
 * Contains username and password for signing operations.
 * 
 * @author vige
 */
public class Signer {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
