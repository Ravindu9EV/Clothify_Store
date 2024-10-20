package entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Supplier")
public class SupplierEntity implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String company;
    private String productID;
    private String email;
}