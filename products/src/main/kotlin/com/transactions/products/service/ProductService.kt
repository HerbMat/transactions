package com.transactions.products.service

import com.transactions.products.domain.Product
import com.transactions.products.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository){
    suspend fun getByProductIds(productIds: List<String>): Flow<Product> {
        return productRepository.findByProductIds(productIds)
    }

    suspend fun getByProductId(productId: String): Product? {
        return productRepository.findByProductId(productId)
    }
}