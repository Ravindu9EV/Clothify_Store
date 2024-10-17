package dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String id;
    private String userID;
    private String customerID;
    private LocalDate orderDate;
    private String paymentType;
    private List<OrderDetail> orderDetails;
}
