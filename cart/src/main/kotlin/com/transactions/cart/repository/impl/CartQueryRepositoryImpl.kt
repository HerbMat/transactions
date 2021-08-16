package com.transactions.cart.repository.impl

import com.transactions.cart.domain.Cart
import com.transactions.cart.repository.CartQueryRepository
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class CartQueryRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): CartQueryRepository {
    override suspend fun deleteCartForUser(userId: String) {
        val criteria = Criteria.where("userId").`is`(userId)
        mongoTemplate.remove(Query.query(criteria), Cart::class.java).awaitSingle()
    }
}