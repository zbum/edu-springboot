package com.example.actuator.sample.health;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class L7checkCheckControllerIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void downAndUp() throws InterruptedException {
        // before
        expectUrlStatus("/l7check", HttpStatus.OK);
        expectUrlStatus("/health", HttpStatus.OK);

        // down
        restTemplate.delete("/l7check");
        // then down
        TimeUnit.MILLISECONDS.sleep(1000);
        expectUrlStatus("/l7check", HttpStatus.SERVICE_UNAVAILABLE);
        expectUrlStatus("/health", HttpStatus.SERVICE_UNAVAILABLE);

        // up
        restTemplate.postForEntity("/l7check", null, Object.class);
        // then up
        TimeUnit.MILLISECONDS.sleep(1000);
        expectUrlStatus("/l7check", HttpStatus.OK);
        expectUrlStatus("/health", HttpStatus.OK);
    }

    private void expectUrlStatus(String url, HttpStatus status) {
        ResponseEntity<Object> res = restTemplate.getForEntity(url, Object.class);
        assertThat(res.getStatusCode(), is(status));
    }
}