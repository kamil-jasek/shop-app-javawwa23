package pl.sda.shopapp.dto;

import java.util.UUID;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-21
 */
public final class CustomerIdDto {

    private UUID id;

    public CustomerIdDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
