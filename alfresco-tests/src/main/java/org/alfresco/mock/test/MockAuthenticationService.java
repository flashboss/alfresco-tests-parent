package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Set;

import org.alfresco.repo.security.authentication.AuthenticationException;
import org.alfresco.service.cmr.security.MutableAuthenticationService;

/**
* Mock implementation of the MockAuthenticationService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockAuthenticationService implements MutableAuthenticationService, Serializable {

/**
* {@inheritDoc}
* @param userName the userName
* @return the result
* @throws AuthenticationException if an error occurs
 */
	@Override
	public boolean getAuthenticationEnabled(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param userName the userName
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void authenticate(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void authenticateAsGuest() throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean guestUserAuthenticationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param userName the userName
* @return the result
 */
	@Override
	public boolean authenticationExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
* @throws AuthenticationException if an error occurs
 */
	@Override
	public String getCurrentUserName() throws AuthenticationException {
		return "tester";
	}

/**
* {@inheritDoc}
* @param userName the userName
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void invalidateUserSession(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param ticket the ticket
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void invalidateTicket(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param ticket the ticket
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void validate(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getCurrentTicket() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getNewTicket() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public void clearCurrentSecurityContext() {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean isCurrentUserTheSystemUser() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDomains() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDomainsThatAllowUserCreation() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDomainsThatAllowUserDeletion() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDomiansThatAllowUserPasswordChanges() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDefaultAdministratorUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDefaultGuestUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param userName the userName
* @return the result
 */
	@Override
	public boolean isAuthenticationMutable(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean isAuthenticationCreationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param userName the userName
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void createAuthentication(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param userName the userName
 */
	@Override
	public void updateAuthentication(String userName, char[] oldPassword, char[] newPassword)
			throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param userName the userName
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void setAuthentication(String userName, char[] newPassword) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param userName the userName
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void deleteAuthentication(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param userName the userName
* @param enabled the enabled
* @throws AuthenticationException if an error occurs
 */
	@Override
	public void setAuthenticationEnabled(String userName, boolean enabled) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

}
