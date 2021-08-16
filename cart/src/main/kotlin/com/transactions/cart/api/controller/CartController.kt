package com.transactions.cart.api.controller

import com.transactions.cart.service.CartService
import com.transactions.cartcommons.api.dto.CartDto
import com.transactions.cartcommons.api.dto.ProductOrderDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class CartController(private val cartService: CartService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PutMapping("/{userId}")
    suspend fun addToCart(@RequestBody productOrder: ProductOrderDto, @PathVariable("userId") userId: String): CartDto {
        logger.info("Added $productOrder for user $userId")
        return cartService.addProductToCart(productOrder, userId)
    }

    @GetMapping("/{userId}")
    suspend fun getCart(@PathVariable("userId") userId: String): CartDto {
        logger.info("Returning cart for user $userId")
        return cartService.getCart(userId)
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{userId}")
    suspend fun clearCart(@PathVariable("userId") userId: String) {
        logger.info("Removing cart for user $userId")
        return cartService.clearCart(userId)
    }
}