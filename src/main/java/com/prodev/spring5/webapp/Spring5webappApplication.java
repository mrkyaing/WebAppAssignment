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
   static String send_opt_Url = "http://localhost:8080/api/v1/sms/send/otp";
   static String verify_otp_url="http://localhost:8080/api/v1/sms/verify/otp";
   static RestTemplate restTemplate = new RestTemplate();

   public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(Spring5webappApplication.class, args);
       try {
          String status=sendOtp("9592562753199");
           System.out.println(status);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

       try {
               String status=verifyOtp("959256275319",153149);
               System.out.println("register success with otp");
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }

    private static String verifyOtp(String phone, int otp) {
        verify_otp_url+="?customerPhone="+ phone;
        verify_otp_url+="&inputOtp="+otp;

        HttpHeaders httpHeaders = getHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(verify_otp_url,HttpMethod.GET, httpEntity, String.class);
       return  responseEntity.getBody();
    }

    private static  String  sendOtp(String phone) {
        send_opt_Url+="?customerPhone="+ phone;
        HttpHeaders httpHeaders = getHeaders();//get the HttpHeaders with user Credential of API EndPoint
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(send_opt_Url,HttpMethod.GET, httpEntity, String.class);
       return responseEntity.getBody();
    }
    private static HttpHeaders getHeaders () {
        String credentials = "dcheckadmin:dcheckadm!n";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedCredentials);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}
