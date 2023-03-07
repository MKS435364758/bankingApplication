package com.bank.webAppliaction;

import com.bank.webAppliaction.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class WebApplication {// implements CommandLineRunner {

	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		Customer[] customers = new Customer[]{
//				new Customer("Murali",new BigDecimal(100.00)),
//				new Customer("krishna",new BigDecimal(100.00)),
//				new Customer("Anna",new BigDecimal(100.00)),
//				new Customer("Hunter",new BigDecimal(100.00)),
//		};
//
//		Arrays.stream(customers).forEach(customerRepository::save);
//
//	}
}
