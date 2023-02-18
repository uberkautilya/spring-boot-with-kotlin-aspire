package io.mcm.springbootwithkotlin.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest

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
    fun aroundServiceCallsExecution(proceedPoint: ProceedingJoinPoint): Any? {
        println("${proceedPoint.signature} invoked with arguments ${proceedPoint.args.contentToString()}")
        val result: Any = proceedPoint.proceed()
        println("${proceedPoint.signature} completed with result: $result")
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
        println("${proceedPoint.signature} invoked with arguments ${proceedPoint.args.contentToString()}")
        val result: Any = proceedPoint.proceed()
        println("${proceedPoint.signature} completed with result: $result")
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
}