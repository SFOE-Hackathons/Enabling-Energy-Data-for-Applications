package ch.opendata.hack.energy.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String send(String address, String name) {
        final String url = "http://" + address + ":8080/hello?name={name}";
        return restTemplate.getForObject(url, String.class, name);
    }

    public void send(List<String> addresses, String name) {
        for(String address : addresses) {
            for(int i = 0; i < 10000;i++) {
                final String url = "http://" + address + ":8080/hello?name={name}";
                restTemplate.getForObject(url, String.class, name);
            }
        }
    }
}