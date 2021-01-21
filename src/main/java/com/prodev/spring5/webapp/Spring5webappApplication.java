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
   static   String send_opt_Url = "http://localhost:8080/api/v1/sms/send/otp";
   static String verify_otp_url="http://localhost:8080/api/v1/sms/verify/otp";
   static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(Spring5webappApplication.class, args);
       //sendOtp("959256275319");
       verifyOtp("959256275319",776718);
    }

    private static void verifyOtp(String phone, int otp) {
        verify_otp_url+="?customerPhone="+ phone;
        verify_otp_url+="&inputOtp="+otp;

        HttpHeaders httpHeaders = getHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(verify_otp_url,HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

    private static void sendOtp(String phone) {
        send_opt_Url+="?customerPhone="+ phone;
        HttpHeaders httpHeaders = getHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(send_opt_Url,HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getHeaders());
    }
    private static HttpHeaders getHeaders () {
        String adminuserCredentials = "dcheckadmin:dcheckadm!n";
        String encodedCredentials = new String(Base64.encodeBase64(adminuserCredentials.getBytes()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedCredentials);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}
