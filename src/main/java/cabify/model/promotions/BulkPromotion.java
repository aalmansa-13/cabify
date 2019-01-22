package cabify.model.promotions;

import cabify.model.order.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

@AllArgsConstructor
public class BulkPromotion extends BasePromotion {

    @Getter @Setter private String productCode;
    @Getter @Setter private int minQuantity;
    @Getter @Setter private double reducedPrice;

    @Override
    public boolean qualifies(OrderLine orderLine) {
        return orderLine.getProductCode().equals(productCode) && orderLine.getQuantity() >= minQuantity;
    }

    @Override
    public void applyDiscount(OrderLine orderLine) {
        orderLine.getItemPrice().setUnitPrice(reducedPrice);
        orderLine.getItemPrice()
                .setAmount(new BigDecimal(reducedPrice)
                        .multiply(new BigDecimal(orderLine.getQuantity()))
                        .setScale(2, ROUND_HALF_UP).doubleValue());
    }
}
