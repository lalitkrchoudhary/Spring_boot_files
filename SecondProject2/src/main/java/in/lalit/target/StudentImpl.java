package in.lalit.target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import in.lalit.dependant.IDependant;



@Component(value = "student")
public class StudentImpl  {
	
	@Autowired
	@Qualifier(value = "java")
	IDependant  material;

public void preparation(String examName) {
	System.out.println("Preparation started for exam:: "+examName);
	String course=material.course();
	double price= material.price();
	System.out.println("Preparation  is going on using "+course+" material with price "+price);
	System.out.println("Preparation for exam "+examName);
	
	
}
	
	
}