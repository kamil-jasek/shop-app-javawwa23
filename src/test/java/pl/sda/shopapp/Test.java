package pl.sda.shopapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-05-24
 */
final class Test {

    @org.junit.jupiter.api.Test
    void test() {
        assertEquals(1, 1);

        var b = new BeanB(new BeanA(), new BeanC());
        b.sayHello();
    }
}
