package entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class ProductEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String size;
    private double price;
    private Integer quantity;
    private String category;
    private String image;
}
