package in.lalit.service;

import java.util.List;

import in.lalit.dto.EmployeeDto;

public interface IEmployeMgmtService {
	
	public List<EmployeeDto> fetchEmpByAdd(String add1, String add2);
	public String registerEmp(EmployeeDto dto);
	public String fetchByName(int no);

}
