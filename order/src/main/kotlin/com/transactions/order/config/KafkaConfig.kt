package com.transactions.order.config

import com.transactions.paymentcommons.api.dto.PaymentOrderDto
import com.transactions.paymentcommons.api.dto.PaymentStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.sender.SenderOptions

@EnableKafka
@Configuration
class KafkaConfig {

    @Bean
    fun reactiveKafkaConsumerTemplate(
        kafkaProperties: KafkaProperties,
        @Value("\${kafka.payments-processed.topic}") topicName: String): ReactiveKafkaConsumerTemplate<String, PaymentStatus> {
        val basicReceiverOptions: ReceiverOptions<String, PaymentStatus> = ReceiverOptions.create(kafkaProperties.buildConsumerProperties())

        return ReactiveKafkaConsumerTemplate(basicReceiverOptions.subscription(listOf(topicName)))
    }

    @Bean
    fun reactiveKafkaProducerTemplate(kafkaProperties: KafkaProperties): ReactiveKafkaProducerTemplate<String, PaymentOrderDto> {
        return ReactiveKafkaProducerTemplate(SenderOptions.create(kafkaProperties.buildProducerProperties()))
    }
}