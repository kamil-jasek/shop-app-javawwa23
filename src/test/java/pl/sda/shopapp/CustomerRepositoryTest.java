package pl.sda.shopapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import pl.sda.shopapp.entity.*;
import pl.sda.shopapp.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
@SpringBootTest
final class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void testFindById() {
        // given
        var person = new Person("Jan", "Kowalski", "89302002003");
        repository.saveAndFlush(person);

        // when
        Optional<Customer> readPerson = repository.findById(person.getId());
        // then
        assertTrue(readPerson.isPresent());
        assertEquals(person, readPerson.get());

        // when
        readPerson = repository.findById(UUID.randomUUID());
        // then
        assertFalse(readPerson.isPresent());
    }

    @Test
    void testCreateAndFind() {
        // given
        var person = new Person("Jan", "Kowalski", "89302002003");
        var address = new Address("test", "test", "01-500", "PL");
        person.addAddress(address);

        var company = new Company(new VatNumber("0123456789"), "Test S.A.");
        var companyAddress = new Address("test", "test", "01-500", "PL");
        company.addAddress(companyAddress);

        // when
        repository.saveAll(Arrays.asList(person, company));
        repository.flush();
//        repository.save(person);
//        repository.save(company);

        // then
        var customers = repository.findAll();
        assertTrue(customers.contains(person));
        assertTrue(customers.contains(company));

        var sortedCustomers = repository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        assertEquals(company, sortedCustomers.get(0));
        assertEquals(person, sortedCustomers.get(1));
    }

    @Test
    void testFindByName() {
        // given
        var company = new Company(new VatNumber("0123456789"), "Test S.A.");
        var companyAddress = new Address("test", "test", "01-500", "PL");
        company.addAddress(companyAddress);
        repository.save(company);

        // when
        var customers = repository.findByName("Test S.A.");

        // then
        assertFalse(customers.isEmpty());
        assertEquals("Test S.A.", customers.get(0).getName());
    }

    @Test
    void testFindByNameStartingWith() {
        // given
        var company1 = new Company(new VatNumber("0123456789"), "Test S.A.");
        var company2 = new Company(new VatNumber("0123456789"), "Testuj S.A.");
        var person = new Person("Jan", "Kowalski", "89302002003");
        repository.saveAll(Arrays.asList(company1, company2, person));

        // when
        var customers = repository.findByNameStartingWith("Test");

        // then
        assertEquals(2, customers.size());
    }
}
