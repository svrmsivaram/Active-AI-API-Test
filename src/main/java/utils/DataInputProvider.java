package utils;

import java.io.File;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider{

	public static String[][] getAllSheetData(String sheetName) {

		String[][] data = null;

		try {
			// Load Excel File
			File fis = new File(sheetName);
			
			// Open the WorkBook 
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			// enter the Sheet
			XSSFSheet sheet = workbook.getSheetAt(0);	

			// get the number of rows
			int rowCount = sheet.getLastRowNum();

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			// Declaration the array size
			data = new String[rowCount][columnCount];

			DataFormatter fm= new DataFormatter();
			// loop through the rows
			for(int i=1; i <rowCount+1; i++){
				try {
					XSSFRow row = sheet.getRow(i);
					
					// loop through the columns
					for(int j=0; j <columnCount; j++){ 
						try {
							String cellValue = "";
							try{
								// Based on Cell Type reading the content from Cell
								if(row.getCell(j).getCellTypeEnum()==CellType.STRING){					
									cellValue = row.getCell(j).getStringCellValue();
									
								}else if(row.getCell(j).getCellTypeEnum()==CellType.NUMERIC){
									cellValue = ""+row.getCell(j).getNumericCellValue();					
								}else if(row.getCell(j).getCellTypeEnum()==CellType.BLANK){
									cellValue = "";
								}
							}catch(NullPointerException e){

							}

							data[i-1][j]  = cellValue; // add to the data - array
						} catch (Exception e) {
							e.printStackTrace();
						}				
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;



	}


}




