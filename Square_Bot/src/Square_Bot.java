import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Square_Bot {
	WebDriver driver;
	String username;
	String password;
	GUI gui;
	
	public Square_Bot() {
		gui = new GUI();
		
	}
	public void Login() {
		
		gui.textAppend("Starting Login\n");
		driver.get("https://squareup.com/login");
		driver.findElement(By.id("email")).sendKeys("cmeys@nbvresorts.com");
		driver.findElement(By.id("password")).sendKeys("Breakers99");
		driver.findElement(By.id("sign-in-button")).click();
		gui.textAppend("Login complete\n");
		try {
			Thread.sleep(4000);
		}catch(Exception ee) {ee.printStackTrace();}
		
	}
	public void process() {
		gui.textAppend("Processing\n");
		driver.get("https://squareup.com/dashboard/sales/transactions");
		
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
		
		while(true) {
			try {
				Thread.sleep(1000);
			}catch(Exception ee) {ee.printStackTrace();}
			System.out.println("lastweek waiting!");
			if(driver.findElements(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[2]/button[4]")).size() != 0) {
				driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div/div/div[2]/button[4]")).click();
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
		gui.textAppend("Download CSV complete\n");
		
	}
	
	public void setBrowser() {		
		System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
		HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		//chromePrefs.put("download.default_directory", "C:"+File.separator+"xampp"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon");
		chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
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
