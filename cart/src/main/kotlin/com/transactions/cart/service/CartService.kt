package com.transactions.cart.service

import com.transactions.cart.domain.Cart
import com.transactions.cart.domain.ProductOrder
import com.transactions.cart.repository.CartRepository
import com.transactions.cartcommons.api.dto.CartDto
import com.transactions.cartcommons.api.dto.ProductOrderDto
import org.springframework.stereotype.Service

@Service
class CartService(private val cartRepository: CartRepository) {
    suspend fun addProductToCart(productOrder: ProductOrderDto, userId: String): CartDto {
        val cart = cartRepository.findCartByUserId(userId)
            ?.let { updateCart(productOrder, it)}
            ?: createNewCart(productOrder, userId)
        return cartRepository.save(cart)
            .let { CartDto(
                it.products.map { productOrderEntity -> ProductOrderDto(productOrderEntity.productId, productOrderEntity.quantity) }.toSet(),
                it.userId)
            }
    }

    suspend fun getCart(userId: String): CartDto {
        return cartRepository.findCartByUserId(userId)
            ?.let { CartDto(
                it.products.map { productOrderEntity -> ProductOrderDto(productOrderEntity.productId, productOrderEntity.quantity) }.toSet(),
                it.userId) }
            ?: CartDto(setOf(), userId)
    }

    suspend fun clearCart(userId: String) {
        cartRepository.deleteCartForUser(userId)
    }


    private suspend fun updateCart(productOrder: ProductOrderDto, cart: Cart): Cart {
        return cart.copy(
            products = updateProductOrderCollectionInCart(cart.products, productOrder)
        )
    }

    private suspend fun createNewCart(productOrder: ProductOrderDto, userId: String): Cart {
        return Cart(products = setOf(ProductOrder(productOrder.productId, productOrder.quantity)), userId = userId)
    }

    private suspend fun updateProductOrderCollectionInCart(productOrderSet: Set<ProductOrder>, productsToAdd: ProductOrderDto): Set<ProductOrder> {
        val (existingProductEntry, restEntries) = productOrderSet.partition { it.productId == productsToAdd.productId }
        val updatedProductOrderEntry = when(existingProductEntry.isNotEmpty()) {
            true -> existingProductEntry.first().copy(quantity = existingProductEntry.first().quantity + productsToAdd.quantity)
            else -> ProductOrder(productsToAdd.productId, productsToAdd.quantity)
        }
        return restEntries.toSet().plus(updatedProductOrderEntry)
    }

}