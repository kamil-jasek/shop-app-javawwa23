package pl.sda.shopapp.service.customer;

import org.springframework.stereotype.Service;
import pl.sda.shopapp.dto.AddressDto;
import pl.sda.shopapp.dto.CreateCompanyDto;
import pl.sda.shopapp.dto.CustomerQueryDto;
import pl.sda.shopapp.dto.CustomerQueryResultDto;
import pl.sda.shopapp.entity.Address;
import pl.sda.shopapp.entity.Company;
import pl.sda.shopapp.entity.VatNumber;
import pl.sda.shopapp.repository.CustomerRepository;
import pl.sda.shopapp.repository.CustomerSpec;
import pl.sda.shopapp.service.geocoding.GeocodingService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static pl.sda.shopapp.util.Preconditions.requireNonNulls;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final GeocodingService addressService;

    public CustomerService(CustomerRepository repository,
                           CustomerMapper mapper,
                           GeocodingService addressService) {
        requireNonNulls(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.addressService = addressService;
    }

    @Transactional
    public UUID createCompany(CreateCompanyDto dto) {
        var company = new Company(new VatNumber(dto.getVatNumber()), dto.getName());
        repository.save(company);
        return company.getId();
    }

    public List<CustomerQueryResultDto> findCustomer(CustomerQueryDto query) {
        var customers = repository.findAll(CustomerSpec.withQuery(query));
        return mapper.map(customers);
    }

    @Transactional
    public void changeCompanyName(UUID customerId, String name) {
        repository.updateCompanyName(customerId, name);
    }

    @Transactional
    public void createAddress(UUID customerId, double latitude, double longitude) {
        var address = addressService.find(latitude, longitude);
        var customer = repository.getOne(customerId);
        customer.addAddress(new Address(
                address.getStreet(), address.getCity(), address.getZipCode(), address.getCountry()));
        repository.save(customer);
    }

    public List<AddressDto> listAddresses(UUID customerId) {
        var customer = repository.getOne(customerId);
        return mapper.mapAddresses(customer.getAddresses());
    }

    @Transactional
    public void deleteAddress(UUID customerId, UUID addressId) {
        var customer = repository.getOne(customerId);
        customer.removeAddress(addressId);
        repository.save(customer);
    }
}
