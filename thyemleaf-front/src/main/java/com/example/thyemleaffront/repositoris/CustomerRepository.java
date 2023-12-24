package com.example.thyemleaffront.repositoris;

import com.example.thyemleaffront.entitis.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
