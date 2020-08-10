package com.dsys.common.sdk.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Title: LogAspect
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: AOP实践
 * @created 2020/8/10 14:24
 */
@Aspect
@Component
public class LogAspect{
    /**
     * @discription 表明这是一个切入点。
     * execution 中的第一个 * 表示方法返回任意值
     * 第二个 * 表示 service 包下的任意类
     * 第三个 * 表示类中的任意方法，括号中的两个点表示方法参数任意，即这里描述的切入点为 service 包下所有类中的所有方法。
     * @author shilp
     * @created 2020/8/10  14:25
     * @Param
     * @Return
    */
    @Pointcut("execution(* com.dsys.*.service.impl.*.*(..))")
    public void pc1(){
    
    }
    
    // 前置通知
    @Before(value = "pc1()")
    public void before(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法开始执行...");
    }
    
    // 后置通知
    @After(value = "pc1()")
    public void after(JoinPoint jp) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法执行结束...");
    }
    
    // 返回通知
    @AfterReturning(value = "pc1()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法返回值为：" + result);
    }
    
    // 异常通知
    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint jp,Exception e) {
        String name = jp.getSignature().getName();
        System.out.println(name + "方法抛异常了，异常是：" + e.getMessage());
    }
    
    // 环绕通知
    @Around("pc1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();
        // 统计方法执行时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println(name + "方法执行时间为：" + (end - start) + " ms");
        return result;
    }
}
