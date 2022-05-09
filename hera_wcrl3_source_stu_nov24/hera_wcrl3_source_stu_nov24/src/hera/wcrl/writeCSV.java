package hera.wcrl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;

public class writeCSV {
public void WriteToCSV2 ( List<String> args, String name) {
		//FileWriter writer = null;
		try {
			FileWriter	writer = new FileWriter(name+ ".csv");
	
			for (int rowNum = 0; rowNum < args.size(); rowNum++)
			{
				String strtowrite = args.get(rowNum);
				//System.out.println("ROW----#"+rowNum+"---OF---" +name+ "------------------> DONE");
          
				writer.write(strtowrite);
				writer.write("\n");
			
			}
			//writer.flush();
			writer.close();
			System.out.println("KOOLJ_writeout CSV2..."+ name + " --->DONE");
			//csvReport = new ArrayList<>();
        } 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
}
public void WriteToCSV ( List<String> args, String name) {
		//FileWriter writer = null;
		try {
			FileWriter	writer = new FileWriter(name+ ".csv", true);
	
			for (int rowNum = 0; rowNum < args.size(); rowNum++)
			{
				String strtowrite = args.get(rowNum);
				//System.out.println("ROW----#"+rowNum+"---OF---" +name+ "------------------> DONE");
          
				writer.write(strtowrite);
				writer.write("\n");
			
			}
			writer.flush();
			writer.close();
			System.out.println("KOOLJ_writeout CSV..."+ name + " --->DONE");
			//csvReport = new ArrayList<>();
        } 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
}

public void WriteToExcel ( String [][] args, String name) {
/*
		File SDCardRoot = Environment.getExternalStorageDirectory();
		File dcim = new File(SDCardRoot.getAbsolutePath() + "/DCIM/DFRS"+"/"+project_folder);
		String fileName=dcim+"/"+name+"_" + System.currentTimeMillis() + ".xls";
		if (("output").equals(name))
		{
			fileName=dcim+"/"+name+"_" + System.currentTimeMillis() + ".xls";
		}
		else
		{
			fileName=dcim+"/"+name+ ".xls";
		}
*/
		//excelreport("FAILED per TOTAL", "-> "+total_failed +"/"+(total_step+total_failed));
		File fileName= new File((name+"_" + System.currentTimeMillis() + ".xls"));
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet();
        HSSFRow myRow = null;
        HSSFCell myCell = null;
        CreationHelper createHelper = myWorkBook.getCreationHelper();
        for (int rowNum = 0; rowNum < args.length; rowNum++){
			myRow = mySheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 2 ; cellNum++){
				myCell = myRow.createCell(cellNum);
				
				//xu ly ten string ".png"
				if (args[rowNum][cellNum]!=null&&args[rowNum][cellNum].contains(".png"))
				{
					CellStyle hlink_style = myWorkBook.createCellStyle();
					Font hlink_font = myWorkBook.createFont();
			        hlink_font.setUnderline(Font.U_SINGLE);
			        hlink_font.setColor(IndexedColors.BLUE.getIndex());
			        hlink_style.setFont(hlink_font);
			        
					Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
			        link.setAddress(args[rowNum][cellNum]);
			        myCell.setHyperlink(link);
			        myCell.setCellStyle(hlink_style);

				}
				else if (args[rowNum][cellNum]!=null&&args[rowNum][cellNum].contains("FAILED"))
				{
					CellStyle hlink_style = myWorkBook.createCellStyle();
					Font hlink_font = myWorkBook.createFont();
			       // hlink_font.setUnderline(Font.U_SINGLE);
			        hlink_font.setColor(IndexedColors.RED.getIndex());
			        hlink_style.setFont(hlink_font);
			        
					//Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
			        //link.setAddress(args[rowNum][cellNum]);
			        //myCell.setHyperlink(link);
			        myCell.setCellStyle(hlink_style);

				}
				else if (args[rowNum][cellNum]!=null&&args[rowNum][cellNum].contains("per TOTAL"))
				{
					CellStyle hlink_style = myWorkBook.createCellStyle();
					Font hlink_font = myWorkBook.createFont();
			        //hlink_font.setUnderline(Font.U_SINGLE);
					hlink_font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			        hlink_font.setColor(IndexedColors.RED.getIndex());
			        hlink_style.setFont(hlink_font);
			        
					//Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
			        //link.setAddress(args[rowNum][cellNum]);
			        //myCell.setHyperlink(link);
			        myCell.setCellStyle(hlink_style);

				}
				else if (args[rowNum][cellNum]!=null&&args[rowNum][cellNum].contains("-> "))
				{
					CellStyle hlink_style = myWorkBook.createCellStyle();
					Font hlink_font = myWorkBook.createFont();
			        //hlink_font.setUnderline(Font.U_SINGLE);
					hlink_font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			        hlink_font.setColor(IndexedColors.RED.getIndex());
			        hlink_style.setFont(hlink_font);
			        
					//Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
			        //link.setAddress(args[rowNum][cellNum]);
			        //myCell.setHyperlink(link);
			        myCell.setCellStyle(hlink_style);

				}
                myCell.setCellValue(args[rowNum][cellNum]);      
            }
        }
        try{
        	
			FileOutputStream out = new FileOutputStream(fileName);
			myWorkBook.write(out);
			System.out.println("KOOLJ_writeout..."+ "DONE");
			
			//excelreport("LOG_WRITTENTOEXCEL","");
			
			out.close();
        }catch(Exception e){ e.printStackTrace();}         
    }
}
