package com.transactions.cart.repository

interface CartQueryRepository {
    suspend fun deleteCartForUser(userId: String)
}