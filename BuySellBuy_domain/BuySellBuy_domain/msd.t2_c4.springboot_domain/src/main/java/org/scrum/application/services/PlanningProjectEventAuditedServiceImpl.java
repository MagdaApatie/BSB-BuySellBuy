package org.scrum.application.services;

import java.util.logging.Logger;

import org.scrum.domain.services.impl.PlanningProjectBusinessWorkflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service("PlanningProjectEventAuditedServiceImpl")
public class PlanningProjectEventAuditedServiceImpl 
		extends PlanningProjectBusinessWorkflowServiceImpl {
	
	private static Logger logger = Logger.getLogger(PlanningProjectEventAuditedServiceImpl.class.getName());
	
	// Event Producer Service
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	// (2) 
	@Override
	public Integer addFeatureToProject(Integer projectId, String featureName, String featureDescription) {
		Integer id = super.addFeatureToProject(projectId, featureName, featureDescription);
		//
		applicationEventPublisher.publishEvent(new WorkflowEvent(
				this,
				new Object[] {projectId, featureName, featureDescription},
				WorkflowAction.ADD_FEATURE_TO_PROJECT));
		logger.info(">>>> Spring Event Processing: Auditing from: PlanningProjectEventAuditedWorkflowServiceImpl");
		//
		return id;
	}
	
}
