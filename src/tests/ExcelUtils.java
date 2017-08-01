package tests;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("unused")
public class ExcelUtils {

private static HSSFSheet ExcelWSheet;

private static HSSFWorkbook ExcelWBook; 

private static HSSFCell Cell;

private static HSSFRow Row;


//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {

try {

  // Open the Excel file

FileInputStream ExcelFile = new FileInputStream(Path);

//Access the required test data sheet

ExcelWBook = new HSSFWorkbook(ExcelFile);

ExcelWSheet = ExcelWBook.getSheet(SheetName);

} catch (Exception e){

throw (e);

}

}


//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception{

try{

  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

  String CellData = Cell.getStringCellValue();

  return CellData;

  }catch (Exception e){

return"";

  }

}
public static String getCellData(String sheet,int RowNum, int ColNum) throws Exception{

try{

	FileInputStream ExcelFile = new FileInputStream("C:/Banner_Auto_Old/Input.xls");

	//Access the required test data sheet

	ExcelWBook = new HSSFWorkbook(ExcelFile);

	ExcelWSheet = ExcelWBook.getSheet(sheet);
	 String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).toString();

  return CellData;

  }catch (Exception e){

return"";

  }

}


//This method is to write in the Excel cell, Row num and Col num are the parameters

@SuppressWarnings({ "static-access" })
public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

	
try{

  Row  = ExcelWSheet.getRow(RowNum);

Cell = Row.getCell(ColNum);

if (Cell == null) {
	
Cell = Row.createCell(ColNum);

Cell.setCellValue(Result);

} else {

Cell.setCellValue(Result);

}

 // Constant variables Test Data path and Test Data file name

  FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
  String status = "<html><font color='red'>PASS</font></html>";
  ExcelWBook.write(fileOut);
  fileOut.flush();

fileOut.close();

}catch(Exception e){
	
e.printStackTrace();

throw (e);

}

}
@SuppressWarnings("deprecation")
public static void setCellData(String Result,  int RowNum, int ColNum,short color) throws Exception	{

	
try{

  Row  = ExcelWSheet.getRow(RowNum);

Cell = Row.getCell(ColNum);

if (Cell == null) {
	
Cell = Row.createCell(ColNum);

Cell.setCellValue(Result);

} else {

Cell.setCellValue(Result);

}

	HSSFCellStyle style = ExcelWBook.createCellStyle();
	HSSFFont font = ExcelWBook.createFont();
	Cell.setCellStyle(style);
    font.setColor(color);
    font.setBoldweight(color);
    style.setFont(font);
   
    //font.setBoldweight(boldweight);
	

  FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
  ExcelWBook.write(fileOut);
  fileOut.flush();

fileOut.close();

}catch(Exception e){
	
e.printStackTrace();

throw (e);

}

}

}