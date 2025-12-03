package it.vige.ws.templateManager.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;

/**
 * Runner class for executing Drools rules.
 * Provides functionality to load and execute Drools rules with given facts.
 *
 * @author lucastancapiano
 */
public class RuleRunner {

	/**
	 * Runs Drools rules with the provided facts.
	 * Loads the rule from byte array, builds the knowledge base,
	 * inserts facts and fires all rules.
	 *
	 * @param rule the byte array containing the Drools rule definition
	 * @param facts the array of fact objects to insert into the session
	 * @throws IOException if there are build errors in the rules
	 */
	public void runRules(byte[] rule, Object[] facts) throws IOException {

		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write("src/main/resources/drools/rule.drl", kieServices.getResources().newByteArrayResource(rule));

		KieBuilder kb = kieServices.newKieBuilder(kfs);

		kb.buildAll();

		if (kb.getResults().hasMessages(Message.Level.ERROR)) {
			throw new IOException("Build Errors:\n" + kb.getResults());
		}

		KieRepository kieRepository = kieServices.getRepository();
		KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
		KieSession kSession = kContainer.newKieSession();

		for (int i = 0; i < facts.length; i++) {
			Object fact = facts[i];
			kSession.insert(fact);
		}

		kSession.fireAllRules();

	}
}
