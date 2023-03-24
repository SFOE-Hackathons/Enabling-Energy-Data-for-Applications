package ch.opendata.hack.energy.sources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Raw data object.
 */
public class RawDataObject {

    private String sourceName;

    private Map<String, String> data = new HashMap<>();

    /**
     * Instantiates a new Raw data object.
     *
     * @param sourceName the source name
     */
    public RawDataObject(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * Gets attribute by name.
     *
     * @param name the name
     * @return the attribute by name
     */
    public String getAttributeByName(final String name) {
        return data.get(name);
    }

    /**
     * Sets attribute.
     *
     * @param name the name
     * @param value the value
     */
    public void setAttribute(final String name, final String value) {
        this.data.put(name, value);
    }

    /**
     * Gets source name.
     *
     * @return the source name
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Map<String, String> getData() {
        return Collections.unmodifiableMap(data);
    }
}
