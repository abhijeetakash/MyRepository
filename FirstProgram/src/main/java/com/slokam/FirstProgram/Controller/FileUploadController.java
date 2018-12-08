package com.slokam.FirstProgram.Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import org.apache.catalina.filters.RemoteIpFilter.XForwardedRequest;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;
import com.slokam.FirstProgram.Exception.DuplicateNameException;
import com.slokam.FirstProgram.Pojo.StudentPojo;
import com.slokam.FirstProgram.Service.IStudentService;
import com.slokam.FirstProgram.Util.FileGenerator;
import com.slokam.FirstProgram.Util.FileGeneratorUtil;

import javax.servlet.http.HttpServletResponse;


@RestController
public class FileUploadController {
	
	@Autowired
	@Qualifier("ss")
	private IStudentService service;
	
	@Value("${application.tempFolder}")
	private String tempFolder;
	
	@RequestMapping("fileUpload")
	public void fileUpload(MultipartFile multipartFile) throws DuplicateNameException
	{
		String name = multipartFile.getOriginalFilename();
		try {
			multipartFile.transferTo( new File("E:\\upload\\"+name));
			File file = new File("E:\\upload\\"+name);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while(line != null)
			{
				String[] strings = line.split(" ");
				StudentPojo studentPojo = new StudentPojo();
				studentPojo.setName(strings[0]);
				studentPojo.setAge(Integer.parseInt(strings[1]));
				studentPojo.setQual(strings[2]);
				studentPojo.setPhone(Long.parseLong(strings[3]));
				studentPojo.setPin(Integer.parseInt(strings[4]));
				service.saveStudent(studentPojo);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			
		} 
		catch (IllegalStateException | IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("File is::"+name);
	}
	
	@RequestMapping("fileDownload/{fieldid}")
	public void fileDownload(HttpServletResponse response, @PathVariable("fieldid") Integer id) 
	{
		try
		{
			File file = new File("C:\\Users\\abhij\\Documents");
			String[] strings = file.list();
			response.addHeader("Content-Disposition", "attachment;filename="+strings[id]);
			PrintWriter printWriter = response.getWriter();
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\abhij\\Documents\\"+strings[id]);
			int data = fileInputStream.read();
			while(data != -1)
			{
				printWriter.write(data);
				data = fileInputStream.read();
				
			}
			fileInputStream.close();
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}  
	}
	
	@RequestMapping(value="/getAllStudentsInFile",method=RequestMethod.GET)
	public void getAllStudentsInFile(HttpServletResponse response) throws IOException
	{
	    List<StudentPojo> studentPojos = service.getAllStudents();
	    String file = "temp_"+System.currentTimeMillis();
	    PrintWriter printWriter = new PrintWriter("F:\\fii\\student\\"+file);
	    for (StudentPojo studentPojo : studentPojos) 
	    {
	    	String string = studentPojo.getName()+","+studentPojo.getAge()+","+studentPojo.getQual()+","+studentPojo.getPhone()+","+studentPojo.getPin();
	    	printWriter.println(string);
		}
	    printWriter.close();
	    
	    response.addHeader("Content-Disposition", "attachment;filename="+file);
	    FileInputStream fis = new FileInputStream("F:\\fii\\student\\"+file);
	    PrintWriter printWriter2 = response.getWriter();
	    int data = fis.read();
	    while(data != -1){
	    	printWriter2.write(data);
	    	data = fis.read();
	    }
	    fis.close();
		
	}
	
	@RequestMapping(value="/getAllStudentsInExcel",method=RequestMethod.GET)
	public void getAllStudentsInExcel(HttpServletResponse response) throws IOException
	{
		String file = "temp_" + System.currentTimeMillis() + ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		HSSFRow row1 = sheet.createRow(1);
		row.createCell(0).setCellValue("Morning Shift");
		row.createCell(1).setCellValue("Afternoon Shift");
		row.createCell(2).setCellValue("Evening Shift");
		row.createCell(3).setCellValue("Night Shift");
		row1.createCell(4).setCellValue("Day Shift");

		FileOutputStream fos = new FileOutputStream("F:\\fii\\student\\" + file);
		workbook.write(fos);
		fos.close();
		workbook.close();

		response.addHeader("Content-Disposition", "attachment;filename=" + file);
		FileInputStream fis = new FileInputStream("F:\\fii\\student\\" + file);
		PrintWriter printWriter2 = response.getWriter();
		int data = fis.read();
		while (data != -1) {
			printWriter2.write(data);
			data = fis.read();
		}
		fis.close();
	}
	
	/*@RequestMapping(value="/getStudentsInExcel",method=RequestMethod.GET)
	public void getStudentsInExcel(HttpServletResponse response) throws IOException
	{
		List<StudentPojo> list = service.getAllStudents();
		String file = "temp_" + System.currentTimeMillis() + ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Student Details");
		
		for (int i = 0; i < list.size(); i++) 
		{
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(list.get(i).getName());
			row.createCell(1).setCellValue(list.get(i).getAge());
			row.createCell(2).setCellValue(list.get(i).getQual());
			row.createCell(3).setCellValue(list.get(i).getPhone());
		}
		
		FileOutputStream fos = new FileOutputStream("F:\\fii\\student\\" + file);
		workbook.write(fos);
		fos.close();
		workbook.close();

		response.addHeader("Content-Disposition", "attachment;filename=" + file);
		FileInputStream fis = new FileInputStream("F:\\fii\\student\\" + file);
		PrintWriter printWriter2 = response.getWriter();
		int data = fis.read();
		while (data != -1) {
			printWriter2.write(data);
			data = fis.read();
		}
		fis.close();

	}
	
	@RequestMapping(value="/getAllStudentsInPdf",method=RequestMethod.GET)
	public void getAllStudentsInPdf(HttpServletResponse response) throws IOException, DocumentException
	{
		List<StudentPojo> list = service.getAllStudents();
		String file = "temp_" + System.currentTimeMillis() + ".pdf";
		Document document = new Document();
		FileOutputStream fos = new FileOutputStream("F:\\fii\\student\\" + file);
		PdfWriter.getInstance(document, fos);
		document.open();
		PdfPTable table = new PdfPTable(5);
		for (StudentPojo lists : list) {
			table.addCell(lists.getName());
			table.addCell(lists.getAge() + "");
			table.addCell(lists.getQual());
			table.addCell(lists.getPhone() + "");
			table.addCell(lists.getPin() + "");
		}

		document.add(table);
		document.close();

		response.addHeader("Content-Disposition", "attachment;filename=" + file);
		FileInputStream fis = new FileInputStream("F:\\fii\\student\\" + file);
		PrintWriter printWriter2 = response.getWriter();
		int data = fis.read();
		while (data != -1) {
			printWriter2.write(data);
			data = fis.read();
		}
		fis.close();

	}
	
	@RequestMapping(value="/getAllStudentsInWord",method=RequestMethod.GET)
	public void getAllStudentsInWord(HttpServletResponse response) throws IOException, DocumentException
	{
	   List<StudentPojo> list = service.getAllStudents();	
	   String file = "temp_"+System.currentTimeMillis()+".doc";
	   XWPFDocument document = new XWPFDocument();
	   FileOutputStream fos = new FileOutputStream("F:\\fii\\student\\"+file);
	   XWPFTable table = document.createTable();
	   
	   
	   for (StudentPojo studentPojo : list)
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
		
	    response.addHeader("Content-Disposition", "attachment;filename=" + file);
		FileInputStream fis = new FileInputStream("F:\\fii\\student\\" + file);
		PrintWriter printWriter2 = response.getWriter();
		int data = fis.read();
		while (data != -1) {
			printWriter2.write(data);
			data = fis.read();
		}
		fis.close();
	}*/
	
	@RequestMapping("getAllDocuments/{fileType}")
	public void getAllDocuments(HttpServletResponse response,  @PathVariable("fileType") String fileType) throws IOException, DocumentException
	
	{
		List<StudentPojo> list = service.getAllStudents();
		String fileName = "temp_" + System.currentTimeMillis(); 
		String filePath = "F:\\fii\\student\\";
		
		switch (fileType) 
		{
		case "excel":
		{
			fileName = fileName+".xls";
			filePath = filePath + fileName;
			FileGenerator.getStudentsInExcel(list, filePath);
		};
			
			break;
			
        case "pdf":
        {
        	fileName = fileName+".pdf";
			filePath = filePath + fileName;
			FileGenerator.getAllStudentsInPdf(list, filePath);
        };
			
			break;
			
        case "word":
        {
        	fileName = fileName+".doc";
			filePath = filePath + fileName;
			FileGenerator.getAllStudentsInWord(list, filePath);
			
        };
	
	       break;

		default:;
		
		}
		
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		FileInputStream fis = new FileInputStream(filePath);
		PrintWriter printWriter2 = response.getWriter();
		int data = fis.read();
		while (data != -1) {
			printWriter2.write(data);
			data = fis.read();
		}
		fis.close();
		
	}
	
	@RequestMapping(value="getAllStudentDocument/{fileTypes}")
	public void getAllStudentDocument(HttpServletResponse response, @PathVariable String fileTypes) throws Exception
	{
		List<StudentPojo> list  = service.getAllStudents();
		String folderName = tempFolder+System.currentTimeMillis();
		
		File file = new File(folderName);
		file.mkdirs();
		
		String zipFilePath = FileGeneratorUtil.getfileRecord(list, folderName, fileTypes);
		
		response.addHeader("Content-Disposition", "attachment;filename=myzip.zip");
		FileInputStream fis = new FileInputStream(zipFilePath);
		PrintWriter printWriter2 = response.getWriter();
		int data = fis.read();
		while (data != -1)
		{
			printWriter2.write(data);
			data = fis.read();
	    }
		fis.close();
	}
	
}

