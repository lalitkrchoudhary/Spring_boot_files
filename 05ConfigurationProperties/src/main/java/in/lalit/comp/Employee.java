package in.lalit.comp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "in.data")
public class Employee {

	private String name;
	private long id;
	
	
	private Company company;
	
	private String[] skillSet;
	
	
	public String[] getSkillSet() {
		return skillSet;
	}


	public void setSkillSet(String[] skillSet) {
		this.skillSet = skillSet;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", company=" + company + ", skillSet="
				+ Arrays.toString(skillSet) + "]";
	}


	
	
	
}