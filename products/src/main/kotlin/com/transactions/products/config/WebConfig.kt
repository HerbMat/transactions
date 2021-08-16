package com.transactions.products.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.reactive.ForwardedHeaderFilter
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.adapter.ForwardedHeaderTransformer
import java.net.URI

@Configuration
class WebConfig {

    @Bean
    fun route() = router {
        GET("/redirect") { ServerResponse.temporaryRedirect(URI.create("/")).build() }
    }

    @Bean
    fun forwardedHeaderTransformer(): ForwardedHeaderTransformer {
        return ForwardedHeaderTransformer()
    }
}