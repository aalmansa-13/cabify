package cabify.model.promotions

import cabify.model.order.OrderLine
import cabify.model.price.Price
import spock.lang.Specification
import spock.lang.Unroll

class TwoForOnePromotionSpecification extends Specification {
    TwoForOnePromotion testObj
    OrderLine orderLineMock = Mock()
    Price itemPrice

    def setup() {
        testObj = new TwoForOnePromotion('CODE')
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
        'CODE'    | 2   | true
        'CODE'    | 3   | true
        'ANOTHER' | 3   | false
    }

    @Unroll
    def "should apply discount for quantity: #qty"() {
        given:
        orderLineMock.quantity >> qty
        itemPrice.setAmount(10d)
        itemPrice.setUnitPrice(5d)

        when:
        testObj.applyDiscount(orderLineMock)

        then:
        itemPrice.amount == amount
        itemPrice.unitPrice == unitPrice

        where:
        qty | amount | unitPrice
        1   | 5d     | 5d
        2   | 5d     | 2.5d
        3   | 10d    | 3.33d
        4   | 10d    | 2.5d
        5   | 15d    | 3.0d
        6   | 15d    | 2.5d
    }
}
