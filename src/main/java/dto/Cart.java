package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private String productID;
    private Double productPrice;
    private Integer productQuantity;
    private Double ProductDiscount;
    private Double productTotalPrice;
}
