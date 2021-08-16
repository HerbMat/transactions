package com.transactions.order.service

import com.transactions.cartcommons.api.dto.CartDto
import com.transactions.order.client.CartClient
import com.transactions.order.client.ProductClient
import com.transactions.order.domain.Order
import com.transactions.order.domain.OrderStatus
import com.transactions.order.domain.ProductOrder
import com.transactions.order.messaging.sender.PaymentSender
import com.transactions.order.repository.OrderRepository
import com.transactions.paymentcommons.api.dto.PaymentOrderDto
import com.transactions.paymentcommons.api.dto.PaymentStatus
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService(
    private val cartClient: CartClient,
    private val productClient: ProductClient,
    private val paymentSender: PaymentSender,
    private val orderRepository: OrderRepository
) {
    val logger = LoggerFactory.getLogger(javaClass)

    suspend fun processPaymentForUser(userId: String): String {
        logger.info("Started")
        val cart = cartClient.getCartForUser(userId)
        logger.info("Cart ${cart}")
        val price = productClient.getPriceForCart(cart)
        logger.info("Price ${price}")
        val order = createOrder(cart, userId, price)
        logger.info("Order $order")
        paymentSender.sendPaymentRequest(PaymentOrderDto(userId, order.id.toString(), price))
        logger.info("Finisged")
        return order.id.toString()
    }

    suspend fun getOrder(orderId: String): Order? {
        return orderRepository.findById(ObjectId(orderId))
    }

    suspend fun getOrderStatus(orderId: String): OrderStatus? {
        return orderRepository.findById(ObjectId(orderId))?.orderStatus
    }

    suspend fun finishOrder(paymentStatus: PaymentStatus) {
        orderRepository.findById(ObjectId(paymentStatus.orderId))?.let {
            val newStatus = if (paymentStatus.isSuccess) OrderStatus.FINISHED else OrderStatus.REJECTED
            orderRepository.save(it.copy(orderStatus = newStatus))
            cartClient.clearCart(it.userId)
        }
    }

    private suspend fun createOrder(
        cart: CartDto,
        userId: String,
        price: BigDecimal
    ): Order {
        val order = Order(
            products = cart.productSet.map { ProductOrder(it.productId, it.quantity, BigDecimal.TEN) }.toSet(),
            userId = userId,
            totalPrice = price,
            orderStatus = OrderStatus.WAITING_FOR_PAYMENT
        )
        return orderRepository.save(order)
    }
}