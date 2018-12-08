package com.slokam.FirstProgram.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.slokam.FirstProgram.Dao.StudentDao;
import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Pojo.StudentPojo;

@Service
public class StudentMangoDBService implements IStudentService{
	
Logger logger = Logger.getLogger(StudentService.class);
	
	@Autowired
	private StudentDao dao;
	
	public StudentPojo getStudentById(Integer id)
	{
		return null;
	}
	
	public List<StudentPojo> getAllStudents()
	{
		return null;
	}
	
	public void saveStudent(StudentPojo student) throws DuplicateNameException
	{
		
	}
	
	public void saveStudents(List<StudentPojo> student)
	{
	    
	}
	
	public void deleteStudent(StudentPojo student)
	{
	    
	}

	@Override
	public List<StudentPojo> getData(String name, Integer age) {
	
		return null;
	}

	@Override
	public List<StudentPojo> getStudentByPage(Integer pageNo, Integer pageSize) {
		
		return null;
	}


}
