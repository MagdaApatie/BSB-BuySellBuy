package org.scrum.application.services;

import java.util.Date;
import java.util.logging.Logger;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.domain.services.IAuditingPlanningProjectBusinessWorkflowService;
import org.scrum.domain.services.IPlanningProjectBusinessWorkflowService;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/*
 * AUDIT Business Service Tests
 * 
 * WORKFLOW Business Service Tests
 * Strategy: AOP
 * 
 */
//JUnit.5
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class Test4_AuditingApplicationEventService {
	private static Logger logger = Logger.getLogger(Test4_AuditingApplicationEventService.class.getName());
	
	/*
	 * Audit Flow built in-place 
	 * by MethodInterceptor aspect
	 */
	@Autowired @Qualifier("PlanningProjectEventAuditedServiceImpl")
	private IPlanningProjectBusinessWorkflowService planningProjectBusinessWorkflowService;
	
	
	@Test @Order(1) //@Disabled
	public void test1_AuditWorkflowService() {
		logger.info("Workflow Service implementation instance:: " + planningProjectBusinessWorkflowService);
		logger.info("Workflow Service implementation class:: " + planningProjectBusinessWorkflowService.getClass().getName());
		// invoke business logic on AOP decorated-bean-object
		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
		Integer projectId = planningProjectBusinessWorkflowService.planNewProject("Planned.Test.Project", tomorow);
		planningProjectBusinessWorkflowService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
		planningProjectBusinessWorkflowService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
		planningProjectBusinessWorkflowService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
		
		Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
		planningProjectBusinessWorkflowService.planCurrentRelease(projectId, futureDateOf45Day);
		
		ProjectCurrentReleaseView viewData = planningProjectBusinessWorkflowService.getProjectSummaryData(projectId);
		logger.info(viewData.toString());	
	}
}
