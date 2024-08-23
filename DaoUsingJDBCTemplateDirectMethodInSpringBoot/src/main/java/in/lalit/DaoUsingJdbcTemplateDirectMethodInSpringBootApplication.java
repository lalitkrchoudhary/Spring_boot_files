package in.lalit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.lalit.service.IEmployeeManagementService;

@SpringBootApplication
public class DaoUsingJdbcTemplateDirectMethodInSpringBootApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(DaoUsingJdbcTemplateDirectMethodInSpringBootApplication.class, args);
		
		try {
			IEmployeeManagementService service= applicationContext.getBean(IEmployeeManagementService.class);
			
			List<Map<String, Object>> list= service.getAllDetails("Narengi", "kalitakuchi");
			
			list.forEach(System.out::println);
			
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		e.printStackTrace();
		}
		
		((ConfigurableApplicationContext) applicationContext).close();
		
	}

}
