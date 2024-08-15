package in.lalit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.lalit.target.StudentImpl;

@SpringBootApplication
public class SecondProject2Application {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(SecondProject2Application.class, args);
		StudentImpl std= context.getBean(StudentImpl.class);
		System.out.println(std);
		std.preparation("FullStack");
		((ConfigurableApplicationContext)context).close();
		
	}

}