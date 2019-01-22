package cabify.engine;

import cabify.repository.catalog.ProductRepository;
import cabify.model.Product;
import cabify.model.order.OrderLine;
import cabify.model.price.Price;
import cabify.repository.promotions.PromotionsRepository;
import lombok.Getter;
import lombok.Setter;

public class ItemPricingEngine {
    @Getter @Setter private ProductRepository productRepository = new ProductRepository();
    @Getter @Setter private PromotionsRepository promotionsRepository = new PromotionsRepository();

    public double priceItem(OrderLine orderLine) {
        Product product = productRepository.find(orderLine.getProductCode());
        orderLine.setItemPrice(getListPrice(orderLine, product));
        promotionsRepository.getItemLevelPromotions().forEach(promotion -> promotion.apply(orderLine));
        return orderLine.getItemPrice().getAmount();
    }

    private Price getListPrice(OrderLine orderLine, Product product) {
        Price itemPrice = new Price();
        itemPrice.setAmount(product.getListPrice() * orderLine.getQuantity());
        itemPrice.setUnitPrice(product.getListPrice());
        return itemPrice;
    }
}
