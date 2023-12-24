package ma.enset.invintoryservice.web;

import ma.enset.invintoryservice.entities.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ma.enset.invintoryservice.repository.ProductRepository;

import java.util.List;

@RestController
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/products")
    public List<Product> products(){
       return productRepository.findAll();

    }
}
