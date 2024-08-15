package in.ineuron;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import in.ineuron.comp.Student;

@SpringBootApplication
@ImportResource(locations = "in/ineuron/cfg/applicationContext.xml")
public class BootProj07DependancyInjectionJavaConfigurationApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication
				.run(BootProj07DependancyInjectionJavaConfigurationApplication.class, args);

		System.out.println("Beans info are :: " + Arrays.toString(context.getBeanDefinitionNames()));
		System.out.println();
		Student student = context.getBean(Student.class);
		System.out.println(student);
		student.preparation(student.getMaterial().getClass().getName());
		((ConfigurableApplicationContext) context).close();
	}
}

