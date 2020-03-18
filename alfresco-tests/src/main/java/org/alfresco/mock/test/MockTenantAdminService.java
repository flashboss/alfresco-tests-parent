package org.alfresco.mock.test;

import java.io.File;
import java.util.List;

import org.alfresco.repo.tenant.Tenant;
import org.alfresco.repo.tenant.TenantAdminService;
import org.alfresco.repo.tenant.TenantDeployer;
import org.alfresco.repo.workflow.WorkflowDeployer;
import org.apache.commons.logging.Log;

public class MockTenantAdminService implements TenantAdminService {

	@Override
	public String getDomainUser(String baseUsername, String tenantDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseNameUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentUserDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserDomain(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTenants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopTenants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deployTenants(TenantDeployer deployer, Log logger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undeployTenants(TenantDeployer deployer, Log logger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(TenantDeployer tenantDeployer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(TenantDeployer tenantDeployer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tenant> getAllTenants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tenant> getTenants(boolean enabledOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(WorkflowDeployer workflowDeployer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTenant(String tenantDomain, char[] adminRawPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTenant(String tenantDomain, char[] adminRawPassword, String contentRoot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTenant(String tenantDomain, char[] adminRawPassword, String contentRoot, String dbUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportTenant(String tenantDomain, File directoryDestination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importTenant(String tenantDomain, File directorySource, String contentRoot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tenant getTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabledTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		return false;
	}

}
