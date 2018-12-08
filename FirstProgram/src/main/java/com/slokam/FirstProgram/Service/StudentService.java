package com.slokam.FirstProgram.Service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.slokam.FirstProgram.Dao.StudentDao;
import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Pojo.StudentPojo;
import com.slokam.FirstProgram.Util.AuditUtil;

@Service("ss")
//@Primary
public class StudentService implements IStudentService {
	
	Logger logger = Logger.getLogger(StudentService.class);
	
	@Autowired
	private StudentDao dao;
	
	@Autowired
	private AuditUtil auditutil;
	
	public StudentPojo getStudentById(Integer id)
	{
		return dao.findOne(id);
	}
	
	public List<StudentPojo> getAllStudents()
	{
		return dao.findAll();
	}
	
	@Transactional
	public void saveStudent(StudentPojo student) throws DuplicateNameException
	{
		List<StudentPojo> list = dao.findByName(student.getName());
		
		if(student.getId() != null)
		{
			StudentPojo oldStudent = this.getStudentById(student.getId());
			auditutil.audit(oldStudent, student);
			
        if(list.size() > 0)
        {
        	throw new DuplicateNameException("This is Duplicate name");
        }
		dao.save(student);
		}
	}
	
	public void saveStudents(List<StudentPojo> student)
	{
	    dao.save(student);
	}
	
	public void deleteStudent(StudentPojo student)
	{
	    dao.delete(student);
	}

	@Override
	public List<StudentPojo> getData(String name, Integer age) {
		
		return dao.getData(name, age);
	}

	@Override
	public List<StudentPojo> getStudentByPage(Integer pageNo, Integer pageSize) {
		
		PageRequest pageRequest = new PageRequest(pageNo, pageSize);
		Page<StudentPojo> page = dao.findAll(pageRequest);
		return page.getContent();
	}

}
