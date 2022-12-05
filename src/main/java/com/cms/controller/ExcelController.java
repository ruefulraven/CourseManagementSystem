package com.cms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cms.entity.Role;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.UserService;

public class ExcelController {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERS_USERS = {"uniqueID", "username", "userControl", "userType", "userEmail", 
								"userPassword", "userLastName", "userFirstName", "userRole"};
	static String[] HEADERS_STUDENTS = {"studentNumber", "firstName", "middleName", "lastName", "schoolYear", 
			"cellphoneNumber", "studentSection"};
	static String SHEET_USERS = "Users";
	static String SHEET_STUDENTS = "Students";
	
	public static boolean hasExcelFormat(MultipartFile file) {
		boolean isTrue = !TYPE.equals(file.getContentType()) ? false : true;
		return isTrue;
	}
	
	public static List<User> excelToDBUsers(InputStream is){
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET_USERS);
			Iterator<Row> rows = sheet.iterator();
			List<User> users = new ArrayList<User>();
			int rowNumber = 0;
			while(rows.hasNext()) {
				Row currentRow = rows.next();
				//Skip header
				//rowNumber = rowNumber == 0 ? rowNumber++ : rowNumber;
				 if(rowNumber == 0) { 
					 rowNumber++; 
					 continue; 
				 }
				 
				Iterator<Cell> cellsInRow = currentRow.iterator();
				User user = new User();
				int cellIdx = 0;
				while(cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					//Each Cell
					switch(cellIdx) {
					case 0:
						user.setUniqueID(currentCell.getStringCellValue());
						break;
					case 1:
						user.setUserName(currentCell.getStringCellValue());
						break;
					case 2:
						user.setUserControl(currentCell.getStringCellValue());
						break;
					case 3:
						user.setUserType(currentCell.getStringCellValue());
						break;
					case 4:
						user.setUserEmail(currentCell.getStringCellValue());
						break;
					case 5:
						user.setUserPassword(currentCell.getStringCellValue());
						break;
					case 6:
						user.setLastName(currentCell.getStringCellValue());
						break;
					case 7:
						user.setFirstName(currentCell.getStringCellValue());
						break;
					case 8:
						user.setRoles(Arrays.asList(new Role(currentCell.getStringCellValue())));
						break;
					}
					cellIdx++;
					}
					users.add(user);
				}
			workbook.close();
			return users;
		}catch(IOException e) {
			throw new RuntimeException("fail to parse excel file: " + e.getMessage());
		}
	}
	
	public static List<Student> excelToDBStudent(InputStream is){
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET_USERS);
			Iterator<Row> rows = sheet.iterator();
			List<Student> students = new ArrayList<Student>();
			int rowNumber = 0;
			while(rows.hasNext()) {
				Row currentRow = rows.next();
				//Skip header
				//rowNumber = rowNumber == 0 ? rowNumber++ : rowNumber;
				 if(rowNumber == 0) { 
					 rowNumber++; 
					 continue; 
				 }
				 
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Student student = new Student();
				int cellIdx = 0;
				while(cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					//Each Cell
					switch(cellIdx) {
					case 0:
						student.setStudentNumber(Integer.parseInt(currentCell.getStringCellValue()));
						break;
					case 1:
						student.setFirstName(currentCell.getStringCellValue());
						break;
					case 2:
						student.setMiddleName(currentCell.getStringCellValue());
						break;
					case 3:
						student.setLastName(currentCell.getStringCellValue());
						break;
					case 4:
						student.setSchoolYear(currentCell.getStringCellValue());
						break;
					case 5:
						student.setCellphoneNumber(currentCell.getStringCellValue());
						break;
					case 6:
						student.setStudentSection(currentCell.getStringCellValue());
						break;
					}
					cellIdx++;
					}
				students.add(student);
				}
			workbook.close();
			return students;
		}catch(IOException e) {
			throw new RuntimeException("fail to parse excel file: " + e.getMessage());
		}
	}
}
