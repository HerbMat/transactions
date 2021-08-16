package com.transactions.order.repository

import com.transactions.order.domain.Order
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: CoroutineCrudRepository<Order, ObjectId> {
    suspend fun findOrderByUserId(userId: String): Order?
}