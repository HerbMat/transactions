package com.transactions.payment.consumer

import com.transactions.payment.client.BankService
import com.transactions.payment.producer.PaymentProcessedProducer
import com.transactions.paymentcommons.api.dto.PaymentStatus
import com.transactions.paymentcommons.api.dto.PaymentOrderDto
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.slf4j.LoggerFactory
import java.lang.Exception
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


@ApplicationScoped
class PaymentConsumer(private val paymentProcessedProducer: PaymentProcessedProducer,
                      @RestClient private val bankService: BankService) {
    companion object {
        val logger = LoggerFactory.getLogger(javaClass::class.java)
    }

    @Incoming("payments-in")
    fun receive(paymentOrderDto: PaymentOrderDto) {
        logger.info("Got a payment: {} - {}", paymentOrderDto.userId, paymentOrderDto.amount)
        try {
            bankService.withdrawMoney(paymentOrderDto.userId, paymentOrderDto.amount)
            bankService.depositMoney("6", paymentOrderDto.amount)
            paymentProcessedProducer.sendPaymentStatus(PaymentStatus(paymentOrderDto.orderId, true))
        } catch (e: Exception) {
            logger.error(e.message, e)
            paymentProcessedProducer.sendPaymentStatus(PaymentStatus(paymentOrderDto.orderId, false))
        }
    }
}