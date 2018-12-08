package com.slokam.FirstProgram.Util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
	
	
	@Before("execution(* com.slokam.FirstProgram.Controller.*.*(..))")
	public void beforeMethod()
	{
		System.out.println("I am Before method");
	}
	
	@After("execution(* com.slokam.FirstProgram.Controller.*.*(..))")
	public void afterMethod()
	{
		System.out.println("I am after method");
	}

	@AfterReturning(pointcut="execution(* com.slokam.FirstProgram.Controller.*.*(..))",returning="obj")
	public void afterReturning(Object obj)
	{
		System.out.println("After returning "+obj);
	}

}
