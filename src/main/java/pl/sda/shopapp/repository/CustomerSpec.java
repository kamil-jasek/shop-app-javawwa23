package pl.sda.shopapp.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import pl.sda.shopapp.dto.CustomerQuery;
import pl.sda.shopapp.entity.Customer;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
public final class CustomerSpec {

    public static Specification<Customer> withQuery(CustomerQuery query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            // where 1=1
            var predicate = criteriaBuilder.conjunction();

            // where 1=1 and (name like :?)
            if (StringUtils.hasText(query.getName())) {
                var namePredicate = criteriaBuilder.like(root.get("name"), query.getName() + "%");
                predicate = criteriaBuilder.and(predicate, namePredicate);
            }

            // where 1=1 and (taxId = :?)
            // where 1=1 and (name like :?) and (taxId = :?)
            if (StringUtils.hasText(query.getTaxId())) {
                var taxIdPredicate = criteriaBuilder.equal(root.get("taxId"), query.getTaxId());
                predicate = criteriaBuilder.and(predicate, taxIdPredicate);
            }

            return predicate;
        };
    }
}
