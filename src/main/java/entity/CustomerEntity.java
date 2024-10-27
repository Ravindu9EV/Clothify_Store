package entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Customer")
public class CustomerEntity implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String email;
    private String contact;
}
