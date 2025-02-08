package net.talaatharb.examplebackend;

import org.junit.jupiter.api.Test;
import org.springdoc.webmvc.api.OpenApiWebMvcResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class Oauth2ExampleBackendApplicationIT extends AbstractControllerIT {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private OpenApiWebMvcResource openApiDocs;

	@Test
	void testSwaggerIsConfigured() {
		assertNotNull(openApiDocs);
	}

	@Test
	void contextLoads() {
		assertNotNull(applicationContext);
	}

}
