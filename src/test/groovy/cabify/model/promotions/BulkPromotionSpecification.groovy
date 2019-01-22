package cabify.model.promotions

import cabify.model.order.OrderLine
import cabify.model.price.Price
import spock.lang.Specification
import spock.lang.Unroll

class BulkPromotionSpecification extends Specification {
    BulkPromotion testObj
    OrderLine orderLineMock = Mock()
    Price itemPrice

    def setup() {
        testObj = new BulkPromotion('CODE', 3, 4)
        itemPrice = new Price()
        orderLineMock.itemPrice >> itemPrice
    }

    @Unroll
    def "qualifies should return #expected for quantity:#qty"() {
        given:
        orderLineMock.productCode >> code
        orderLineMock.quantity >> qty

        expect:
        testObj.qualifies(orderLineMock) == expected

        where:
        code      | qty | expected
        'CODE'    | 1   | false
        'CODE'    | 2   | false
        'CODE'    | 3   | true
        'CODE'    | 4   | true
        'ANOTHER' | 3   | false
        'ANOTHER' | 4   | false
    }

    @Unroll
    def "should apply discount for quantity: #qty"() {
        given:
        orderLineMock.quantity >> qty

        when:
        testObj.applyDiscount(orderLineMock)

        then:
        itemPrice.amount == amount
        itemPrice.unitPrice == unitPrice

        where:
        qty | amount | unitPrice
        2   | 8d     | 4d
        3   | 12d    | 4d
        4   | 16d    | 4d
    }
}
