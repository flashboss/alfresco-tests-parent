package org.alfresco.mock.test.script;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.jscript.ValueConverter;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.module.ModuleDetails;
import org.alfresco.service.cmr.module.ModuleService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ScriptPagingDetails;
import org.springframework.extensions.surf.util.I18NUtil;
import org.springframework.extensions.surf.util.ISO8601DateFormat;

/**
 * Mock implementation of script utilities for testing purposes.
 * Provides various utility functions for JavaScript scripts.
 *
 * @author lucastancapiano
 */
public class MockUtils extends BaseScopableProcessorExtension implements Serializable {

	/** The namespace begin character. */
	private final static String NAMESPACE_BEGIN = "" + QName.NAMESPACE_BEGIN;

	/** The service registry. */
	private ServiceRegistry services;

	/**
	 * Sets the service registry.
	 *
	 * @param services the service registry
	 */
	public void setServiceRegistry(ServiceRegistry services) {
		this.services = services;
	}

	/**
	 * Pads a string with zero '0' characters to the required length.
	 *
	 * @param s the string to pad with leading zero '0' characters
	 * @param len the length to pad to
	 * @return padded string or the original if already at len characters
	 */
	public String pad(String s, int len) {
		String result = s;
		for (int i = 0; i < (len - s.length()); i++) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * Gets a JS node object from a string noderef.
	 *
	 * @param nodeRefString string reference to a node
	 * @return a JS node object
	 */
	public ScriptNode getNodeFromString(String nodeRefString) {
		NodeRef nodeRef = new NodeRef(nodeRefString);
		return (ScriptNode) new ValueConverter().convertValueForScript(this.services, getScope(), null, nodeRef);
	}

	/**
	 * Gets a boolean value from a string.
	 *
	 * @param booleanString boolean string
	 * @return the boolean value
	 * @see Boolean#parseBoolean(String)
	 */
	public boolean toBoolean(String booleanString) {
		return Boolean.parseBoolean(booleanString);
	}

	/**
	 * Checks if a module is installed.
	 *
	 * @param moduleName module name (e.g. "org.alfresco.module.foo")
	 * @return true if the module is currently installed
	 */
	public boolean moduleInstalled(String moduleName) {
		ModuleService moduleService = (ModuleService) this.services
				.getService(QName.createQName(NamespaceService.ALFRESCO_URI, "ModuleService"));
		if (moduleService != null) {
			ModuleDetails moduleDetail = (ModuleDetails) moduleService.getModule(moduleName);
			return (moduleDetail != null);
		}
		return false;
	}

	/**
	 * Formats timeInMillis to ISO 8601 formatted string.
	 *
	 * @param timeInMillis the time in milliseconds
	 * @return the ISO 8601 formatted string
	 */
	public String toISO8601(long timeInMillis) {
		return ISO8601DateFormat.format(new Date(timeInMillis));
	}

	/**
	 * Formats date to ISO 8601 formatted string.
	 *
	 * @param date the date
	 * @return the ISO 8601 formatted string
	 */
	public String toISO8601(Date date) {
		return ISO8601DateFormat.format(date);
	}

	/**
	 * Parses date from ISO formatted string.
	 *
	 * @param isoDateString the ISO 8601 date string
	 * @return the parsed date
	 */
	public Date fromISO8601(String isoDateString) {
		return ISO8601DateFormat.parse(isoDateString);
	}

	/**
	 * Creates a short-form QName string from a long-form QName string.
	 *
	 * @param s fully qualified QName string
	 * @return the short form of the QName string, e.g. "cm:content"
	 */
	public String shortQName(String s) {
		return createQName(s).toPrefixString(services.getNamespaceService());
	}

	/**
	 * Creates a fully qualified QName string from a short-form QName string.
	 *
	 * @param s short form QName string, e.g. "cm:content"
	 * @return fully qualified QName string
	 */
	public String longQName(String s) {
		return createQName(s).toString();
	}

	/**
	 * Builds a paging object from the supplied Max Items and Skip Count.
	 *
	 * @param maxItems the maximum items
	 * @param skipCount the skip count
	 * @return the paging details
	 */
	public ScriptPagingDetails createPaging(int maxItems, int skipCount) {
		return new ScriptPagingDetails(maxItems, skipCount);
	}

	/**
	 * Builds a paging object from the supplied Max Items, Skip Count and Query Execution ID.
	 *
	 * @param maxItems the maximum items
	 * @param skipCount the skip count
	 * @param queryExecutionId the query execution ID
	 * @return the paging details
	 */
	public ScriptPagingDetails createPaging(int maxItems, int skipCount, String queryExecutionId) {
		return new ScriptPagingDetails(maxItems, skipCount, queryExecutionId);
	}

	/**
	 * Builds a paging object from the supplied Args object.
	 * Requires that the parameters have their standard names,
	 * i.e. "maxItems" and "skipCount".
	 *
	 * @param args the arguments map
	 * @return the paging details
	 */
	public ScriptPagingDetails createPaging(Map<String, String> args) {
		int maxItems = -1;
		int skipCount = -1;
		String queryId = null;

		if (args.containsKey("maxItems")) {
			try {
				maxItems = Integer.parseInt(args.get("maxItems"));
			} catch (NumberFormatException e) {
			}
		}
		if (args.containsKey("skipCount")) {
			try {
				skipCount = Integer.parseInt(args.get("skipCount"));
			} catch (NumberFormatException e) {
			}
		}

		if (args.containsKey("queryId")) {
			queryId = args.get("queryId");
		} else if (args.containsKey("queryExecutionId")) {
			queryId = args.get("queryExecutionId");
		}

		return new ScriptPagingDetails(maxItems, skipCount, queryId);
	}

	/**
	 * Creates a QName from either a fully qualified or short-name QName string.
	 *
	 * @param s fully qualified or short-name QName string
	 * @return the QName
	 */
	private QName createQName(String s) {
		QName qname;
		if (s.indexOf(NAMESPACE_BEGIN) != -1) {
			qname = QName.createQName(s);
		} else {
			qname = QName.createQName(s, this.services.getNamespaceService());
		}
		return qname;
	}

	/**
	 * Disables rule execution for this thread.
	 */
	public void disableRules() {
		services.getRuleService().disableRules();
	}

	/**
	 * Enables rule execution for this thread.
	 */
	public void enableRules() {
		services.getRuleService().enableRules();
	}

	/**
	 * Sets current Locale from string.
	 *
	 * @param localeStr the locale string
	 */
	public void setLocale(String localeStr) {
		Locale newLocale = I18NUtil.parseLocale(localeStr);
		I18NUtil.setLocale(newLocale);
	}

	/**
	 * Returns current thread's locale.
	 *
	 * @return the locale string
	 */
	public String getLocale() {
		return I18NUtil.getLocale().toString();
	}
}
