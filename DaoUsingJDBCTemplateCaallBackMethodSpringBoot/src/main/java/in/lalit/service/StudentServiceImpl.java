package in.lalit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lalit.bo.StudentBo;
import in.lalit.dao.IStudentDao;
import in.lalit.dto.StudentDto;


@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	IStudentDao dao;

	@Override
	public StudentDto getStudentByNo(int no) {
		System.out.println("StudentServiceImpl.getStudentByNo()");
		
	StudentBo bo=dao.getStudentByNo(no);
	StudentDto dto = new StudentDto();
	BeanUtils.copyProperties(bo, dto);
	dto.setSno(1);
	if(bo.getSage()>30) {
		dto.setGrade("Older");
	}
	
	return dto;
	}

	@Override
	public List<StudentDto> getAllStudents(String name1, String name2) {
		List<StudentBo> studentbo= dao.getAllStudents(name1, name2);
		
		List<StudentDto> dto= new ArrayList<StudentDto>();
		
		studentbo.forEach(bo -> {
			StudentDto sdto= new StudentDto();
			BeanUtils.copyProperties(bo, sdto);
			
			if(bo.getSage()>30) {
				sdto.setGrade("older");
			}
			else{
				sdto.setGrade("not older");
			}
		    
			sdto.setSno(dto.size()+1);
			
			dto.add(sdto);
		    
		});
	    
	   
		
		return dto;
	}

}
