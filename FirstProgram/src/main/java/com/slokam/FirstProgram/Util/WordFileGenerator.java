package com.slokam.FirstProgram.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.slokam.FirstProgram.Pojo.StudentPojo;

public class WordFileGenerator implements IFileGenerator {

	@Override
	public String getStudentsRecord(List<StudentPojo> studentPojos, String folderName) throws IOException
	{
		   String fileName = System.currentTimeMillis()+FileFormat.WORD.getFormate();
		   String filePath = folderName +File.separator+ fileName;
		   XWPFDocument document = new XWPFDocument();
		   FileOutputStream fos = new FileOutputStream(filePath);
		   XWPFTable table = document.createTable();
		   
		   
		   for (StudentPojo studentPojo : studentPojos)
		   {
			   XWPFTableRow row = table.createRow();
			   row.addNewTableCell().setText(studentPojo.getName());
			   row.addNewTableCell().setText(studentPojo.getAge()+"");
			   row.addNewTableCell().setText(studentPojo.getQual());
			   row.addNewTableCell().setText(studentPojo.getPhone()+"");
			   row.addNewTableCell().setText(studentPojo.getPin()+"");
		   } 
		    document.write(fos);
		    document.close();
			return fileName;
			
	}

	

}
