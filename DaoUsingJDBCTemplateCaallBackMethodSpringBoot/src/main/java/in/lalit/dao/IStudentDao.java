package in.lalit.dao;

import java.util.List;

import in.lalit.bo.StudentBo;

public interface IStudentDao {
	
	public StudentBo getStudentByNo(int no);
	public List<StudentBo> getAllStudents(String name1, String name2);

}
