package com.transactions.paymentcommons.api.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PaymentStatus @JsonCreator constructor(@JsonProperty("orderId") val orderId: String, @JsonProperty("success") val isSuccess: Boolean)
