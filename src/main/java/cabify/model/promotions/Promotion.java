package cabify.model.promotions;

import cabify.model.order.OrderLine;

public interface Promotion {
    boolean qualifies(OrderLine orderLine);

    void apply(OrderLine orderLine);
}
