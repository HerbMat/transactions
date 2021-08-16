package com.transactions.products.api.dto

import java.math.BigDecimal

data class ProductDto(
    val id: String,
    val name: String,
    val price: BigDecimal
)