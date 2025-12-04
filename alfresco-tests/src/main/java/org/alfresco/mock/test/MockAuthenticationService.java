package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Set;

import org.alfresco.repo.security.authentication.AuthenticationException;
import org.alfresco.service.cmr.security.MutableAuthenticationService;

/**
 * Mock implementation of the Alfresco AuthenticationService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockAuthenticationService implements MutableAuthenticationService, Serializable {

	@Override
 /**
 * Get authentication enabled.
 *
 * @param userName the user name
 * @return the boolean
 */
	public boolean getAuthenticationEnabled(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Authenticate.
 *
 * @param userName the user name
 * @param password the password
 */
	public void authenticate(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /** Authenticate as guest. */
	public void authenticateAsGuest() throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Guest user authentication allowed.
 *
 * @return the boolean
 */
	public boolean guestUserAuthenticationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Authentication exists.
 *
 * @param userName the user name
 * @return the boolean
 */
	public boolean authenticationExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get current user name.
 *
 * @return the string
 */
	public String getCurrentUserName() throws AuthenticationException {
		return "tester";
	}

	@Override
 /**
 * Invalidate user session.
 *
 * @param userName the user name
 */
	public void invalidateUserSession(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Invalidate ticket.
 *
 * @param ticket the ticket
 */
	public void invalidateTicket(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Validate.
 *
 * @param ticket the ticket
 */
	public void validate(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get current ticket.
 *
 * @return the string
 */
	public String getCurrentTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get new ticket.
 *
 * @return the string
 */
	public String getNewTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /** Clear current security context. */
	public void clearCurrentSecurityContext() {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Is current user the system user.
 *
 * @return the boolean
 */
	public boolean isCurrentUserTheSystemUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get domains.
 *
 * @return the set
 */
	public Set<String> getDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get domains that allow user creation.
 *
 * @return the set
 */
	public Set<String> getDomainsThatAllowUserCreation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get domains that allow user deletion.
 *
 * @return the set
 */
	public Set<String> getDomainsThatAllowUserDeletion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get domians that allow user password changes.
 *
 * @return the set
 */
	public Set<String> getDomiansThatAllowUserPasswordChanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get default administrator user names.
 *
 * @return the set
 */
	public Set<String> getDefaultAdministratorUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get default guest user names.
 *
 * @return the set
 */
	public Set<String> getDefaultGuestUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Is authentication mutable.
 *
 * @param userName the user name
 * @return the boolean
 */
	public boolean isAuthenticationMutable(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Is authentication creation allowed.
 *
 * @return the boolean
 */
	public boolean isAuthenticationCreationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createAuthentication(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Update authentication.
 *
 * @param userName the user name
 * @param oldPassword the old password
 * @param newPassword the new password
 */
	public void updateAuthentication(String userName, char[] oldPassword, char[] newPassword)
			throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAuthentication(String userName, char[] newPassword) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Delete authentication.
 *
 * @param userName the user name
 */
	public void deleteAuthentication(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAuthenticationEnabled(String userName, boolean enabled) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

}
