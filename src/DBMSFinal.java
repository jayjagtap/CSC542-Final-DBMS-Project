import java.sql.*;


public class DBMSFinal {
	
	static Connection conn = null;
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/jjjagtap";
	private static final String user = "jjjagtap";
	private static final String password = "200311438";

	public static void main(String[] args) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Driver missing!");
		}

		try {
			conn = DriverManager.getConnection(jdbcURL, user, password);
			
			//Our methods go here
			addStaff(conn);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("=========== Closed connection ===========");
		}

	}
	
	public static void addStaff(Connection conn) {
	Statement statement = null;
    ResultSet result = null;
    try {
		statement = conn.createStatement();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	try {
		statement.executeUpdate("INSERT INTO Staff values('Bond',777,'9043296128','Champion Court', 22, 'Male');");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
