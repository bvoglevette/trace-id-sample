package com.my.dev.trace.id.sample

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MyTraceIdTests(@Autowired private val webTestClient: WebTestClient) {

    @Test
    fun `Assert info endpoint works as expected with webTestClient`() {

        val traceKey = "x-b3-traceid"
        val traceValue = "463ac35c9f6413ad48485a3953bb6124"

        val spanKey = "x-b3-spanid"
        val spanValue = "a2fb4a1d1a96d312"

        webTestClient.get()
            .uri("http://localhost:9080/trace")
            .header(traceKey, traceValue)
            .header(spanKey, spanValue)
            .exchange()
            .expectStatus().isOk
            .expectHeader().valueEquals(traceKey, traceValue)
            .expectBody<String>().isEqualTo("OK")
    }

}