package ch.opendata.hack.energy.sources.rest;

import ch.opendata.hack.energy.sources.RawDataObject;
import ch.opendata.hack.energy.sources.Source;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Rest source.
 */
public class RestSource extends Source {

    private final String url;

    @Transient
    private final RestReaderService restReaderService;

    /**
     * Instantiates a new Rest source.
     *
     * @param sourceName the source name
     * @param url the url
     * @param restReaderService the rest reader service
     */
    public RestSource(String sourceName, String url, RestReaderService restReaderService) {
        this.setName(sourceName);
        this.url = url;
        this.restReaderService = restReaderService;
    }

    @Override
    public List<RawDataObject> readSource() {

        final List<RawDataObject> ret = new ArrayList<>();
        final String string = restReaderService.send(url);
        final Gson gson = new Gson();
        final List<LinkedTreeMap<String, Object>> result = gson.fromJson(string, new TypeToken<List<LinkedTreeMap<String, Object>>>() {}.getType());

        for (Map<String, Object> map : result) {

            final RawDataObject rawDataObject = new RawDataObject(this.getName());

            for (Entry<String, Object> entry : map.entrySet()) {
                rawDataObject.setAttribute(entry.getKey(), entry.getValue().toString());
            }
            ret.add(rawDataObject);
        }

        return ret;
    }
}
