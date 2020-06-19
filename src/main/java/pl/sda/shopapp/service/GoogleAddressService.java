package pl.sda.shopapp.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;
import pl.sda.shopapp.dto.GoogleAddressDto;

import java.io.IOException;

import static com.google.maps.model.AddressComponentType.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@Service
class GoogleAddressService {

    private final GeoApiContext context;

    public static class GoogleAddressServiceException extends RuntimeException {
        GoogleAddressServiceException(String msg) {
            super(msg);
        }
    }

    GoogleAddressService(GeoApiContext context) {
        requireNonNulls(context);
        this.context = context;
    }

    GoogleAddressDto findAddress(double latitude, double longitude) {
        try {
            var results = GeocodingApi
                    .reverseGeocode(context, new LatLng(latitude, longitude))
                    .await();
            if (results.length == 0) {
                return null;
            }
            var result = results[0];
            var components = result.addressComponents;
            return new GoogleAddressDto(
                    getValue(components, ROUTE) + " " + getValue(components, STREET_NUMBER),
                    getValue(components, POSTAL_CODE),
                    getValue(components, LOCALITY),
                    getValue(components, COUNTRY)
            );

        } catch (ApiException | InterruptedException | IOException e) {
            throw new GoogleAddressServiceException("Service Failure: " + e.getMessage());
        }
    }

    private String getValue(AddressComponent[] components, AddressComponentType type) {
        return stream(components)
                .filter(component -> asList(component.types).contains(type))
                .map(component -> component.shortName)
                .findFirst()
                .orElse("UNKNOWN");
    }
}
