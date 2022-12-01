package com.my.dev.trace.id.sample

import io.micrometer.tracing.Tracer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class MyTraceIdController(@Autowired private val tracer: Tracer) {

    //curl -v -H "x-b3-spanid: 7b654659d26126f4" -H "x-b3-traceid: 63860f25be2e77dc7b654659d2612645" localhost:9080/trace

    @GetMapping("/trace")
    fun info(@RequestHeader headers: Map<String, String>): ResponseEntity<String> {

        val traceKey = "x-b3-traceid"

        // Using header + removing tracer in constructor make the test working
        // val responseHeaders = HttpHeaders()
        // responseHeaders.set(traceKey, headers.get(traceKey))

        // Using tracer the traceId is retrieved at runtime but not during test
        val responseHeaders = HttpHeaders()
        responseHeaders.set(traceKey, tracer.currentSpan()?.context()?.traceId().toString())

        return ResponseEntity
            .ok()
            .headers(responseHeaders)
            .body("OK")
    }

}