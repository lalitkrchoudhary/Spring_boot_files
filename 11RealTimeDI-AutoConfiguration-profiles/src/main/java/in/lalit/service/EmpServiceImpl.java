package in.lalit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import in.lalit.dao.EmpDao;
import in.lalit.dto.EmpDto;

public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpDao dao;
	

	@Override
	public List<EmpDto> fetchEmpsByDesgs(String[] desgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
