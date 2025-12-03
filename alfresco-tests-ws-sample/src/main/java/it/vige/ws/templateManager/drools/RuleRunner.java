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
 * Author: Luca Stancapiano
 */
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class RuleRunner {

//	private Log logger = getLog(RuleRunner.class);

	public void runRules(byte[] rule, Object[] facts) throws IOException {

		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write( "src/main/resources/drools/rule.drl", kieServices.getResources().newByteArrayResource(rule) );

		KieBuilder kb = kieServices.newKieBuilder(kfs);

		kb.buildAll();

		if (kb.getResults().hasMessages(Message.Level.ERROR))
		{
			throw new IOException("Build Errors:\n" + kb.getResults());
		}

		KieRepository kieRepository = kieServices.getRepository();
		KieContainer kContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
		KieSession kSession = kContainer.newKieSession();

		for (int i = 0; i < facts.length; i++) {
			Object fact = facts[i];
//			logger.debug("Inserting fact: " + fact);
			kSession.insert(fact);
		}

		kSession.fireAllRules();

	}
}