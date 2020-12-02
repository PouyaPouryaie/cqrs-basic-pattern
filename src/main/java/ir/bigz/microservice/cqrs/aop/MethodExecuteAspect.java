package ir.bigz.microservice.cqrs.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodExecuteAspect {

    @AfterReturning(value = "@annotation(ir.bigz.microservice.cqrs.aop.GeneralLog)",returning = "result")
    public void generalLog(JoinPoint joinPoint, Object result) {
        log.info(" {} returned with value {} ", joinPoint, result);
    }
}
