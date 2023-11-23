package com.softuni.mobilelele.service.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    // we do this just so we can pass this as an annotation further down to Spring
    @Pointcut("execution(* com.softuni.mobilelele.service.OfferService.getAllOffers(..))")
    public void trackOfferSearch() {
    }

    @Pointcut("@annotation(WornIfTimeExecutionExceeds)") // match all methods annotated with this annotation
    public void warnIfTimeExecutionExceeds() {
    }
}
