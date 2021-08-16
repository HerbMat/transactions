package com.transactions.products.api.controller

import com.transactions.cartcommons.api.dto.ProductOrderDto
import com.transactions.products.api.dto.ProductDto
import com.transactions.products.service.ProductService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.result.view.RedirectView
import java.math.BigDecimal

@RestController("/products")
class ProductController(private val productService: ProductService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    suspend fun getProductsById(@RequestParam("productId", required = false, defaultValue = "") productIds: List<String>): Flow<ProductDto> {
        logger.info("$productIds")
        return productService.getByProductIds(productIds).map { ProductDto(it.productId, it.name, it.price) }
    }

    @PutMapping("/price")
    suspend fun getProductsPrice(@RequestBody cart: List<ProductOrderDto>): BigDecimal {
        val productQuantityMap = cart.associate { it.productId to it.quantity }
        return productService.getByProductIds(productQuantityMap.keys.toList())
            .map { it.price.multiply(productQuantityMap[it.productId]?.let { quantity -> BigDecimal(quantity) }) }
            .reduce{ firstPrice, secondPrice -> firstPrice.plus(secondPrice)}
    }
}