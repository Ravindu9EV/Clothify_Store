package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDetailKey implements Serializable {
    @Column(name="OrderID")
    String orderID;

    @Column(name = "productID")
    String productID;
}
