package pl.sda.shopapp.service.geocoding;

import pl.sda.shopapp.dto.GeocodeAddressDto;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-20
 */
public interface GeocodingService {

    GeocodeAddressDto find(double latitude, double longitude);
}
