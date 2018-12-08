package com.slokam.FirstProgram.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Pojo.StudentPojo;
import com.slokam.FirstProgram.Service.IAuditService;
import com.slokam.FirstProgram.Service.IStudentService;
import com.slokam.FirstProgram.Util.StringUtil;


@RestController
@RequestMapping("student")
public class StudentController {
	
	//Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	//@Qualifier("studentService")
	@Qualifier("ss")
	private IStudentService service;
	
	@Autowired
	private StringUtil stringUtil;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StudentPojo> getStudentById(@PathVariable Integer id) throws Exception
	{
		//if(true)
			//throw new Exception();
		//logger.debug("Debuging getStudentById started");
		//logger.error("Data available for id::"+id);
		//logger.fatal("Data available for id::"+id);
		//logger.trace("Data available for id::"+id);
		//logger.warn("Data available for id::"+id);
		//logger.info("Data available for id::"+id);
		ResponseEntity<StudentPojo> responseEntity = null;
		StudentPojo student = service.getStudentById(id);
		if(student != null)
		{
			//logger.debug("Data available for id::"+id);
		    System.out.println("For git");
			responseEntity = new ResponseEntity<>(student,HttpStatus.OK);
		}
		else
		{
			//logger.debug("Data not available for id::"+id);
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//logger.debug("Debuging getStudentById Ends");
		return responseEntity;
		
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<StudentPojo> getAllStudents() throws IOException
	{
		//if(true)
			//throw new IOException();
		stringUtil.test();
		//logger.debug("Debuging getAllStudents started");
		return service.getAllStudents();
		
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity saveStudent(@Valid @RequestBody StudentPojo student, BindingResult result) throws DuplicateNameException
	{
		//logger.debug("Debuging saveStudent started")
		ResponseEntity response = null;
		if (result.hasErrors()) 
		{
			List<ObjectError> list = result.getAllErrors();
			List<String> strings = new ArrayList<>();
			for (ObjectError errors : list)
			{
				strings.add(errors.getDefaultMessage());
			}
			response = new ResponseEntity<>(strings, HttpStatus.BAD_REQUEST);
			return response;
		}
		
		
		/*String newStudent = student.getName();
		
		String oldName = oldStudent.getName();
		
		AuditPojo auditPojo = new AuditPojo();
		if(!newStudent.equals(oldName))
		{
		
		auditPojo.setPropertyName("name");
		auditPojo.setClassName(student.getClass().getName());
		auditPojo.setNewValue(newStudent);
		auditPojo.setOldValue(oldName);
		auditPojo.setOperation("operation");
		auditPojo.setUser("user");
		}
		iauditService.saveAudit(auditPojo);*/
		
		service.saveStudent(student);
		response = new ResponseEntity<>(HttpStatus.OK);
		//logger.debug("Debuging saveStudent Ends");
		return response;
	}

	@RequestMapping(value="/all",method=RequestMethod.POST)
	public void saveStudents(@RequestBody List<StudentPojo> student)
	{
		//logger.debug("Debuging saveStudents started");
		service.saveStudents(student);
		//logger.debug("Debuging saveStudents Ends");
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteStudent(@RequestBody StudentPojo student)
	{
		//logger.debug("Debuging deleteStudent started");
		service.deleteStudent(student);
		//logger.debug("Debuging deleteStudent Ends");
	}
	
	/*@ExceptionHandler(DuplicateNameException.class)
	public ResponseEntity handle()
	{
		ResponseEntity response = new ResponseEntity<>(HttpStatus.CONFLICT);
		return response;
	}*/
	
	@RequestMapping(value="/getData/{name}/{age}")
	public List<StudentPojo> getData(@PathVariable String name, @PathVariable Integer age)
	{
		return service.getData(name+"%", age);
	}
	
	@RequestMapping(value="/getStudentByPage/{pageNo}/{pageSize}")
	public List<StudentPojo> getStudentByPage(@PathVariable Integer pageNo, @PathVariable Integer pageSize )
	{
		return service.getStudentByPage(pageNo, pageSize);
	}

}
