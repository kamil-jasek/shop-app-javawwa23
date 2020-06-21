package pl.sda.shopapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sda.shopapp.dto.*;
import pl.sda.shopapp.service.customer.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-20
 */
@RestController
@RequestMapping("/api/customers")
@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER_MANAGER')")
class CustomerRestController {

    private final CustomerService service;

    CustomerRestController(CustomerService service) {
        requireNonNulls(service);
        this.service = service;
    }

    @GetMapping
    List<CustomerQueryResultDto> list() {
        return service.findCustomer(new CustomerQueryDto());
    }

    @PostMapping
    ResponseEntity<CustomerIdDto> createCustomer(@RequestBody @Valid CreateCompanyDto dto) {
        var id = service.createCompany(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CustomerIdDto(id));
    }

    @PatchMapping(path = "/{customerId}/companyName")
    ResponseEntity<Void> changeCompanyName(@PathVariable UUID customerId,
                                           @RequestBody @Valid ChangeCompanyNameDto dto) {
        service.changeCompanyName(customerId, dto.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{customerId}/addresses")
    List<AddressDto> listAddresses(@PathVariable UUID customerId) {
        return service.listAddresses(customerId);
    }

    @PostMapping(path = "/{customerId}/addresses")
    ResponseEntity<Void> createAddress(@PathVariable UUID customerId,
                                       @RequestBody @Valid CreateGeocodingAddressDto dto) {
        service.createAddress(customerId, dto.getLatitude(), dto.getLongitude());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping(path = "/{customerId}/addresses/{addressId}")
    ResponseEntity<Void> deleteAddress(@PathVariable UUID customerId, @PathVariable UUID addressId) {
        service.deleteAddress(customerId, addressId);
        return ResponseEntity.ok().build();
    }
}
