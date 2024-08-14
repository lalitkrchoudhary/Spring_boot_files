package in.lalit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.lalit.comp.Student;
import in.lalit.comp.Student2;

@SpringBootApplication
public class ConfigurationProperties4Application {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(ConfigurationProperties4Application.class, args);
		Student student= context.getBean(Student.class);
		System.out.println(student);
		Student2 student2= context.getBean(Student2.class);
		System.out.println(student2);
		((ConfigurableApplicationContext) context).close();
	}

}
