package com.bank.card;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(

		info = @Info(
				title="Cards MicroService REST API Documentation"
				,description = "Easy Bank Accounts Microservice REST API Documentation"
				,version = "V1",

				contact = @Contact(
						name="Arpit Pathak",
						email="pathakarpit9454@gmail.com"),

				license = @License(
						name = "Arpit@License"

				)
		),

		externalDocs = @ExternalDocumentation(
				description = "Easy Bank Accounts Microservice Rest Api Documentation"
		)
)
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
