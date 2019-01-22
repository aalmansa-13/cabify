package cabify.engine


import cabify.model.Product
import cabify.model.order.OrderLine
import cabify.repository.catalog.ProductRepository
import spock.lang.Specification

class ItemPricingEngineSpecification extends Specification {
    ItemPricingEngine testObj
    OrderLine orderLine
    ProductRepository productRepositoryMock = Mock()
    Product productMock = Mock()

    def setup() {
        testObj = new ItemPricingEngine(productRepository: productRepositoryMock)
        productRepositoryMock.find('code1') >> productMock
        productMock.listPrice >> 23d
        orderLine = new OrderLine("code1", 2, null)
    }

    def "should price an order line"() {
        when:
        def result = testObj.priceItem(orderLine)

        then:
        result == 46d
        orderLine.itemPrice.amount == 46d
        orderLine.itemPrice.unitPrice == 23d
    }
}
