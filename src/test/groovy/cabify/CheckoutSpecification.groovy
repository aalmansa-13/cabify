package cabify

import cabify.engine.ItemPricingEngine

class CheckoutSpecification extends spock.lang.Specification {

    Checkout testObj
    ItemPricingEngine itemPricingEngine = new ItemPricingEngine()

    def setup() {
        testObj = new Checkout(itemPricingEngine: itemPricingEngine)
    }

    def "should not apply promotions"() {
        given:
        testObj.scan("VOUCHER")
        testObj.scan("TSHIRT")
        testObj.scan("MUG")

        expect:
        testObj.total() == 32.50
    }

    def "should apply 2x1 promotion"() {
        given:
        testObj.scan("VOUCHER")
        testObj.scan("TSHIRT")
        testObj.scan("VOUCHER")

        expect:
        testObj.total() == 25d
    }

    def "should apply Bulk promotion"() {
        given:
        testObj.scan("TSHIRT")
        testObj.scan("TSHIRT")
        testObj.scan("TSHIRT")
        testObj.scan("VOUCHER")
        testObj.scan("TSHIRT")

        expect:
        testObj.total() == 81.00d
    }

    def "should apply 2x1 and Bulk promotion"() {
        given:
        testObj.scan("VOUCHER")
        testObj.scan("TSHIRT")
        testObj.scan("VOUCHER")
        testObj.scan("VOUCHER")
        testObj.scan("MUG")
        testObj.scan("TSHIRT")
        testObj.scan("TSHIRT")

        expect:
        testObj.total() == 74.50d
    }

}
