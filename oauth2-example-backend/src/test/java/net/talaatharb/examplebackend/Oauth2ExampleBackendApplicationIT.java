package net.talaatharb.examplebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class Oauth2ExampleBackendApplicationIT extends AbstractControllerIT {

	@Autowired
	ApplicationContext applicationContext;
	@Test
	void contextLoads() {
		assertNotNull(applicationContext);
	}

}
