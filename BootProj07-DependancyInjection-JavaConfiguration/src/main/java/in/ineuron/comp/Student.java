package in.ineuron.comp;

import javax.inject.Inject;
import javax.inject.Named;

import in.ineuron.dependent.ICourseMaterial;

@Named("std")
public class Student {

	static {
		System.out.println("Student.class file is loading...");
	}

	public Student() {
		System.out.println("Student Object is instantiated...");
	}

	@Inject
	@Named(value="courseId")
	private ICourseMaterial material;

	public void preparation(String examName) {
		System.out.println("Preparation started for :: " + examName);
		String courseContent = material.courseContent();
		double price = material.price();
		System.out.println("Prepartion is going on using :: " + courseContent + " material with price :: " + price);
		System.out.println("Preparation completed for :: " + examName);
	}

	public ICourseMaterial getMaterial() {
		return material;
	}

	public void setMaterial(ICourseMaterial material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Student [material=" + material + "]";
	}
	

}
