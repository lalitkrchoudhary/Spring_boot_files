package in.lalit;


import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.lalit.dto.StudentDto;
import in.lalit.service.IStudentService;



@SpringBootApplication
public class CallBackMethodUsingJdbcTemplate {

	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(CallBackMethodUsingJdbcTemplate.class, args);
		
	    IStudentService service= applicationContext.getBean(IStudentService.class);
	    System.out.println(service);
	    StudentDto student= service.getStudentByNo(3);
	    System.out.println(student);
	    
	    List<StudentDto> studentAll= service.getAllStudents("Lalit", "Dhoni");
	   studentAll.forEach(System.out::println);
	    
		
		((ConfigurableApplicationContext) applicationContext).close();
		
	}

}
