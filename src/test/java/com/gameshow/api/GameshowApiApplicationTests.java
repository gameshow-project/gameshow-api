package com.gameshow.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GameshowApiApplication.class)
@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
class GameshowApiApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
