package com.example.actuator.sample.health;

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
public class L7checkCheckControllerIntegrationTest {
    private static final String L7CHECK = "/l7check";
    private static final String HEALTH = "/health";
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void downAndUp() throws InterruptedException {
        // before
        expectUrlStatus(L7CHECK, HttpStatus.OK);
        expectUrlStatus(HEALTH, HttpStatus.OK);

        // down
        restTemplate.delete(L7CHECK);
        // then down
        TimeUnit.MILLISECONDS.sleep(1000);
        expectUrlStatus(L7CHECK, HttpStatus.SERVICE_UNAVAILABLE);
        expectUrlStatus(HEALTH, HttpStatus.SERVICE_UNAVAILABLE);

        // up
        restTemplate.postForEntity(L7CHECK, null, Object.class);
        // then up
        TimeUnit.MILLISECONDS.sleep(1000);
        expectUrlStatus(L7CHECK, HttpStatus.OK);
        expectUrlStatus(HEALTH, HttpStatus.OK);
    }

    private void expectUrlStatus(String url, HttpStatus status) {
        ResponseEntity<Object> res = restTemplate.getForEntity(url, Object.class);
        assertThat(res.getStatusCode(), is(status));
    }
}
