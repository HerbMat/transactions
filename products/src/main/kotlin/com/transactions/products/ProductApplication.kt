package com.transactions.products

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(proxyBeanMethods = false)
class ProductApplication

fun main(args: Array<String>) {
    runApplication<ProductApplication>(*args)
}
