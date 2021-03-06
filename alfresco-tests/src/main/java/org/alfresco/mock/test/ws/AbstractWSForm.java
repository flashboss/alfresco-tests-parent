package org.alfresco.mock.test.ws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.alfresco.mock.test.AbstractForm;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Description;

public abstract class AbstractWSForm extends AbstractForm {

	@Autowired
	private Container container;
	
	@Autowired
	private Description description;
	
	protected DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");

	protected abstract AbstractWebScript getAbstractWebScript();

	@Before
	public void init() {
		super.init();
		getAbstractWebScript().init(container, description);
	}

}