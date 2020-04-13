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
			System.out.println("3. Update Editor");
			System.out.println("4. Update Journalist");
			System.out.println("5. Update Staff");
			System.out.println("6. Delete Staff");
			System.out.println("7. Add Publications");
			System.out.println("8. Update Publication");
			System.out.println("2. Update Author");
			System.out.println("3. Update Editor");
			System.out.println("4. Update Journalist");
			System.out.println("5. Update Staff");
			System.out.println("6. Delete Staff");
			System.out.println("0. Exit");
			
			
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
			 case 2:
				try {
					a.update_author();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 break;
			 case 3:
				 try {
					a.update_editor();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 4:
				 try {
					a.update_journalist();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 5:
				 try {
					a.update_staff();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 6:
				 try {
					a.delete_staff();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 7:
				 try {
					a.add_publications();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 8:
				 try {
					a.update_publication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 default: System.out.println("Invalid Entry, try Again :)"); 
			break;
			 	
			 }
	            
		}while(choice != 0);
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
        } while (choice == -1);
        return choice;
    }
	
}
