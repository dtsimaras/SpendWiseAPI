package com.tsimo.spendwise;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition( info = @Info (title = "SpendWiseAPI", description = "Introducing SpendWiseAPI: Streamline expense" +
		" and income management effortlessly. Track, categorize, and analyze financial data with ease." +
		" Simplify budgeting and gain valuable insights for informed decision-making. Empower your financial" +
		" journey with our robust, user-friendly API.",
		version = "0.1")
)
public class SpendwiseApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpendwiseApplication.class, args);
	}
}