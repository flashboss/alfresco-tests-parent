package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Set;

import org.alfresco.repo.security.authentication.AuthenticationException;
import org.alfresco.service.cmr.security.MutableAuthenticationService;

/**
 * Mock implementation of MutableAuthenticationService for testing purposes.
 * Provides a simple authentication service that returns "tester" as the current user.
 *
 * @author lucastancapiano
 */
public class MockAuthenticationService implements MutableAuthenticationService, Serializable {

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name to check
	 * @return true if authentication is enabled, false otherwise
	 * @throws AuthenticationException if authentication check fails
	 */
	@Override
	public boolean getAuthenticationEnabled(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name
	 * @param password the password
	 * @throws AuthenticationException if authentication fails
	 */
	@Override
	public void authenticate(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws AuthenticationException if guest authentication fails
	 */
	@Override
	public void authenticateAsGuest() throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if guest user authentication is allowed, false otherwise
	 */
	@Override
	public boolean guestUserAuthenticationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name to check
	 * @return true if authentication exists, false otherwise
	 */
	@Override
	public boolean authenticationExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the current user name ("tester")
	 * @throws AuthenticationException if unable to get current user
	 */
	@Override
	public String getCurrentUserName() throws AuthenticationException {
		return "tester";
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name whose session to invalidate
	 * @throws AuthenticationException if invalidation fails
	 */
	@Override
	public void invalidateUserSession(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param ticket the ticket to invalidate
	 * @throws AuthenticationException if invalidation fails
	 */
	@Override
	public void invalidateTicket(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param ticket the ticket to validate
	 * @throws AuthenticationException if validation fails
	 */
	@Override
	public void validate(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the current ticket
	 */
	@Override
	public String getCurrentTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return a new ticket
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
	 *
	 * @return true if current user is system user, false otherwise
	 */
	@Override
	public boolean isCurrentUserTheSystemUser() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the set of domains
	 */
	@Override
	public Set<String> getDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the set of domains that allow user creation
	 */
	@Override
	public Set<String> getDomainsThatAllowUserCreation() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the set of domains that allow user deletion
	 */
	@Override
	public Set<String> getDomainsThatAllowUserDeletion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the set of domains that allow password changes
	 */
	@Override
	public Set<String> getDomiansThatAllowUserPasswordChanges() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the default administrator user names
	 */
	@Override
	public Set<String> getDefaultAdministratorUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the default guest user names
	 */
	@Override
	public Set<String> getDefaultGuestUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name to check
	 * @return true if authentication is mutable, false otherwise
	 */
	@Override
	public boolean isAuthenticationMutable(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if authentication creation is allowed, false otherwise
	 */
	@Override
	public boolean isAuthenticationCreationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name
	 * @param password the password
	 * @throws AuthenticationException if creation fails
	 */
	@Override
	public void createAuthentication(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @throws AuthenticationException if update fails
	 */
	@Override
	public void updateAuthentication(String userName, char[] oldPassword, char[] newPassword)
			throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name
	 * @param newPassword the new password
	 * @throws AuthenticationException if setting fails
	 */
	@Override
	public void setAuthentication(String userName, char[] newPassword) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name
	 * @throws AuthenticationException if deletion fails
	 */
	@Override
	public void deleteAuthentication(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param userName the user name
	 * @param enabled the enabled flag
	 * @throws AuthenticationException if setting fails
	 */
	@Override
	public void setAuthenticationEnabled(String userName, boolean enabled) throws AuthenticationException {
		// TODO Auto-generated method stub
	}

}
