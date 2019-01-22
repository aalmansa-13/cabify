package cabify.model.order;

import cabify.model.price.Price;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLine {
    private String productCode;
    private int quantity;
    private Price itemPrice;
}
