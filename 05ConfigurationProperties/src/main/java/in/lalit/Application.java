package in.lalit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.lalit.comp.Employee;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(Application.class, args);
		Employee emp= context.getBean(Employee.class);
		System.out.println(emp);
		
		((ConfigurableApplicationContext) context).close();
		
	}

}
