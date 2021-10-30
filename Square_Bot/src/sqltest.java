import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class sqltest {
	Statement st;
	public sqltest() {
		setDataBaseConnection();
		
		try {
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
			
		}catch(Exception ee) {}
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
	 public static void main(String args[]) {new sqltest();}

}
