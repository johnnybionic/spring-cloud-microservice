package org.johnny.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents a person.
 * <p>
 * Created by johnny on 03/08/2016.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    public static final String FULL_NAME_FORMAT = "%s %s";

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Helper method to get the full name.
     *
     * @return the formatted full name
     */
    public String getFullName() {
        return String.format(FULL_NAME_FORMAT, firstName, lastName);
    }
}
