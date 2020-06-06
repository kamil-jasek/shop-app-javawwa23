package pl.sda.shopapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shopapp.entity.Address;
import pl.sda.shopapp.entity.Company;
import pl.sda.shopapp.entity.Person;
import pl.sda.shopapp.entity.VatNumber;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
@SpringBootTest
final class CustomerTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testCreatePerson() {
        // given
        var person = new Person("Jan", "Kowalski", "89302002003");
        var address = new Address("test", "test", "01-500", "PL");
        person.addAddress(address);

        // when
        em.persist(person);
        em.flush();
        em.clear();
        var readPerson = em.find(Person.class, person.getId());

        // then
        assertEquals(readPerson, person);
        assertEquals(1, readPerson.getAddresses().size());
        assertEquals(readPerson.getAddresses().get(0), address);
    }

    @Test
    @Transactional
    void testCreateCompany() {
        // given
        var company = new Company(new VatNumber("0123456789"), "Test S.A.");
        var address = new Address("test", "test", "01-500", "PL");
        company.addAddress(address);

        // when
        em.persist(company);
        em.flush();
        em.clear();
        var readCompany = em.find(Company.class, company.getId());

        // then
        assertEquals(readCompany, company);
        assertEquals(1, company.getAddresses().size());
        assertEquals(company.getAddresses().get(0), address);
    }
}
