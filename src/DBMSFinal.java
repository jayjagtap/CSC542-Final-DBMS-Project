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

	public static void runJournalist() {
		int choice;
		API a = new API();
		do {
			System.out.println("Welcome to Manager Section: Here are the APIS");
			System.out.println("1. Display Periodicals");
			System.out.println("2. Display Articles");
			System.out.println("3. Display Publication");
			System.out.println("4. Add Articles");
			System.out.println("5. Total Articles");
			System.out.println("6. Display Edit Periodicals");
			System.out.println("7. Update Periodicals");
			System.out.println("8. Update Article Text ");
			System.out.println("0. Exit");
			
			
			System.out.println("Please enter operation number to be performed");
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
	            
		}while(choice != 0);
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
			 default: System.out.println("Exiting Manager View :)"); 	
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
