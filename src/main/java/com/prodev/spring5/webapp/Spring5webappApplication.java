package com.prodev.spring5.webapp;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Arrays;

@SpringBootApplication
public class Spring5webappApplication {
   static   String baseUrl = "http://localhost:8080/api/v1/sms/send/otp?customerPhone=";
    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(Spring5webappApplication.class, args);
       sendOtp("959256275319");
    }

    private static HttpHeaders getHeaders ()
    {
        String adminuserCredentials = "dcheckadmin:dcheckadm!n";
        String encodedCredentials =
                new String(Base64.encodeBase64(adminuserCredentials.getBytes()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedCredentials);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private static void sendOtp(String phone)
    {


        String restUrl = baseUrl+ phone;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = getHeaders();

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl,
                HttpMethod.GET, httpEntity, String.class);

        if(responseEntity.hasBody()) {
            System.out.println(responseEntity.getBody());
        }
        else
        {
            System.out.println("User not found");
        }

    }
}
