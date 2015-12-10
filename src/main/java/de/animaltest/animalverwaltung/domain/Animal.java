package de.animaltest.animalverwaltung.domain;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
@XmlRootElement
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Animal implements Serializable {
    public static final String NAME_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
    private static final long serialVersionUID = -6237755745506804405L;
    private static final int NAME_LENGTH_MIN = 2;
    private static final int NAME_LENGTH_MAX = 32;
    private static final int DESC_LENGTH_MIN = 2;
    private static final int DESC_LENGTH_MAX = 200;


    private Long id;

    @NotNull
    @Size(min = NAME_LENGTH_MIN, max = NAME_LENGTH_MAX, message = "{animal.name.size}")
    @Pattern(regexp = NAME_PATTERN, message = "{animal.name.pattern}")
    private String name;

    @Size(min = DESC_LENGTH_MIN, max = DESC_LENGTH_MAX, message = "{animal.name.size}")
    private String description;

    @DefaultValue("false")
    @NotNull
    private boolean vermittelt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVermittelt() {
        return vermittelt;
    }

    public void setVermittelt(boolean vermittelt) {
        this.vermittelt = vermittelt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (isVermittelt() != animal.isVermittelt()) return false;
        if (!getName().equals(animal.getName())) return false;
        return getDescription().equals(animal.getDescription());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (isVermittelt() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vermittelt=" + vermittelt +
                '}';
    }
}
