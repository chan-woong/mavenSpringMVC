package com.java.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect { // 공통클래스
  public static Logger logger = Logger.getLogger(LogAspect.class.getName());
  public static final String LogMsg = "LogMsg--------";

  @Around(value = "within(com.java..*)")
  public Object advice(ProceedingJoinPoint joinPoint) { // 공통함수
    Object obj = null;

    try {
      // logger.info(LogMsg + joinPoint.getTarget().getClass().getName() + "\t" +
      // joinPoint.getSignature().getName());
      obj = joinPoint.proceed();
    } catch (Throwable e) {
      e.printStackTrace();
    }

    return obj;
  }
}
