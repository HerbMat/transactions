package com.transactions.products.repository

import com.transactions.products.domain.Product
import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CoroutineCrudRepository<Product, ObjectId>, ProductQueryRepository {
    suspend fun findByProductId(productId: String): Product?
}
