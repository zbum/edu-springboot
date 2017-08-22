package com.nhnent.springboot.dooray;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DoorayHookSender {

    private RestTemplate resttemplate;

    private String url;

    public DoorayHookSender(RestTemplate resttemplate, String url) {
        this.resttemplate = resttemplate;
        this.url = url;
    }

    public void send(DoorayHook doorayHook) {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<DoorayHook> entity = new HttpEntity<DoorayHook>(doorayHook, headers);
        ResponseEntity<String> exchange = resttemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

}
