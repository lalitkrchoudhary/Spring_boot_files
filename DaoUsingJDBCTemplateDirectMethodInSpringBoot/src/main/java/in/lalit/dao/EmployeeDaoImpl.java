package in.lalit.dao;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
	
	private static final String SQL_SELECT_QUERY = "select count(*) from students";

	private static final String SQL_SELECT_BY_ID = "select sname from students where sid=?";

	private static final String SQL_EMPLOYEE_BY_NO = "select sid,sname,sage,sadd from students where sid =?";

	private static final String QUERY_ALL = "select sid,sname,sage,sadd from students where sadd in (?,?) order by sname ";

	private static final String QUERY_INSERT = "insert into students (sid,sname,sage,sadd) values(?,?,?,?)";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	






	@Override
	public int getTheCount() {
		System.out.println("JdbcTemplate name is : "+jdbcTemplate.getClass().getName());
		
		return jdbcTemplate.queryForObject(SQL_SELECT_QUERY, Integer.class);
	}
	






	@Override
	public String getEmpByName(int eno) {
		
		return  jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, String.class,eno);
	}






	@Override
	public Map<String, Object> getEmpDetailsByNo(int eno) {
		Map<String, Object> map= jdbcTemplate.queryForMap(SQL_EMPLOYEE_BY_NO, eno);
		return map;
	}






	@Override
	public List<Map<String, Object>> getAllDetails(String des1, String des2) {
	   List<Map<String, Object>> list = jdbcTemplate.queryForList(QUERY_ALL,des1,des2);
	  
		return list;
	}






	@Override
	public int insertEmp(int sid, String sname, int sage, String sadd) {
	int row=	jdbcTemplate.update(QUERY_INSERT, sid,sname,sage,sadd);
				return row;
	}
	

}
