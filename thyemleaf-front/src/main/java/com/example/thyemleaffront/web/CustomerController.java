package com.example.thyemleaffront.web;

import com.example.thyemleaffront.entitis.Customer;
import com.example.thyemleaffront.model.Product;
import com.example.thyemleaffront.repositoris.CustomerRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Value("${inventory.service.base.uri}")
    private String inventoryServiceBaseUri;
    private  CustomerRepository customerRepository;
    private ClientRegistrationRepository clientRegistrationRepository;


    @Autowired
    public CustomerController(CustomerRepository customerRepository, ClientRegistrationRepository clientRegistrationRepository) {
        this.customerRepository = customerRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
     public List<Customer> customers(){
        return customerRepository.findAll();
     }


    @GetMapping("/customers")
    private String customer(Model model){
        List<Customer> customers=this.customers();
        model.addAttribute("customers",customers);
        return "customers";
    }
    @GetMapping("/products")
    private String products(Model model){
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            DefaultOidcUser oidcUser = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
            String jwtTokenValue = oidcUser.getIdToken().getTokenValue();
            RestClient restClient = RestClient.create(inventoryServiceBaseUri);
            List<Product> products = restClient.get()
                    .uri("http://localhost:4444/products")
                    .headers(httpHeaders -> httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenValue))
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            model.addAttribute("products", products);
            System.out.println(products.size());
            return "products";



    }
    @GetMapping
    private String index(){
        return "index";
    }
    @GetMapping("/notAutorized")
    private String notAutorized(){
        return "notAutorized";
    }
    @GetMapping("/auth")
    @ResponseBody
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @GetMapping("/oauth2Login")
    public String oauth2Login(Model model) {
        String authorizationRequestBaseUri = "oauth2/authorization";
        Map<String, String> oauth2AuthenticationUrls = new HashMap();
        Iterable<ClientRegistration> clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;;
        clientRegistrations.forEach(registration ->{
            oauth2AuthenticationUrls.put(registration.getClientName(),
                    authorizationRequestBaseUri + "/" + registration.getRegistrationId());
        });
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "oauth2Login";

    }

}
