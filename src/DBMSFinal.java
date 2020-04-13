import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;


public class DBMSFinal {
	
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/jjjagtap";
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Connection conn = null;
    static String user = "jjjagtap";
    static String passwd = "200311438";

	public static void main(String[] args) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Driver missing!");
		}
		
		runManager();
	}
	
	public static void runManager() {
		int choice;
		API a = new API();
		
		do {
			System.out.println("Welcome to Manager Section: Here are the APIS");
			System.out.println("1. Add Staff");
			System.out.println("2. Update Author");
			System.out.println("Welcome to Manager Section: Here are the APIS");
			System.out.println("Welcome to Manager Section: Here are the APIS");
			System.out.println("Welcome to Manager Section: Here are the APIS");
			System.out.println("Welcome to Manager Section: Here are the APIS");
			System.out.println("Welcome to Manager Section: Here are the APIS");
			
			
			System.out.println("Please enter operation number to be performed");
			choice = getMenuChoice();
			 switch (choice) {
			 case 1: try {
					a.add_staff();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			 break;
			 case 2: try {
					API.update_author();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			 break;
			 default: System.out.println("Invalid Entry, try Again :)"); 
				 
			 	
			 }
	            
		}while(false);
	}
	
	public static int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }
	
}
