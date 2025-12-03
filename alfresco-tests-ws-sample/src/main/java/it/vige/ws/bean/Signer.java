package it.vige.ws.bean;

/**
* Mock implementation of the Signer class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class Signer {

/**
* The username.
 */
	private String username;
/**
* The password.
 */
	private String password;
	
/**
* Gets the username.
* @return the result
 */
	public String getUsername() {
		return username;
	}
/**
* Sets the username.
* @param username the username
 */
	public void setUsername(String username) {
		this.username = username;
	}
/**
* Gets the password.
* @return the result
 */
	public String getPassword() {
		return password;
	}
/**
* Sets the password.
* @param password the password
 */
	public void setPassword(String password) {
		this.password = password;
	}
}
