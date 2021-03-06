package com.ofss.fileUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ofss.frameworkUtilities.ERRORCODES;
import com.ofss.frameworkUtilities.EnvironmentVariables;
import com.ofss.frameworkUtilities.FrameWorkConstants;

public class ExcelUtilities {

	final private static Logger LOGGER = Logger.getLogger(ExcelUtilities.class.getName());

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public ArrayList<Map<String,String>> getTestDataMapList(String testDataFileName) throws Exception {
	ArrayList<Map<String,String>> testDataList = new ArrayList<Map<String,String>>();
	FileInputStream ExcelFile = null ;
		
	try {
		
				//Open the Excel file
				String testDataFilePath  = new EnvironmentVariables().getInputTestDataFolderPath() + File.separator +
											FrameWorkConstants.TESTDATA_FILENAME_PREFIX + testDataFileName + ".xlsx";	
				
				ExcelFile = new FileInputStream(testDataFilePath);
				
				XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
				
				Map<String,Map<String,String>> testDataKeysMap = getColumnHeaders(ExcelWBook);
				
				if(testDataKeysMap!=null) {
					int totalNumberOfRows = ExcelWBook.getSheetAt(0).getLastRowNum();
					Map<String,String> testDataMap = null ;
					for(int rowNumber=1;rowNumber<=totalNumberOfRows;rowNumber++) {
						testDataMap = getTestDataAtRow(ExcelWBook,rowNumber,testDataKeysMap);
						if(testDataMap != null && testDataMap.containsKey("EXECUTE") && testDataMap.get("EXECUTE").equalsIgnoreCase("YES")){
							if(!testDataList.contains(testDataMap)){
								System.out.println(testDataMap);
								testDataList.add(testDataMap);
							}else{
								LOGGER.info("Duplicate Test Record Found at row Number :" + (rowNumber++) 
										+ " in Test Data WorkBook :"
										+ ExcelWBook.getProperties().getCoreProperties().getTitle());
							}
						}
					}	
					
				}
				
			}catch (Exception e){
	
				LOGGER.info("Unknown Excpetion In reading testdata file");
	
			}
	return testDataList;

}


private Map<String, String> getTestDataAtRow(XSSFWorkbook currentWBook, int rowNumber, Map<String,Map<String,String>> testDataKeysMap) {
	Map<String, String> currentRowTestDataMap = new LinkedHashMap<String,String>();
	int testDataReadStatus =  ERRORCODES.DEFAULT_CODE;
	try {
		for(String currentSheet : testDataKeysMap.keySet()) {
		
			XSSFSheet currentWSheet = currentWBook.getSheet(currentSheet);
			XSSFRow currentRow 		= currentWSheet.getRow(rowNumber);
			String testDataKey,testDataValue = null;
			for(String columnIndex : testDataKeysMap.get(currentSheet).keySet() ) {
				testDataKey = testDataKeysMap.get(currentSheet).get(columnIndex).toUpperCase();
				testDataValue = currentRow.getCell(Integer.parseInt(columnIndex)).getStringCellValue();
				currentRowTestDataMap.put(testDataKey, testDataValue);
			}
		}	
	}catch(Exception e) {
		testDataReadStatus= ERRORCODES.OPERATION_FAILURE;
		LOGGER.error("Error in Reading test Data from row :" + rowNumber + "In WorkBook : " + 
				currentWBook.getProperties().getCoreProperties().getTitle() );
		LOGGER.error(e);
	}
	
	if(testDataReadStatus==ERRORCODES.DEFAULT_CODE) {
		return currentRowTestDataMap;
	}else {
		return null;
	}

}

/*
 * This Method is to read all the available column headers for the given testdata workbook.
 *
 * Its Verifies for any empty column headers in each work sheet.
 * 
 */

private Map<String,Map<String,String>> getColumnHeaders(XSSFWorkbook currentWBook){
	
			
			int headersReadStatus =  ERRORCODES.DEFAULT_CODE;
			int totalNoOfSheets = currentWBook.getNumberOfSheets();
			
			Map<String,Map<String,String>> columnHeadersMap = new LinkedHashMap<String,Map<String,String>>();
			
			
			try {
		
				for(int currentSheetNo=0;currentSheetNo<totalNoOfSheets;currentSheetNo++) {
					
					Map<String,String> sheetColumnHeadersMap = new LinkedHashMap<String,String>();
					XSSFSheet currentWSheet = currentWBook.getSheetAt(currentSheetNo);
					
					XSSFRow headerRow = currentWSheet.getRow(0);
								
					for(Integer cellNo=0; cellNo<headerRow.getLastCellNum();cellNo++) {
						if(headerRow.getCell(cellNo).getStringCellValue() != null) {
							if(sheetColumnHeadersMap.containsKey(headerRow.getCell(cellNo).getStringCellValue())) {
								LOGGER.warn("Duplicate Test Data Column found at cell: " + headerRow.getCell(cellNo).getAddress() + "In Sheet :"  + 
										currentWSheet.getSheetName() + " of WorkBook : " + 
										currentWBook.getProperties().getCoreProperties().getTitle());
							}
							sheetColumnHeadersMap.put(cellNo.toString(),headerRow.getCell(cellNo).getStringCellValue().toUpperCase());
						}else {
							headersReadStatus  = ERRORCODES.INVALID_TEST_DATA;
								LOGGER.info("Empty Test Data Column found at cell: " + headerRow.getCell(cellNo).getAddress() + "In Sheet :"  + 
										currentWSheet.getSheetName() + " of WorkBook : " + 
										currentWBook.getProperties().getCoreProperties().getTitle());
							break;
						}
					}
					
					if(headersReadStatus!=ERRORCODES.DEFAULT_CODE) {
						break;
					}else {
						if(!columnHeadersMap.containsKey(currentWSheet.getSheetName())){
							columnHeadersMap.put(currentWSheet.getSheetName(), sheetColumnHeadersMap);
						}else {
							LOGGER.error("Duplicate TestData Sheet :" + currentWSheet.getSheetName() + " in WorkBook : " + 
										currentWBook.getProperties().getCoreProperties().getTitle());
						}
					}
				
				}
				
			}catch(Exception e) {
				headersReadStatus = ERRORCODES.OPERATION_FAILURE;
				LOGGER.error("Unexpected Error while reading at test data from WorkBook : " + 
						currentWBook.getProperties().getCoreProperties().getTitle());
			};
			
			if(headersReadStatus==ERRORCODES.DEFAULT_CODE) {
				return columnHeadersMap;
			}else {
				return null;
			}

}



}
