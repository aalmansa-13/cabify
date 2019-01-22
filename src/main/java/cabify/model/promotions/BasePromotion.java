package cabify.model.promotions;

import cabify.model.order.OrderLine;


public abstract class BasePromotion implements Promotion {

    public abstract boolean qualifies(OrderLine orderLine);

    public abstract void applyDiscount(OrderLine orderLine);

    public final void apply(OrderLine orderLine) {
        if (qualifies(orderLine)) {
            applyDiscount(orderLine);
        }
    }
}
