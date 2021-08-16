package com.transactions.products.repository.impl

import com.transactions.products.domain.Product
import com.transactions.products.repository.ProductQueryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class ProductRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate): ProductQueryRepository {
    override fun findByQuery(query: Query): Flow<Product> {
        return mongoTemplate.find(query, Product::class.java).asFlow()
    }

    override fun findByProductIds(productIds: List<String>): Flow<Product> {
        val criteria = Criteria()
        if (productIds.isNotEmpty()) {
            criteria.and("productId").`in`(productIds)
        }
        return findByQuery(Query.query(criteria))
    }
}