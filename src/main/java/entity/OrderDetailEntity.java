package entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "OrderDetail")
public class OrderDetailEntity implements SuperEntity {

    @EmbeddedId
    OrderDetailKey id;

//    @ManyToOne
//    @MapsId("OrderID")
//    @JoinColumn(name="OrderId")
//    OrderEntity order;
//    @ManyToOne
//    @MapsId("ProductID")
//    @JoinColumn(name = "id")
//    ProductEntity product;
   @Column(name="OrderID",insertable = false ,updatable  = false)
    private String orderID;
   @Column(name="ProductID",insertable = false ,updatable  = false)
    private String productID;
    private Integer quantity;
    private double discount;

}
