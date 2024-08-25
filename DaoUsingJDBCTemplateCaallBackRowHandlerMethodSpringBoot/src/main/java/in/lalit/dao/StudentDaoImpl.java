package in.lalit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.lalit.bo.StudentBo;

@Repository
public class StudentDaoImpl implements IStudentDao {
	
	private static final String QUERY_BY_ID = "select sid,sname,sage,sadd from students where sid=?";
	private static final String QUERY_BY_NAME = "select sid, sname,sage,sadd from students where sname in (?,?)";
	private static final String QUERY_BY_NAMES = "select sid,sname, sage,sadd from students where sadd in (?,?,?)";
	private static final String GET_STUDENT_BY_ADD = "select sid, sname,sage,sadd from students where sadd = ?";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public StudentBo getStudentByNo(int no) {
		System.out.println("StudentDaoImpl.getStudentByNo()");
		
		StudentBo bo= jdbcTemplate.queryForObject(QUERY_BY_ID,new StudentMapper(),no);
		
		return bo;
	}
	
	
	// Instead of whole logic we can also simple write "new BeanPropertyRowMapper<StudentBo>(StudentBo.class)"
	// to copy the data from resultSet to StudentBo
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
			System.out.println("StudentDaoImpl.StudentRowMapper.mapRow()::::::RowMapper");
			StudentBo bo = new StudentBo();
			bo.setSid(rs.getInt(1));
			bo.setSname(rs.getString(2));
			bo.setSage(rs.getInt(3));
			bo.setSadd(rs.getString(4));
			return bo;
		}

		
		
	}

	
	//using RowSetExtractor "Here we have to send manually because there is no ROw count"
	@Override
	public List<StudentBo> getStudentByName(String name1, String name2, String name3) {
		
		return  jdbcTemplate.query(QUERY_BY_NAMES, new ResultSetExtractor<List<StudentBo>>() {

			@Override
			public List<StudentBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				System.out.println("StudentDaoImpl.getStudentByName():::::Row Extractor");
				List<StudentBo> bo = null;
				bo=new ArrayList<StudentBo>();
				
				while(rs.next()) {
				 StudentBo stdbo= new StudentBo();
				 stdbo.setSid(rs.getInt(1));
				 stdbo.setSname(rs.getString(2));
				 stdbo.setSage(rs.getInt(3));
				 stdbo.setSadd(rs.getString(4));
				 
				 bo.add(stdbo);
				}
				return bo;
			}
			
		} ,name1,name2, name3);
	}


	@Override
	public List<StudentBo> getStudentByAdd(String add) {
		
		List<StudentBo> list = new ArrayList<StudentBo>();
		jdbcTemplate.query(GET_STUDENT_BY_ADD,new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("StudentDaoImpl.getStudentByAdd(...).new RowCallbackHandler() {...}.processRow()::::::RowCallBackHandler");
				StudentBo bo = new StudentBo();
				bo.setSid(rs.getInt(1));
				bo.setSname(rs.getString(2));
				bo.setSage(rs.getInt(3));
				bo.setSadd(rs.getString(4));
				list.add(bo);
				
				
			}
		},add);
		
		return list;
	}
	
    

}
