package com.transactions.products.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
data class Product(
    @Id
    val id: ObjectId = ObjectId.get(),
    val productId: String,
    val name: String,
    val price: BigDecimal
)
