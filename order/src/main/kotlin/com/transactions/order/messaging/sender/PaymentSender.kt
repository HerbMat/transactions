package com.transactions.order.messaging.sender

import com.transactions.paymentcommons.api.dto.PaymentOrderDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Component

@Component
class PaymentSender(
    private val kafkaTemplate: ReactiveKafkaProducerTemplate<String, PaymentOrderDto>,
    @Value("\${kafka.payments.topic}") private val topicName: String) {

    suspend fun sendPaymentRequest(paymentOrder: PaymentOrderDto) {
        kafkaTemplate.send(topicName, paymentOrder)
            .subscribe()
    }
}