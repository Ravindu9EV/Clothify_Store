package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements User {
    private String id;
    private String name;
    private String contact;
    private String email;
    private String password;
}
