package in.inueron.dao;

import java.util.List;
import java.util.Map;

public interface IEmployeeDao {
	
	public int getTheCount();
	
	public String getEmpByName(int eno);
	public Map<String, Object> getEmpDetailsByNo(int eno);
	public List<Map<String, Object>> getAllDetails(String des1, String des2);
	public int insertEmp(int sid, String sname, int sage, String sadd);

}
