package entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class EmployeeEntity implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String contact;
    private String company;
    private String email;
    private String password;
}
