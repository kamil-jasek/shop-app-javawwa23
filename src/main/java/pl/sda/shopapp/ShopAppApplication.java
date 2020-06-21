package pl.sda.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ShopAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopAppApplication.class, args);
	}

}
