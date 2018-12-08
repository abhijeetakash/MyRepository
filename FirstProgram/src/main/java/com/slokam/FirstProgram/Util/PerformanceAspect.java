package com.slokam.FirstProgram.Util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
	
	/*long startTime;
	
	@Before("execution(* com.slokam.FirstProgram.*.*.*(..))")
	public void beforeMethod(JoinPoint joinPoint)
	{
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().toString();
		System.out.println("Before method::"+methodName+" "+className);
		startTime = System.currentTimeMillis();
	}
	
	@After("execution(* com.slokam.FirstProgram.*.*.*(..))")
	public void afterMethod(JoinPoint joinPoint)
	{
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().toString();
		System.out.println("After method::"+methodName+" "+className);
		long endTime = System.currentTimeMillis();
		System.out.println("The execution time of::"+methodName+" of "+className+" is"+( endTime - startTime));
	}*/
	
	
	long startTime;
	
	@Around("execution(* com.slokam.FirstProgram.*.*.*(..))")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		
		
		long startTime = System.currentTimeMillis();
		String methodName = proceedingJoinPoint.getSignature().getName();
		String className = proceedingJoinPoint.getTarget().toString();
		System.out.println("Before method::"+methodName+" "+className);
		Object object = null;
		
			object = proceedingJoinPoint.proceed();
		
		long endTime = System.currentTimeMillis();
		System.out.println("After method::"+methodName+" "+className);
		
		long totalTime=endTime-startTime;
		System.out.println("The execution time of::"+methodName+" of "+className+" is::::"+totalTime);
		return object;
	}

}
