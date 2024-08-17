package in.lalit.service;

import java.util.List;

import in.lalit.dto.EmpDto;

public interface EmpService {
	public List<EmpDto> fetchEmpsByDesgs(String desgs[]);

}
