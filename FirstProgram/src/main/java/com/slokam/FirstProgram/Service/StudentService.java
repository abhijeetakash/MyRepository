package com.slokam.FirstProgram.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.slokam.FirstProgram.Dao.StudentDao;
import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Pojo.StudentPojo;

@Service("ss")
//@Primary

public class StudentService implements IStudentService {
	
	Logger logger = Logger.getLogger(StudentService.class);
	
	@Autowired
	private StudentDao dao;
	
	public StudentPojo getStudentById(Integer id)
	{
		return dao.findOne(id);
	}
	
	public List<StudentPojo> getAllStudents()
	{
		return dao.findAll();
	}
	
	public void saveStudent(StudentPojo student) throws DuplicateNameException
	{
		List<StudentPojo> list = dao.findByName(student.getName());
        if(list.size() > 0)
        {
        	throw new DuplicateNameException("This is Duplicate name");
        }
		dao.save(student);
	}
	
	public void saveStudents(List<StudentPojo> student)
	{
	    dao.save(student);
	}
	
	public void deleteStudent(StudentPojo student)
	{
	    dao.delete(student);
	}

}
