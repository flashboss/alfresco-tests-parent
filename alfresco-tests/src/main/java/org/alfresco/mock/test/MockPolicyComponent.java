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
* Mock implementation of the MockPolicyComponent class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockPolicyComponent implements PolicyComponent {

/**
* {@inheritDoc}
* @param policy the policy
* @return the result
 */
	@Override
	public <P extends ClassPolicy> ClassPolicyDelegate<P> registerClassPolicy(Class<P> policy) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param policy the policy
* @return the result
 */
	@Override
	public <P extends PropertyPolicy> PropertyPolicyDelegate<P> registerPropertyPolicy(Class<P> policy) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param policy the policy
* @return the result
 */
	@Override
	public <P extends AssociationPolicy> AssociationPolicyDelegate<P> registerAssociationPolicy(Class<P> policy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Collection<PolicyDefinition> getRegisteredPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param policyType the policyType
* @param policy the policy
* @return the result
 */
	@Override
	public PolicyDefinition<Policy> getRegisteredPolicy(PolicyType policyType, QName policy) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param policyType the policyType
* @param policy the policy
* @return the result
 */
	@Override
	public boolean isRegisteredPolicy(PolicyType policyType, QName policy) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ClassBehaviourBinding> bindClassBehaviour(QName policy, QName className,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ServiceBehaviourBinding> bindClassBehaviour(QName policy, Object service,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindPropertyBehaviour(QName policy, QName className,
			QName propertyName, Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindPropertyBehaviour(QName policy, QName className,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ServiceBehaviourBinding> bindPropertyBehaviour(QName policy, Object service,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindAssociationBehaviour(QName policy, QName className,
			QName assocName, Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ClassFeatureBehaviourBinding> bindAssociationBehaviour(QName policy, QName className,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public BehaviourDefinition<ServiceBehaviourBinding> bindAssociationBehaviour(QName policy, Object service,
			Behaviour behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param definition the definition
 */
	@Override
	public void removeClassDefinition(BehaviourDefinition<ClassBehaviourBinding> definition) {
		// TODO Auto-generated method stub

	}

}
