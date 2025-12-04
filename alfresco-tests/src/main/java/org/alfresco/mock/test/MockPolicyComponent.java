package org.alfresco.mock.test;

import java.util.Collection;

import org.alfresco.repo.policy.AssociationPolicy;
import org.alfresco.repo.policy.AssociationPolicyDelegate;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.BehaviourDefinition;
import org.alfresco.repo.policy.ClassBehaviourBinding;
import org.alfresco.repo.policy.ClassFeatureBehaviourBinding;
import org.alfresco.repo.policy.ClassPolicy;
import org.alfresco.repo.policy.ClassPolicyDelegate;
import org.alfresco.repo.policy.Policy;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.policy.PolicyDefinition;
import org.alfresco.repo.policy.PolicyType;
import org.alfresco.repo.policy.PropertyPolicy;
import org.alfresco.repo.policy.PropertyPolicyDelegate;
import org.alfresco.repo.policy.ServiceBehaviourBinding;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of PolicyComponent for testing purposes.
 * 
 * @author vige
 */
public class MockPolicyComponent implements PolicyComponent {
	/**
	 * Register class policy.
	 *
	 * @param policy the policy
	 */
	@Override
	public <P extends ClassPolicy> ClassPolicyDelegate<P> registerClassPolicy(Class<P> policy) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Register property policy.
	 *
	 * @param policy the policy
	 */
	@Override
	public <P extends PropertyPolicy> PropertyPolicyDelegate<P> registerPropertyPolicy(Class<P> policy) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Register association policy.
	 *
	 * @param policy the policy
	 */
	@Override
	public <P extends AssociationPolicy> AssociationPolicyDelegate<P> registerAssociationPolicy(Class<P> policy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	/**
	 * Get registered policies.
	 *
	 * @return the collection
	 */
	public Collection<PolicyDefinition> getRegisteredPolicies() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get registered policy.
	 *
	 * @param policyType the policy type
	 * @param policy the policy
	 * @return the policy definition
	 */
	@Override
	public PolicyDefinition<Policy> getRegisteredPolicy(PolicyType policyType, QName policy) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Is registered policy.
	 *
	 * @param policyType the policy type
	 * @param policy the policy
	 * @return the boolean
	 */
	@Override
	public boolean isRegisteredPolicy(PolicyType policyType, QName policy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BehaviourDefinition<ClassBehaviourBinding> bindClassBehaviour(QName policy, QName className,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ServiceBehaviourBinding> bindClassBehaviour(QName policy, Object service,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindPropertyBehaviour(QName policy, QName className,
			QName propertyName, Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindPropertyBehaviour(QName policy, QName className,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ServiceBehaviourBinding> bindPropertyBehaviour(QName policy, Object service,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindAssociationBehaviour(QName policy, QName className,
			QName assocName, Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindAssociationBehaviour(QName policy, QName className,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BehaviourDefinition<ServiceBehaviourBinding> bindAssociationBehaviour(QName policy, Object service,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Remove class definition.
	 *
	 * @param definition the definition
	 */
	@Override
	public void removeClassDefinition(BehaviourDefinition<ClassBehaviourBinding> definition) {
		// TODO Auto-generated method stub
		
	}

}
