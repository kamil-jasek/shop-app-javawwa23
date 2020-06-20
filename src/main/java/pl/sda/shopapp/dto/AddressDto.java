package pl.sda.shopapp.dto;

import java.util.Objects;
import java.util.UUID;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
public final class AddressDto {
    private UUID id;
    private String street;
    private String zipCode;
    private String city;
    private String country;

    public AddressDto(UUID id, String street, String zipCode, String city, String country) {
        this.id = id;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return id.equals(that.id) &&
                street.equals(that.street) &&
                zipCode.equals(that.zipCode) &&
                city.equals(that.city) &&
                country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, zipCode, city, country);
    }
}
