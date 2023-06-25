package com.br.wes.request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class HttpRequestToApp {
   
	public String sendHttpRequest(String user, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8989/v1/validUserPassword/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url + "?user=" + user + "&password=" + password,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().value() == 404) {
                return "Invalid User or Password";
            } else {
                // Handle other HTTP error codes if needed
                return "Error: " + ex.getStatusCode().toString();
            }
        }
    }
}