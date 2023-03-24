package ch.opendata.hack.energy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * The type Date value.
 */
@Entity
@Table(name = "DateValues")
public class DateValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name="object_id")
    private DatabaseObject object;
    private String name;
    private LocalDate value;

    /**
     * Instantiates a new Date value.
     *
     * @param object the object
     * @param name the name
     * @param value the value
     */
    public DateValue(DatabaseObject object, String name, LocalDate value) {
        this.object = object;
        this.name = name;
        this.value = value;
    }

    /**
     * Instantiates a new Date value.
     */
    public DateValue() {

    }

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
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
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
     * Gets value.
     *
     * @return the value
     */
    public LocalDate getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(LocalDate value) {
        this.value = value;
    }
}
