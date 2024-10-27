package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Admin")
public class AdminEntity implements SuperEntity {

        @Id
        private String id;
        private String name;
        private String contact;
        private String email;
        private String password;

}
