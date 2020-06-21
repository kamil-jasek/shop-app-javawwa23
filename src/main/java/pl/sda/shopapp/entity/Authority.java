package pl.sda.shopapp.entity;

import pl.sda.shopapp.util.JpaOnly;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-21
 */
@Entity
@Table(name = "authorities")
public final class Authority {

    @Id
    private UUID id;

    private String username;

    private String authority;

    @JpaOnly
    private Authority() {
    }

    public Authority(String username, String authority) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return id.equals(authority1.id) &&
                authority.equals(authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }
}
