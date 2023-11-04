package org.scrum.domain.services;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.scrum.application.services.Test4_AuditingApplicationService;
import org.scrum.application.services.Test5_EventWorkflowService;

@Suite
@SelectClasses({
	Test1_SimpleDomainService.class, 
	Test2_ComputingDomainServices.class,
	Test3_BusinessWorkflowService.class,
	Test4_AuditingApplicationService.class,
	Test5_EventWorkflowService.class,
	Test6_ValidatingDomainService.class 
})
//@SelectPackages("org.scrum.application.services")
public class AllTests {

}

/*
* Junit5 docs: https://junit.org/junit5/docs/current/user-guide/#overview
*/