Hi there!

I have implemented a very simple item pricing engine that will apply the promotions
that are active in the system. These promotions are provided by the Promotions repository.
The 3 products active in the system are provided by the Product repository.
The repositories in real life would integrate with a persistence store (DB, file system),
and there would be a layer to translate the persisted promotions into objects, so they can 
be used by the engine. 

Mainly for simplicity and trying to fit it within 3 hours, I have left out:
* No different currencies supported
* The repositories are very simple and don't implement any persistence mechanism.
* No injection framework used for simplicity
* No logging added, although Production code should have it

Things included:
* Used BigDecimal in operations to avoid rounding issues
* Unit tests using Spock and Groovy
* Used the lombok library (https://projectlombok.org/) to reduce boiler plate code

I enjoyed it anyway, I wished I could have more time.

Thanks and kind regards!


