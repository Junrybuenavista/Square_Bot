import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Square_Bot extends Thread{
	WebDriver driver;
	String username;
	String password;
	GUI gui;
	Date start,end;
	SimpleDateFormat dateformat1;
	SimpleDateFormat dateformat2,dateformatDay;
	String startDate,endDate;
	Statement st;
	ResultSet rs;
	
	public Square_Bot() {
		gui = new GUI();
		dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
		dateformat2 = new SimpleDateFormat("MM/dd/yyyy");
		dateformatDay = new SimpleDateFormat("EEEEE");
		setDataBaseConnection();
		setEndDate();
		
		
	}
	public void Login() {

		gui.textAppend("Starting Login\n");
		//driver.get("https://squareup.com/login");
		driver.get("https://squareup.com/dashboard/sales/transactions");
		try {
			Thread.sleep(2000);
		}catch(Exception ee) {ee.printStackTrace();}
		driver.findElement(By.id("email")).sendKeys("cmeys@nbvresorts.com");
		driver.findElement(By.id("password")).sendKeys("Breakers99");
		driver.findElement(By.id("sign-in-button")).click();
		gui.textAppend("Login complete\n");
		try {
			Thread.sleep(4000);
		}catch(Exception ee) {ee.printStackTrace();}
		
	}
	public void setEndDate() {
		
		try {
			
	        rs=st.executeQuery("Select date_end from date_record where ID=1");
			rs.next();
			if(rs.getDate("date_end")==null) {
	        Calendar cal2 = Calendar.getInstance();
	        cal2.add(Calendar.DATE, -8);
	        Date date2 = cal2.getTime();    
	        endDate = dateformat2.format(date2);
	        st.execute("UPDATE date_record SET date_end = '"+dateformat1.format(date2)+"' where ID=1");
	        System.out.println("date null");
	        
			}
			else {
				 endDate = dateformat2.format(rs.getDate("date_end"));
				 System.out.println("date not null");
			}
		
		
		System.out.println("end date:"+endDate);
		}catch(Exception ee) {ee.printStackTrace();}
	}
	public void setStartDate() {
		 	Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -2);
	        Date date1 = cal.getTime();    
	        startDate = dateformat2.format(date1);
	        System.out.println("start date:"+startDate);
	}
	public void run() {
		
		
		String currentdate="";
		while(true) {
			try {
				Thread.sleep(2000);
			}catch(Exception ee) {ee.printStackTrace();}
			System.out.println("Waiting");
			
			 if(dateformatDay.format(new Date()).equalsIgnoreCase("Saturday")&&!currentdate.equalsIgnoreCase(dateformat1.format(new Date()))) {
					setBrowser();
					setStartDate();
					gui.textAppend("Processing\n");
					
					while(true) {
						Login();			
						
							while(true) {
								
								
								try {
									Thread.sleep(1000);
								}catch(Exception ee) {ee.printStackTrace();}
								System.out.println("dropdown date waiting!");
								if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/button")).size() != 0) {
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/button")).click();
									System.out.println("dropdown date done!");
									break;
								}	
							}
							
							//while(true) {
							//	try {
								//	Thread.sleep(1000);
								//}catch(Exception ee) {ee.printStackTrace();}
								//System.out.println("lastweek waiting!");
								//if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[2]/button[4]")).size() != 0) {
								//	driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[2]/button[4]")).click();
								//	System.out.println("lastweek done!");
								//	break;
								//}	
							//}
							
							while(true) {
										try {
											Thread.sleep(1000);
										}catch(Exception ee) {ee.printStackTrace();}
										System.out.println("start date waiting!");
										if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[1]/input")).size() != 0) {
											driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[1]/input")).clear();
											
											try {
												Thread.sleep(2000);
											}catch(Exception ee) {ee.printStackTrace();}
											driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[1]/input")).sendKeys(endDate);
											System.out.println("lastweek done!");
											break;
										}	
									}
							try {
								Thread.sleep(2000);
							}catch(Exception ee) {ee.printStackTrace();}
								
							while(true) {
								try {
									Thread.sleep(1000);
								}catch(Exception ee) {ee.printStackTrace();}
								System.out.println("end date waiting!");
								if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[2]/input")).size() != 0) {
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[2]/input")).clear();
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[2]/input")).clear();
									try {
										Thread.sleep(2000);
									}catch(Exception ee) {ee.printStackTrace();}
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[2]/input")).sendKeys(startDate);
									try {
										Thread.sleep(2000);
									}catch(Exception ee) {ee.printStackTrace();}
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[1]/div[2]/div[2]/div[2]/input")).sendKeys(Keys.RETURN);
									System.out.println("lastweek done!");
									break;
								}	
							}
							
							
							
							while(true) {
								try {
									Thread.sleep(1000);
								}catch(Exception ee) {ee.printStackTrace();}
								System.out.println("dropdown download waiting!");
								if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/div/div/button")).size() != 0) {
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/div/div/button")).click();
									System.out.println("dropdown download done!");
									break;
								}
									
							}
							
							while(true) {
								try {
									Thread.sleep(1000);
								}catch(Exception ee) {ee.printStackTrace();}
								System.out.println("download waiting!");
								if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/div/div/div/span[3]/form/button")).size() != 0) {
									driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/div/div/div/span[3]/form/button")).click();
									System.out.println("download done!");
									break;
								}	
							}
							Process_CSV csv = new Process_CSV(driver,this);
							csv.start();
							suspend();
							try {
								Thread.sleep(10000);
							}catch(Exception ee) {ee.printStackTrace();}
							
							gui.textAppend("Download CSV complete\n");
							currentdate = dateformat1.format(new Date());
							break;
				}
			 }	
		}		
		
	}
	
	public void setBrowser() {		
		System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
		HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "C:"+File.separator+"Square_download");
		chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
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
	
	class GUI  
	{   JTextArea area;
	
	     GUI(){  
		    	 area=new JTextArea();
		    	 area.setEditable(false);
		    	
		    	 
		    	 JScrollPane scrollableTextArea = new JScrollPane(area);
				   scrollableTextArea.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
				        public void adjustmentValueChanged(AdjustmentEvent e) {  
				            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
				        }
				    });
		    	 JFrame frame=new JFrame("Square Bot is Running");
		    	 frame.add(scrollableTextArea);
		    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	 frame.setSize(500,300);
		    	 frame.setLocationRelativeTo(null);
		    	 frame.setVisible(true);
	     }
	     public void textAppend(String stringIn) {
	    	 area.append(stringIn);
	     }
	     public void textClear() {
	    	 area.setText("");
	     }
	}
	
}
