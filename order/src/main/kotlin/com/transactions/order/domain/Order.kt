package com.transactions.order.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
data class Order(

    @Id
    val id: ObjectId = ObjectId.get(),
    val products: Set<ProductOrder>,
    val userId: String,
    val totalPrice: BigDecimal,
    val orderStatus: OrderStatus
)
