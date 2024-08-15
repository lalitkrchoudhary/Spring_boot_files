package in.ineuron.dependent;

import javax.inject.Named;

@Named("dotNet")
public final class DotNetCourseMaterial implements ICourseMaterial {

	static {
		System.out.println("DotNetCourseMaterial.class file is loading...");
	}

	public DotNetCourseMaterial() {
		System.out.println("DotNetCourseMaterial :: Zero param constructor...");
	}

	@Override
	public String courseContent() {
		System.out.println("DotNetCourseMaterial.courseContent()");
		return "1. c#oops 2.c#ExcpetionHandling 3.c#Collection";
	}

	@Override
	public double price() {
		return 250;
	}

}
