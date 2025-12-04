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

	/** The required cache. */
	private RequiredCache requiredCache = new MockRequiredCache();

	/**
	 * Get id.
	 *
	 * @return the string
	 */
@Override
	public String getId() {
		return "test";
	}

	/**
	 * Get short name.
	 *
	 * @return the string
	 */
@Override
	public String getShortName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get description.
	 *
	 * @return the string
	 */
@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get store path.
	 *
	 * @return the string
	 */
@Override
	public String getStorePath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get script path.
	 *
	 * @return the string
	 */
@Override
	public String getScriptPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get package.
	 *
	 * @return the path
	 */
@Override
	public Path getPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get desc path.
	 *
	 * @return the string
	 */
@Override
	public String getDescPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get desc document.
	 *
	 * @return the input stream
	 */
@Override
	public InputStream getDescDocument() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get kind.
	 *
	 * @return the string
	 */
@Override
	public String getKind() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get familys.
	 *
	 * @return the set
	 */
@Override
	public Set<String> getFamilys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get required authentication.
	 *
	 * @return the required authentication
	 */
@Override
	public RequiredAuthentication getRequiredAuthentication() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get run as.
	 *
	 * @return the string
	 */
@Override
	public String getRunAs() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get required transaction.
	 *
	 * @return the required transaction
	 */
@Override
	public RequiredTransaction getRequiredTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get required transaction parameters.
	 *
	 * @return the required transaction parameters
	 */
@Override
	public RequiredTransactionParameters getRequiredTransactionParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get required cache.
	 *
	 * @return the required cache
	 */
@Override
	public RequiredCache getRequiredCache() {
		return requiredCache;
	}

	/**
	 * Get method.
	 *
	 * @return the string
	 */
@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get u r is.
	 *
	 * @return the string[]
	 */
@Override
	public String[] getURIs() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get format style.
	 *
	 * @return the format style
	 */
@Override
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get default format.
	 *
	 * @return the string
	 */
@Override
	public String getDefaultFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get negotiated formats.
	 *
	 * @return the negotiated format[]
	 */
@Override
	public NegotiatedFormat[] getNegotiatedFormats() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get extensions.
	 *
	 */
@Override
	public Map<String, Serializable> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get lifecycle.
	 *
	 * @return the lifecycle
	 */
@Override
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get multipart processing.
	 *
	 * @return the boolean
	 */
@Override
	public boolean getMultipartProcessing() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Set multipart processing.
	 *
	 * @param multipartProcessing the multipart processing
	 */
@Override
	public void setMultipartProcessing(boolean multipartProcessing) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get arguments.
	 *
	 * @return the argument type description[]
	 */
@Override
	public ArgumentTypeDescription[] getArguments() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get request types.
	 *
	 * @return the type description[]
	 */
@Override
	public TypeDescription[] getRequestTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get response types.
	 *
	 * @return the type description[]
	 */
@Override
	public TypeDescription[] getResponseTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
