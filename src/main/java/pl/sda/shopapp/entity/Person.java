package pl.sda.shopapp.entity;

import pl.sda.shopapp.util.JpaOnly;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
@Entity
@DiscriminatorValue("person")
public final class Person extends Customer {

    private String firstName;
    private String lastName;

    @JpaOnly
    private Person() {
    }

    public Person(String firstName, String lastName, String taxId) {
        super(firstName + " " + lastName, taxId);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
