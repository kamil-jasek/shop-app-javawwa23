package pl.sda.shopapp.entity;

import pl.sda.shopapp.util.JpaOnly;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-21
 */
@Entity
@Table(name = "users")
public final class User {

    @Id
    private String username;

    private String password;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private List<Authority> authorities;

    @JpaOnly
    private User() {}

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void addAuthorities(List<Authority> authorities) {
        authorities.forEach(this::addAuthority);
    }

    public void addAuthority(Authority authority) {
        if (!authorities.contains(authority)) {
            authorities.add(authority);
        }
    }

    public List<Authority> getAuthorities() {
        return new ArrayList<>(authorities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
