package in.lalit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import in.lalit.bo.EmployeeBo;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
	
	private static final String QUERY_BY_NAME = "select sname from students where sid=:no";
	private static final String QUERY_BY_ADD = "select sid,sname,sage,sadd from students where sadd in (:add1,:add2)";
	private static final String INSERT_QUERY = "insert into  students (sname,sage,sadd) values(:sname,:sage,:sadd)";
	@Autowired
	NamedParameterJdbcTemplate template;

	@Override
	public List<EmployeeBo> fetchEmpByAdd(String add1, String add2) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("add1", add1);
		map.addValue("add2", add2);

		return template.query(QUERY_BY_ADD,map,rs->{
			 
			List<EmployeeBo> bo = new ArrayList<EmployeeBo>();
			while(rs.next()) {
				EmployeeBo empBo= new EmployeeBo();
				empBo.setSid(rs.getInt(1));
				empBo.setSname(rs.getString(2));
				empBo.setSage(rs.getInt(3));
				empBo.setSadd(rs.getString(4));
				bo.add(empBo);
			}
			return bo;
		});
		
	}

	@Override
	public int registerEmp(EmployeeBo bo) {
		//used for inserting the value in the data base
		BeanPropertySqlParameterSource map = new BeanPropertySqlParameterSource(bo);
		
		return template.update(INSERT_QUERY, map);
	}

	@Override
	public String fetchByName(int no)  {
		
		
		  MapSqlParameterSource pa = new MapSqlParameterSource();
	        pa.addValue("no", no);
	        
	      // or this type we can write//  Map<String, Integer> map = Map.of("no",no);
	        
	     // or we can write this type // HashMap<String, Integer> hm= new HashMap<String, Integer>(null); 
	      //hm.put("no"no);
		 return template.queryForObject(QUERY_BY_NAME, pa ,String.class);
		 
		 
		
	
	}

}
