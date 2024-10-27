package entity;

import dto.OrderDetail;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Orders")
public class OrderEntity implements SuperEntity {
    @Id
    private String id;
    private String userID;
    private String customerID;
    private LocalDate orderDate;
    private String paymentType;
    @OneToMany
    private List<OrderDetailEntity> orderDetails;
}
