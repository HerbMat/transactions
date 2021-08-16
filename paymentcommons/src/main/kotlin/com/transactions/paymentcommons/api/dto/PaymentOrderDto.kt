package com.transactions.paymentcommons.api.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class PaymentOrderDto @JsonCreator constructor(@JsonProperty("userId") val userId: String, @JsonProperty("orderId") val orderId: String, @JsonProperty("amount") val amount: BigDecimal)
