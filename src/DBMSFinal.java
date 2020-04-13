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
		runEditor();
		runAuthor();
	}
	
	public static void runAuthor(){
		System.out.println("");
		System.out.println("");
		System.out.println("***********************");
		System.out.println("You are in Authors View");
		System.out.println("***********************");
		System.out.println("");
		System.out.println("");
		System.out.println("The following are the operations that can be performed by the Editor");
		System.out.println("Please select the number correspondingly");
		System.out.println("");
		System.out.println("");
		int choice;;
		API obj = new API();

		do{
			System.out.println("1. Update Books");
			System.out.println("2. Add Chapters");
			System.out.println("3. Total Chapters for a Particular Book");
			System.out.println("4. Update Chapter Texts");
			System.out.println("5. Display Publication");
			System.out.println("6. Display Books");
			System.out.println("7. Display Chapters");
			System.out.println("8. Display EditBooks");

			System.out.println("Please Enter the operation number to be performed");
			choice = getMenuChoice();
			switch (choice){
				case 1 : try{
					obj.update_books();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				break;
			 case 2:
				try {
					obj.add_chapters();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 break;
			 case 3:
				 try {
					obj.total_chapters();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 4:
				 try {

					obj.update_chapters_text();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 5:
				 try {
					obj.displayPublication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 6:
				 try {
					obj.displayBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 7:
				 try {
					obj.displayChapters();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 8:
				 try {
					obj.displayEditBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			default: System.out.println("Invalid Entry, try Again :)"); 
			break;
		}
	}while(choice!=0);
}

	public static void runEditor(){
		System.out.println("***********************");
		System.out.println("You are in Editors View");
		System.out.println("***********************");

		System.out.println("");
		System.out.println("");
		System.out.println("The following are the operations that can be performed by the Editor");
		System.out.println("Please select the number correspondingly");
		System.out.println("");
		System.out.println("");
		int choice;;
		API obj = new API();

		do{
			System.out.println("1. Update Books");
			System.out.println("2. Update Periodicals");
			System.out.println("3. Update Publication");
			System.out.println("4. Display Total Number of Chapters for an ISBN");
			System.out.println("5. Display Total Number of Articles for an ISBN");
			System.out.println("6. Display Total Number of Articles");
			System.out.println("7. Update Article Texts");
			System.out.println("8. Update Chapter Texts");
			System.out.println("9. Delete an Article for an ISBN");
			System.out.println("10. Display Periodicals");
			System.out.println("11. Display Publication");
			System.out.println("12. Display Articles");
			System.out.println("13. Display Books");
			System.out.println("14. Display Chapters");
			System.out.println("15. Display EditBooks");
			System.out.println("16. Display EditPeriodicals");
			System.out.println("0. To Exit the Editor View");

			System.out.println("Please Enter the operation number to be performed");
			choice = getMenuChoice();
			switch (choice){
				case 1 : try{
					obj.update_books();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				break;
			 case 2:
				try {
					obj.update_periodicals();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 break;
			 case 3:
				 try {
					obj.update_publication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 4:
				 try {
					obj.total_chapters();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 5:
				 try {
					obj.total_articles();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 6:
				 try {
					obj.total_number_articles();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 7:
				 try {
					obj.update_articles_text();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 8:
				 try {
					obj.update_chapters_text();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;

				 case 9:
				 try {
					obj.delete_article();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 10:
				 try {
					obj.displayPeriodicals();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 11:
				 try {
					obj.displayPublication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 12:
				 try {
					obj.displayArticles();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 13:
				 try {
					obj.displayBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 14:
				 try {
					obj.displayChapters();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 15:
				 try {
					obj.displayEditBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				 case 16:
				 try {
					obj.displayEditPeriodicals();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 default: System.out.println("Invalid Entry, try Again :)"); 
			break;
			 
			}
		}while(choice!=0);
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
