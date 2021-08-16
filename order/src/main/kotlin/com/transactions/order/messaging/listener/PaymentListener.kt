package com.transactions.order.messaging.listener

import com.transactions.order.client.CartClient
import com.transactions.order.domain.OrderStatus
import com.transactions.order.service.OrderService
import com.transactions.paymentcommons.api.dto.PaymentStatus
import kotlinx.coroutines.reactor.mono
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.stereotype.Component

@Component
class PaymentListener(
    private val reactiveKafkaConsumerTemplate: ReactiveKafkaConsumerTemplate<String, PaymentStatus>,
    private val orderService: OrderService,
    private val cartClient: CartClient) {
    val logger = LoggerFactory.getLogger(javaClass)

    @EventListener(ApplicationStartedEvent::class)
    fun listenPayments() {
        reactiveKafkaConsumerTemplate.receive()
            .map { it.value() }
            .doOnNext {
                logger.info("Received ${it?.orderId} ${it?.isSuccess}")
                finishPayment(it)
            }
            .subscribe()
    }

    fun finishPayment(paymentStatus: PaymentStatus) {
        mono {
            orderService.finishOrder(paymentStatus)
        }.subscribe()
    }
}