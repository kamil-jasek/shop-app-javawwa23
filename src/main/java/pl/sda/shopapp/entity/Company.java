package pl.sda.shopapp.entity;

import pl.sda.shopapp.util.JpaOnly;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Objects;

import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
@Entity
@DiscriminatorValue("company")
public final class Company extends Customer {

    @Embedded
    private VatNumber vatNumber;

    @JpaOnly
    private Company() {
    }

    public Company(VatNumber vatNumber, String name) {
        super(name, vatNumber.getValue());
        requireNonNulls(vatNumber);
        this.vatNumber = vatNumber;
    }

    public VatNumber getVatNumber() {
        return vatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return vatNumber.equals(company.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vatNumber);
    }
}
