package com.neuedatraining.CreditCardApplication;

import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;
import com.neuedatraining.CreditCardApplication.exception.CardUserAlreadyFoundException;
import com.neuedatraining.CreditCardApplication.service.CardUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class CreditCardApplication {

	/*

	Swagger -> http://localhost:8080/swagger-ui.html
	Localhost ->http://localhost:8080/

	*/
	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(CreditCardApplication.class, args);
	}

}
