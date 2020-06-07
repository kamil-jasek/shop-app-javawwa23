package pl.sda.shopapp.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shopapp.entity.Address;
import pl.sda.shopapp.entity.Company;
import pl.sda.shopapp.entity.VatNumber;
import pl.sda.shopapp.repository.CustomerRepository;
import pl.sda.shopapp.repository.CustomerRepository.CustomerByCity;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@SpringBootTest
@Transactional
final class CustomerNativeQueryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void testGroupByCity() {
        // given
        var company1 = new Company(new VatNumber("0123456789"), "Test S.A.");
        company1.addAddress(new Address("test", "Wawa", "01-500", "PL"));

        var company2 = new Company(new VatNumber("0123456789"), "Test S.A.");
        company2.addAddress(new Address("test", "Wawa", "01-500", "PL"));

        var company3 = new Company(new VatNumber("0123456789"), "Test S.A.");
        company2.addAddress(new Address("test", "Krakow", "01-500", "PL"));

        repository.saveAll(asList(company1, company2, company3));

        // when
        List<Object[]> grouped = repository.countCustomersByCity();

        // then
        assertEquals(2, grouped.size());

        Object[] row1 = grouped.get(0);
        assertEquals("Krakow", row1[0]);
        assertEquals(BigInteger.valueOf(1), row1[1]);

        Object[] row2 = grouped.get(1);
        assertEquals("Wawa", row2[0]);
        assertEquals(BigInteger.valueOf(2), row2[1]);
    }

    @Test
    void testGroupByCityWithInterface() {
        // given
        var company1 = new Company(new VatNumber("0123456789"), "Test S.A.");
        company1.addAddress(new Address("test", "Wawa", "01-500", "PL"));

        var company2 = new Company(new VatNumber("0123456789"), "Test S.A.");
        company2.addAddress(new Address("test", "Wawa", "01-500", "PL"));

        var company3 = new Company(new VatNumber("0123456789"), "Test S.A.");
        company2.addAddress(new Address("test", "Krakow", "01-500", "PL"));

        repository.saveAll(asList(company1, company2, company3));

        // when
        List<CustomerByCity> grouped = repository.groupCustomersByCity();

        // then
        assertEquals(2, grouped.size());

        var row1 = grouped.get(0);
        assertEquals("Krakow", row1.getCity());
        assertEquals(1, row1.getCount());

        var row2 = grouped.get(1);
        assertEquals("Wawa", row2.getCity());
        assertEquals(2, row2.getCount());
    }
}
