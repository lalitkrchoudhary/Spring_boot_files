package in.lalit.dependant;

import org.springframework.stereotype.Component;

@Component(value = "dotnet")
public class DotNetImpl implements IDependant{

	@Override
	public String course() {
		System.out.println("Dot net course material");
		return "1. c#oops 2.c#Exception";
	}

	@Override
	public double price() {
		System.out.println("Dot net price ");
		return 400;
	}

}
