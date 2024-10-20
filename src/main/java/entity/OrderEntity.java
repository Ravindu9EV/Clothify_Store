package entity;

import dto.OrderDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderEntity implements SuperEntity {
    @Id
    private String id;
    private String userID;
    private String customerID;
    private LocalDate orderDate;
    private String paymentType;
    private List<OrderDetail> orderDetails;
}
