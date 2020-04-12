import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JJClass {
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/jjjagtap";
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Connection conn = null;
    static String user = "jjjagtap";
    static String passwd = "200311438";

    public static void main(String[] args) {


            // Load the driver. This creates an instance of the driver
	    // and calls the registerDriver method to make MariaDB Thin
	    // driver, available to clients.


    try {
        Class.forName("org.mariadb.jdbc.Driver");
    } catch (Exception e) {
        System.out.println("Driver missing!");
    }
    try {
            //Functions
            //total_revenue_for_each_city();
           // revenue_for_city();
    		//get_total_expense();
    		//get_staff_payment_remaining();
    		//get_staff_payment_availed();
    		//find_distributors_by_city();
    		//check_editor_publications();
    		//totalprice_perisbn_perdistributor_permonth();
    		calculate_payment_within_daterange();
    }catch(Exception e){
        System.out.println(e);
    }
    }

	public static void total_revenue_for_each_city() {
		PreparedStatement ps = null;
	    try {
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
			String query = "select d.city,sum(d.amountowed) + sum(o.cost) + sum(o.shippingcost) as \"Revenue Generated\"\r\n" + 
		    		"from Distributors d left join Orders o on d.id=o.distributorid group by d.city;";
		    Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        List<String> l = new ArrayList<String>();
	        l.add("City");
	        l.add("Revenue");
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i<l.size(); i++)
	        	sb.append(String.format("| %-15s", l.get(i)));
	        System.out.println(sb);
	        while (rs.next()) {
	        	l.clear();
	        	sb.setLength(0);
	            int revenue = rs.getInt("Revenue Generated");
	            String city = rs.getString("city");
	            l.add(city);
	            l.add(Integer.toString(revenue));
	            for(int i=0; i<l.size(); i++)
		        	sb.append(String.format("| %-15s", l.get(i)));
		        System.out.println(sb);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     }
	
	public static void revenue_for_city() {
		PreparedStatement ps = null;
		System.out.println("Enetr the name of the city to get revenue details: ");
		
		Scanner sc = new Scanner(System.in);
		int flag = 0;
		String city = "";
		
        try{
        	conn = DriverManager.getConnection(jdbcURL, user, passwd);
        	city = br.readLine();
            String sql_chk = "select city from Distributors WHERE city = ?;"; 
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true){
                flag = 1;
            }
            else{
                System.out.println("City does not exist in Distributor table");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    
        if(flag == 1) {
        	//System.out.println("Came in try after flag = 1");
        	try {
    			conn = DriverManager.getConnection(jdbcURL, user, passwd);
    			String query = "select d.city,sum(d.amountowed) + sum(o.cost) + sum(o.shippingcost) as \"Revenue Generated\"\r\n" + 
    					"from Distributors d join Orders o on d.id=o.distributorid\r\n" + 
    					" and d.city = ?;";
    			ps = null;
    			ps = conn.prepareStatement(query);
    			ps.setString(1, city);
    		    //Statement stmt = conn.createStatement();
    	        ResultSet rs = ps.executeQuery();
    	        List<String> l = new ArrayList<String>();
    	        l.add("City");
    	        l.add("Revenue");
    	        StringBuilder sb = new StringBuilder();
    	        for(int i=0; i<l.size(); i++)
    	        	sb.append(String.format("| %-15s", l.get(i)));
    	        System.out.println(sb);
    	        while (rs.next()) {
    	        	l.clear();
    	        	sb.setLength(0);
    	            int revenue = rs.getInt("Revenue Generated");
    	            String city_name = rs.getString("city");
    	            l.add(city_name);
    	            l.add(Integer.toString(revenue));
    	            for(int i=0; i<l.size(); i++)
    		        	sb.append(String.format("| %-15s", l.get(i)));
    		        System.out.println(sb);
    	         }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

        }
	    
     }
	
	public static void get_total_expense() {
		PreparedStatement ps = null;
	    try {
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
			String query = "select sum(payment) from Pays;";
		    Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        List<String> l = new ArrayList<String>();
	        l.add("Total Expense");
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i<l.size(); i++)
	        	sb.append(String.format("| %-15s", l.get(i)));
	        System.out.println(sb);
	        while (rs.next()) {
	        	l.clear();
	        	sb.setLength(0);
	            int expense = rs.getInt("sum(payment)");
	            l.add(Integer.toString(expense));
	            for(int i=0; i<l.size(); i++)
		        	sb.append(String.format("| %-15s", l.get(i)));
		        System.out.println(sb);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//API: get staff payment remaining
	public static void get_staff_payment_remaining() {
		PreparedStatement ps = null;
	    try {
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
			String query = "select staffid, sum(payment) from Pays where TrackingPayment = \"No\" group by staffid";
		    Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        List<String> l = new ArrayList<String>();
	        l.add("staffid");
	        l.add("sum(payment remaining)");
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i<l.size(); i++)
	        	sb.append(String.format("| %-15s", l.get(i)));
	        System.out.println(sb);
	        while (rs.next()) {
	        	l.clear();
	        	sb.setLength(0);
	        	int id = rs.getInt("staffid");
	            int payment = rs.getInt("sum(payment)");
	        	l.add(Integer.toString(id));
	            l.add(Integer.toString(payment));
	            for(int i=0; i<l.size(); i++)
		        	sb.append(String.format("| %-15s", l.get(i)));
		        System.out.println(sb);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//API: get staff payment availed
		public static void get_staff_payment_availed() {
			PreparedStatement ps = null;
		    try {
				conn = DriverManager.getConnection(jdbcURL, user, passwd);
				String query = "select staffid, sum(payment) from Pays where TrackingPayment = \"Yes\" group by staffid ";
			    Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        
		        List<String> l = new ArrayList<String>();
		        l.add("staffid");
		        l.add("sum(payment availed)");
		        StringBuilder sb = new StringBuilder();
		        for(int i=0; i<l.size(); i++)
		        	sb.append(String.format("| %-15s", l.get(i)));
		        System.out.println(sb);
		        while (rs.next()) {
		        	l.clear();
		        	sb.setLength(0);
		        	int id = rs.getInt("staffid");
		            int payment = rs.getInt("sum(payment)");
		        	l.add(Integer.toString(id));
		            l.add(Integer.toString(payment));
		            for(int i=0; i<l.size(); i++)
			        	sb.append(String.format("| %-15s", l.get(i)));
			        System.out.println(sb);
		         }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
		
		//API: Find distributors by city
		public static void find_distributors_by_city() {
			PreparedStatement ps = null;
			System.out.println("Enter the name of the city to get distributors details in that city: ");
		
			int flag = 0;
			String city = "";
			
	        try{
	        	conn = DriverManager.getConnection(jdbcURL, user, passwd);
	        	city = br.readLine();
	            String sql_chk = "select city from Distributors WHERE city = ?;"; 
	            ps = conn.prepareStatement(sql_chk);
	            ps.setString(1, city);
	            ResultSet rs = ps.executeQuery();
	            
	            if(rs.next() == true){
	                flag = 1;
	            }
	            else{
	                System.out.println("City does not exist in Distributor table");
	            }
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    
	        if(flag == 1) {
	        	//System.out.println("Came in try after flag = 1");
	        	try {
	    			conn = DriverManager.getConnection(jdbcURL, user, passwd);
	    			String query = "SELECT * FROM Distributors WHERE city = \"New York City\" OR city = ?;";
	    			ps = null;
	    			ps = conn.prepareStatement(query);
	    			ps.setString(1, city);
	    		    //Statement stmt = conn.createStatement();
	    	        ResultSet rs = ps.executeQuery();
	    	        List<String> l = new ArrayList<String>();
	    	        l.add("id");
	    	        l.add("name");
	    	        l.add("City");
	    	        l.add("Phone Number");
	    	        l.add("Type");
	    	        l.add("Amount Owed");
	    	        l.add("Contact Person");
	    	        StringBuilder sb = new StringBuilder();
	    	        for(int i=0; i<l.size(); i++)
	    	        	sb.append(String.format("| %-17s", l.get(i)));
	    	        System.out.println(sb);
	    	        while (rs.next()) {
	    	        	l.clear();
	    	        	sb.setLength(0);
	    	        
	    	            int id = rs.getInt("id");
	    	            String name = rs.getString("name");
	    	            String city_name = rs.getString("city");
	    	            String phone = rs.getString("phonenumber");
	    	            String type = rs.getString("type");
	    	            int amount = rs.getInt("amountowed");
	    	            String person = rs.getString("contactperson");
	    	            
	    	            l.add(Integer.toString(id));
	    	            l.add(name);
	    	            l.add(city_name);
	    	            l.add(type);
	    	            l.add(Integer.toString(amount));
	    	            l.add(person);
	    	            
	    	            
	    	            for(int i=0; i<l.size(); i++)
	    		        	sb.append(String.format("| %-17s", l.get(i)));
	    		        System.out.println(sb);
	    	         }
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}

	        }
		}
	
	
	public static void check_editor_publications() {
		PreparedStatement ps = null;
		System.out.println("Enter Editor ID: ");
	
		int flag = 0;
		String id = "";
		
        try{
        	conn = DriverManager.getConnection(jdbcURL, user, passwd);
        	id = br.readLine();
            String sql_chk = "select editorid from Assign WHERE editorid = ?;"; 
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true){
                flag = 1;
            }
            else{
                System.out.println("Editor ID not in Assign Table");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    
        if(flag == 1) {
        	//System.out.println("Came in try after flag = 1");
        	try {
    			conn = DriverManager.getConnection(jdbcURL, user, passwd);
    			String query = "SELECT editorid, ISBN FROM Assign WHERE editorid = ?;";
    			ps = null;
    			ps = conn.prepareStatement(query);
    			ps.setString(1, id);
    		    //Statement stmt = conn.createStatement();
    	        ResultSet rs = ps.executeQuery();
    	        List<String> l = new ArrayList<String>();
    	        l.add("Editor id");
    	        l.add("ISBN");
    	        StringBuilder sb = new StringBuilder();
    	        for(int i=0; i<l.size(); i++)
    	        	sb.append(String.format("| %-17s", l.get(i)));
    	        System.out.println(sb);
    	        while (rs.next()) {
    	        	l.clear();
    	        	sb.setLength(0);
    	        
    	            int editorid = rs.getInt("editorid");
    	            String name = rs.getString("ISBN");
    	            
    	            l.add(Integer.toString(editorid));
    	            l.add(name);
    	            
    	            for(int i=0; i<l.size(); i++)
    		        	sb.append(String.format("| %-17s", l.get(i)));
    		        System.out.println(sb);
    	         }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

        }
	}
	
	
	public static void totalprice_perisbn_perdistributor_permonth() {
		PreparedStatement ps = null;
	    try {
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
			String query = "select MONTH(o.orderdate) as Month,o.ISBN, o.distributorid, sum(p.price*o.numcopies) as \"Price Generated\" from Orders o join Publication p on o.ISBN where o.ISBN = p .ISBN group by MONTH(o.orderdate), o.ISBN, o.distributorid;";
		    Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        List<String> l = new ArrayList<String>();
	        l.add("Month");
	        l.add("ISBN");
	        l.add("Distributor ID");
	        l.add("Price Generated");
	        
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i<l.size(); i++)
	        	sb.append(String.format("| %-15s", l.get(i)));
	        System.out.println(sb);
	        while (rs.next()) {
	        	l.clear();
	        	sb.setLength(0);
	            int month = rs.getInt("MONTH");
	            String ISBN = rs.getString("o.distributorid");
	            int distributorid = rs.getInt("o.distributorid");
	            int price = rs.getInt("Price Generated");
	            
	            
	            l.add(Integer.toString(month));
	            l.add(ISBN);
	            l.add(Integer.toString(distributorid));
	            l.add(Integer.toString(price));
	            for(int i=0; i<l.size(); i++)
		        	sb.append(String.format("| %-15s", l.get(i)));
		        System.out.println(sb);
	         }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void calculate_payment_within_daterange() {
		
		String mydate1 = "";
		String mydate2 = "";
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );  // United States style of format.
		format.setLenient(false);
		java.util.Date myDate1 =  new java.util.Date();
		java.util.Date myDate2 = new java.util.Date();
		
		
		int flag = 1;
		do {
		try {
			System.out.println("Enter Begin of Date Range");
			mydate1 = br.readLine();
			try {
				myDate1 = format.parse( mydate1 );
				flag = 1;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				flag = 0;
				System.out.println("Invalid Date, Please Enter again");
				//e.printStackTrace();
			}		
			//System.out.println(mydate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}while(flag == 0);
		
		flag = 1;
		do {
		try {
			System.out.println("Enter End of Date Range");
			mydate2 = br.readLine();
			try {
				myDate2 = format.parse( mydate2 );
				flag = 1;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				flag = 0;
				System.out.println("Invalid Date, Please Enter again");
				//e.printStackTrace();
			}		
			//System.out.println(mydate2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}while(flag == 0);
		
		PreparedStatement ps = null;
		
		flag = 0;
		
        try{
        	conn = DriverManager.getConnection(jdbcURL, user, passwd);
            String sql_chk = "select staffid, sum(payment) from Pays where paydate between ? and ? group by staffid"; 
            ps = conn.prepareStatement(sql_chk);
            java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());
            ps.setDate(1, sqlDate1);
            ps.setDate(2, sqlDate2);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() == true){
                flag = 1;
            }
            else{
                System.out.println("No results for this date range ");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    
        if(flag == 1) {
        	//System.out.println("Came in try after flag = 1");
        	try {
        		conn = DriverManager.getConnection(jdbcURL, user, passwd);
                String sql_chk = "select staffid, sum(payment) from Pays where paydate between ? and ? group by staffid"; 
                ps = conn.prepareStatement(sql_chk);
                java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());
                java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());
                ps.setDate(1, sqlDate1);
                ps.setDate(2, sqlDate2);
                ResultSet rs = ps.executeQuery();
    	        List<String> l = new ArrayList<String>();
    	        l.add("Staff ID");
    	        l.add("Total Payments");
    	        StringBuilder sb = new StringBuilder();
    	        for(int i=0; i<l.size(); i++)
    	        	sb.append(String.format("| %-15s", l.get(i)));
    	        System.out.println(sb);
    	        while (rs.next()) {
    	        	l.clear();
    	        	sb.setLength(0);
    	        	int id = rs.getInt("staffid");
    	            int revenue = rs.getInt("sum(payment)");
    	            l.add(Integer.toString(id));
    	            l.add(Integer.toString(revenue));
    	            for(int i=0; i<l.size(); i++)
    		        	sb.append(String.format("| %-15s", l.get(i)));
    		        System.out.println(sb);
    	         }
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

        }

		
	}
}