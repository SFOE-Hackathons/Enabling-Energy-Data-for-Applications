package ch.opendata.hack.energy.json;

import ch.opendata.hack.energy.model.DatabaseObject;
import ch.opendata.hack.energy.model.DateValue;
import ch.opendata.hack.energy.model.DoubleValue;
import ch.opendata.hack.energy.model.IntegerValue;
import ch.opendata.hack.energy.model.StringValue;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Json object.
 */
public class JsonObject {

    private final Map<String, Object> attributes = new HashMap<>();

    private final String source;

    /**
     * Instantiates a new Json object.
     *
     * @param databaseObject the database object
     */
    public JsonObject(final DatabaseObject databaseObject) {

        this.source = databaseObject.getDatasource();

        for(DateValue dateValue : databaseObject.getDateValueMap()) {
            attributes.put(dateValue.getName(), dateValue.getValue());
        }

        for(StringValue stringValue : databaseObject.getStringValueMap()) {
            attributes.put(stringValue.getName(), stringValue.getValue());
        }

        for(DoubleValue doubleValue : databaseObject.getDoubleValueMap()) {
            attributes.put(doubleValue.getName(), doubleValue.getValue());
        }

        for(IntegerValue integerValue : databaseObject.getIntegerValueMap()) {
            attributes.put(integerValue.getName(), integerValue.getValue());
        }
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public String getSource() {
        return source;
    }
}
