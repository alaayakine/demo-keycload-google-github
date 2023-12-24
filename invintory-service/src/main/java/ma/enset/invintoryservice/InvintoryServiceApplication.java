package ma.enset.invintoryservice;

import ma.enset.invintoryservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ma.enset.invintoryservice.repository.ProductRepository;


import java.util.UUID;

@SpringBootApplication
public class InvintoryServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(InvintoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
            return args -> {
                productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Computer")
                    .quantities(12)
                    .price(4300)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Phone")
                    .quantities(11)
                    .price(12000)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Printer")
                    .quantities(3)
                    .price(1200)
                    .build());
        };
    }
}
