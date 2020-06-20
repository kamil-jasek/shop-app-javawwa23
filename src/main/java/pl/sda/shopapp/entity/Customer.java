package pl.sda.shopapp.entity;

import pl.sda.shopapp.util.JpaOnly;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
public abstract class Customer {

    @Id
    private UUID id;
    private String name;
    private String taxId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    @JpaOnly
    protected Customer() {
    }

    protected Customer(String name, String taxId) {
        requireNonNulls(name, taxId);
        this.id = UUID.randomUUID();
        this.name = name;
        this.taxId = taxId;
        this.addresses = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTaxId() {
        return taxId;
    }

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }

    public void removeAddress(UUID addressId) {
        addresses.stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .ifPresent(address -> addresses.remove(address));
    }

    public List<Address> getAddresses() {
        return new ArrayList<>(addresses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                name.equals(customer.name) &&
                taxId.equals(customer.taxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, taxId);
    }
}
