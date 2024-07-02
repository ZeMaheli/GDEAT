package externalaiservice.ai.config

import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig(
    private val config: ExternalAIApiConfig
) {
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofMinutes(4)) // Set the response timeout
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(240)) // Read timeout
                conn.addHandlerLast(WriteTimeoutHandler(240)) // Write timeout
            }

        return builder.baseUrl(config.baseUrl)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}