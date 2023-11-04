package org.scrum;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.scrum.domain.services.IAuditingPlanningProjectBusinessWorkflowService;
import org.scrum.domain.services.IPlanningProjectBusinessWorkflowService;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootScrumApplication extends SpringBootServletInitializer
{
	private static Logger logger = Logger.getLogger(SpringBootScrumApplication.class.getName());
	
	public static void main(String[] args) {
		logger.info("Loading ... :: SpringBoot - ScrumApplication Default Settings ...  ");
		SpringApplication.run(SpringBootScrumApplication.class, args);
	}

	// AOP Auditing Service - Infrastructure setup //
	@Autowired
	@Qualifier("PlanningProjectBusinessWorkflowServiceFacade")
	private IPlanningProjectBusinessWorkflowService planningProjectBusinessWorkflowService;

	@Autowired @Qualifier("AuditingPlanningProjectApplicationServiceImpl")
	private IAuditingPlanningProjectBusinessWorkflowService auditApplicationService;

	@Bean(name="proxyAuditedApplicationService")
	public IPlanningProjectBusinessWorkflowService initAuditedApplicationService() {
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice((AfterReturningAdvice)auditApplicationService);
		pf.setTarget(planningProjectBusinessWorkflowService);
		IPlanningProjectBusinessWorkflowService proxyAuditedApplicationService =
				(IPlanningProjectBusinessWorkflowService) pf.getProxy();
		logger.info(">>> Loading IPlanningProjectBusinessWorkflowService as AOP Service >>>");
		return proxyAuditedApplicationService;
	}
}