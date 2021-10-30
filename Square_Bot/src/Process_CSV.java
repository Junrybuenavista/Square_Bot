import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JTextArea;

import org.openqa.selenium.WebDriver;

public class Process_CSV extends Thread{

	WebDriver driver;
	Square_Bot bot;
	Statement st;
	ResultSet rs;
	
	public Process_CSV(WebDriver driver,Square_Bot bot) {
    	this.driver=driver;
    	this.bot=bot;
    	setDataBaseConnection();
    }
	 public void run() {
		 while(true) {
			 try {
				 Thread.sleep(1000);
				 List<String> files = findFiles(Paths.get("C:"+File.separator+"Square_download"), "csv");                  
		         File f=new File(files.get(0));
		         f.renameTo(new File("C:"+File.separator+"Square_download"+File.separator+"square_data.csv"));			            
				 System.out.println("File renamed");
				 
				 st.execute("DELETE FROM `square_data`");
				 
				 st.execute("LOAD DATA INFILE 'C://Square_download//square_data.csv'\r\n"
				 		+ "INTO TABLE square_data\r\n"
				 		+ "FIELDS TERMINATED BY ',' ENCLOSED BY '\"'\r\n"
				 		+ "LINES TERMINATED BY '\\n'\r\n"
				 		+ "IGNORE 1 ROWS\r\n"
				 		+ "(@Date,\r\n"
				 		+ "Time, \r\n"
				 		+ "Time_Zone,\r\n"
				 		+ "Category,\r\n"
				 		+ "Item, \r\n"
				 		+ "Qty, \r\n"
				 		+ "Price_Point_Name,\r\n"
				 		+ "SKU,\r\n"
				 		+ "Modifiers_Applied,\r\n"
				 		+ "Gross_Sales,\r\n"
				 		+ "Discounts, \r\n"
				 		+ "Net_Sales,\r\n"
				 		+ "Tax,\r\n"
				 		+ "Transaction_ID,\r\n"
				 		+ "Payment_ID,\r\n"
				 		+ "Device_Name,\r\n"
				 		+ "Notes,\r\n"
				 		+ "Details,\r\n"
				 		+ "Event_Type,\r\n"
				 		+ "Location,\r\n"
				 		+ "Dining_Option,\r\n"
				 		+ "Customer_ID,\r\n"
				 		+ "Customer_Name,\r\n"
				 		+ "Customer_Reference_ID,\r\n"
				 		+ "Unit,\r\n"
				 		+ "Count,\r\n"
				 		+ "GTIN)\r\n"
				 		+ "SET Date = STR_TO_DATE(@Date, '%m/%d/%Y');");
				 
				 driver.get("http://localhost/googleapi/square-query.php");				 
				 Thread.sleep(4000);
				 driver.quit();
				 st.execute("DELETE FROM `square_data`");
				 Path fileToDeletePath = Paths.get("C:"+File.separator+"Square_download"+File.separator+"square_data.csv");
				 Files.delete(fileToDeletePath);
				 
		         bot.resume();
				 break;
			 }catch (IndexOutOfBoundsException ee) {
		           System.out.println("Waiting for file");
		        }
			 catch(Exception ee) {ee.printStackTrace();}
			 
		 }
	 }
	 public void setDataBaseConnection() {
			while(true) {
				System.out.println("Database connecting");
				try{  
					Thread.sleep(1500);
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/square_db","root","");  	
					 st=con.createStatement();
					 break;
					
				   }catch(Exception e){}
			}
		}
	 public static List<String> findFiles(Path path, String fileExtension)
		        throws IOException {

		        if (!Files.isDirectory(path)) {
		            throw new IllegalArgumentException("Path must be a directory!");
		        }

		        List<String> result;

		        try (Stream<Path> walk = Files.walk(path)) {
		            result = walk
		                    .filter(p -> !Files.isDirectory(p))
		                    // this is a path, not string,
		                    // this only test if path end with a certain path
		                    //.filter(p -> p.endsWith(fileExtension))
		                    // convert path to string first
		                    .map(p -> p.toString().toLowerCase())
		                    .filter(f -> f.endsWith(fileExtension))
		                    .collect(Collectors.toList());
		        }

		        return result;
		    }
}
