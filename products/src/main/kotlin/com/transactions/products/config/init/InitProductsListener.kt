package com.transactions.products.config.init

import com.transactions.products.domain.Product
import com.transactions.products.repository.ProductRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.math.BigDecimal


@Profile("dev")
@Component
class InitProductsListener(private val productRepository: ProductRepository): ApplicationListener<ApplicationStartedEvent> {
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        mono {
            productRepository.deleteAll()
            productRepository.save(Product(productId = "1", name = "Product A", price = BigDecimal(10)))
            productRepository.save(Product(productId = "2", name = "Product B", price = BigDecimal(15)))
            productRepository.save(Product(productId = "3", name = "Product C", price = BigDecimal(20)))
            productRepository.save(Product(productId = "4", name = "Product D", price = BigDecimal(25)))
            productRepository.save(Product(productId = "5", name = "Product E", price = BigDecimal(30)))
        }.subscribe()
    }
}