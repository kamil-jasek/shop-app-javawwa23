package pl.sda.shopapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopAppApplicationTests {

	@Autowired
	private BeanB beanB;

	@Test
	void contextLoads() {
		beanB.sayHello();
	}

}
