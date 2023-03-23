package ch.opendata.hack.energy.controller;

import ch.opendata.hack.energy.model.DummyModel;
import ch.opendata.hack.energy.repository.DummyRepository;
import ch.opendata.hack.energy.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DummyRestController {
    private static final Logger logger = LoggerFactory.getLogger(DummyRestController.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    private final DummyRepository repository;
    private RestService restService;

    public DummyRestController(@Autowired DummyRepository repository, @Autowired RestService restService) {
        this.repository = repository;
        this.restService = restService;
    }

    @GetMapping("/send")
    public ResponseEntity<String> send(@RequestParam("address") final String address, @RequestParam("name") final String person) {
        logger.info("Send to address {} person {}", address, person);
        String message = restService.send(address, person);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam("name") final String name) {

        logger.info("Dummy: {}", name);

        final DummyModel model = new DummyModel();
        model.setDate(LocalDate.now());
        model.setName(name);

        repository.save(model);

        return ResponseEntity.ok("hello" + model);
    }
}