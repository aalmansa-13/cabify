package cabify.model.promotions;

import cabify.model.order.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

@AllArgsConstructor
public class TwoForOnePromotion extends BasePromotion {

    @Getter @Setter private String productCode;

    @Override
    public boolean qualifies(OrderLine orderLine) {
        return orderLine.getProductCode().equals(productCode) && orderLine.getQuantity() > 1;
    }

    @Override
    public void applyDiscount(OrderLine orderLine) {
        int discountedItems = orderLine.getQuantity() / 2;
        double unitPrice = orderLine.getItemPrice().getUnitPrice();
        BigDecimal unitPriceBD = new BigDecimal(unitPrice);
        BigDecimal amountBD = unitPriceBD.multiply(new BigDecimal(orderLine.getQuantity() - discountedItems));
        orderLine.getItemPrice().setAmount(amountBD.setScale(2, ROUND_HALF_UP).doubleValue());
        orderLine.getItemPrice().setUnitPrice(amountBD.divide(new BigDecimal(orderLine.getQuantity()), 2, ROUND_HALF_UP).doubleValue());
    }
}
