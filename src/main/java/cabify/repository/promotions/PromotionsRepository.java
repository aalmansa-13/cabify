package cabify.repository.promotions;

import cabify.model.promotions.BulkPromotion;
import cabify.model.promotions.Promotion;
import cabify.model.promotions.TwoForOnePromotion;

import java.util.Arrays;
import java.util.List;

public class PromotionsRepository {

    public static Promotion VOUCHER2x1 = new TwoForOnePromotion("VOUCHER");
    public static Promotion TSHIRTBULK = new BulkPromotion("TSHIRT", 3, 19);


    public List<Promotion> getItemLevelPromotions() {
        return Arrays.asList(VOUCHER2x1, TSHIRTBULK);
    }
}
