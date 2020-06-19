package pl.sda.shopapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shopapp.dto.GoogleAddressDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-16
 */
@SpringBootTest
class GoogleAddressServiceTest {

    @Autowired
    private GoogleAddressService service;

    @Test
    void testFindAddress() {
        // given
        double latitude = 52.250440;
        double longitude = 20.877409;

        // when
        var address = service.findAddress(latitude, longitude);

        // then
        assertEquals(new GoogleAddressDto("Spychowska 17", "01-472", "Warszawa", "PL"), address);
    }
}
