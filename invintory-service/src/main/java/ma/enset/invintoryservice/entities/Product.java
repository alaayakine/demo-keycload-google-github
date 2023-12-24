package ma.enset.invintoryservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Builder
@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantities;
}
