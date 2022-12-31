package net.talaatharb.examplebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Tag("Integration")
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public
class AbstractControllerIT {
    @Autowired
    protected MockMvc mockMVC;

    @Autowired
    protected ObjectMapper objectMapper;
}
