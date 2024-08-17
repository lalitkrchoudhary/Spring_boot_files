package in.lalit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import in.lalit.dto.EmpDto;
import in.lalit.service.EmpService;
import in.lalit.vo.EmpVo;

@Controller("empController")
public class EmpController {
	
	@Autowired
	private EmpService service;
	
	public List<EmpVo> showEmpsByDesgs(String degs[]){
		
		List<EmpDto> listDTO= service.fetchEmpsByDesgs(degs);
		
		//
		
		
		
		
		return null;
		
		
		
	}
	
	
	

}
