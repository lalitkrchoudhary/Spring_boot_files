package in.ineuron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import in.ineuron.target.Student;

@SpringBootApplication
@ImportResource(locations = "in/ineuron/cfg/applicationContext.xml")
public class BootProj02DependancyInjectionApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(BootProj02DependancyInjectionApplication.class, args);
		System.out.println("*****Container started");

		Student student = context.getBean(Student.class);
		System.out.println(student);

		student.preparation(student.getMaterial().getClass().getName());

		((ConfigurableApplicationContext) context).close();

	}

}
