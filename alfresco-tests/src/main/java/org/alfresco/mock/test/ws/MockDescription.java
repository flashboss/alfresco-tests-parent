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
* Mock implementation of the MockDescription class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockDescription implements Description {

	private RequiredCache requiredCache = new MockRequiredCache();

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getId() {
		return "test";
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getShortName() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getStorePath() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getScriptPath() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Path getPackage() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDescPath() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
* @throws IOException if an error occurs
 */
	@Override
	public InputStream getDescDocument() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getKind() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getFamilys() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RequiredAuthentication getRequiredAuthentication() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getRunAs() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RequiredTransaction getRequiredTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RequiredTransactionParameters getRequiredTransactionParameters() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RequiredCache getRequiredCache() {
		return requiredCache;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public String[] getURIs() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDefaultFormat() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public NegotiatedFormat[] getNegotiatedFormats() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<String, Serializable> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean getMultipartProcessing() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param multipartProcessing the multipartProcessing
 */
	@Override
	public void setMultipartProcessing(boolean multipartProcessing) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
 */
	@Override
	public ArgumentTypeDescription[] getArguments() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public TypeDescription[] getRequestTypes() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public TypeDescription[] getResponseTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
