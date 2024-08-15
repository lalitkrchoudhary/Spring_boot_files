package in.lalit;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import in.lalit.conn.WishMessageGenerator;

@SpringBootApplication
public class FirstProject1Application {
	
	
	@Bean(name = "ldt")
	public LocalDateTime createLocalDate() {
		LocalDateTime ldt= LocalDateTime.now();
		return ldt;
	}

	public static void main(String[] args) {
    ApplicationContext context=	SpringApplication.run(FirstProject1Application.class, args);
    WishMessageGenerator wmg= context.getBean(WishMessageGenerator.class);
    String res=wmg.getMessage("sachin");
    System.out.println(res);
    
    System.out.println("Bean ID:  "+Arrays.toString(context.getBeanDefinitionNames()));
    
    
	}

}