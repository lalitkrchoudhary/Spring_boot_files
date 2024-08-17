package in.lalit.dao;

import java.util.List;

import in.lallit.bo.EmpBo;

public interface EmpDao {
	
	public List<EmpBo> fetchEmpsByDesgs(String cond);

}
