// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Date;


public class Test {

    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/skumar29";
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Connection conn = null;
    static String user = "skumar29";
    static String passwd = "200314584";

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
            //Our Method Calls-------------------------
            //add_chapters();
            //add_articles();
            //total_chaps();
            //total_articles();
            //add_distributor();
            //delete_distributor();
            //bill_distributor();
            //update_distributor_balance();
            //total_number_articles();
            //update_chapters_text();
            //delete_article();
            assign();
    }catch(Exception e){
        System.out.println(e);
    }
}

static void add_chapters() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Add Chapters Menu:");
    String isbn = "";
    String texts = "";
    String tc_id = "";

    int c_id = 0;
    int flag = 1;
    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Books WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist in Books table");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    if(flag == 2){
        do {
            System.out.println("Enter the Text:");

            try{
                texts = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(texts.isEmpty());
    }

    if(flag == 2){
        do{
            System.out.println("Enter Chapter_ID:");

            try{
                tc_id = br.readLine();
                if(!tc_id.isEmpty()){
                    c_id = Integer.parseInt(tc_id);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(tc_id.isEmpty());
    }



    if(flag==2){
        sql_insert_stmt = "INSERT INTO Chapters(ISBN, texts, id) VALUES(?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, isbn);
        ps.setString(2, texts);
        ps.setInt(3, c_id);

        ps.executeUpdate();
    }
}

static void add_articles() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Add Articles Menu:");
    String isbn = "";
    String texts = "";
    String te_id = "";

    int e_id = 0;
    int flag = 1;
    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Periodicals WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist in the Periodicals table");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    if(flag == 2){
        do {
            System.out.println("Enter the Text:");

            try{
                texts = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(texts.isEmpty());
    }

    if(flag == 2){
        do{
            System.out.println("Enter Chapter_ID:");

            try{
                te_id = br.readLine();
                if(!te_id.isEmpty()){
                    e_id = Integer.parseInt(te_id);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(te_id.isEmpty());
    }



    if(flag==2){
        sql_insert_stmt = "INSERT INTO Articles(ISBN, texts, id) VALUES(?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, isbn);
        ps.setString(2, texts);
        ps.setInt(3, e_id);

        ps.executeUpdate();
    }
}

static void total_chapters() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Total Number of Chapters Menu:");

    String isbn = "";

    int flag = 1;
    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Books WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist in the Chapters table");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());


    if(flag==2){
        sql_insert_stmt = "SELECT COUNT(*) FROM Chapters WHERE ISBN = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, isbn);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println("Total number of chapters in ISBN - " + isbn + " = " + rs.getInt(1));
        }
    }
}

static void total_articles() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Total Number of Articles Menu:");

    String isbn = "";

    int flag = 1;
    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Periodicals WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist in the Periodicals table");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());


    if(flag==2){
        sql_insert_stmt = "SELECT COUNT(*) FROM Articles WHERE ISBN = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, isbn);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println("Total number of chapters in ISBN - " + isbn + " = " + rs.getInt(1));
        }
    }
}

static void add_staff() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;

    System.out.println("Add Enter Staff");

    String staff_name = "";
    String staff_id = "";
    String phone_number = "";
    String staff_address = "";
    String age = "";
    String staff_gender = "";
    int s_id;
    int s_age;
    do
    {
        System.out.println("Enter Staff ID");
        try{
            staff_id = br.readLine();
            if(!staff_id.isEmpty()){
                s_id = Integer.parseInt(staff_id);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_id.isEmpty());

    do
    {
        System.out.println("Enter Staff Name");
        try{
            staff_name = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_name.isEmpty());

    do
    {
        System.out.println("Enter Staff Phone Number");
        try{
            phone_number = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(phone_number.isEmpty());

    do
    {
        System.out.println("Enter Staff Address");
        try{
            staff_address= br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_address.isEmpty());

    do
    {
        System.out.println("Enter Staff age");
        try{
            age = br.readLine();
            if(!age.isEmpty()){
                s_age = Integer.parseInt(age)
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(age.isEmpty());

    do
    {
        System.out.println("Enter Staff Gender");
        try{
            staff_gender = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_gender.isEmpty());

    sql_insert_stmt = "INSERT INTO Staff VALUES(?,?,?,?,?,?);";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setInt(1, s_id);
    ps.setString(2, staff_name);
    ps.setString(3, phone_number);
    ps.setString(4, staff_address);
    ps.setInt(5, s_age);
    ps.setString(6, staff_gender);
    ps.executeUpdate();

    System.out.println("If the above staff is Author, enter 1");
    System.out.println("If the above staff is Journalist, enter 2");
    System.out.println("If the above staff is Editor, enter 3");
    int choice = getinput();

    if(choice==1)
    {
        String type = "";
        do{
        System.out.println("Enter Author Type");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());

        sql_insert_stmt = "INSERT INTO Authors VALUES(?,?);";
        ps.setInt(1, s_id);
        ps.setString(2, type);
        ps.executeUpdate();
    }
    else if(choice==2)
    {
        String type = "";
        do{
        System.out.println("Enter Editor Type");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());

        sql_insert_stmt = "INSERT INTO Editors VALUES(?,?);";
        ps.setInt(1, s_id);
        ps.setString(2, type);
        ps.executeUpdate();
    }
    else if(choice==3)
    {
        String type = "";
        do{
        System.out.println("Enter Journalist Type");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());

        sql_insert_stmt = "INSERT INTO Journalists VALUES(?,?);";
        ps.setInt(1, s_id);
        ps.setString(2, type);
        ps.executeUpdate();
    }
}

static void update_staff()  throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Staff");

    String staff_id = "";
    int s_id;

    do
    {
        System.out.println("Enter Staff ID");
        try{
            staff_id = br.readLine();
            if(!staff_id.isEmpty()){
                s_id = Integer.parseInt(staff_id);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_id.isEmpty());

}

static void delete_staff() throws SQLExveption {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Delete Staff Menu:");

    String staff_id = "";
    int s_id;
    int flag = 1;

    System.out.println("Enter 1 if the staff is Author");
    System.out.println("Enter 2 if the staff is Journalist");
    System.out.println("Enter 1 if the staff is Editor");
    int choice = getinput();

    do
    {
        System.out.println("Enter Staff ID");
        try{
            staff_id = br.readLine();
            if(!staff_id.isEmpty()){
                flag = 2;
                s_id = Integer.parseInt(staff_id);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_id.isEmpty());

    if(flag==2){
        sql_insert_stmt = "DELETE FROM Pays WHERE staffid = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, s_id);
        ps.executeUpdate();

        if(choice==1)
        {
            sql_insert_stmt = "DELETE from WriteBooks where authorid = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, s_id);
            ps.executeUpdate();
            flag=3;
        }

        else if(choice==2)
        {
            sql_insert_stmt = "DELETE from Writeperiodicals where journalistid = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, s_id);
            ps.executeUpdate();
            flag=3
        }

        else if(choice==3)
        {
            sql_insert_stmt = "DELETE from Assign where editorid = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, s_id);
            ps.executeUpdate();

            sql_insert_stmt = "DELETE from EditBooks where editorid = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, s_id);
            ps.executeUpdate();

            sql_insert_stmt = "DELETE from Editperiodicals where editorid = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, s_id);
            ps.executeUpdate();
            flag=3;
        }

        if(flag==3)
        {
        sql_insert_stmt = "DELETE FROM Staff WHERE id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, s_id);
        ps.executeUpdate();
    }
    else{
        System.out.println("Entered Choices are out of the scope and invalid");
    }
    }
    else{
        System.out.println("Entered Staff ID does not exist");
    }
}

static void add_publications() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Adding Publications Menu");

    String isbn = "";
    String title = "";
    String topic = "";
    String price = "";
    int cost;

    do{
        System.out.println("Enter ISBN Number");
        try{
            isbn = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    do{
        System.out.println("Enter Title");
        try{
            title = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(title.isEmpty());

    do{
        System.out.println("Enter Topic");
        try{
            topic = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(topic.isEmpty());

    do{
        System.out.println("Enter Price");
        try{
            price = br.readLine();
            if(!price.isEmpty()){
                cost = Integer.parseInt(price);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(price.isEmpty());

    do{
        System.out.println("Enter Date of Creation");
        try{
            java.util.Date date=new java.util.Date();
            java.sql.Date dateofcreation=new java.sql.Date(date.getTime());
            }catch(Exception e){
            System.out.println(e);
        }
    }while(dateofcreation.isEmpty())
    do{
        System.out.println("Enter Date of Publishing");
        try{
            java.util.Date date=new java.util.Date();
            java.sql.Date dateofpublishing=new java.sql.Date(date.getTime());
            }catch(Exception e){
            System.out.println(e);
        }
    }while(dateofpublishing.isEmpty())

    sql_insert_stmt = "INSERT into Publication values(?,?,?,?,?,?);";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, isbn);
    ps.setDate(2, dateofcreation);
    ps.setString(3, title);
    ps.setString(4, topic);
    ps.setDate(5, dateofpublishing);
    ps.setInt(6, cost);
    ps.executeUpdate();

    System.out.println("If the above publication is Books, enter 1");
    System.out.println("If the above publication is Periodicals, enter 2");
    int choice = getinput();

    if(choice==1)
    {
        String edition = "";
        do{
        System.out.println("Enter Edition");
        try{
            edition = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(edition.isEmpty());

        sql_insert_stmt = "INSERT INTO BOOKS VALUES(?,?);";
        ps.setString(1, isbn);
        ps.setString(2, edition);
        ps.executeUpdate();
    }

    if(choice==2)
    {
        String periodicity = "";
        String type = ""
        do{
        System.out.println("Enter Periodicity");
        try{
            periodicity = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(periodicity.isEmpty());

        do{
        System.out.println("Enter Type of the Periodicals");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());

        sql_insert_stmt = "INSERT INTO BOOKS VALUES(?,?,?);";
        ps.setString(1, isbn);
        ps.setString(2, periodicity);
        ps.setString(3,type);
        ps.executeUpdate();
    }      
}

static void delete_publication()  throws SQLException{

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Delete Publication Menu:");
    int flag=1;
    String ISBN = "";

    System.out.println("Enter 1 if it is a Book");
    System.out.println("Enter 2 if it is a Periodical");
    int choice = getinput();
    
    do{
        System.out.println("Enter ISBN Number");
        try{
            isbn = br.readLine();
            flag = 2;
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    if(flag==2){

        if(choice==1){
            
        }

        sql_insert_stmt = "DELETE from Publication where ISBN = ?;";
        ps.setString(1,isbn);
        ps.executeUpdate();
    }
}

static void add_distributor() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Add Distributor Menu:");

    String name = "";
    String phone_number = "";
    String city = "";
    String address = "";
    String type = "";
    String contact_person = "";
    String sd_id = "";
    String sa_o = "";

    int d_id = 0;

    double amount_owed = 0.0d;


    do {
        System.out.println("Enter Distributor ID:");
        try{
            sd_id = br.readLine();
            if(!sd_id.isEmpty()){
                d_id = Integer.parseInt(sd_id);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(sd_id.isEmpty());

    do {
        System.out.println("Enter the Distributor Name:");

        try{
            name = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(name.isEmpty());

    do{
            System.out.println("Enter Distributor Phone Number"); //ensure it's of 10 digits
            try{
                phone_number = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(phone_number.isEmpty());

        do{
            System.out.println("Enter Distributor City");
            try{
                city = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(city.isEmpty());

        do{
            System.out.println("Enter Distributor Address");
            try{
                address = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(address.isEmpty());

        do{
            System.out.println("Enter Distributor Type");
            try{
                type = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(type.isEmpty());

        do{
            System.out.println("Enter Distributor Owed Amount");
            try{
                sa_o = br.readLine();
                if(!sa_o.isEmpty()){
                    amount_owed = Double.parseDouble(sa_o);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(sa_o.isEmpty());
        
        do{
            System.out.println("Enter Distributor Contact Person");
            try{
                contact_person = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(contact_person.isEmpty());

        
        sql_insert_stmt = "INSERT INTO Distributors VALUES(?,?,?,?,?,?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, d_id);
        ps.setString(2, name);
        ps.setString(3, phone_number);
        ps.setString(4, city);
        ps.setString(5, address);
        ps.setString(6, type);
        ps.setDouble(7, amount_owed);
        ps.setString(8, contact_person);
        ps.executeUpdate();
}

static void delete_distributor() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Delete Distributor Menu:");
    String sd_id = "";

    int d_id = 0;
    int flag = 1;
    do {
        System.out.println("Enter Distributor ID:");
        try{
            sd_id = br.readLine();

            String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, sd_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag = 2;
                d_id = Integer.parseInt(sd_id);
                break;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(sd_id.isEmpty());
    
    if(flag==2){
        sql_insert_stmt = "DELETE FROM Distributors WHERE id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, d_id);

        ps.executeUpdate();
    }
    else{
        System.out.println("Entered Distributor ID does not exist");
    }

}

static void bill_distributor() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Bill Distributor Menu:");
    String sd_id = "";

    int d_id = 0;
    int flag = 1;
    do {
        System.out.println("Enter Distributor ID:");
        try{
            sd_id = br.readLine();

            String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, sd_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag = 2;
                d_id = Integer.parseInt(sd_id);
                break;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(sd_id.isEmpty());
    
    if(flag==2){
        sql_insert_stmt = "SELECT sum(cost) + sum(shippingcost) as distributor_Bill FROM Orders WHERE distributorid = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, d_id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println("Total Bill for Distributor ID - " + d_id + " = " + rs.getInt(1));
        }
    }
    else{
        System.out.println("Entered Distributor ID does not exist");
    }
}

static void update_distributor_balance() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    
    System.out.println("Update Distributor Balance Menu:");
    String sd_id = "";
    String da_a = "";

    int d_id = 0;
    int flag = 1;

    double amount_owed = 0.0d;

    do {
        System.out.println("Enter Distributor ID:");
        try{
            sd_id = br.readLine();
            if(!sd_id.isEmpty()){
                d_id = Integer.parseInt(sd_id);
                String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, d_id);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    flag = 2;
                    break;
                }
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }while(sd_id.isEmpty());

    do {
        System.out.println("Enter Distributor Balance:");
        try{
            da_a = br.readLine();
            if(!da_a.isEmpty()){
                amount_owed = Double.parseDouble(da_a);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(da_a.isEmpty());
    
    if(flag == 2){
        sql_insert_stmt = "UPDATE Distributors SET amountowed = ? WHERE id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setDouble(1, amount_owed);
        ps.setInt(2, d_id);
        ps.executeUpdate();
    }
    else{
        System.out.println("Entered Distributor ID does not exist");
    }
}

static void total_number_articles() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    //ystem.out.println("Total Number of Articles Menu:");
    
    
    sql_insert_stmt = "SELECT Count(*) AS Total_Number_of_Articles FROM Articles;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        System.out.println("Total number of Articles"+ " = " + rs.getInt(1));
    }
}

static void update_articles_text() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Update Article's Text Menu:");
    String isbn = "";
    String texts = "";
    String tc_id = "";

    int c_id = 0;
    int flag_o = 1;
    int flag_t = 1;

    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Articles WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag_o = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    if(flag_o == 2){
        do {
            System.out.println("Enter the Text:");
            
            try{
                texts = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(texts.isEmpty());
    }

    if(flag_o == 2){
        do{
            System.out.println("Enter Article_ID:");
            
            try{
                tc_id = br.readLine();
                if(!tc_id.isEmpty()){
                    c_id = Integer.parseInt(tc_id);

                    String sql_chk = "SELECT * FROM Articles WHERE id = ?;";
                    ps = conn.prepareStatement(sql_chk);
                    ps.setInt(1, c_id);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        flag_t = 2;
                        break;
                    }
                    else{
                        System.out.println("Article ID does not exist");
                    }
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
        }while(tc_id.isEmpty());
    }


    
    if(flag_o ==2 && flag_t ==2){
        sql_insert_stmt = "UPDATE Articles SET texts = ? WHERE ISBN = ? AND id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, texts);
        ps.setString(2, isbn);
        ps.setInt(3, c_id);

        ps.executeUpdate();
    }
}

static void update_chapters_text() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Update Chapter's Text Menu:");
    String isbn = "";
    String texts = "";
    String tc_id = "";

    int c_id = 0;
    int flag_o = 1;
    int flag_t = 1;

    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Chapters WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag_o = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    if(flag_o == 2){
        do {
            System.out.println("Enter the Updated Text:");
            
            try{
                texts = br.readLine();
            }catch(Exception e){
                System.out.println(e);
            }
        }while(texts.isEmpty());
    }

    if(flag_o == 2){
        do{
            System.out.println("Enter Chapter_ID:");
            
            try{
                tc_id = br.readLine();
                if(!tc_id.isEmpty()){
                    c_id = Integer.parseInt(tc_id);

                    String sql_chk = "SELECT * FROM Chapters WHERE id = ?;";
                    ps = conn.prepareStatement(sql_chk);
                    ps.setInt(1, c_id);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        flag_t = 2;
                        break;
                    }
                    else{
                        System.out.println("Chapter ID does not exist");
                    }
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
        }while(tc_id.isEmpty());
    }


    
    if(flag_o ==2 && flag_t ==2){
        sql_insert_stmt = "UPDATE Chapters SET texts = ? WHERE ISBN = ? AND id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, texts);
        ps.setString(2, isbn);
        ps.setInt(3, c_id);

        ps.executeUpdate();
    }
}

static void delete_article() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Delete Article Menu:");
    String isbn = "";
    String tc_id = "";

    int c_id = 0;
    int flag_o = 1;
    int flag_t = 1;

    do {
        System.out.println("Enter ISBN:");
        try{
            isbn = br.readLine();

            String sql_chk = "SELECT * FROM Articles WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                flag_o = 2;
                break;
            }
            else{
                System.out.println("ISBN does not exist");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());


    if(flag_o == 2){
        do{
            System.out.println("Enter Article_ID:");
            
            try{
                tc_id = br.readLine();
                if(!tc_id.isEmpty()){
                    c_id = Integer.parseInt(tc_id);

                    String sql_chk = "SELECT * FROM Articles WHERE id = ?;";
                    ps = conn.prepareStatement(sql_chk);
                    ps.setInt(1, c_id);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        flag_t = 2;
                        break;
                    }
                    else{
                        System.out.println("Article_ID does not exist");
                    }
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
        }while(tc_id.isEmpty());
    }


    
    if(flag_o ==2 && flag_t ==2){
        sql_insert_stmt = "DELETE FROM Articles WHERE ISBN = ? and id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        
        ps.setString(1, isbn);
        ps.setInt(2, c_id);

        ps.executeUpdate();
    }
}

static void assign() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Assign Menu:");
    
    int m_id = 0;
    int e_id = 0;

    String isbn = "";
    String sm_id = "";
    String se_id = "";

    int t1 = 1;
    int t2 = 1;
    int t3 = 1;

    do {
        System.out.println("Enter Manager ID:");
        try{
            sm_id = br.readLine();
            if(!sm_id.isEmpty()){
                m_id = Integer.parseInt(sm_id);

                String sql_chk = "SELECT * FROM Manager WHERE id = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, m_id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    t1 = 2;
                    break;
                }
                else{
                    System.out.println("Manager ID does not exist");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(sm_id.isEmpty());

    if(t1==2){
        do {
            System.out.println("Enter the Editor ID:");
            
            try{
                se_id = br.readLine();
                if(!se_id.isEmpty()){
                    e_id = Integer.parseInt(se_id);

                    String sql_chk = "SELECT * FROM Editors WHERE id = ?;";
                    ps = conn.prepareStatement(sql_chk);
                    ps.setInt(1, e_id);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        t2 = 2;
                        break;
                    }
                    else{
                        System.out.println("Editor ID does not exist");
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(se_id.isEmpty());
    }

    if(t1==2 && t2==2){
        do {
            System.out.println("Enter ISBN:");
            try{
                isbn = br.readLine();

                String sql_chk = "SELECT * FROM Publication WHERE ISBN = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    t3 = 2;
                    break;
                }
                else{
                    System.out.println("ISBN does not exist");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(isbn.isEmpty());
    }

    

    if(t1==2 && t2==2 && t3==2){
        sql_insert_stmt = "INSERT INTO Assign VALUES(?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, m_id);
        ps.setInt(2, e_id);
        ps.setString(3, isbn);
        
        ps.executeUpdate();
    }
    
}

/*
    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
    */
}
