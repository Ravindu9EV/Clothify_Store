package dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private String size;
    private double price;
    private Integer quantity;
    private String categoryID;
    private String image;


}
