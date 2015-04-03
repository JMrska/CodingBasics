package mrj.aspect;

import java.time.LocalTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	
	@Before("allCircles() && allGetters()")
	public void LoggingAdvice(JoinPoint jP){
		System.out.println("Kind " + jP.getKind());
		System.out.println("Signature " + jP.getSignature());
		System.out.println("Args " + jP.getArgs());
		System.out.println("SourceLocation " + jP.getSourceLocation());
		System.out.println("Static Part " + jP.getStaticPart());
		System.out.println("Target " + jP.getTarget().getClass().getName());
		
		System.out.println("String " + jP.toString());
		System.out.println("-------------");
	}
	
	@Before("allGetters()")
	public void SecondLoggingAdvice(){
		System.out.println("Second Advice started");
	}
	
	@AfterThrowing(pointcut="args(name)", throwing="retString")
	public void onSetterThrows(String name, Exception retString){
		System.out.println("Returned something");
	}
	
	@AfterReturning(pointcut="args(name)",returning="retString")
	public void onSetter(String name, String retString){
		System.out.println("Returned something");
	}
	@Pointcut("execution(* get*(..))")
	public void allGetters(){
		
	}
	@Pointcut("within(mrj.model.*)")
	//@Pointcut("within(mrj.model..*)")  allso all subpackages
	public void allCircles(){}
	
	
	@Pointcut("args(mrj.model.Circle)")
	public void allWithCircleParam(){}
	
	@Around("allGetters()")
	public void aroundGetter(ProceedingJoinPoint pjp){
		LocalTime beginn = LocalTime.now();		
		try {
			pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalTime end = LocalTime.now();
		System.out.println("It took " + (end.getNano()-beginn.getNano()));
	}
}
