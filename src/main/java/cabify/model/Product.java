package cabify.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String code;
    private String description;
    private double listPrice;
}
