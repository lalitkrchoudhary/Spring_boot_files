package in.lalit;


import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.lalit.dto.EmployeeDto;
import in.lalit.service.IEmployeMgmtService;





@SpringBootApplication
public class CallBackMethodUsingJdbcTemplate {

	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(CallBackMethodUsingJdbcTemplate.class, args);
		
	   IEmployeMgmtService service= applicationContext.getBean(IEmployeMgmtService.class);
	   System.out.println(service);
	   
	   System.out.println();
	   
	  String name= service.fetchByName(1);
	  System.out.println(name);
		
	  System.out.println();
	  List<EmployeeDto> address= service.fetchEmpByAdd("Narengi", "birkuchi");
	  address.forEach(System.out::println);
	  
	  System.out.println();
	  
	  EmployeeDto dto = new EmployeeDto();
	 
	  dto.setSname("Kholi");
	  dto.setSage(32);
	  dto.setSadd("Bombay");
	 String status= service.registerEmp(dto);
	 System.out.println(status);
	  
		((ConfigurableApplicationContext) applicationContext).close();
		
		
		
	}

}
