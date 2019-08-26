package org.nexters.cultureland.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static Logger log;

    @Pointcut("execution(* org.nexters.cultureland.api.controller.*Controller.*(..))")
    public void loggingForController() {}

    @Around("loggingForController()")
    public Object loggingController(final ProceedingJoinPoint pjp) throws Throwable {
        setLoggerForClass(pjp.getSignature().getDeclaringType());

        log.info("request args: {}", pjp.getArgs());
        Object result = pjp.proceed();
        log.info("response: {}", result);

        return result;
    }

    private void setLoggerForClass(final Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }
}
