package com.housesline.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import org.apache.log4j.spi.ErrorCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import exception.DataHandleException;

/**
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
public class ExceptionAspect {
	

	@Pointcut("execution(* com.housesline.dao.*.*(..))")
	private void pointcut(){};
	
	@Before("pointcut()")
	public void before(JoinPoint joinPoint) throws ClassNotFoundException {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info(getMethodNameAndArgs(joinPoint));
	}
	
	@After(value="pointcut()")
	public void after(JoinPoint joinPoint){
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.info(getMethodNameAndArgs(joinPoint));
	}
	
	@Around(value="pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		Object retVal = pjp.proceed();
		return retVal;
	}
	
	/** 
     * AfterReturning ����ҵ���߼����������˳��󣬲����Ƿ��з���ֵ�������˳��󣬾�ִ�д�Advice 
     * @author lyl 
     * @param joinPoint 
     */  
    @AfterReturning(value = "pointcut()", returning = "retVal")  
    public void afterReturning(JoinPoint joinPoint, String retVal) {
    	
    	Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    	logger.info(getMethodNameAndArgs(joinPoint)+"��������ִ�����");
    	
    } 
    
    @AfterThrowing(value="pointcut()", throwing="e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable{
    	Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    	logger.error("----------------dao method throw Exception start-----------------");
    	logger.error(getMethodNameAndArgs(joinPoint));
    	logger.error("ConstantUtil.getTrace(e): " +  getTrace(e));
    	logger.error("�쳣���ƣ�" + e.getClass().toString());
    	logger.error("e.getMessage()" + e.getMessage());
    	logger.error("----------------dao method throw Exception end-----------------");
    	
    	if(e.getClass().equals(SQLException.class)){
    		throw new DataHandleException("���ݿ�����쳣");
    	}
    	if(e.getClass().equals(Exception.class)){
    		throw new RuntimeException("ϵͳ����");
    	}
    	
    }
	
	
	
	
	/** 
     * ��ȡ�������Ͳ��� 
     * @author lyl 
     * @param joinPoint 
     * @return 
     */  
    private String getMethodNameAndArgs(JoinPoint joinPoint){  
        Object[] args = joinPoint.getArgs();  
        StringBuffer sb = new StringBuffer("���󷽷���");  
        sb.append(joinPoint.getTarget().getClass().getName() + "."  
                + joinPoint.getSignature().getName() + "(");  
        for (int i = 0; i < args.length; i++) {  
            sb.append("arg[" + i + "]: " + args[i] + ",");  
        }  
        if (args.length > 0) {  
            sb.deleteCharAt(sb.length() - 1);  
        }  
        sb.append(")");  
        return sb.toString();  
    }
    
    /** 
     * ���쳣��Ϣ�����log�ļ� 
     * @param t 
     * @return 
     */  
    public static String getTrace(Throwable t) {          
        StringWriter stringWriter= new StringWriter();          
        PrintWriter writer= new PrintWriter(stringWriter);          
        t.printStackTrace(writer);          
        StringBuffer buffer= stringWriter.getBuffer();         
        return buffer.toString();      
    } 
}
