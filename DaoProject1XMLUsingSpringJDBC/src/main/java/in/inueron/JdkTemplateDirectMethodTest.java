package in.inueron;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.inueron.service.IEmployeeManagementService;

/**
 * Hello world!
 *
 */
public class JdkTemplateDirectMethodTest 
{
    public static void main( String[] args )
    {

    ApplicationContext context =	new ClassPathXmlApplicationContext("in/inueron/cfg/applicationContext.xml");
    IEmployeeManagementService service= context.getBean(IEmployeeManagementService.class);
    System.out.println(service);
    
    try {
		int result= service.fetchTheCount();
		System.out.println("the count is : " +result );
		
		String str= service.getEmpByName(3);
		System.out.println("the details are : "+str);
		
		Map<String, Object>  emp= service.getEmpDetailsByNo(5);
		System.out.println("The result is for single row: "+emp);
		
	    List<Map<String, Object>> list =service.getAllDetails("Narengi", "birkuchi");
	    		
	    		list.forEach(System.out::println);
	    		
	    int res=service.insertEmp(1, "Dhoni", 54, "Delhi");
	    System.out.println("the data has been inserted : "+res);
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
    
    
    ((AbstractApplicationContext) context).close();
    
    }
}
