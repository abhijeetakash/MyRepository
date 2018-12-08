package com.slokam.FirstProgram.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.slokam.FirstProgram.Pojo.StudentPojo;

public class ExcelFileGenerator implements IFileGenerator {

	@Override
	public String getStudentsRecord(List<StudentPojo> studentPojos, String folderName) throws IOException
	{
		String fileName = System.currentTimeMillis() +FileFormat.EXCEL.getFormate();
		String filePath = folderName +File.separator+ fileName;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Student Details");
		FileOutputStream fos = new FileOutputStream(filePath);
		int count = 0;
		for(StudentPojo pojo:studentPojos)
		{
			HSSFRow row = sheet.createRow(count);
			row.createCell(0).setCellValue(pojo.getName());
			row.createCell(1).setCellValue(pojo.getAge()+"");
			row.createCell(2).setCellValue(pojo.getQual());
			row.createCell(3).setCellValue(pojo.getPhone()+"");
			row.createCell(4).setCellValue(pojo.getPin()+"");
			count++;
		}
		
		workbook.write(fos);
		fos.close();
		workbook.close();
		return fileName;
	}
	
		
	
}
	