package ir.bigz.microservice.cqrs.aop;

import org.aspectj.lang.annotation.Pointcut;

/***
 * One AOP best practice is to define a common class to store all the pointcuts.
 * This helps in maintaining the pointcuts in one place.
***/
public class CommonJoinPointConfig {

    @Pointcut("execution(* ir.bigz.microservice.cqrs.repository.*.*(..))")
    public void dataLayerExecution(){}

    @Pointcut("execution(* ir.bigz.microservice.cqrs.service.*.*(..))")
    public void businessLayerExecution(){}
}
