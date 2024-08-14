package in.lalit.dependant;

import org.springframework.stereotype.Component;

@Component(value = "java")
public class JavaCImpl implements IDependant {

	@Override
	public String course() {
		System.out.println("Java course material");
		return "1. c#JavaScript 2.c#Python";
	}

	@Override
	public double price() {
		System.out.println("Java  price ");
		return 500;
	}

}
