package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private String orderID;
    private String productID;
    private Integer quantity;
    private double discount;
}
