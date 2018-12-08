package com.slokam.FirstProgram.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.slokam.FirstProgram.Pojo.StudentPojo;

public class PdfFileGenerator implements IFileGenerator {

	@Override
	public String getStudentsRecord(List<StudentPojo> studentPojos, String folderName) throws FileNotFoundException, DocumentException
	{
		String fileName = System.currentTimeMillis() +FileFormat.PDF.getFormate();
		String filePath = folderName +File.separator+ fileName;
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
		return fileName;

	}
		
}

	

