package com.transactions.order.domain

import java.math.BigDecimal

data class ProductOrder(val productId: String, val quantity: Int, val price: BigDecimal)