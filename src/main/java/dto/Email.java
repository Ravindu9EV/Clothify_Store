package dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {
    private String title;
    private String senderEmail;
    private String reciverEmail;
    private LocalDate date;
    private String time;
    private String description;

}
