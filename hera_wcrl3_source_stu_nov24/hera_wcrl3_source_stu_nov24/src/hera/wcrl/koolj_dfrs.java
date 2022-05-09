package hera.wcrl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.PixelGrabber;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.lightcouch.CouchDbClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;





import com.google.common.graph.ElementOrder.Type;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.metamx.emitter.core.Emitters;
//import com.google.gson.reflect.TypeToken;




//import com.github.nkzawa.socketio.client.*;
import io.socket.*;
import io.socket.client.IO;

public class koolj_dfrs {
	String caromapTestip ="";
	
	int forstepvar = 0;
	public String test_xls;
	public String current_testcase;
	public int total_step = 0;
	public int total_failed = 0;
	String suite_xls;
	String map_xls;
	String batch_xls;
	String config_xls;
	String KOOLJ_log;
	String project_folder = "";
	String currentURL = "";
	int cwrlcount = 0;
	int cvsflag = 0;
	
	String randomglob = "";	
	int randomglob_yes = 1;
	
	String[][] outputReport = new String[2000][2];
	List<String>  csvReport = new ArrayList<>();
	List<String> csvget = new ArrayList<>();
	Object[][] data_suite;
	Object[][] data_test;
	Object[][] data_key;
	Object[][] data_url_batch;
	Object[][] data_read18;
	Object[][] data_read6;
	Object[][] data_read5;
	Object[][] data_read4;
	int read_first = 1;
	int file_download_done = 0;
	int outputReport_step1 = 0;
	int outputReport_step2 = 0;
	int csvReport_step1 = 0;
	
	long starttime = 0;
	long endtime = 0;
	long elapsedtime = 0;	
	
	int resid = 0;
	int read_idx_row = -1;
	int read_idx_row2 = -1;
	//int read_idx_col = 0;
	//int value_inx_acc = 0;
	//int value_inx_start = 0;
	
	//Activity act_var;
	String class_name;
	String class_text;
	
	public String numrun = "";
	
