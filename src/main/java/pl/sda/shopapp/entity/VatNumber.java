package pl.sda.shopapp.entity;

import pl.sda.shopapp.util.JpaOnly;
import pl.sda.shopapp.util.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
@Embeddable
public final class VatNumber {

    @Column(name = "vat_number")
    private String value;

    @JpaOnly
    private VatNumber() {}

    public VatNumber(String value) {
        requireNonNulls(value);
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (!value.matches("\\d{10}")) {
            throw new IllegalArgumentException("Vat number is invalid: " + value);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VatNumber vatNumber = (VatNumber) o;
        return value.equals(vatNumber.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
