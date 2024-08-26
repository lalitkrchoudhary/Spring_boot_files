package in.lalit.dao;

import java.util.List;

import in.lalit.bo.EmployeeBo;
import in.lalit.dto.EmployeeDto;

public interface IEmployeeDao {
	public List<EmployeeBo> fetchEmpByAdd(String add1, String add2);
	public int registerEmp(EmployeeBo bo);
	public String fetchByName(int n0);


}
