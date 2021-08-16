package com.transactions.order.jackson

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.transactions.paymentcommons.api.dto.PaymentStatus
import org.apache.kafka.common.header.Headers

object CustomJsonTypeResolver {

    @JvmStatic
    fun resolveType(topic: String, data: ByteArray, headers: Headers): JavaType {
        return TypeFactory.defaultInstance().constructType(PaymentStatus::class.java)
    }
}