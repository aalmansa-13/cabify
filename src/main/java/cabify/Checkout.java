package cabify;

import cabify.engine.ItemPricingEngine;
import cabify.model.order.OrderLine;

import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private Map<String, OrderLine> orderLines = new HashMap<>();
    private ItemPricingEngine itemPricingEngine = new ItemPricingEngine();

    public void scan(String productCode) {
        OrderLine line = new OrderLine(productCode, 1, null);
        addItemToOrder(productCode, line);
    }

    public Double total() {
        return orderLines.values()
                .stream()
                .map(itemPricingEngine::priceItem)
                .reduce(0d, (x, y) -> x + y);
    }

    private void addItemToOrder(String productCode, OrderLine line) {
        if (orderLines.containsKey(productCode)) {
            OrderLine orderLine = orderLines.get(productCode);
            orderLine.setQuantity(orderLine.getQuantity() + 1);
        } else {
            orderLines.put(productCode, line);
        }
    }

}
