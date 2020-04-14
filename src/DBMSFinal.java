import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;


public class DBMSFinal {
	
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/nkashya";
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Connection conn = null;
    static String user = "nkashya";
    static String passwd = "200314563";

	public static void main(String[] args) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Driver missing!");
		}
		
		int choice;
		do {
			System.out.println("");
			System.out.println("");
			System.out.println("*********************************");
			System.out.println("Welcome to WolfPub DB, best in NC");
			System.out.println("*********************************");
			System.out.println("1. Manager");
			System.out.println("2. Editor");
			System.out.println("3. Author");
			System.out.println("4. Journalist");
			System.out.println("5. Admin");
			choice = getMenuChoice();
			switch (choice) {
			case 0:
				break;
			 case 1: 
					runManager();
			 break;
			 case 2:		
					runEditor();
			 break;
			 case 3:
					runAuthor();
				 break;
			 case 4:
					 runJournalist();
				 break;
			 case 5:
				 runAdmin();
			 break;
			default:
				System.out.println("Invalid Entry, Enter again");
			}
			}while(choice != 0);
		System.out.println("Thank You!! Have a Good Day!!");
		
	}

	public static void runAuthor(){
		System.out.println("");
		System.out.println("");
		System.out.println("***********************");
		System.out.println("Welcome to Authors View");
		System.out.println("***********************");
		System.out.println("");
		System.out.println("The following are the operations that can be performed by the Author");
		System.out.println("Please select the number correspondingly");
		System.out.println("");
		System.out.println("");
		int choice;
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
			System.out.println("0. To Exit the Author View");
			System.out.println("");
			System.out.println("");
			System.out.println("Please Enter the operation number to be performed");
			System.out.println("");
			System.out.println("");
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
		if(choice!=0){
		System.out.println("");
		System.out.println("");
		System.out.println("Please Enter 0 to logout of Author's View");
		System.out.println("Please Enter 1 to Continue in Author's View");
		choice = getMenuChoice();
	}

	}while(choice!=0);
}

	public static void runEditor(){
		System.out.println("");
		System.out.println("");
		System.out.println("***********************");
		System.out.println("Welcome to Editors View");
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
			System.out.println("");
			System.out.println("");
			System.out.println("Please Enter the operation number to be performed");
			System.out.println("");
			System.out.println("");
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
			if(choice!=0){
		System.out.println("");
		System.out.println("");
		System.out.println("Please Enter 0 to logout of Editor's View");
		System.out.println("Please Enter 1 to Continue in Editor's View");
		choice = getMenuChoice();
	}
		}while(choice!=0);
	}

	public static void runJournalist() {
		int choice;
		API a = new API();
		do {
			System.out.println("");
			System.out.println("");
			System.out.println("***********************");
			System.out.println("Welcome to Journalist View");
			System.out.println("***********************");
			System.out.println("");
			System.out.println("");
			System.out.println("The following are the operations that can be performed by the Journalist");
			System.out.println("Please select the number correspondingly");
			System.out.println("");
			System.out.println("");
			System.out.println("1. Display Periodicals");
			System.out.println("2. Display Articles");
			System.out.println("3. Display Publication");
			System.out.println("4. Add Articles");
			System.out.println("5. Total Articles");
			System.out.println("6. Display Edit Periodicals");
			System.out.println("7. Update Periodicals");
			System.out.println("8. Update Article Text ");
			System.out.println("0. Exit");
			System.out.println("");
			System.out.println("");
			System.out.println("Please enter operation number to be performed");
			System.out.println("");
			System.out.println("");
			choice = getMenuChoice();
			 switch (choice) {
			 case 1: try {
					a.displayPeriodicals();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			 break;
			 case 2:
				try {
					a.displayArticles();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 break;
			 case 3:
				 try {
					a.displayPublication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 4:
				 try {
					 a.add_articles();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 5:
				 try {
					a.total_articles();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 6:
				 try {

					a.total_articles();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 7:
				 try {
					a.update_periodicals();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 8:
				 try {

					a.update_articles_text();;

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;

			 default: System.out.println("Exiting Journalist View :)"); 	
			 }
			 if(choice!=0){
	            System.out.println("");
		System.out.println("");
		System.out.println("Please Enter 0 to logout of Journalist's View");
		System.out.println("Please Enter 1 to Continue in Journalist's View");
		choice = getMenuChoice();
}
		}while(choice != 0);
	}
	
	public static void runManager() {
		int choice;
		API a = new API();
		
		do {
			System.out.println("");
			System.out.println("");
			System.out.println("***********************");
			System.out.println("Welcome to Managers View");
			System.out.println("***********************");
			System.out.println("");
			System.out.println("");
			System.out.println("The following are the operations that can be performed by the Manager");
			System.out.println("Please select the number correspondingly");
			System.out.println("");
			System.out.println("");
			System.out.println("1. Add Staff");
			System.out.println("2. Update Author");
			System.out.println("3. Update Editor");
			System.out.println("4. Update Journalist");
			System.out.println("5. Update Staff");
			System.out.println("6. Delete Staff");
			System.out.println("7. Add Publications");
			System.out.println("8. Update Publication");
			System.out.println("9. Delete Publication");
			System.out.println("10. Delete Order");
			System.out.println("11. Update Order");
			System.out.println("12. Display Staff Details");
			System.out.println("13. Display Publications");
			System.out.println("14. Add Distributor");
			System.out.println("15. Delete Distributor");
			System.out.println("16. Bill Distributor");
			System.out.println("17. Update Distributor Balance");
			System.out.println("18. Assign Editor to Publication");
			System.out.println("19. Know publication for editor");
			System.out.println("20. Add Payment");
			System.out.println("21. Generate Distributor Report");
			System.out.println("22. View Report of all Distributors");
			System.out.println("23. Generated Distributor report by Month");
			System.out.println("24. View Orders by Month");
			System.out.println("25. Get Total Distributor Count");
			System.out.println("25. Get Total Expense");
			System.out.println("26. Update Distributor");
			System.out.println("27. Total Revenue for each city");
			System.out.println("28. Check Total Price per ISBN per distributor per month");
			System.out.println("29. Calculate Payment within Date Range");
			System.out.println("30. Get Orders between Date Range");
			System.out.println("31. Display Payment History");
			System.out.println("32. Display Orders");
			System.out.println("33. Display Publication using Title or Topic");
			System.out.println("0. Exit");
			System.out.println("");
			System.out.println("");
			
			System.out.println("Please enter operation number to be performed");
			System.out.println("");
			System.out.println("");
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
			 case 9:
				 try {
					a.delete_publication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 10:
				 try {
					a.delete_order();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 11:
				 try {
					a.update_order();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 12:
				 try {
					a.displayStaff();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 13:
				 try {
					a.displayPublication();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 14:
				 try {
					a.add_distributor();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 15:
				 try {
					a.delete_distributor();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 16:
				 try {
					a.bill_distributor();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 17:
				 try {
					a.update_distributor_balance();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 18:
				 try {
					a.assign();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 19:
				 try {
					a.publication_by_editor();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 20:
				 try {
					a.insert_pay();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 21:
				 try {
					a.generate_distributor_report();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 22:
				 try {
					a.generate_distributors_report();;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 23:
				 try {
					a.distributor_report_by_month();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 24:
				 try {
					a.orders_by_month();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 25:
				 try {
					a.total_expense();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 26:
				 try {
					a.Revenue_by_City();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 27:
				 try {
					a.total_revenue_for_each_city();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 28:
				 try {
					a.totalprice_perisbn_perdistributor_permonth();;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 29:
				 try {
					a.calculate_payment_within_daterange();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 30:
				 try {
					a.orders_date_range();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 
			 case 31:
				 try {
					a.display_pays();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 32:
				 try {
					a.display_orders();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
				case 33:
				 try {
					a.display_events();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 default: System.out.println("Exiting Manager View :)"); 	
			 }
			 if(choice!=0){
			 System.out.println("");
		System.out.println("");
		System.out.println("Please Enter 0 to logout of Manager's View");
		System.out.println("Please Enter 1 to Continue in Manager's View");
		choice = getMenuChoice();
	}
		}while(choice != 0);
	}
	
	public static void runAdmin() {
		System.out.println("");
		System.out.println("");
		System.out.println("***********************");
		System.out.println("You are in Admins View");
		System.out.println("***********************");
		System.out.println("");
		System.out.println("");
		System.out.println("The following are the operations that can be performed by the Admin");
		System.out.println("Please select the number correspondingly");
		System.out.println("");
		System.out.println("");

		int choice;;
		API obj = new API();

		do{
			System.out.println("1. Add Manager");
			System.out.println("2. Update Manager");
			System.out.println("3. Delete Manager");
			System.out.println("0. To Exit the Manager View");
			System.out.println("");
			System.out.println("");

			System.out.println("Please Enter the operation number to be performed");
			System.out.println("");
			System.out.println("");
			choice = getMenuChoice();
			System.out.println("Choice: " + choice);
			switch (choice){
				case 1 : try{
					obj.add_manager();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				break;
			 case 2:
				try {
					obj.update_manager();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 break;
			 case 3:
				 try {
					obj.delete_manager();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 default: 
			 System.out.println("Invalid Entry, try Again :)"); 
			 												
		}
		if(choice!=0){
		System.out.println("");
		System.out.println("");
		System.out.println("Please Enter 0 to logout of Admin's View");
		System.out.println("Please Enter 1 to Continue in Admin's View");
		choice = getMenuChoice();
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
            	choice = -1;
                System.out.println("Invalid selection. Numbers only please.");
            }
        } while (choice == -1);
        return choice;
    }
	
}
