package com.softuni.mobilelele.service.aop;

import com.softuni.mobilelele.service.MonitoringService;
import com.softuni.mobilelele.service.impl.MonitoringServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitoringAspect {

    private final MonitoringService monitoringService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringServiceImpl.class);


    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Before("PointCuts.trackOfferSearch()")
    public void logOfferSearch() {
        monitoringService.logOfferSearch();
    }

    @Around("PointCuts.warnIfTimeExecutionExceeds()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable { // proceed with the execution of the method, so we can hook before and after
        WornIfTimeExecutionExceeds annotation = getAnnotation(joinPoint);
        long timeout = annotation.timeInMillis(); // get the time from the annotation of the method

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        var returnValue = joinPoint.proceed();
        stopWatch.stop();

        if (stopWatch.getLastTaskTimeMillis() > timeout) {
//            System.out.println("WARN: " + joinPoint.getSignature() + " took " + stopWatch.getLastTaskTimeMillis() + " ms.");
//                LOGGER.warn("WARN: " + joinPoint.getSignature() + " took " + stopWatch.getLastTaskTimeMillis() + " ms.");
            LOGGER.warn("The method {} ran for {} ms, which is longer than the expected {} ms",
                    joinPoint.getSignature(),
                    stopWatch.getLastTaskTimeMillis(),
                    timeout);
        }

        return returnValue;
    }

    private static WornIfTimeExecutionExceeds getAnnotation(ProceedingJoinPoint joinPoint) { //so we can get the annotation from the method
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            return joinPoint
                    .getTarget()
                    .getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(WornIfTimeExecutionExceeds.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong", e);
        }
    }
}
