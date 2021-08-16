package com.transactions.payment.kafka

import com.transactions.paymentcommons.api.dto.PaymentOrderDto
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

class PaymentOrderDeserializer: ObjectMapperDeserializer<PaymentOrderDto>(PaymentOrderDto::class.java) {

}