package io.mcm.springbootwithkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBootWithKotlinApplication>(*args)
}
