package com.transactions.cart.repository

import com.transactions.cart.domain.Cart
import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository: CoroutineCrudRepository<Cart, ObjectId>, CartQueryRepository {
    suspend fun findCartByUserId(userId: String): Cart?
}