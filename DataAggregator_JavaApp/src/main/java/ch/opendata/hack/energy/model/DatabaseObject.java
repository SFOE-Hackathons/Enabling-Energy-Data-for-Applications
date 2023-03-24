package ch.opendata.hack.energy.model;

import ch.opendata.hack.energy.json.Datatype;
import ch.opendata.hack.energy.sources.ParseUtils;
import ch.opendata.hack.energy.sources.RawDataObject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Database object.
 */
@Entity
@Table(name = "Objects")
public class DatabaseObject {

    @Transient
    private final Map<String, Datatype> dataTypes = new HashMap<>();

    @OneToMany(targetEntity=IntegerValue.class, mappedBy="object",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<IntegerValue> integerValueMap = new ArrayList<>();

    @OneToMany(targetEntity=DoubleValue.class, mappedBy="object",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<DoubleValue> doubleValueMap = new ArrayList<>();

    @OneToMany(targetEntity=StringValue.class, mappedBy="object",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<StringValue> stringValueMap = new ArrayList<>();

    @OneToMany(targetEntity=DateValue.class, mappedBy="object",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<DateValue> dateValueMap = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String datasource;

    /**
     * Instantiates a new Database object.
     */
    public DatabaseObject() {

    }

    /**
     * Init dataypes.
     *
     * @param rawDataObject the raw data object
     */
    public void initDataypes(RawDataObject rawDataObject) {
        dataTypes.putAll(ParseUtils.parseDataTypes(rawDataObject));
    }

    /**
     * Init dataypes.
     */
    public void initDataypes() {

        for(IntegerValue value : integerValueMap ) {
            this.dataTypes.put(value.getName(), Datatype.INTEGER);
        }

        for(DoubleValue value : doubleValueMap ) {
            this.dataTypes.put(value.getName(), Datatype.DOUBLE);
        }

        for(StringValue value : stringValueMap ) {
            this.dataTypes.put(value.getName(), Datatype.STRING);
        }

        for(DateValue value : dateValueMap ) {
            this.dataTypes.put(value.getName(), Datatype.DATE);
        }

    }

    /**
     * Read values from raw object.
     *
     * @param rawDataObject the raw data object
     */
    public void readValuesFromRawObject(RawDataObject rawDataObject) {
        this.initDataypes(rawDataObject);
        Map<String, Object> values = ParseUtils.parseRawDataObject(rawDataObject, dataTypes);

        for(Entry<String, Datatype> entry : dataTypes.entrySet()) {

            final Datatype datatype = entry.getValue();
            final String key = entry.getKey();

            switch (datatype) {
                case INTEGER -> integerValueMap.add(new IntegerValue(this, key, (Integer) values.get(key)));
                case DATE -> dateValueMap.add(new DateValue(this, key, (LocalDate) values.get(key)));
                case DOUBLE -> doubleValueMap.add(new DoubleValue(this, key, (Double) values.get(key)));
                case STRING -> stringValueMap.add(new StringValue(this, key,(String) values.get(key)));
            }
        }
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets integer value map.
     *
     * @return the integer value map
     */
    public List<IntegerValue> getIntegerValueMap() {
        return Collections.unmodifiableList(integerValueMap);
    }

    /**
     * Gets double value map.
     *
     * @return the double value map
     */
    public List<DoubleValue> getDoubleValueMap() {
        return Collections.unmodifiableList(doubleValueMap);
    }

    /**
     * Gets string value map.
     *
     * @return the string value map
     */
    public List<StringValue> getStringValueMap() {
        return Collections.unmodifiableList(stringValueMap);
    }

    /**
     * Gets date value map.
     *
     * @return the date value map
     */
    public List<DateValue> getDateValueMap() {
        return Collections.unmodifiableList(dateValueMap);
    }

    /**
     * Has attribute boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean hasAttribute(String name) {
        return this.dataTypes.containsKey(name);
    }

    /**
     * Gets datasource.
     *
     * @return the datasource
     */
    public String getDatasource() {
        return datasource;
    }

    /**
     * Sets datasource.
     *
     * @param datasource the datasource
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
}
