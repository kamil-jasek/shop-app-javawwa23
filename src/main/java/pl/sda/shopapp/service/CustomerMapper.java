package pl.sda.shopapp.service;

import org.springframework.stereotype.Component;
import pl.sda.shopapp.dto.CustomerQueryResultDto;
import pl.sda.shopapp.entity.Company;
import pl.sda.shopapp.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-07
 */
@Component
class CustomerMapper {

    public List<CustomerQueryResultDto> map(List<Customer> customers) {
        return customers.stream()
                .map(this::map)
                .collect(toList());
    }

    private CustomerQueryResultDto map(Customer customer) {
        return new CustomerQueryResultDto(
                customer.getId(),
                customer.getName(),
                customer.getTaxId(),
                customer instanceof Company
                        ? CustomerQueryResultDto.CustomerType.COMPANY
                        : CustomerQueryResultDto.CustomerType.PERSON
        );
    }
}
