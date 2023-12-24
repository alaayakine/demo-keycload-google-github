package com.example.thyemleaffront;

import com.example.thyemleaffront.entitis.Customer;
import com.example.thyemleaffront.repositoris.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ThyemleafFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThyemleafFrontApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("alaa").email("alaa@gmail.com").build());
            customerRepository.save(Customer.builder().name("sabile").email("sabile@gmail.com").build());
            customerRepository.save(Customer.builder().name("h2113").email("h2113@gmail.com").build());

        };
    }
}
