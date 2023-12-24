package com.example.thyemleaffront.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantities;
}
