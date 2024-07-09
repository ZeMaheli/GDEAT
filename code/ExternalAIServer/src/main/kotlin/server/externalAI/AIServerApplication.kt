package server.externalAI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AIServerApplication

fun main(args: Array<String>) {
    runApplication<AIServerApplication>(*args)
}
