package com.transactions.order.domain

enum class OrderStatus {
    CREATED,
    WAITING_FOR_PAYMENT,
    REJECTED,
    FINISHED
}