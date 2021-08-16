package com.transactions.order.client

import com.transactions.cartcommons.api.dto.CartDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import java.math.BigDecimal

@Service
class ProductClient(webclientBuilder: WebClient.Builder, @Value("\${transactions.product.client}") productClientUri: String) {

    private val webClient = webclientBuilder.baseUrl(productClientUri).build()

    suspend fun getPriceForCart(cart: CartDto): BigDecimal {
        return webClient.put().uri("/price")
            .body(BodyInserters.fromValue(cart.productSet))
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange { it.awaitBody() }
    }
}