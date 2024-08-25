package in.lalit.service;

import java.util.List;

import in.lalit.bo.StudentBo;
import in.lalit.dto.StudentDto;

public interface IStudentService {
	
	public StudentDto getStudentByNo(int no);
	public List<StudentDto> getAllStudents(String name1, String name2);
	public List<StudentDto> getStudentByName(String name1, String name2, String name3);
	public List<StudentDto> getStudentByAdd(String add);


}
