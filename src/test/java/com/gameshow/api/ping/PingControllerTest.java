package com.gameshow.api.ping;

import com.gameshow.api.GameshowApiApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GameshowApiApplication.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class PingControllerTest {

    @Test
    void test() {
        assertTrue(true);
    }

}
