package com.transactions.order.api.controlller

import com.transactions.order.domain.Order
import com.transactions.order.domain.OrderStatus
import com.transactions.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(private val orderService: OrderService) {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{userId}")
    suspend fun processPaymentForUser(@PathVariable userId: String): String {
        return orderService.processPaymentForUser(userId)
    }

    @GetMapping("/{orderId}")
    suspend fun getOrder(@PathVariable orderId: String): ResponseEntity<Order> {
        return orderService.getOrder(orderId)?.let { ResponseEntity.ok(it) }
            ?: return ResponseEntity.notFound().build()
    }

    @GetMapping("/{orderId}/status")
    suspend fun getOrderStatus(@PathVariable orderId: String): ResponseEntity<OrderStatus> {
        return orderService.getOrderStatus(orderId)?.let { ResponseEntity.ok(it) }
            ?: return ResponseEntity.notFound().build()
    }
}