	//Open CONFIG to BATCH,SUITE,TEST files
	public void openconfig(String config_xls,WebDriver driver, String rooturl, String runnum) throws InterruptedException, IOException {
		//time evaluation
		//2018-07-14T11:21:48.062Z
		//2018-07-30T01:05:44.380Z
		numrun = runnum;
		
		
		Object[][] data_batch = CreateDataFromCSV(config_xls);
		
		//check NULL data_batch
		if (data_batch == null) 
		{
			System.out.println("KOOLJ_log..."+ "DATA IS NOT AVAIL");
			//excelreport("DATA IS NOT AVAIL","");
		}
		else
		{
			
			System.out.println("KOOLJ_log..."+ "DATA IS AVAIL");
			//excelreport("DATA IS AVAIL","");
			
			//Get project folder
			if (data_batch[1][0].toString().trim().equals("project_folder"))
			{
				project_folder = data_batch[1][1].toString().trim();
			}
			else 
			{
				System.out.println("KOOLJ_log..."+ "THERE IS NO PROJECT FOLDER");
				//excelreport("THERE IS NO PROJECT FOLDER","");
			}	
			
			//if files from HTTP, download them
			if (!project_folder.equals(""))
			{
				file_download_done = 1;
				data_url_batch = CreateDataFromCSV("url_batch.xls");
				for (int i_d=0; i_d< data_url_batch.length; i_d++)
				{					
					if (data_url_batch[i_d][1].toString().trim().equals("yes"))
					{
						//URLfile(data_url_batch[i_d][2].toString(),data_url_batch[i_d][0].toString().trim());
					}
					else 
					{
						file_download_done = 3;
					}	
					
				}
				
				if (file_download_done > 1)
				{
					//Find to run SUITE
					String data_suite_var="batch.xls";
					//KOOLJ_log=KOOLJ_log+"\n"+"RUN BATCH: "+ data_suite_var;
					System.out.println("KOOLJ_BATCH: ..."+ data_suite_var);
					//excelreport("LOG_BATCH: ",data_suite_var);
					data_suite = CreateDataFromCSV(data_suite_var);

				}
			}
			else
			{	
				System.out.println("KOOLJ_log..."+ "THERE IS NO PROJECT FOLDER");
				//excelreport("THERE IS NO PROJECT FOLDER","");
			}	
			
			//Find to run TEST
			if (file_download_done > 2)
			{
				for (int ii=0; ii< data_suite.length; ii++)
				{
					
					String data_test_var="" + data_suite[ii][0].toString().trim() +".xls";
					//KOOLJ_log=KOOLJ_log+"\n"+"RUN SUITE:______ "+ data_test_var;
					System.out.println("KOOLJ_SUITE_"+ii+": "+ data_test_var);
					//excelreport("LOG_SUITE_"+ii+": ", data_test_var);
					data_test = CreateDataFromCSV(data_test_var);
					
					//Find to run KEY
					for (int iii=0; iii< data_test.length; iii++)
					{
						String data_key_var="" + data_test[iii][0].toString().trim() +".xls";
						current_testcase = data_suite[ii][0].toString().trim()+"_"+data_test[iii][0].toString().trim();
						KOOLJ_log=KOOLJ_log+"\n"+"RUN TEST:______ "+ data_key_var;
						System.out.println("KOOLJ_TEST_"+iii+": "+ data_key_var);
						//excelreport("LOG_TEST_"+iii+": ", data_key_var);
						data_key = CreateDataFromCSV(data_key_var);
						
						//Run each KEY
						//System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + data_key.length);
						String[] keyx_label=new String[data_key.length];
						String[] valuex_label=new String[data_key.length];
						String[] key_for=new String[data_key.length];
						String[] valuestart_for=new String[data_key.length];
						String[] valueend_for=new String[data_key.length];
						String[] valueacce_for=new String[data_key.length];
						String[] key_endfor=new String[data_key.length];
						String[] key_if=new String[data_key.length];
						String[] key_endif=new String[data_key.length];
						String[] key_else=new String[data_key.length];

						int for_count = 0;
						int for_count_backward = 0;
						int for_step = 0;
						int for_step_backward = 0;						
						int endfor_step = 0;
						int keyx_label_step = 0;
						int iiii_label = 0;
						int value_valuestart_for = 0;
						int value_valueend_for = 0;
						int value_valueacce_for = 0;
						int varstore_kv_step = 0;
						int varstore_step = 0;
						int varstore_count = 0;
						double key_stepstart = 0;
						double key_stepend = 0;
						double key_stepacc = 0;
						int if_step = 0;
						int if_step_backward = 0;
						int else_step_backward = 0;
						int if_count = 0;
						int if_count_backward = 0;
						int if_logic = 0;
						int if_located = 0;
						int validate_if = 0;
						int validate_endif = 0;
						int validate_for = 0;
						int validate_endfor = 0;
						
						
						String key_ifstart = "";
						String key_ifend = "";
												
			
						//Run each KEY
						for (int iiii=iiii_label; iiii< data_key.length; iiii++)
						{
							
							String key_target = data_key[iiii][1].toString().trim();
						    if(key_target.equals("sel_getpfb"))
							{
								String v2 = data_key[iiii][2].toString().trim();
								String v3 = data_key[iiii][3].toString().trim();
								String v4 = data_key[iiii][4].toString().trim();
								String v8 = data_key[iiii][5].toString().trim();
								String v5 = data_key[iiii][6].toString().trim();
								String v6 = data_key[iiii][7].toString().trim();
								String v7 = data_key[iiii][8].toString().trim();
								sel_getpfb(driver, v2, v3, v4, v8, v5, v6, v7);
							
							}
	
						}				
					}		

				}
			}
			else
			{
				System.out.println("KOOLJ_log..."+"There is no TEST to run");
				////excelreport("THERE IS NO TEST TO RUN","");
			}
		}
		
	}

//Define keywords
//===========================================================
public static boolean isNumeric(String string) 
{
  try
    {
      double d = Double.parseDouble(string);
    }
    catch(NumberFormatException e)
    {
      return false;
    }
    return true;
  
}
public int toInt(String runfrom) throws InterruptedException
{
	int runfromnum = 0;
	if (isNumeric(runfrom))  
	{
		Double value_to_set_double = Double.parseDouble(runfrom);
		DecimalFormat df = new DecimalFormat("###.#");
		String value_to_set = df.format(value_to_set_double).toString();
		runfromnum = Integer.parseInt(value_to_set);
	}
	return runfromnum;
}
public  int hashCode(String string) {
	int PRIME = 31;
    return string != null ? string.hashCode() * PRIME : 0;  // PRIME = 31 or another prime number.
}
public void sel_getpfb(WebDriver driver, String type, String runfrom,String frame, String seedword, String totalses, String scrollnum, String dbsubtotal) throws InterruptedException
{
	try {
		
		String imgstorefolder = dbsubtotal;
		//build list input
		//read frame
		try {
			data_read6 = CreateDataFromCSV3(frame+".csv");
			Thread.sleep(500);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//read words
		try {
			data_read5 = CreateDataFromCSV3(seedword+".csv");
			Thread.sleep(500);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create seed csv
		writeCSV writemyCSVidRUN =  new writeCSV();
		ArrayList RUNIDcsvget = new ArrayList<>();
		for (int i=0; i < data_read5.length; i++)
		{
			for (int j=0; j < data_read6.length; j++)
			{
				RUNIDcsvget.add(data_read6[j][0].toString() + data_read5[i][0].toString() +data_read6[j][1].toString());
			}
		}	
		writemyCSVidRUN.WriteToCSV2(RUNIDcsvget, "seed");	
		Thread.sleep(500);
		//create input
		try {
			data_read4 = CreateDataFromCSV3("seed.csv");
			Thread.sleep(500);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//number of STARTRUN
		int runfromnum = toInt(runfrom);
		if (runfromnum != 0)
			runfromnum = runfromnum -1;
		
		//get totalrun
		int totalrun = toInt(totalses);
		
		
		//get totalscan
		int totalscanvar = 0;

		int index = 0;
		int numrun_int = Integer.parseInt(numrun);
			
		csvget = new ArrayList<>();
		
//disable couchdb    	//connect dbcouch using lightcouch
/*
		try {
    		ClassLoader ctxClassLoader = Thread.currentThread().getContextClassLoader();
    		try {
    			Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
    			couchdbclientfbgooimg = new CouchDbClient(databasefbgooimg, autoCreate, protocol, "localhost", port, username, password);
    			//couchdbclientfbcmcount = new CouchDbClient(databasefbumcount, autoCreate, protocol, hostname, port, username, password);
    		} finally {
    			Thread.currentThread().setContextClassLoader(ctxClassLoader);
    		}
    	} catch (Throwable e) {
    		System.out.println("KOOLJ_data------ERR DBCOUCH----------------: "+ e.getMessage());
    	}
*/		
		//read save tmp
		//read CSV gender template
		try {
			data_read18 = CreateDataFromCSV3("gooimg_done.csv");
			Thread.sleep(300);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//disable couchdb
		
		writeCSV writemyCSVtimeline =  new writeCSV();
		String eleid = "";
		String RUNeleid = "";
		
		
		//random time
		double randomTIME = 0.5 + Math.random() * (2.8 - 1.2);
		int currentrun = 0;
		
		int INTelementID = hashCode(randomTIME + "");
		eleid = String.valueOf(INTelementID);
		
		//convert scroll num
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrolltimes = toInt(scrollnum);

		String imgsource = "";
		String fburlsource = "";

		String seedurl = "";
		HashMap<String, Object> map = null;

		String json = "";
		String jsonOutputComm = "";
		int totaladded = 0;
		int totaladdedcount = 0;
		JSONObject sockobj = new JSONObject();
		int beforesroll = 0;
		int afterscroll = 0;
		int stepsroll=0;
		
		
		for (int i=runfromnum; i < data_read4.length; i++)
		{
			if (currentrun > 0) i = i -1;
			System.out.println("KOOLJ_------HERE-------------: ");
			//System.out.println("KOOLJ_------HERE-------------: " + data_read4.length);	
			index = i + currentrun * totalrun + numrun_int - 1;
			//System.out.println("KOOLJ_data-----------HERE-----" + index);
			seedurl = data_read4[index][0].toString();
			//System.out.println("KOOLJ_data===========================================================================================");
			System.out.println("KOOLJ_data================================= S T A R T W I T H ================== RECORD #: " + index + "====== VALUE: ");
			System.out.println("KOOLJ_data===================================== V A L U E ===============================: " +data_read4[index][0].toString());		
			

			driver.get(seedurl);
			Thread.sleep(500);
			
	    	
			//scroll down
			beforesroll = 0;
			afterscroll = 0;
			stepsroll=0;
			System.out.println("KOOLJ_data-----------scrolling down-----" + scrolltimes);
			for (int s=0; s< scrolltimes; s++)
			{
				js.executeScript("window.scrollBy(0,2500)"); 
				//System.out.println("KOOLJ_data-----------scrolling down-----" + s);
				Thread.sleep(1000);
				
				if(driver.findElements(By.xpath("//*[@id=\"islrg\"]/div[1]/div")).size() >0)
					afterscroll = driver.findElements(By.xpath("//*[@id=\"islrg\"]/div[1]/div")).size();
				
				//System.out.println("KOOLJ_data-------------------------------CURR NUM IMG-----" + afterscroll);
				stepsroll++;
				if(stepsroll > 2) {
					//System.out.println("KOOLJ_data-------------------------------B4--"+beforesroll+"-----AFT--" + afterscroll);
					if(beforesroll == afterscroll) {					
						if(afterscroll > 200) {
							if(driver.findElement(By.xpath("//*[@id=\"islmp\"]/div/div/div/div/div[4]/div[2]/div[1]/div")).getText().indexOf("end") != -1) {
								Thread.sleep(300);
								break;
							}
							driver.findElement(By.xpath("//*[@id=\"islmp\"]/div/div/div/div/div[5]/input")).click();
							Thread.sleep(1000);

						}
						else
							break;
						
					}
					else {
						beforesroll = afterscroll;
						stepsroll = 0;
						
					}
				}
			}
			
			totalscanvar =  driver.findElements(By.xpath("//*[@id=\"islrg\"]/div[1]/div")).size();
			totaladded = 0;
			WebElement targetimg = null;
			WebElement targetfburl = null;
			fburlsource = "1";
			String INTelementIDvar = "";
			RUNIDcsvget = new ArrayList<>();
			
			totaladdedcount = 0; 
			for (int l=1; l<= totalscanvar; l++)
			{
				
				if(driver.findElements(By.xpath("//*[@id=\"islrg\"]/div[1]/div["+l+"]/a[1]/div[1]/img")).size() >0)
					targetimg = driver.findElement(By.xpath("//*[@id=\"islrg\"]/div[1]/div["+l+"]/a[1]/div[1]/img"));
				if(driver.findElements(By.xpath("//*[@id=\"islrg\"]/div[1]/div["+l+"]/a[2]")).size() > 0)
					targetfburl = driver.findElement(By.xpath("//*[@id=\"islrg\"]/div[1]/div["+l+"]/a[2]"));
				
				if(targetimg.getAttribute("src") != null)
					imgsource = targetimg.getAttribute("src");
				//if(targetimg.getAttribute("href") != null)
				fburlsource = targetfburl.getAttribute("href");
				INTelementID = hashCode(fburlsource);
				INTelementIDvar = "fbgooimg_"+INTelementID;
				
//disable couchdb				//check double  
				//if(!couchdbcheckdoublicate(databasefbgooimg,INTelementIDvar, "localhost")) {
//check googimg_done
				if (!isRunRecord(INTelementIDvar,type,data_read18)) {
					//save img
			        if (imgsource.indexOf("base64") != -1) {
			        	//System.out.println("KOOLJ_HTML...------------------1-------------------HERE... "+l);
						String base64 = imgsource.split(",")[1];
						byte[] data = Base64.getDecoder().decode(base64);
						try {
							OutputStream stream = new FileOutputStream(imgstorefolder+"/"+INTelementIDvar+".jpg");
						    stream.write(data);
						}
					    catch(Exception se){
					    	System.out.println("KOOLJ_data-----------------ERR SAVING IMG------" + se.getMessage());
					    }
			        }
			        //url img
			        else {
			        	//System.out.println("KOOLJ_HTML...-------------------2------------------HERE... "+l);
						URL imageURL = new URL(imgsource);
						try(InputStream in = imageURL.openStream()){
						    Files.copy(in, Paths.get(imgstorefolder+"/"+INTelementIDvar+".jpg"));
						}
					    catch(Exception se){
					    	System.out.println("KOOLJ_data-----------------ERR SAVING IMG------" + se.getMessage());
					    }
			        }
			        //System.out.println("KOOLJ_HTML...-------------------------------------HERE... "+l);
					//save to DONE record 
					RUNIDcsvget.add(type + "," +INTelementIDvar +","+fburlsource);

					//add to db
					map = new HashMap<String, Object>();
			        map.put("_id", INTelementIDvar);
			        map.put("fblink", fburlsource);
//disable couchdb			      //check double again
			        //if(!couchdbcheckdoublicate(databasefbgooimg,INTelementIDvar, "localhost")) 
			        if (!isRunRecord(INTelementIDvar,type,data_read18)) {
			        	//couchdbclientfbgooimg.save(map);
			        	
				        totaladdedcount++;
				        if(totaladdedcount >5) {
							//save DONE
							writemyCSVidRUN.WriteToCSV(RUNIDcsvget, "gooimg_done");
							RUNIDcsvget = new ArrayList<>();
				        	totaladdedcount=0;
				        }else if (l== totalscanvar && totaladdedcount <=5) {
							writemyCSVidRUN.WriteToCSV(RUNIDcsvget, "gooimg_done");
							RUNIDcsvget = new ArrayList<>();
				        } 	
			        }
//disable couchdb   
					if(fburlsource.length() > 30)
						System.out.println("KOOLJ_data----img---" + INTelementIDvar + "---fblink---" + fburlsource.substring(24,31) +"... -------------- DONE ITEM# "+ l +"/"+ totalscanvar + "/RECORD#" + index); 
			       // }else	
						//System.out.println("KOOLJ_SESSION #: "+numrun_int+"----OF ITEM # "+ + l +"/"+ totalscanvar + "/RECORD#" + index+"-----JUST FINISHED");
							
				}else	
					System.out.println("KOOLJ_SESSION #: "+numrun_int+"----OF ITEM # "+ + l +"/"+ totalscanvar + "/RECORD#" + index+"-----JUST FINISHED");
				//}
			}//end all img
			
			
			//save to all count
			//}//type is gsc
			currentrun++;
			Thread.sleep(2000);
		}//end all records
	}
	catch (Exception e){
		total_failed++;
	}
}
public boolean isRunRecord(String idrun, String typerun, Object[][] objsavetmp) throws InterruptedException {
	boolean isRunExisted = false;
	
	for (int s2=0; s2 < objsavetmp.length; s2++)
	{
		//System.out.println("KOOLJ_-------------CHECKING------------- "+data_read18[s2][0] + "---------"+data_read18[s2][1]); 
		if(typerun.equals(data_read18[s2][0].toString()))
		if(idrun.equals(data_read18[s2][1].toString())) {
			isRunExisted = true;
			break;
		}	
	}	
	return isRunExisted;
}
//===========================================================
public Object[][] CreateDataFromCSV3 (String csvFile) throws FileNotFoundException {

	//String csvFile = "output_fl.csv";
	String line = "";
	//String cvsSplitBy = ",";
	String[][] data2 = null;
	int i = 0;
	int j = 1;
	String value = null;
	//Scanner inputStream = new Scanner(csvFile);
	try {
		Scanner inputStream = new Scanner(new File(csvFile));
		//System.out.println("CSV_________ URL=" + inputStream);
		List<String>  csv2get = new ArrayList<>();

		while (inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			csv2get.add(line);
		}
		int k = csv2get.size();
		j =  csv2get.get(0).split(",").length;
		data2 = new String[k][j];
		//System.out.println("CSV_________ J = " + csv2get.get(0));
		for (i=0;i<k;i++)
		{
			String[] rowelement =  csv2get.get(i).split(",");
			if (rowelement.length > 1) {
				for (j = 0; j < rowelement.length; j++) {
					//System.out.println("CSV_________ LIST= " +i+" " + csv2get.get(i));
					//value = csv2get.get(i);
					//if  (rowelement[j].toString().equals(" "))
					//rowelement[j] = "";
					data2[i][j] = rowelement[j];
					//System.out.println("CSV_________ ARR=" + data2[i][j]);
				}
			}
			else {
				for (j = 0; j < 1; j++) {
					//System.out.println("CSV_________ LIST= " +i+" " + csv2get.get(i));
					//value = csv2get.get(i);
					data2[i][j] = csv2get.get(i);
					//System.out.println("CSV_________ ARR=" + data2[i][j]);
				}
			}
		}
		inputStream.close();
	}
	catch (FileNotFoundException e) {
		//excelreport("LOG_CSVnotfound",e.getMessage());
	}
	catch (IOException e) {
		System.out.println("Catch_IO_"+ e.fillInStackTrace().toString());
		//excelreport("LOG_CSVopenning",e.getMessage());

	}

	return data2;
}
public Object[][] CreateDataFromCSV2 (String csvFile) throws FileNotFoundException {

    //String csvFile = "output_fl.csv";
    String line = "";
    //String cvsSplitBy = ",";
    String[][] data2 = null; 
	int i = 0;
	int j = 1;
	String value = null;
    //Scanner inputStream = new Scanner(csvFile);
	try { 
	    Scanner inputStream = new Scanner(new File(csvFile));
	    //System.out.println("CSV_________ URL=" + inputStream);
	    List<String>  csv2get = new ArrayList<>();
	    
		while (inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			csv2get.add(line);
		}
		int k = csv2get.size();
		data2 = new  String[k][j];
		for (i=0;i<k;i++)
		{
			for (j = 0; j < 1; j++) {
				data2[i][j] = csv2get.get(i);
				//System.out.println("CSV_________ ARR=" + data2[i][j]);
			}
		}
		inputStream.close();
	}
	catch (FileNotFoundException e) { 
		//excelreport("LOG_CSVnotfound",e.getMessage());
	} 
	catch (IOException e) { 
		System.out.println("Catch_IO_"+ e.fillInStackTrace().toString());
		//excelreport("LOG_CSVopenning",e.getMessage());
		
	}
	
    return data2; 
}
	//Load EXCEL file
	public Object[][] CreateDataFromCSV(String file_xls) { 
		//Start to open to read file
		File DatatestExcel = new File(file_xls); 
		HSSFWorkbook workbook; 
		String[][] data = null; 
		FileInputStream stream = null;
		System.out.println("KOOLJ_EXCELload "+ file_xls);
		//excelreport("LOG_EXCELload",file_xls);
		
		//KOOLJ_log=KOOLJ_log+"\n"+"XLS_load" + file_xls;
		try { 
			stream = new FileInputStream(DatatestExcel); 
			workbook = new HSSFWorkbook(stream); 
			HSSFSheet sheet = workbook.getSheetAt(0); 
			int rows = sheet.getLastRowNum() + 1; 
			short cells = sheet.getRow(0).getLastCellNum(); 
			data = new String[rows][cells]; 
			List<String> list = new ArrayList<String>(); 
			////excelreport("FAILED per TOTAL", total_failed +"/"+total_step);
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
						//System.out.println("-----"+i+":"+j+"----------------"+data [i][j].toString());
						// 
					} 
					
			 }
			//data [rows][cells+1] = total_failed+"/"+total_step;
		} 
		catch (FileNotFoundException e) { 
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
					//excelreport("LOG_XLSclosing",e.getMessage());
				}
			}
		}
		//System.out.println(data[0][0]);
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
	private void updateProgress(int downloadedSize, int totalSize) {
		String downprogress_var;
		//System.out.println("KOOLJ_loading...", Long.toString((downloadedSize/totalSize)*100)+"%");
		//KOOLJ_log=KOOLJ_log+"\n"+"Downloading status... "+Long.toString((downloadedSize/totalSize)*100)+"%"; 
	} 

}
