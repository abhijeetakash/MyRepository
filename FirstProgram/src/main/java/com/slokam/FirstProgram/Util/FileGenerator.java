package com.slokam.FirstProgram.Util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Controller;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.slokam.FirstProgram.Pojo.StudentPojo;

@Controller
public class FileGenerator {
	
	public static void getStudentsInExcel(List<StudentPojo> studentPojos, String filePath) throws IOException
	{
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Student Details");
		FileOutputStream fos = new FileOutputStream(filePath);
		int count = 0;
		for(StudentPojo pojo:studentPojos)
		{
			HSSFRow row = sheet.createRow(count);
			row.createCell(1).setCellValue(pojo.getName());
			row.createCell(1).setCellValue(pojo.getAge()+"");
			row.createCell(1).setCellValue(pojo.getQual());
			row.createCell(1).setCellValue(pojo.getPhone()+"");
			row.createCell(1).setCellValue(pojo.getPin()+"");
			count++;
		}
		
		workbook.write(fos);
		fos.close();
		workbook.close();
	}
	
	public static void getAllStudentsInPdf(List<StudentPojo> studentPojos, String filePath) throws IOException, DocumentException
	{
		Document document = new Document();
		FileOutputStream fos = new FileOutputStream(filePath);
		PdfWriter.getInstance(document, fos);
		document.open();
		PdfPTable table = new PdfPTable(5);
		for (StudentPojo lists : studentPojos) {
			table.addCell(lists.getName());
			table.addCell(lists.getAge() + "");
			table.addCell(lists.getQual());
			table.addCell(lists.getPhone() + "");
			table.addCell(lists.getPin() + "");
		}

		document.add(table);
		document.close();

	}
	
	
	public static void getAllStudentsInWord(List<StudentPojo> studentPojos, String filePath) throws IOException, DocumentException
	{
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
	}
}
