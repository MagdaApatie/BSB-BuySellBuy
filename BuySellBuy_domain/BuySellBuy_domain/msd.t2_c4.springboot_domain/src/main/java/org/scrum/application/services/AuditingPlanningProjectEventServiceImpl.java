package org.scrum.application.services;

import java.util.logging.Logger;

import org.scrum.domain.services.AuditingPlanningProjectBusinessWorkflowServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("AuditingPlanningProjectEventServiceImpl")
public class AuditingPlanningProjectEventServiceImpl
	extends
		AuditingPlanningProjectBusinessWorkflowServiceImpl
	implements 
		ApplicationListener<WorkflowEvent>{
	
	private static Logger logger = Logger.getLogger(AuditingPlanningProjectEventServiceImpl.class.getName());

	@Override
	public void onApplicationEvent(WorkflowEvent event) {
		logger.info(">>>> Spring Event Processing: Auditing for: " + event);
		if (event.getWorkflowAction().equals(WorkflowAction.ADD_FEATURE_TO_PROJECT)) {
			Integer projectId = (Integer)event.getWorkflowParameters()[0];
			String featureName = (String)event.getWorkflowParameters()[1];
			auditProjectFeature(projectId, featureName);
			
		}
	}
}
