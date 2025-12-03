package org.alfresco.mock.test.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.alfresco.mock.test.AbstractForm;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Description;

/**
* Abstract base class for web script form-based tests.
* This class provides common functionality for testing Alfresco web scripts
* using mock services without requiring a full server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public abstract class AbstractWSForm extends AbstractForm {

/**
* The container.
 */
	@Autowired
	private Container container;

/**
* The description.
 */
	@Autowired
	private Description description;

/**
* The date format for file naming.
 */
	protected DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");

/**
* Gets the abstract web script instance.
*
* @return the abstract web script
 */
	protected abstract AbstractWebScript getAbstractWebScript();

/**
* Initializes the component.
 */
	@Before
	public void init() {
		super.init();
		getAbstractWebScript().init(container, description);
	}

}