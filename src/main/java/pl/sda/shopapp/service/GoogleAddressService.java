package pl.sda.shopapp.service;

import org.springframework.stereotype.Service;
import pl.sda.shopapp.dto.GoogleAddressDto;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@Service
class GoogleAddressService {

//    private GoogleApi api;

    public GoogleAddressDto findAddress(double latitude, double longitude) {
//        Place place = api.findPlace(latitude, longitude);
        return new GoogleAddressDto("Test", "01-500", "Wawa", "PL");
    }
}
