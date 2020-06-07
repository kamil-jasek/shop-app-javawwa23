package pl.sda.shopapp.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.shopapp.entity.Address;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AddressTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void testCreateAddress() {
        var address = new Address("str", "Wawa", "01-300", "PL");
        em.persist(address);

        em.flush();
        em.clear();

        var readAddress = em.find(Address.class, address.getId());
        assertEquals(readAddress, address);
    }
}
