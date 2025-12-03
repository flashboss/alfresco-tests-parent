package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.extensions.webscripts.ArgumentTypeDescription;
import org.springframework.extensions.webscripts.Description;
import org.springframework.extensions.webscripts.NegotiatedFormat;
import org.springframework.extensions.webscripts.Path;
import org.springframework.extensions.webscripts.TypeDescription;

/**
 * Mock implementation of Description for testing purposes.
 * 
 * @author vige
 */
public class MockDescription implements Description {

	private RequiredCache requiredCache = new MockRequiredCache();

	@Override
	public String getId() {
		return "test";
	}

	@Override
	public String getShortName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStorePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScriptPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getDescDocument() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getFamilys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequiredAuthentication getRequiredAuthentication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRunAs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequiredTransaction getRequiredTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequiredTransactionParameters getRequiredTransactionParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequiredCache getRequiredCache() {
		return requiredCache;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getURIs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NegotiatedFormat[] getNegotiatedFormats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Serializable> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMultipartProcessing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMultipartProcessing(boolean multipartProcessing) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArgumentTypeDescription[] getArguments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDescription[] getRequestTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDescription[] getResponseTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
