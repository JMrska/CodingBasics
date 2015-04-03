package mrj.main;

import mrj.service.ShapeService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		ShapeService service = context.getBean("ShapeService",ShapeService.class);
		service.getCircle().getName();
		service.getTriangle().getName();
		
	}
}
