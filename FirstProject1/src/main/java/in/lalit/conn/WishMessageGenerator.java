package in.lalit.conn;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "wmg")
public class WishMessageGenerator {

	@Autowired
	private LocalDateTime date;
	
	//Business Logic
	public String getMessage(String user) {
		int hour =date.getHour();
		
		if(hour<12) return "Good Moringin "+user;
		else if(hour<16) return "Good Afternoon "+user;
		else if(hour< 20) return "Good Evening " +user;
		else return "Good Night"+user;
	}
	
}
