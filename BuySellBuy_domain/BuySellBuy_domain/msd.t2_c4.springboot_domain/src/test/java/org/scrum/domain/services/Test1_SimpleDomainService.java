package org.scrum.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


/*
 * DOMAIN Service Tests
 * Strategy: Simple-Facade
 */

//JUnit.5
@SpringBootTest
public class Test1_SimpleDomainService {
	private static Logger logger = Logger.getLogger(Test1_SimpleDomainService.class.getName());

	@Autowired @Qualifier("ProjectDomainService")
	private IProjectDomainService service;
	
	@Test
	public void test() {
		logger.info("Service implementation object :: " + service);
		logger.info("Service implementation class :: " + service.getClass().getName());
		//
		Integer featureCount = service.getProjectFeaturesCount(1);
		assertTrue(featureCount > 0, "Features not counting...");
		logger.info("Feature count autowired xml:: " + featureCount);
	}
}

/*
* https://www.logicbig.com/tutorials/spring-framework/spring-core/integration-testing.html
*/