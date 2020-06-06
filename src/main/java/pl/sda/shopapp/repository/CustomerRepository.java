package pl.sda.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shopapp.entity.Customer;
import pl.sda.shopapp.entity.Person;

import java.util.List;
import java.util.UUID;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // select * from Customer c where c.name = ?1
    List<Customer> findByName(String name);

    // select * from Customer c where c.name like = ?1%
    List<Customer> findByNameStartingWith(String name);
}
