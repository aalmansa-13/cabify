package cabify.repository.catalog;

import cabify.model.Product;
import cabify.repository.catalog.exception.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ProductRepository {

    public static Product VOUCHER = new Product("VOUCHER", "Cabify Voucher", 5d);
    public static Product TSHIRT  = new Product("TSHIRT", "Cabify T-Shirt", 20d);
    public static Product MUG = new Product("MUG", "Cafify Coffee Mug", 7.50d);

    private static Map<String, Product> allProducts = new HashMap<>();

    static {
        allProducts.put("VOUCHER", VOUCHER);
        allProducts.put("TSHIRT", TSHIRT);
        allProducts.put("MUG", MUG);
    }

    public Product find(String productCode) {
        if (allProducts.containsKey(productCode)) {
            return allProducts.get(productCode);
        }
        throw new ProductNotFoundException(format("Product with code %s not found", productCode));
    }
}
