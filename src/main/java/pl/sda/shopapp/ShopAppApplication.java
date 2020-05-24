package pl.sda.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopAppApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(ShopAppApplication.class, args);
		var beanA = context.getBean(BeanA.class);
		beanA.sayHello();
	}

}
