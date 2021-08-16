package com.transactions.cart.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Cart(
    @Id
    val id: ObjectId = ObjectId.get(),
    val products: Set<ProductOrder>,
    val userId: String
)
