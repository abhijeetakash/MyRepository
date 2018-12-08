package com.slokam.FirstProgram.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import com.slokam.FirstProgram.Exception.UnsupportedFileFormateExceptions;
import com.slokam.FirstProgram.Pojo.StudentPojo;

public class FileGeneratorUtil {
	
	public static String getfileRecord(List<StudentPojo> studentPojos, String folderName, @PathVariable String fileTypes) throws Exception
	{
		String[] fileType = fileTypes.split(",");
		for(String ft:fileType)
		{
			IFileGenerator iFileGenerator = null;
			switch (ft) {
			case "excel":
				iFileGenerator = new ExcelFileGenerator();
			break;
			
			case "pdf":
				iFileGenerator = new PdfFileGenerator();
			break;
			
			case "word":
				iFileGenerator = new WordFileGenerator();
			break;

			default: throw new UnsupportedFileFormateExceptions(ft+" is not supprted");
			}
			iFileGenerator.getStudentsRecord(studentPojos, folderName);
		}
		 File file = new File(folderName);
		 File [] files = file.listFiles();
		 String zipFilePath = folderName+FileFormat.ZIP.getFormate(); 
		 FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
		 ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
		 for(File file2:files)
		 {
			 ZipEntry zipEntry = new ZipEntry(file2.getName());
			 zipOutputStream.putNextEntry(zipEntry);
			 FileInputStream fileInputStream = new FileInputStream(file2);
			 int data = fileInputStream.read();
			 while(data != -1)
			 {
				 zipOutputStream.write(data);
				 data = fileInputStream.read();
			 }
			 fileInputStream.close();
		 }
		 zipOutputStream.close();
		return zipFilePath;
	}

}
