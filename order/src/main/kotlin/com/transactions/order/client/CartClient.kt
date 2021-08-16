package com.transactions.order.client

import com.transactions.cartcommons.api.dto.CartDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import org.springframework.web.reactive.function.client.awaitBodilessEntity

@Service
class CartClient(webclientBuilder: WebClient.Builder, @Value("\${transactions.cart.client}") cartClientUri: String) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val webClient = webclientBuilder.baseUrl(cartClientUri).build()

    suspend fun getCartForUser(userId: String): CartDto {
        return webClient.get().uri("/{userId}", userId)
            .accept(MediaType.APPLICATION_JSON)
            .awaitExchange { it.awaitBody() }
    }

    suspend fun clearCart(userId: String) {
        webClient.delete().uri("/{userId}", userId)
            .awaitExchange { it.awaitBodilessEntity() }
    }
}