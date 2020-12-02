package ir.bigz.microservice.cqrs.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class UserAccessAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before(value = "execution(* ir.bigz.microservice.cqrs.service.*.*(..))")
    public void before(JoinPoint joinPoint){

        log.info("Check for user Access");
        log.info("Allowed execution for {}", joinPoint);
    }

    @Around("ir.bigz.microservice.cqrs.aop.CommonJoinPointConfig.dataLayerExecution()")
    public void processTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time Taken by {} is {} ", joinPoint, timeTaken);
    }
}
