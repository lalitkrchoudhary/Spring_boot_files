package in.lalit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.lalit.bo.StudentBo;

@Repository
public class StudentDaoImpl implements IStudentDao {
	
	private static final String QUERY_BY_ID = "select sid,sname,sage,sadd from students where sid=?";
	private static final String QUERY_BY_NAME = "select sid, sname,sage,sadd from students where sname in (?,?)";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public StudentBo getStudentByNo(int no) {
		System.out.println("StudentDaoImpl.getStudentByNo()");
		
		StudentBo bo= jdbcTemplate.queryForObject(QUERY_BY_ID,new StudentMapper(),no);
		
		return bo;
	}
	
	private static class StudentMapper implements RowMapper<StudentBo>{

		@Override
		public StudentBo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			StudentBo bo = new StudentBo();
			bo.setSid(rs.getInt(1));
			bo.setSname(rs.getString(2));
			bo.setSage(rs.getInt(3));
			bo.setSadd(rs.getString(4));
			
			return bo;
		}
		
	}

	@Override
	public List<StudentBo> getAllStudents(String name1, String name2) {
		
		return jdbcTemplate.query(QUERY_BY_NAME, new StudentRowMapper(), name1,name2);
		
	}
	
	private static class StudentRowMapper implements RowMapper<StudentBo>{

		@Override
		public StudentBo mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentBo bo = new StudentBo();
			bo.setSid(rs.getInt(1));
			bo.setSname(rs.getString(2));
			bo.setSage(rs.getInt(3));
			bo.setSadd(rs.getString(4));
			return bo;
		}

		
		
	}
	
    

}
