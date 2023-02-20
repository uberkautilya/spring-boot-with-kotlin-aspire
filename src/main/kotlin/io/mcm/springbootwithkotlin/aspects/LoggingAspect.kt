package io.mcm.springbootwithkotlin.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

    @Pointcut("execution(* io.mcm.springbootwithkotlin.service.*.*(..))")
    fun logServiceCallsPointcut() {
    }

    @Around("logServiceCallsPointcut()")
    fun aroundServiceCallsExecution(proceedPoint: ProceedingJoinPoint): Any? {
        val serviceLogger = LoggerFactory.getLogger(proceedPoint.signature.declaringType)
        serviceLogger.info("${proceedPoint.signature} invoked with arguments \n${proceedPoint.args.contentToString()}")
        val result: Any = proceedPoint.proceed()
        serviceLogger.info("${proceedPoint.signature} completed with result: \n$result")
        /*
        proceedPoint.args: [2]
        proceedPoint.kind: method-execution
        proceedPoint.signature: BooksResponse io.mcm.springbootwithkotlin.service.BooksService.findById(long)
        proceedPoint.sourceLocation: org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@57c32c19
        proceedPoint.target: io.mcm.springbootwithkotlin.service.BooksService@681d918c
        proceedPoint.staticPart: execution(BooksResponse io.mcm.springbootwithkotlin.service.BooksService.findById(long))
        */
        return result
    }

    @Pointcut("execution(* io.mcm.springbootwithkotlin.controller.*.*(..))")
    fun logControllerCallsPointcut() {
    }

    @Around("logControllerCallsPointcut()")
    fun aroundControllerCallsExecution(proceedPoint: ProceedingJoinPoint): Any? {
        val signature = proceedPoint.signature
        val controllerLogger = LoggerFactory.getLogger(signature.declaringType)
        controllerLogger.info("$signature invoked with arguments \n${proceedPoint.args.contentToString()}")
        println("signature.name: ${signature.name}")
        println("signature.declaringTypeName: ${signature.declaringTypeName}")
        println("signature.declaringTypeName: ${signature.declaringTypeName}")
        println("signature.modifiers: ${signature.modifiers}")
        println("signature.declaringType.name: ${signature.declaringType.name}")
        println("signature.declaringType.simpleName: ${signature.declaringType.simpleName}")
        println("signature.declaringType.typeName: ${signature.declaringType.typeName}")
        val result: Any = proceedPoint.proceed()
        controllerLogger.info("$signature completed with result: \n$result")
        return result
    }
}