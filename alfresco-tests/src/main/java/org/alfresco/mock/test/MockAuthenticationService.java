package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Set;

import org.alfresco.repo.security.authentication.AuthenticationException;
import org.alfresco.service.cmr.security.MutableAuthenticationService;

/**
 * Mock implementation of the Alfresco AuthenticationService for testing
 * purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockAuthenticationService implements MutableAuthenticationService, Serializable {
	/**
	 * Get authentication enabled.
	 *
	 * @param userName the user name
	 * @return the boolean
	 */
	@Override
	public boolean getAuthenticationEnabled(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Authenticate.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	@Override
	public void authenticate(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Authenticate as guest.
	 *
	 */
	@Override
	public void authenticateAsGuest() throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Guest user authentication allowed.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean guestUserAuthenticationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Authentication exists.
	 *
	 * @param userName the user name
	 * @return the boolean
	 */
	@Override
	public boolean authenticationExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get current user name.
	 *
	 * @return the string
	 */
	@Override
	public String getCurrentUserName() throws AuthenticationException {
		return "tester";
	}

	/**
	 * Invalidate user session.
	 *
	 * @param userName the user name
	 */
	@Override
	public void invalidateUserSession(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Invalidate ticket.
	 *
	 * @param ticket the ticket
	 */
	@Override
	public void invalidateTicket(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Validate.
	 *
	 * @param ticket the ticket
	 */
	@Override
	public void validate(String ticket) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Get current ticket.
	 *
	 * @return the string
	 */
	@Override
	public String getCurrentTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get new ticket.
	 *
	 * @return the string
	 */
	@Override
	public String getNewTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Clear current security context.
	 *
	 */
	@Override
	public void clearCurrentSecurityContext() {
		// TODO Auto-generated method stub

	}

	/**
	 * Is current user the system user.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isCurrentUserTheSystemUser() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get domains.
	 *
	 * @return the set
	 */

	@Override
	public Set<String> getDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get domains that allow user creation.
	 *
	 * @return the set
	 */

	@Override
	public Set<String> getDomainsThatAllowUserCreation() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get domains that allow user deletion.
	 *
	 * @return the set
	 */

	@Override
	public Set<String> getDomainsThatAllowUserDeletion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get domians that allow user password changes.
	 *
	 * @return the set
	 */

	@Override
	public Set<String> getDomiansThatAllowUserPasswordChanges() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get default administrator user names.
	 *
	 * @return the set
	 */

	@Override
	public Set<String> getDefaultAdministratorUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get default guest user names.
	 *
	 * @return the set
	 */

	@Override
	public Set<String> getDefaultGuestUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is authentication mutable.
	 *
	 * @param userName the user name
	 * @return the boolean
	 */

	@Override
	public boolean isAuthenticationMutable(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is authentication creation allowed.
	 *
	 * @return the boolean
	 */

	@Override
	public boolean isAuthenticationCreationAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Create authentication.
	 *
	 * @param userName the user name
	 * @param password the password
	 */

	@Override
	public void createAuthentication(String userName, char[] password) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Update authentication.
	 *
	 * @param userName    the user name
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 */

	@Override
	public void updateAuthentication(String userName, char[] oldPassword, char[] newPassword)
			throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Set authentication.
	 *
	 * @param userName    the user name
	 * @param newPassword the new password
	 */

	@Override
	public void setAuthentication(String userName, char[] newPassword) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Delete authentication.
	 *
	 * @param userName the user name
	 */

	@Override
	public void deleteAuthentication(String userName) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Set authentication enabled.
	 *
	 * @param userName the user name
	 * @param enabled  the enabled
	 */

	@Override
	public void setAuthenticationEnabled(String userName, boolean enabled) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

}
