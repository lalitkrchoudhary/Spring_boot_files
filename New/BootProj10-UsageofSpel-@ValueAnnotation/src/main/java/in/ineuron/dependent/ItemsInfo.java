package in.ineuron.dependent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("item")
public class ItemsInfo {

	@Value("${items.info.idlyPrice}")
	public float idlyPrice;

	@Value("${items.info.dosaPrice}")
	public float dosaPrice;

	@Value("${items.info.vadaPrice}")
	public float vadaPrice;

	@Override
	public String toString() {
		return "ItemsInfo [idlyPrice=" + idlyPrice + ", dosaPrice=" + dosaPrice + ", vadaPrice=" + vadaPrice + "]";
	}

}
