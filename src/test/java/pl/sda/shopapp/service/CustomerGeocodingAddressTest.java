package pl.sda.shopapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.sda.shopapp.dto.GeocodeAddressDto;
import pl.sda.shopapp.entity.Address;
import pl.sda.shopapp.entity.Company;
import pl.sda.shopapp.entity.VatNumber;
import pl.sda.shopapp.repository.CustomerRepository;
import pl.sda.shopapp.service.geocoding.GeocodingService;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.given;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-20
 */
@SpringBootTest
@Transactional
final class CustomerGeocodingAddressTest {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @MockBean
    private GeocodingService geocodingService;

    @Test
    void testCreateAddress() {
        // given
        var company = new Company(new VatNumber("0123456789"), "Test SA");
        repository.save(company);
        repository.flush();

        given(geocodingService.find(anyDouble(), anyDouble()))
                .willReturn(new GeocodeAddressDto("Spychowska 2A", "01-472", "Warszawa", "PL"));

        // when
        service.createAddress(company.getId(), 52.250714, 20.876190);

        // then
        var companyRead = repository.getOne(company.getId());
        var expectedAddress = new Address("Spychowska 2A", "Warszawa", "01-472", "PL");

        assertFalse(companyRead.getAddresses().isEmpty());
        assertTrue(companyRead.getAddresses().contains(expectedAddress));
    }
}
