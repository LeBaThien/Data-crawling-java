package hera.wcrl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class loadCSV {
	
	private static final CellReference Row = null;
	public String firefox_link;
	public String chrome_link;
	public String safari_link;
	public String ie_link;
	public String webhost_link;
	public String dev;
	//Load EXCEL file
	public Object[][] CreateDataFromCSV(String file_xls) { 
		//Start to open to read file
		File DatatestExcel = new File(file_xls); 
		HSSFWorkbook workbook; 
		String[][] data = null; 
		FileInputStream stream = null;
		System.out.println("ConfigWeb_EXCELload "+ file_xls);

		try { 
			stream = new FileInputStream(DatatestExcel); 
			workbook = new HSSFWorkbook(stream); 
			HSSFSheet sheet = workbook.getSheetAt(0); 
			int rows = sheet.getLastRowNum() + 1; 
			short cells = sheet.getRow(0).getLastCellNum(); 
			data = new String[rows][cells]; 
			List<String> list = new ArrayList<String>(); 

			for (int i = 0; i < rows; i++) { 
				HSSFRow row = sheet.getRow(i); 
					for (short j = 0; j < cells; j++) { 
						HSSFCell cell = row.getCell(j); 
						String value = null; 
						if (cell != null) { 
							value = cellToString(cell); 
						}
						//if (value == "Failed")
						data [i][j] = value; 
						// 
					} 
					
			 }
			//data [rows][cells+1] = total_failed+"/"+total_step;
		} 
		catch (FileNotFoundException e) { 
			System.out.println("LOG_XLSnotfound_"+ e.fillInStackTrace().toString());
			//excelreport("LOG_XLSnotfound",e.getMessage());
		} 
		catch (IOException e) { 
			System.out.println("Catch_IO_"+ e.fillInStackTrace().toString());
			//excelreport("LOG_XLSopenning",e.getMessage());
			
		}
		finally {
			//close file
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("ClosingErr_"+ e.fillInStackTrace().toString());
					//excelreport("LOG_XLSclosing",e.getMessage());
				}
			}
		}
		return data; 
	}
	
	//Verify Excel results 
	public static String cellToString(HSSFCell cell) { 
		int type = cell.getCellType(); 
		Object result; 
		switch (type) { 
			case HSSFCell.CELL_TYPE_NUMERIC: // 0 
			result = cell.getNumericCellValue(); 
			break; 
			case HSSFCell.CELL_TYPE_STRING: // 1 
			result = cell.getStringCellValue(); 
			break; 
			case HSSFCell.CELL_TYPE_FORMULA: // 2 
			result = cell.getStringCellValue(); 
			//throw new RuntimeException("We can't evaluate formulas in Java"); 
			break;
			case HSSFCell.CELL_TYPE_BLANK: // 3 
			result = ""; 
			break; 
			case HSSFCell.CELL_TYPE_BOOLEAN: // 4 
			result = cell.getBooleanCellValue(); 
			break; 
			case HSSFCell.CELL_TYPE_ERROR: // 5 
			throw new RuntimeException("This cell has an error"); 
			default: 
			throw new RuntimeException("We don't support this cell type: " + type); 
		}
		return result.toString(); 
	}
	public void openconfigW(String config_xls) throws InterruptedException{
		System.out.println("START");
		Object[][] data_batch = CreateDataFromCSV(config_xls);

		//check NULL data_batch
		if (data_batch == null) 
		{
			System.out.println("ConfigWeb_log..."+ "DATA IS NOT AVAIL");
		}
		else
		{
			//System.out.println("ConfigWeb_log..."+ "DATA IS AVAIL");
			//Get project folder
			//System.out.println("ConfigWeb is now to ..."+ data_batch[1][1].toString().trim());
			firefox_link = data_batch[0][1].toString().trim();
			//System.out.println("firefox_link: " + firefox_link);
			chrome_link = data_batch[1][1].toString().trim();
			//System.out.println("chrome_link: " + chrome_link);
			safari_link = data_batch[3][1].toString().trim();
			//System.out.println("safari_link: " + safari_link);
			ie_link = data_batch[2][1].toString().trim();
			//System.out.println("ie_link: " + ie_link);
			webhost_link = data_batch[4][1].toString().trim();
			System.out.println("webhost_link: " + webhost_link);
			dev = data_batch[5][1].toString().trim();
		}
	}
}
