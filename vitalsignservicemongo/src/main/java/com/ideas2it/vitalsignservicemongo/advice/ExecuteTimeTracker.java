package com.ideas2it.vitalsignservicemongo.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ExecuteTimeTracker is used to execute custom time tracker annotation.
 * 
 * @author Vigneshwaran N
 */
@Aspect
@Component
public class ExecuteTimeTracker {

	Logger logger = LogManager.getLogger(ExecuteTimeTracker.class);

	@Around("@annotation(com.ideas2it.vitalsignservicemongo.advice.TimeTrackerAnnotation)")
	public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
		final long startTime = System.currentTimeMillis();
		Object obj = joinPoint.proceed();
		final long endTime = System.currentTimeMillis();
		logger.info("Method name is :->" + joinPoint.getSignature() + " Execution Time is :->" + (endTime - startTime));
		return obj;
	}

}
