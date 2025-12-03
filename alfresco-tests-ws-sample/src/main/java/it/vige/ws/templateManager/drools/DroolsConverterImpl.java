package it.vige.ws.templateManager.drools;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.extensions.webscripts.WebScriptException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.pdfbox.io.IOUtils.toByteArray;

/**
 * Author: Luca Stancapiano
 */
/**
 * @author vige
 */
public class DroolsConverterImpl {

	Logger logger = Logger.getLogger("FILE2");

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private DateFormat convertedDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Map<String, String> jsonMap;

	public void replace(XWPFRun r, String templateField, String jsonField) {
		String runText = r.getText(0);

		if (runText != null && runText.contains(templateField)) {

			String value = jsonMap.get(jsonField);

			if (value != null) {

				// padding is added, if any
				Pattern pattern = Pattern.compile(">(\\d\\d)}$");
				Matcher m = pattern.matcher(templateField);

				String paddedValue = value;
				if (m.find()) {
					String lString = m.group(1);

					int length = lString != null ? Integer.valueOf(lString) : -1;
					if (length > 0) {
						paddedValue = StringUtils.rightPad(value, length);
					}
				}

				String newText = runText.replace(templateField, paddedValue);
				r.setText(newText, 0);
			}
		}
	}

	public void replaceDate(XWPFRun r, String templateField, String jsonField) {
		String runText = r.getText(0);
		if (runText != null && runText.contains(templateField)) {

			String value = jsonMap.get(jsonField);
			if (value != null) {
				String newText;
				try {
					Date dateValue = dateFormat.parse(value);
					String fdate = convertedDateFormat.format(dateValue);
					newText = runText.replace(templateField, fdate);
				} catch (ParseException e) {
					newText = runText.replace(templateField, "");
				}

				r.setText(newText, 0);
			}
		}
	}

	public void replaceCurrency(XWPFRun r, String templateField, String jsonField) {
		String runText = r.getText(0);
		if (runText != null && runText.contains(templateField)) {

			String value = jsonMap.get(jsonField);
			if (value != null) {
				String newText;
				try {
					BigDecimal bdValue = new BigDecimal(value);

					Locale locale = new Locale("it", "IT");
					String pattern = "€ ###,###,##0.00";

					DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
					decimalFormat.applyPattern(pattern);

					String fValuta = decimalFormat.format(bdValue);
					newText = runText.replace(templateField, fValuta);
				} catch (IllegalArgumentException e) {
					logger.error("Currency parsing error: " + e.getMessage());
					newText = runText.replace(templateField, "");
				}

				r.setText(newText, 0);
			}
		}
	}

	public void replaceMultivalue(XWPFRun r, String templateField, String jsonField) {
		String runText = r.getText(0);
		String value = jsonMap.get(jsonField);

		if (value != null && runText.length() > 0) {

			String templateKey = templateField.substring(2, templateField.length() - 1);

			if (runText != null && runText.contains(templateField)) {

				String newTextStep1 = runText.replaceAll("\\$\\{" + templateKey + "\\|\\*\\}", "X");

				String newTextStep2 = newTextStep1.replaceAll("\\$\\{" + templateKey + "\\|" + value + "\\}", "X");

				String newTextFinal = newTextStep2.replaceAll("\\$\\{" + templateKey + "\\|\\w{1,10}\\}", " ");

				r.setText(newTextFinal, 0);

			}
		}
	}

	public boolean checkIfContinue(XWPFRun r) {
		String runText = r.getText(0);
		return runText != null && runText.contains("${");
	}

	/**
	 * Convert a single template based on the drools rule file and
	 * information extracted from the json
	 * 
	 * @param templateIS
	 * @param droolsRuleIS
	 * @param jsonMap
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream fillTemplate(InputStream templateIS, InputStream droolsRuleIS,
			Map<String, String> jsonMap, String nomeFile) throws IOException {
		this.jsonMap = jsonMap;
		// fill in the document
		XWPFDocument template = new XWPFDocument(templateIS);

		byte[] rule = toByteArray(droolsRuleIS);
		final DocParam docParam = new DocParam(template, jsonMap, this);

		final KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write("src/main/resources/drools/rule.drl", kieServices.getResources().newByteArrayResource(rule));
		KieBuilder kb = kieServices.newKieBuilder(kfs).buildAll();

		if (kb.getResults().hasMessages(Message.Level.ERROR)) {
			throw new WebScriptException("Error in substitution rules " + kb.getResults());
		}

		KieRepository kieRepository = kieServices.getRepository();
		KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
		StatelessKieSession kSession = kContainer.newStatelessKieSession();

		// exec drools rule (stateless)
		kSession.execute(docParam);

		ByteArrayOutputStream templateOs = new ByteArrayOutputStream();

		/* Imposto i metadati del documento */
		try (XWPFWordExtractor word = new XWPFWordExtractor(template)) {

			CoreProperties info = word.getCoreProperties();
			info.setTitle(nomeFile);
			info.setSubjectProperty(nomeFile);
			info.setCreator("Sample Bank - http://www.vige.it/");
			template.write(templateOs);
		}

		logger.info("template compilation completed");

		return templateOs;
	}

}
