package pl.sda.shopapp.dto;

import java.util.Objects;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
public final class GeocodeAddressDto {
    private String street;
    private String zipCode;
    private String city;
    private String country;

    public GeocodeAddressDto(String street, String zipCode, String city, String country) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
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
        GeocodeAddressDto that = (GeocodeAddressDto) o;
        return Objects.equals(street, that.street) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, city, country);
    }
}
