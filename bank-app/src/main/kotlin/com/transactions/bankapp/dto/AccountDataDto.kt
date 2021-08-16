package com.transactions.bankapp.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class AccountDataDto @JsonCreator constructor(@JsonProperty("userId") val userId: String, @JsonProperty("balance") val balance: BigDecimal)
