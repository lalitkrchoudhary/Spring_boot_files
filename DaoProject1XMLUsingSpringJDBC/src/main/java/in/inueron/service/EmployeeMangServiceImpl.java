package in.inueron.service;

import java.util.List;
import java.util.Map;

import in.inueron.dao.IEmployeeDao;

public class EmployeeMangServiceImpl implements IEmployeeManagementService {

	private IEmployeeDao dao;
	
	
	
	public EmployeeMangServiceImpl(IEmployeeDao dao) {
		
		this.dao = dao;
	}

	






	@Override
	public int fetchTheCount() {
	
		System.out.println("Service  name is : "+dao.getClass().getName());
		
		return dao.getTheCount();
	}

	@Override
	public String toString() {
		return "EmployeeMangServiceImpl [dao=" + dao + "]";
	}








	@Override
	public String getEmpByName(int eno) {
		// TODO Auto-generated method stub
		return dao.getEmpByName(eno);
	}








	@Override
	public Map<String, Object> getEmpDetailsByNo(int eno) {
		//String means column name and Object means data
		
		return dao.getEmpDetailsByNo(eno);
	}








	@Override
	public List<Map<String, Object>> getAllDetails(String des1, String des2) {
		// TODO Auto-generated method stub
		return dao.getAllDetails(des1, des2);
	}








	@Override
	public int insertEmp(int sid, String sname, int sage, String sadd) {

		return dao.insertEmp(sid, sname, sage, sadd);
	}
}
