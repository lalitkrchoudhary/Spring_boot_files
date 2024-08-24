package in.lalit.service;

import java.util.List;


import in.lalit.dto.StudentDto;

public interface IStudentService {
	
	public StudentDto getStudentByNo(int no);
	public List<StudentDto> getAllStudents(String name1, String name2);

}
