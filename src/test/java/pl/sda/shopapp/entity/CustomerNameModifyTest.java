package pl.sda.shopapp.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shopapp.entity.Company;
import pl.sda.shopapp.entity.Customer;
import pl.sda.shopapp.entity.Person;
import pl.sda.shopapp.entity.VatNumber;
import pl.sda.shopapp.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@SpringBootTest
@Transactional
final class CustomerNameModifyTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void testModifyCompanyName() {
        // given
        var company = new Company(new VatNumber("0123456789"), "CHANGE ME");
        repository.saveAndFlush(company);
        em.clear();

        // when
        int updated = repository.updateCompanyName(company.getId(), "TEST S.A.");

        // then
        assertEquals(1, updated);
        var customer = repository.findById(company.getId()).get();
        assertEquals("TEST S.A.", customer.getName());
    }
}
