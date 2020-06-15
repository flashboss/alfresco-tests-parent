package org.alfresco.mock.test.ws;

import org.alfresco.mock.test.AbstractForm;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Description;

public abstract class AbstractWSForm extends AbstractForm {

	@Autowired
	private Container container;
	
	@Autowired
	private Description description;

	protected abstract DeclarativeWebScript getDeclarativeWebScript();

	@Before
	public void init() {
		super.init();
		getDeclarativeWebScript().init(container, description);
	}

}