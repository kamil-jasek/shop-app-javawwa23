package pl.sda.shopapp.dto;

import java.util.Objects;
import java.util.UUID;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
public final class CustomerQueryResultDto {

    public enum CustomerType {
        COMPANY, PERSON;
    }

    private UUID customerId;
    private String name;
    private String taxId;
    private CustomerType type;

    public CustomerQueryResultDto(UUID customerId, String name, String taxId, CustomerType type) {
        this.customerId = customerId;
        this.name = name;
        this.taxId = taxId;
        this.type = type;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getTaxId() {
        return taxId;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerQueryResultDto that = (CustomerQueryResultDto) o;
        return customerId.equals(that.customerId) &&
                name.equals(that.name) &&
                taxId.equals(that.taxId) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, taxId, type);
    }
}
