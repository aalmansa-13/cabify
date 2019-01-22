package cabify.repository.catalog

import cabify.repository.catalog.exception.ProductNotFoundException
import spock.lang.Specification

import static cabify.repository.catalog.ProductRepository.*

class ProductRepositorySpecification extends Specification {
    ProductRepository testObj

    def setup() {
        testObj = new ProductRepository()
    }

    def "should find product by code"() {
        expect:
        testObj.find("VOUCHER") == VOUCHER
        testObj.find("TSHIRT") == TSHIRT
        testObj.find("MUG") == MUG
    }

    def "should throw ProductNotFound exception"() {
        when:
        testObj.find("UNKNOWN")

        then:
        def exception = thrown(ProductNotFoundException)
        exception.message == "Product with code UNKNOWN not found"
    }
}
