package com.my.dev.trace.id.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TraceIdSampleApplication

	fun main(args: Array<String>) {
		runApplication<TraceIdSampleApplication>(*args)
	}
