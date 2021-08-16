package com.transactions.cartcommons.api.dto

data class CartDto(val productSet: Set<ProductOrderDto>, val userId: String)
