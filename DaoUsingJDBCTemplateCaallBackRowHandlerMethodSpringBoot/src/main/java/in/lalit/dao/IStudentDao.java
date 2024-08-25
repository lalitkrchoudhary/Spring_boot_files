package in.lalit.dao;

import java.util.List;

import in.lalit.bo.StudentBo;

public interface IStudentDao {
	
	public StudentBo getStudentByNo(int no);
	public List<StudentBo> getAllStudents(String name1, String name2);
	public List<StudentBo> getStudentByName(String name1, String name2, String name3);
	public List<StudentBo> getStudentByAdd(String add);

}
