package org.johnny.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a person.
 *
 * Created by johnny on 03/08/2016.
 */
@Data
@AllArgsConstructor
public class Person {

    int id;
    String firstName;
    String lastName;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
