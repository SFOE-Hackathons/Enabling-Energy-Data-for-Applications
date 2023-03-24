package ch.opendata.hack.energy.controller;

import ch.opendata.hack.energy.json.SearchResult;
import ch.opendata.hack.energy.model.DatabaseObject;
import ch.opendata.hack.energy.repository.ObjectRepository;
import ch.opendata.hack.energy.json.LocalDateAdapter;
import ch.opendata.hack.energy.sources.Sources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.GsonBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * The type Rest controller.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    private final ObjectRepository repository;
    private final Sources sources;

    /**
     * Instantiates a new Rest controller.
     *
     * @param repository the repository
     * @param sources the sources
     */
    public RestController(@Autowired ObjectRepository repository, @Autowired Sources sources) {
        this.repository = repository;
        this.sources = sources;
    }

    /**
     * Gets .
     *
     * @param filter the filter
     * @return the
     * @throws JsonProcessingException the json processing exception
     */
    //TODO: Remove code duplicates in methods and improve filter logic
    @GetMapping("/getall")
    public ResponseEntity<String> getall(@RequestParam("filter") Optional<String> filter) throws JsonProcessingException {

        logger.info("started with parameter: {1}", filter);

        final List<DatabaseObject> objects = repository.findAll();

        List<DatabaseObject> result = Collections.emptyList();
        objects.forEach(object -> object.initDataypes());

        if(filter.isPresent()) {
            final String[] params = filter.get().split(";");

            for(String param : params) {

                if(param.startsWith("hasAttribute:")) {

                    final String criterias = param.substring(param.indexOf(":") + 1);
                    final String[] sepCriterias = criterias.split(",");

                    result = objects.stream().filter(object -> {

                        boolean found = true;

                        for(String val : sepCriterias) {
                            if(!object.hasAttribute(val)) {
                                found &= Boolean.FALSE;
                            }
                        }
                        return found;
                    }).collect(Collectors.toList());
                }
            }
        }

        final SearchResult searchResult = SearchResult.create(result);
        final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).setPrettyPrinting().create();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(gson.toJson(searchResult));
    }

    /**
     * Gets from source.
     *
     * @param name the name
     * @return the from source
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("/getFromSource")
    public ResponseEntity<String> getFromSource(@RequestParam("name") String name) throws JsonProcessingException {

        logger.info("started with parameter name: {1}", name);

        final DatabaseObject objectExample = new DatabaseObject();
        objectExample.setDatasource(name);
        final Example<DatabaseObject> example = Example.of(objectExample);

        final List<DatabaseObject> objects = repository.findAll(example);
        objects.forEach(DatabaseObject::initDataypes);

        final SearchResult searchResult = SearchResult.create(objects);
        final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).setPrettyPrinting().create();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(gson.toJson(searchResult));
    }

    /**
     * Gets all registered data sources.
     *
     * @return the all registered data sources
     */
    @GetMapping("/getAllRegisteredDataSources")
    public ResponseEntity<String> getAllRegisteredDataSources() {

        logger.info("started");

        final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).setPrettyPrinting().create();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(gson.toJson(this.sources.getSources().stream().map(source -> source.getName()).collect(Collectors.toList())));
    }
}