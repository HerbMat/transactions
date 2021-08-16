package com.transactions.payment.producer

import com.transactions.paymentcommons.api.dto.PaymentStatus
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PaymentProcessedProducer(@Channel("payments-out") private val emitter: Emitter<PaymentStatus>) {

    fun sendPaymentStatus(paymentStatus: PaymentStatus) {
        emitter.send(paymentStatus)
    }
}