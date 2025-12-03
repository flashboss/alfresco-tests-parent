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
 * Mock implementation of MockDescription for testing purposes.
 *
 * @author vige
 */
public class MockDescription implements Description {

	/** The required cache. */
	private RequiredCache requiredCache = new MockRequiredCache();

	@Override
	/**
	 * Get id.
	 *
	 * @return the result
	 */
	public String getId() {
		return "test";
	}

	@Override
	/**
	 * Get short name.
	 *
	 * @return the result
	 */
	public String getShortName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get description.
	 *
	 * @return the result
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get store path.
	 *
	 * @return the result
	 */
	public String getStorePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get script path.
	 *
	 * @return the result
	 */
	public String getScriptPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get package.
	 *
	 * @return the result
	 */
	public Path getPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get desc path.
	 *
	 * @return the result
	 */
	public String getDescPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get desc document.
	 *
	 * @return the result
	 */
	public InputStream getDescDocument() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get kind.
	 *
	 * @return the result
	 */
	public String getKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get familys.
	 *
	 * @return the result
	 */
	public Set<String> getFamilys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get required authentication.
	 *
	 * @return the result
	 */
	public RequiredAuthentication getRequiredAuthentication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get run as.
	 *
	 * @return the result
	 */
	public String getRunAs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get required transaction.
	 *
	 * @return the result
	 */
	public RequiredTransaction getRequiredTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get required transaction parameters.
	 *
	 * @return the result
	 */
	public RequiredTransactionParameters getRequiredTransactionParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get required cache.
	 *
	 * @return the result
	 */
	public RequiredCache getRequiredCache() {
		return requiredCache;
	}

	@Override
	/**
	 * Get method.
	 *
	 * @return the result
	 */
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
	/**
	 * Get format style.
	 *
	 * @return the result
	 */
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get default format.
	 *
	 * @return the result
	 */
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
	/**
	 * Get extensions.
	 *
	 */
	public Map<String, Serializable> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get lifecycle.
	 *
	 * @return the result
	 */
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get multipart processing.
	 *
	 * @return the result
	 */
	public boolean getMultipartProcessing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Set multipart processing.
	 *
	 * @param multipartProcessing the multipart processing
	 */
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
