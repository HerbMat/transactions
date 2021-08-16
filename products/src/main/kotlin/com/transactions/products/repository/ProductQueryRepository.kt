package com.transactions.products.repository

import com.transactions.products.domain.Product
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.core.query.Query

interface ProductQueryRepository {
    fun findByQuery(query: Query): Flow<Product>
    fun findByProductIds(productIds: List<String>): Flow<Product>
}