package com.slokam.FirstProgram.Dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slokam.FirstProgram.Controller.StudentController;
import com.slokam.FirstProgram.Pojo.StudentPojo;

@Repository
public interface StudentDao extends JpaRepository<StudentPojo, Integer>{
	
	Logger logger = Logger.getLogger(StudentDao.class);
	
	public List<StudentPojo> findByName(String name);
	
	@Query("from StudentPojo where name like ?1 and age< ?2")
	public List<StudentPojo> getData(String name, Integer age);

}
