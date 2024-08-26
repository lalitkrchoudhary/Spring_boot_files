package in.lalit.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import in.lalit.bo.EmployeeBo;
import in.lalit.dao.IEmployeeDao;
import in.lalit.dto.EmployeeDto;

@Service
public class EmployeeMgmtServiceImpl  implements IEmployeMgmtService{
	
	@Autowired
	IEmployeeDao dao;

	@Override
	public List<EmployeeDto> fetchEmpByAdd(String add1, String add2) {
		
		List<EmployeeBo> bo= dao.fetchEmpByAdd(add1, add2);
		List<EmployeeDto> dto= new ArrayList<EmployeeDto>();
		bo.forEach(bos->{
			EmployeeDto d = new EmployeeDto();
			BeanUtils.copyProperties(bos, d);
			dto.add(d);
			
		});
		// TODO Auto-generated method stub
		return dto;
		
		
		
		
	
	}

	@Override
	public String registerEmp(EmployeeDto dto) {
		String status=null;
		EmployeeBo bo= new EmployeeBo();
		BeanUtils.copyProperties(dto, bo);
		
		int res= dao.registerEmp(bo);
		if(res==1) {
		status="Record insert successful";	
		}
		else {
			status="not inserted";
		}
return status;
	}

	@Override
	public String fetchByName(int no) {
		String name= dao.fetchByName(no);
		return name;
	}

}
