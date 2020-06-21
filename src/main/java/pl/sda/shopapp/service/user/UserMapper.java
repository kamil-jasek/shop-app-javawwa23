package pl.sda.shopapp.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.shopapp.dto.CreateUserDto;
import pl.sda.shopapp.entity.Authority;
import pl.sda.shopapp.entity.User;

import static java.util.stream.Collectors.toList;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-21
 */
@Component
class UserMapper {

    private final PasswordEncoder encoder;

    UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    User map(CreateUserDto dto) {
        var user = new User(dto.getUsername(), encoder.encode(dto.getPassword()), true);
        user.addAuthorities(dto.getRoles()
                .stream()
                .map(role -> new Authority(dto.getUsername(), "ROLE_" + role))
                .collect(toList()));
        return user;
    }
}
