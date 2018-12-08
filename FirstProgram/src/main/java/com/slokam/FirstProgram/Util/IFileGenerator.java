package com.slokam.FirstProgram.Util;

import java.util.List;

import com.slokam.FirstProgram.Pojo.StudentPojo;

public interface IFileGenerator {
	
	public String getStudentsRecord(List<StudentPojo> studentPojos, String folderName)throws Exception;

}
