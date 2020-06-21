package pl.sda.shopapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shopapp.dto.CreateCompanyDto;
import pl.sda.shopapp.dto.CustomerQueryDto;
import pl.sda.shopapp.dto.CustomerQueryResultDto;
import pl.sda.shopapp.entity.*;
import pl.sda.shopapp.repository.CustomerRepository;
import pl.sda.shopapp.service.customer.CustomerService;

import javax.transaction.Transactional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@SpringBootTest
@Transactional
final class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @Test
    void testCreateCompany() {
        // given
        var createDto = new CreateCompanyDto("Test S.A.", "0123456789");

        // when
        var id = service.createCompany(createDto);

        // then
        var companyOptional = repository.findById(id);
        assertTrue(companyOptional.isPresent());
    }

    @Test
    void testFindCustomer() {
        // given
        var company = new Company(new VatNumber("0123456789"), "Test SA");
        var person1 = new Person("Test", "Kowalski", "89302002003");
        var person2 = new Person("Adam", "Nowak", "89302002001");
        repository.saveAll(asList(company, person1, person2));
        repository.flush();

        var query = new CustomerQueryDto();
        query.setName("Test");

        // when
        var customers = service.findCustomer(query);

        // then
        assertEquals(2, customers.size());

        var dto1 = new CustomerQueryResultDto(
                company.getId(), company.getName(),
                company.getTaxId(), CustomerQueryResultDto.CustomerType.COMPANY);
        assertTrue(customers.contains(dto1));

        var dto2 = new CustomerQueryResultDto(
                person1.getId(), person1.getName(), person1.getTaxId(),
                CustomerQueryResultDto.CustomerType.PERSON);
        assertTrue(customers.contains(dto2));
    }

    @Test
    void testCreateAddressWithGeocoding() {
        // given
        var company = new Company(new VatNumber("0123456789"), "Test SA");
        repository.save(company);
        repository.flush();

        // when
        service.createAddress(company.getId(), 52.250714, 20.876190);

        // then
        var companyRead = repository.getOne(company.getId());
        var expectedAddress = new Address("Spychowska 2A", "Warszawa", "01-472", "PL");

        assertFalse(companyRead.getAddresses().isEmpty());
        assertTrue(companyRead.getAddresses().contains(expectedAddress));
    }
}
