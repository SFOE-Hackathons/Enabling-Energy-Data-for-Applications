package ch.opendata.hack.energy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The type Integer value.
 */
@Entity
@Table(name = "IntegerValues")
public class IntegerValue {

    /**
     * Instantiates a new Integer value.
     *
     * @param databaseObject the database object
     * @param name the name
     * @param value the value
     */
    public IntegerValue(DatabaseObject databaseObject, String name, Integer value) {
        this.value = value;
        this.object = databaseObject;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "object_id")
    private DatabaseObject object;

    private Integer value;

    /**
     * Instantiates a new Integer value.
     */
    public IntegerValue() {

    }

    private String name;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets value.
     *
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}
