package io.mcm.springbootwithkotlin.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(LoggingAspect::class.java)
    }

    @Pointcut("execution(* io.mcm.springbootwithkotlin.service.*.*(..))")
    fun logServiceCallsPointcut() {
    }

    @Around("logServiceCallsPointcut()")
    fun aroundServiceCallsExecution(proceedPoint: ProceedingJoinPoint) {
        LOGGER.info("Before execution of service method")
        println(proceedPoint.args)
        println(proceedPoint.kind)
        println(proceedPoint.signature)
        println(proceedPoint.sourceLocation)
        println(proceedPoint.target)
        println(proceedPoint.staticPart)

        proceedPoint.proceed()

        LOGGER.info("proceedPoint.signature:{}", proceedPoint.signature)
    }
}