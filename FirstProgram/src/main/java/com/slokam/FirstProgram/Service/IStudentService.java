package com.slokam.FirstProgram.Service;

import java.util.List;
import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Pojo.StudentPojo;

public interface IStudentService {
	
	public StudentPojo getStudentById(Integer id);
	
	public List<StudentPojo> getAllStudents();
	
	public void saveStudent(StudentPojo student) throws DuplicateNameException;
	
	
	public void saveStudents(List<StudentPojo> student);
	
	
	public void deleteStudent(StudentPojo student);

}
