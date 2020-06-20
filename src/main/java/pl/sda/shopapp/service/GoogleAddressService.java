package pl.sda.shopapp.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;
import pl.sda.shopapp.dto.GoogleAddressDto;

import java.io.IOException;
import java.util.List;

import static com.google.maps.model.AddressComponentType.*;
import static java.util.Arrays.asList;
import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@Service
class GoogleAddressService {

    public static class GoogleAddressServiceException extends RuntimeException {
        GoogleAddressServiceException(String msg) {
            super(msg);
        }
    }

    private final GeoApiContext context;

    GoogleAddressService(GeoApiContext context) {
        requireNonNulls(context);
        this.context = context;
    }

    public GoogleAddressDto findAddress(double latitude, double longitude) {
        try {
            var results = GeocodingApi
                    .reverseGeocode(context, new LatLng(latitude, longitude))
                    .await();

            if (results.length > 0) {
                return extractAddress(results[0]);
            } else {
                throw new GoogleAddressServiceException("Cannot find address");
            }
        } catch (ApiException | InterruptedException | IOException e) {
            throw new GoogleAddressServiceException("Google service failed: " + e.getMessage());
        }
    }

    private GoogleAddressDto extractAddress(GeocodingResult result) {
        var streetNumber = "UKNOWN";
        var street = "UNKNOWN";
        var city = "UNKNOWN";
        var zipCode = "UNKNOWN";
        var country = "UNKNOWN";

        for (AddressComponent component : result.addressComponents) {
            var types = asList(component.types);
            if (types.contains(STREET_NUMBER)) {
                streetNumber = component.shortName;
            } else if (types.contains(ROUTE)) {
                street = component.shortName;
            } else if (types.contains(LOCALITY)) {
                city = component.shortName;
            } else if (types.contains(POSTAL_CODE)) {
                zipCode = component.shortName;
            } else if (types.contains(COUNTRY)) {
                country = component.shortName;
            }
        }

        return new GoogleAddressDto(street + " " + streetNumber, zipCode, city, country);
    }
}
