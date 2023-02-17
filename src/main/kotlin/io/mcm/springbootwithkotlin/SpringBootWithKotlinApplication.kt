package io.mcm.springbootwithkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class SpringBootWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBootWithKotlinApplication>(*args)
}
