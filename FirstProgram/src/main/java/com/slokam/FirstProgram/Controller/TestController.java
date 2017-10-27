package com.slokam.FirstProgram.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.FirstProgram.Pojo.StudentPojo;

@RestController
public class TestController {
	
	@RequestMapping("getData")
	public String getData()
	{
		return "Hi bro";
		
	}
	
	@RequestMapping("getStringArrayData")
	public String[] getStringArrayData()
	{
		String[] str = {"A","B","C","D","E"};
		return str;
	}
	
	@RequestMapping("getStudentData")
	public StudentPojo getStudentData()
	{
		StudentPojo student = new StudentPojo("A", 22, "BTECH", 12345l);
		return student;
	}
	
	@RequestMapping("getListData")
	public List<StudentPojo> getListData()
	{
		StudentPojo student1 = new StudentPojo("A", 21, "BTECH", 12345l);
		StudentPojo student2 = new StudentPojo("B", 22, "MTECH", 22345l);
		StudentPojo student3 = new StudentPojo("C", 23, "MCA", 32345l);
		StudentPojo student4 = new StudentPojo("D", 24, "BCA", 42345l);
		StudentPojo student5 = new StudentPojo("E", 25, "MSC", 52345l);
		
		List<StudentPojo> list = new ArrayList<>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		list.add(student4);
		list.add(student5);
		
		return list;
	}
	
	@RequestMapping("getMapData")
	public Map<Integer, StudentPojo> getMapData()
	{
		StudentPojo student1 = new StudentPojo("A", 21, "BTECH", 12345l);
		StudentPojo student2 = new StudentPojo("B", 22, "MTECH", 22345l);
		StudentPojo student3 = new StudentPojo("C", 23, "MCA", 32345l);
		StudentPojo student4 = new StudentPojo("D", 24, "BCA", 42345l);
		StudentPojo student5 = new StudentPojo("E", 25, "MSC", 52345l);
		
		Map<Integer, StudentPojo> map = new HashMap<>();
		map.put(1, student1);
		map.put(2, student2);
		map.put(3, student3);
		map.put(4, student4);
		map.put(5, student5);
		
		return map;
	}
	

}
