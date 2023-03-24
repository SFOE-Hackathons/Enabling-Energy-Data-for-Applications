package ch.opendata.hack.energy.sources.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The type Rest reader service.
 */
@Service
public class RestReaderService {
    private final RestTemplate restTemplate;

    /**
     * Instantiates a new Rest reader service.
     *
     * @param restTemplateBuilder the rest template builder
     */
    public RestReaderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Send string.
     *
     * @param url the url
     * @return the string
     */
    public String send(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}