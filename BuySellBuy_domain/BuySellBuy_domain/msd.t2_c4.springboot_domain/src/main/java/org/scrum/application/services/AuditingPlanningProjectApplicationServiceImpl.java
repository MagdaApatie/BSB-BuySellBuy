package org.scrum.application.services;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.scrum.domain.services.AuditingPlanningProjectBusinessWorkflowServiceImpl;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Service;

@Service("AuditingPlanningProjectApplicationServiceImpl")
public class AuditingPlanningProjectApplicationServiceImpl
	extends
		AuditingPlanningProjectBusinessWorkflowServiceImpl
	implements 
		MethodInterceptor, AfterReturningAdvice, MethodBeforeAdvice{
	
	private static Logger logger = Logger.getLogger(AuditingPlanningProjectApplicationServiceImpl.class.getName());

	// AOP Invocation Logic
	@Override
	public Object invoke(MethodInvocation ic) throws Throwable {
		// Before invoking target object
		logger.info(">>>> SpringAOP.INTERCEPTION: Entering MethodInvocation for: " 
					+ ic.getMethod().getName()
					+ ", " + ic.getMethod().getDeclaringClass()
					+ ", " + ic.getThis().getClass());
		try {
			//Object result =  ic.proceed();
			if ( ic.getMethod().getDeclaringClass().getName()
						.equals("org.scrum.domain.services.impl.PlanningProjectBusinessWorkflowServiceImpl")
				&& ic.getMethod().getName().equals("addFeatureToProject")) {
				logger.info(">>>>>> SpringAOP.INTERCEPTION: AUDIT PlanningProjectBusinessWorkflowServiceImpl::addFeatureToProject");
				// Decorated Business Logic
				Integer projectId = (Integer) ic.getArguments()[0];
				String featureName = (String) ic.getArguments()[1];
				auditProjectFeature(projectId, featureName);
			}
			// invoke target
			Object result =  ic.proceed();
			//
			System.out.println(">>>>>> " + result);
			//
			return result;
		} finally {
			logger.info(">>>> SpringAOP.INTERCEPTION: Exiting MethodInvocation for: " + ic.getMethod().getName());
		}
	}
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) 
		throws Throwable {
		// target is already invoked
		logger.info(">>>> SpringAOP.INTERCEPTION: Execute afterReturning for: "  + method.getName()
				+ ", " + target.getClass());
		//
		if (method.getName().equals("addFeatureToProject")) {
			Integer newProjectId = (Integer) returnValue;
			
			// Decorated Business Logic
			Integer projectId = (Integer) args[0];
			String featureName = (String) args[1];
			auditProjectFeature(projectId, featureName);
	
			logger.info(">>>...>>> Spring.AOP.DECORATED PROJECT with features: " + newProjectId);
		}
		
	}
	//
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// target is already invoked
		logger.info(">>>> SpringAOP.INTERCEPTION: Execute before of: "  + method.getName()
				+ ", " + target.getClass());
		if (method.getName().equals("addFeatureToProject")) {
			// Decorated Business Logic
			Integer projectId = (Integer) args[0];
			String featureName = (String) args[1];
			auditProjectFeature(projectId, featureName);
	
		}
		logger.info(">>>> SpringAOP.INTERCEPTION: Exiting aspect for: " + method.getName());
	}
	//
}
