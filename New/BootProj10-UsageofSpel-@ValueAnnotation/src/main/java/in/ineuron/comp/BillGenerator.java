package in.ineuron.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.ineuron.dependent.ItemsInfo;

@Component("bill")
public class BillGenerator {

	@Value("#{item.idlyPrice+item.dosaPrice+item.vadaPrice}")
	private Float billAmount;
	
	@Value("Accord")
	private String hotelName;
	
	@Autowired
	private ItemsInfo info;

	@Override
	public String toString() {
		return "BillGenerator [billAmount=" + billAmount + ", hotelName=" + hotelName + ", info=" + info + "]";
	}
	
	
}
