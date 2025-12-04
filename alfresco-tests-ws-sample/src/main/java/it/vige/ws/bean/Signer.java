package it.vige.ws.bean;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class Signer {

 /** The username. */
	private String username;
 /** The password. */
	private String password;
	
 /**
 * Get username.
 *
 * @return the string
 */
	public String getUsername() {
		return username;
	}
 /**
 * Set username.
 *
 * @param username the username
 */
	public void setUsername(String username) {
		this.username = username;
	}
 /**
 * Get password.
 *
 * @return the string
 */
	public String getPassword() {
		return password;
	}
 /**
 * Set password.
 *
 * @param password the password
 */
	public void setPassword(String password) {
		this.password = password;
	}
}
