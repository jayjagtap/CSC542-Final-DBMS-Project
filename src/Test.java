// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)

import java.text.SimpleDateFormat;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
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
import java.util.Scanner;
import java.text.ParseException;
import java.sql.Date;
import java.util.List;

public class Test {

    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/nkashya";
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Connection conn = null;
    static String user = "nkashya";
    static String passwd = "200314563";

    public static void main(String[] args) 
    {

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (Exception e) {
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

            //add_staff();
            //delete_staff();
            //update_staff();
            //update_author();
            //update_editor();
            //update_journalist();
            //delete_publication();
            //update_books();
            //update_periodicals();
            //delete_order();
            //add_publications();
            //update_order();
            //displayManager();
            //displayStaff();
            //displayPeriodicals();
            //displayPublication();

            //assign();
            //insert_orders();
            //update_publication();


        }catch(Exception e){
            System.out.println(e);
        }   
    }

static void add_staff() throws SQLException
{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;

    System.out.println("Add Enter Staff");

    String staff_name = "";
    String phone_number = "";
    String staff_address = "";
    String staff_gender = "";
    int s_id = -1;
    int s_age= -1;
    do{
        System.out.println("Enter Staff ID");
        try{
            s_id = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_id<0);

        do{
        try{
            System.out.println("Enter Staff Name");
            staff_name = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_name.isEmpty());

        do{
        System.out.println("Enter Staff Phone Number");
        try{
            phone_number = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(phone_number.isEmpty());

        do{
        System.out.println("Enter Staff Address");
        try{
            staff_address= br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_address.isEmpty());

        do{
        System.out.println("Enter Staff age");
        try{
            s_age = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_age<0);

        do{
        System.out.println("Enter Staff Gender");
        try{
            staff_gender = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_gender.isEmpty());

    sql_insert_stmt = "INSERT INTO Staff VALUES(?,?,?,?,?,?);";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, staff_name);
    ps.setInt(2, s_id);
    ps.setString(3, phone_number);
    ps.setString(4, staff_address);
    ps.setInt(5, s_age);
    ps.setString(6, staff_gender);
    ps.executeUpdate();

    System.out.println("If the above staff is Author, enter 1");
    System.out.println("If the above staff is Editor, enter 2");
    System.out.println("If the above staff is Journalist, enter 3");
    int choice = getinput();

    String type = "";
    if(choice==1)
    {
        do{
        System.out.println("Enter Author Type");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());

        sql_insert_stmt = "INSERT INTO Authors VALUES(?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, s_id);
        ps.setString(2, type);
        ps.executeUpdate();
    }
    else if(choice==2)
    {
        
        do{
        System.out.println("Enter Editor Type");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());
        sql_insert_stmt = "INSERT INTO Editors VALUES(?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, s_id);
        ps.setString(2, type);
        ps.executeUpdate();
    }
    else if(choice==3)
    {
        do{
        System.out.println("Enter Journalist Type");
        try{
            type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(type.isEmpty());

        sql_insert_stmt = "INSERT INTO Journalists VALUES(?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, s_id);
        ps.setString(2, type);
        ps.executeUpdate();
    }
}

static void update_author() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Author");

    int s_id = -1;
    String rs_type = "";
    String new_type = "";

    do{
    System.out.println("Enter Staff ID");
        try{
                s_id = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_id<0);

    String data_retrieval = "Select type from Authors where id = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setInt(1,s_id);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_type = rs.getString("type");
    }
    System.out.println("If you want to update the type, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter author Type");
        try{
            new_type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(new_type.isEmpty());

    rs_type = new_type;
}
    sql_insert_stmt = "Update Authors set type = ? where id = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_type);
    ps.setInt(2,s_id);
    ps.executeUpdate();
}

static void update_editor() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of editor");

    int s_id = -1;
    String rs_type = "";
    String new_type = "";

    do{
    System.out.println("Enter Staff ID");
        try{
                s_id = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_id<0);

    String data_retrieval = "Select type from Editors where id = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setInt(1,s_id);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_type = rs.getString("type");
}
    System.out.println("If you want to update the type, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter Editor Type");
        try{
            new_type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(new_type.isEmpty());

    rs_type = new_type;
}
    sql_insert_stmt = "Update Editors set type = ? where id = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_type);
    ps.setInt(2,s_id);
    ps.executeUpdate();
}

static void update_journalist() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Journalist");

    int s_id = -1;
    String rs_type = "";
    String new_type = "";

    do{
    System.out.println("Enter Staff ID");
        try{
                s_id = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_id<0);

    String data_retrieval = "Select type from Journalists where id = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setInt(1,s_id);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_type = rs.getString("type");
}
    System.out.println("If you want to update the type, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter Journalist Type");
        try{
            new_type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(new_type.isEmpty());

    rs_type = new_type;
}
    sql_insert_stmt = "Update Journalists set type = ? where id = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_type);
    ps.setInt(2,s_id);
    ps.executeUpdate();
}

static void update_books() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Books");

    String isbn = "";
    String rs_edition = "";
    String new_edition = "";

    do{
    System.out.println("Enter isbn");
        try{
                isbn = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    String data_retrieval = "Select edition from Books where ISBN = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setString(1,isbn);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_edition = rs.getString("edition");
    }
    System.out.println("If you want to update the edition, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter Book Edition");
        try{
            new_edition = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(new_edition.isEmpty());

    rs_edition = new_edition;
}
    sql_insert_stmt = "Update Books set edition = ? where ISBN = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_edition);
    ps.setString(2,isbn);
    ps.executeUpdate();

}

static void update_periodicals() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Periodicals");

    String isbn = "";
    String rs_type = "";
    String new_type = "";
    String rs_periodicity = "";
    String new_periodicity = "";

do{
    System.out.println("Enter isbn");
        try{
                isbn = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    String data_retrieval = "Select periodicity,type from Periodicals where ISBN = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setString(1,isbn);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_periodicity = rs.getString("periodicity");
        rs_type = rs.getString("type");
    }
    System.out.println("If you want to update the periodicity, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter Periodicity");
        try{
            new_periodicity = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(new_periodicity.isEmpty());

    rs_periodicity = new_periodicity;
}

    System.out.println("If you want to update the type, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter type");
        try{
            new_type = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(new_type.isEmpty());

    rs_type = new_type;
}
    sql_insert_stmt = "Update Periodicals set periodicity = ?,type = ? where isbn = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_periodicity);
    ps.setString(2, rs_type);
    ps.setString(3,isbn);
    ps.executeUpdate();
    
}
static void update_staff()  throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Staff");
    int s_id = -1;
    String staff_name = "";
    String phone_number = "";
    String staff_address = "";
    String staff_gender = "";
    int s_age = -1;

    String rs_name = "";
    String rs_phonenumber = "";
    String rs_address = "";
    int rs_age = -1;
    String rs_gender = "";

    do{
        System.out.println("Enter Staff ID");
        try{
                s_id = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_id<0);

    String data_retrieval = "Select name,id,phonenumber,address,age,gender from Staff where id = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setInt(1,s_id);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_name = rs.getString("name");
        rs_phonenumber = rs.getString("phonenumber");
        rs_address = rs.getString("address");
        rs_age = rs.getInt("age");
        rs_gender = rs.getString("gender");
    }

    System.out.println("If you want to update the name, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter Staff Name");
        try{
            staff_name = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_name.isEmpty());

    rs_name = staff_name;
}

    System.out.println("If you want to update the phonenumber, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter phonenumber");
        try{
            phone_number = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(phone_number.isEmpty());

    rs_phonenumber = phone_number;
}
    System.out.println("If you want to update the address, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter address");
        try{
            staff_address = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }while(staff_address.isEmpty());

    rs_address = staff_address;
}while(staff_address.isEmpty());
}

    System.out.println("If you want to update the gender, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
    {
    do
    {
        System.out.println("Enter gender");
        try{
            staff_gender = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(staff_gender.isEmpty());
    rs_gender = staff_gender;
}

    System.out.println("If you want to update the age, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
    {
        System.out.println("Enter age");
        try{
            s_age= getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    rs_age = s_age;
    }   

    sql_insert_stmt = "Update Staff set name = ?,phonenumber = ?, address = ?,age=?,gender = ? where id=?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_name);
    ps.setString(2, rs_phonenumber);
    ps.setString(3, rs_address);
    ps.setInt(4, rs_age);
    ps.setString(5, rs_gender);
    ps.setInt(6,s_id);
    ps.executeUpdate();
}

static void delete_staff() throws SQLException {

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Delete Staff Menu:");

    int s_id = -1;
    int flag = 1;

    System.out.println("Enter 1 if it is an Author");
    System.out.println("Enter 2 if it is an Journalist");
    System.out.println("Enter 3 if it is an Editor");
    int choice = getinput();
    do{
        System.out.println("Enter Staff ID");
        try{
                flag = 2;
                s_id = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(s_id<0);

    if(flag==2){
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
            flag=3;
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

    String dateofcreation = "";
    String dateofpublishing = "";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    java.util.Date myDate1 = new java.util.Date();
    java.util.Date myDate2 = new java.util.Date();

    int flag = 1;
    int flag2 = 1;

    String isbn = "";
    String title = "";
    String topic = "";
    double price = -1;

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
            price = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(price<0);

    do{
    System.out.println("Enter Date of Creation");
    try{
                dateofcreation = br.readLine();
                try{
                myDate1 = format.parse(dateofcreation);
                flag=1;
            }catch(ParseException e){
                flag=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag==0);


        do{
    System.out.println("Enter Date of Publishing");
    try{
                dateofpublishing = br.readLine();
                try{
                myDate2 = format.parse(dateofpublishing);
                flag2=1;
            }catch(ParseException e){
                flag2=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag2==0);

    java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());
    java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());
            

    sql_insert_stmt = "INSERT into Publication values(?,?,?,?,?,?);";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, isbn);
    ps.setDate(2, sqlDate1);
    ps.setString(3, title);
    ps.setString(4, topic);
    ps.setDate(5, sqlDate2);
    ps.setDouble(6, price);
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

        sql_insert_stmt = "INSERT INTO Books VALUES(?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, isbn);
        ps.setString(2, edition);
        ps.executeUpdate();
    }

    if(choice==2)
    {
        String periodicity = "";
        String type = "";
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

        sql_insert_stmt = "INSERT INTO Periodicals VALUES(?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, isbn);
        ps.setString(2, periodicity);
        ps.setString(3,type);
        ps.executeUpdate();
    }      
}

static void update_publication() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Updating the information of Publication");
    
    String isbn = "";
    String title = "";
    String topic = "";
    double price = -1;
    String rs_title = "";
    String rs_topic = "";
    double rs_price = 0;
    java.util.Date rs_dateofcreation = new java.util.Date();
    java.util.Date rs_dateofpublishing = new java.util.Date();

    String dateofcreation = "";
    String dateofpublishing = "";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    java.util.Date myDate1 = new java.util.Date();
    java.util.Date myDate2 = new java.util.Date();

    int flag = 1;
    int flag2 = 1;

    System.out.println("Enter ISBN");
    do{
    try{
        isbn = br.readLine();
    }catch(Exception e){
        System.out.println(e);
    }
}while(isbn.isEmpty());

    String data_retrieval = "Select ISBN,dateofcreation,title,topic,publicationdate,price from Publication where ISBN = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setString(1,isbn);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_title = rs.getString("title");
        rs_topic = rs.getString("topic");
        rs_price = rs.getDouble("price");
        rs_dateofcreation = rs.getDate("dateofcreation");
        rs_dateofpublishing = rs.getDate("publicationdate");
    }

    System.out.println("If you want to update the title, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
        {
    do
    {
        System.out.println("Enter title");
        try{
            title = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(title.isEmpty());

    rs_title = title;
}
    System.out.println("If you want to update the topic, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
        {
    do
    {
        System.out.println("Enter topic");
        try{
            topic = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(topic.isEmpty());

    rs_topic = topic;
}

    System.out.println("If you want to update the dateofcreation, please enter 1 else press 0");
    choice = getinput();  

    if(choice==1)
    {
        do{
    System.out.println("Enter Date of Creation");
    try{
                dateofcreation = br.readLine();
                try{
                myDate1 = format.parse(dateofcreation);
                flag=1;
            }catch(ParseException e){
                flag=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag==0);
        rs_dateofcreation = myDate1;
    }


    System.out.println("If you want to update the dateofpublishing, please enter 1 else press 0");
    choice = getinput();  
    if (choice==1)
    {
    do{
    System.out.println("Enter Date of Publishing");
    try{
                dateofpublishing = br.readLine();
                try{
                myDate2 = format.parse(dateofpublishing);
                flag2=1;
            }catch(ParseException e){
                flag2=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag2==0);

        rs_dateofpublishing = myDate2;
    }

    java.sql.Date sqlDate1 = new java.sql.Date(rs_dateofcreation.getTime());
    java.sql.Date sqlDate2 = new java.sql.Date(rs_dateofpublishing.getTime());
              

    System.out.println("If you want to update the price, please enter 1 else press 0");
    choice = getinput();

    if(choice==1) 
    {
        do{
        System.out.println("Enter price");
        try{
            price = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(price<0);
    rs_price = price;
}
    sql_insert_stmt = "Update Publication set title = ?,topic = ?, price = ?, dateofcreation = ?, publicationdate = ? where ISBN = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setString(1, rs_title);
    ps.setString(2, rs_topic);
    ps.setDouble(3, rs_price);
    ps.setDate(4,sqlDate1);
    ps.setDate(5,sqlDate2);
    ps.setString(6, isbn);
    ps.executeUpdate();
}

static void delete_publication()  throws SQLException{

    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Delete Publication Menu:");
    int flag=1;
    String isbn = "";

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
            sql_insert_stmt = "DELETE from EditBooks where isbn = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ps.executeUpdate();

            sql_insert_stmt = "DELETE from WriteBooks where isbn = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ps.executeUpdate();
        }
        if(choice==2)
        {
            sql_insert_stmt = "DELETE from EditPeriodicals where isbn = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ps.executeUpdate();

            sql_insert_stmt = "DELETE from Writeperiodicals where isbn = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ps.executeUpdate();
        }
        sql_insert_stmt = "DELETE from Publication where ISBN = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1,isbn);
        ps.executeUpdate();
        }
    }

static void insert_orders() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Insert Orders : ");

    String dateoforder = "";
    String dateofdelivery = "";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    java.util.Date myDate1 = new java.util.Date();
    java.util.Date myDate2 = new java.util.Date();

    int flag = 1;
    int flag2 = 1;

    int distributorid = -1;
    int managerid = -1;
    int orderid = -1;
    String isbn = "";
    int numofcopies = -1;
    double cost = 0;
    double shippingcost = 0;
    double rs_price_val = 0;

        do{
        try{
            System.out.println("Enter OrderId");
            orderid = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(orderid<0);

    do{
        try{
            System.out.println("Enter ManagerId");
            managerid = getinput();

            String queryCheck = "SELECT * from Manager WHERE id = ?;";
            ps = conn.prepareStatement(queryCheck);
            ps.setInt(1,managerid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
            }
            else{
                System.out.println("Entered Manager Id not present in our database");
                managerid = -1;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(managerid<0);

    do{
        try{
            System.out.println("Enter DistributorId");
            distributorid = getinput();

            String queryCheck = "SELECT * from Distributors WHERE id = ?;";
            ps = conn.prepareStatement(queryCheck);
            ps.setInt(1,distributorid);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

            }
            else{
                System.out.println("Entered DistributorId not present in our database");
                distributorid = -1;
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }while(distributorid<0);

    do{
        try{
            System.out.println("Enter isbn");
            isbn = br.readLine();

            String queryCheck = "SELECT * from Publication WHERE ISBN = ?;";
            ps = conn.prepareStatement(queryCheck);
            ps.setString(1,isbn);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
            }
            else{
                System.out.println("Entered isbn not present in our database");
                isbn = "";
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    do{
        try{
            System.out.println("Enter Number of Copies");
            numofcopies = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(numofcopies<0);

        String queryCheck = "SELECT price from Publication WHERE isbn = '" + isbn + "';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCheck);

        while(rs.next())
        {
            rs_price_val = rs.getDouble("price");
        }
        cost = rs_price_val*numofcopies;

        shippingcost = 25*numofcopies;

        do{
    System.out.println("Enter Date of Order");
    try{
                dateoforder = br.readLine();
                try{
                myDate1 = format.parse(dateoforder);
                flag=1;
            }catch(ParseException e){
                flag=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag==0);


        do{
    System.out.println("Enter Date of delivery");
    try{
                dateofdelivery = br.readLine();
                try{
                myDate2 = format.parse(dateofdelivery);
                flag2=1;
            }catch(ParseException e){
                flag2=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag2==0);

    java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());
    java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());
        

        sql_insert_stmt = "INSERT INTO Orders VALUES(?,?,?,?,?,?,?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, distributorid);
        ps.setInt(2, managerid);
        ps.setInt(3, orderid);
        ps.setString(4, isbn);
        ps.setInt(5, numofcopies);
        ps.setDate(6, sqlDate1);
        ps.setDate(7, sqlDate2);
        ps.setDouble(8, cost);
        ps.setDouble(9, shippingcost);
        ps.executeUpdate();

}

static void delete_order() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("delete from the Orders Menu:");

    int orderid = -1;
    do{
        try{
            System.out.println("Enter OrderId");
            orderid = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(orderid<0);

    sql_insert_stmt = "DELETE from Orders where orderid = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setInt(1,orderid);
    ps.executeUpdate();
}

static void update_order() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("Update from the Orders Menu:");

    String isbn = "";
    int managerid = -1;
    int distributorid = -1;
    int orderid = -1;
    int numofcopies = -1;

    int rs_distributorid = -1;
    int rs_managerid = -1;
    int rs_numofcopies = -1;
    String rs_isbn = "";

    java.util.Date rs_deliverydate = new java.util.Date();
    java.util.Date rs_orderdate = new java.util.Date();

    String deliverydate = "";
    String orderdate = "";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    java.util.Date myDate1 = new java.util.Date();
    java.util.Date myDate2 = new java.util.Date();

    int flag = 1;
    int flag2 = 1;

    System.out.println("Enter OrderID");
    do{
    try{
        orderid = getinput();
    }catch(Exception e){
        System.out.println(e);
    }
}while(orderid<0);

    String data_retrieval = "Select distributorid,managerid,ISBN,numcopies,deliverydate,orderdate from Orders where orderid = ?;";
    ps = conn.prepareStatement(data_retrieval);
    ps.setInt(1,orderid);
    ResultSet rs = ps.executeQuery();
    while(rs.next())
    {
        rs_distributorid = rs.getInt("distributorid");
        rs_managerid = rs.getInt("managerid");
        rs_isbn = rs.getString("isbn");
        rs_numofcopies = rs.getInt("numcopies");
        rs_deliverydate = rs.getDate("deliverydate");
        rs_orderdate = rs.getDate("orderdate");
    }

    System.out.println("If you want to update the distributorid, please enter 1 else press 0");
    int choice = getinput();

    if(choice==1)
        {
    do
    {
        System.out.println("Enter distributorid");
        try{
            distributorid = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(distributorid<0);

    rs_distributorid = distributorid;
}
    System.out.println("If you want to update the managerid, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
        {
    do
    {
        System.out.println("Enter managerid");
        try{
            managerid = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(managerid<0);

    rs_managerid = managerid;
}
    System.out.println("If you want to update the isbn, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
        {
    do
    {
        System.out.println("Enter isbn");
        try{
            isbn = br.readLine();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(isbn.isEmpty());

    rs_isbn = isbn;
}

    System.out.println("If you want to update the deliverydate, please enter 1 else press 0");
    choice = getinput();  

    if(choice==1)
    {
        do{
    System.out.println("Enter new deliverydate");
    try{
                deliverydate = br.readLine();
                try{
                myDate1 = format.parse(deliverydate);
                flag=1;
            }catch(ParseException e){
                flag=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag==0);
        rs_deliverydate = myDate1;
    }


    System.out.println("If you want to update the orderdate, please enter 1 else press 0");
    choice = getinput();  
    if (choice==1)
    {
    do{
    System.out.println("Enter new order date");
    try{
                orderdate = br.readLine();
                try{
                myDate2 = format.parse(orderdate);
                flag2=1;
            }catch(ParseException e){
                flag2=0;
                System.out.println("Invalid Date please reenter");
            }
            }catch(Exception e){
            System.out.println(e);
        }
        }while(flag2==0);

        rs_orderdate = myDate2;
    }

    java.sql.Date sqlDate1 = new java.sql.Date(rs_deliverydate.getTime());
    java.sql.Date sqlDate2 = new java.sql.Date(rs_orderdate.getTime());
              

    System.out.println("If you want to update the number of copies, please enter 1 else press 0");
    choice = getinput();

    if(choice==1)
    { 
    do{
        System.out.println("Enter new num of copies");
        try{
            numofcopies = getinput();
        }catch(Exception e){
            System.out.println(e);
        }
    }while(numofcopies<0);
    rs_numofcopies = numofcopies;
}
        double ms_price_val = -1;
        String queryCheck = "SELECT price from Publication WHERE isbn = ?;";
        ps = conn.prepareStatement(queryCheck);
        ps.setString(1,isbn);
        ResultSet ms = ps.executeQuery();

        while(ms.next())
        {
            ms_price_val = ms.getDouble("price");
        }
        double cost = ms_price_val*numofcopies;

        double shippingcost = 25*numofcopies;



    sql_insert_stmt = "Update Orders set distributorid = ?,managerid = ?, isbn = ?, numcopies = ?, deliverydate = ?,orderdate = ?, cost = ?, shippingcost = ? where orderid = ?;";
    ps = conn.prepareStatement(sql_insert_stmt);
    ps.setInt(1, rs_distributorid);
    ps.setInt(2, rs_managerid);
    ps.setString(3, rs_isbn);
    ps.setInt(4,rs_numofcopies);
    ps.setDate(5,sqlDate1);
    ps.setDate(6,sqlDate2);
    ps.setDouble(7, cost);
    ps.setDouble(8,shippingcost);
    ps.setInt(9,orderid);
    ps.executeUpdate();

}
static int getinput()
    {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        return num;
    }

static void displayManager() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("View Manager Table:");
    System.out.println("");
    int rs_managerid = -1;
    String rs_managername = "";
    String rs_phonenumber = "";
    String rs_address = "";
    int rs_age = -1;
    String rs_gender = "";
    String data_retrieval = "Select name, id, phonenumber, address, age, gender from Manager;";
    ps = conn.prepareStatement(data_retrieval);
    ResultSet rs = ps.executeQuery();

        List<String> l = new ArrayList<String>();
            l.add("ID");
            l.add("Name");
            l.add("Phone Number");
            l.add("Address");
            l.add("Age");
            l.add("Gender");

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<l.size(); i++)
                sb.append(String.format("| %-20s", l.get(i)));
            System.out.println(sb);

            while (rs.next()) {
                l.clear();
                sb.setLength(0);
                rs_managerid = rs.getInt("id");
                rs_managername = rs.getString("name");
                rs_phonenumber = rs.getString("phonenumber");
                rs_address = rs.getString("address");
                rs_age = rs.getInt("age");
                rs_gender = rs.getString("gender");

                l.add(Integer.toString(rs_managerid));
                l.add(rs_managername);
                l.add(rs_phonenumber);
                l.add(rs_address);
                l.add(Integer.toString(rs_age));
                l.add(rs_gender);
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-20s", l.get(i)));
                System.out.println(sb);
    }
}

static void displayStaff() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("View Staff Table:");
    System.out.println("");
    int rs_staffid = -1;
    String rs_staffname = "";
    String rs_phonenumber = "";
    String rs_address = "";
    int rs_age = -1;
    String rs_gender = "";
    String data_retrieval = "Select name, id, phonenumber, address, age, gender from Staff;";
    ps = conn.prepareStatement(data_retrieval);
    ResultSet rs = ps.executeQuery();

        List<String> l = new ArrayList<String>();
            l.add("ID");
            l.add("Name");
            l.add("Phone Number");
            l.add("Address");
            l.add("Age");
            l.add("Gender");

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<l.size(); i++)
                sb.append(String.format("| %-25s", l.get(i)));
            System.out.println(sb);

            while (rs.next()) {
                l.clear();
                sb.setLength(0);
                rs_staffid = rs.getInt("id");
                rs_staffname = rs.getString("name");
                rs_phonenumber = rs.getString("phonenumber");
                rs_address = rs.getString("address");
                rs_age = rs.getInt("age");
                rs_gender = rs.getString("gender");

                l.add(Integer.toString(rs_staffid));
                l.add(rs_staffname);
                l.add(rs_phonenumber);
                l.add(rs_address);
                l.add(Integer.toString(rs_age));
                l.add(rs_gender);
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-25s", l.get(i)));
                System.out.println(sb);
    }
}

static void displayPeriodicals() throws SQLException{
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("View Periodicals Table:");
    System.out.println("");

    String rs_isbn = "";
    String rs_periodicity = "";
    String rs_type = "";


    String data_retrieval = "Select ISBN,periodicity,type from Periodicals;";
    ps = conn.prepareStatement(data_retrieval);
    ResultSet rs = ps.executeQuery();

        List<String> l = new ArrayList<String>();
            l.add("ISBN");
            l.add("Periodicity");
            l.add("Type");

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<l.size(); i++)
                sb.append(String.format("| %-25s", l.get(i)));
            System.out.println(sb);

            while (rs.next()) {
                l.clear();
                sb.setLength(0);
                rs_isbn = rs.getString("ISBN");
                rs_periodicity = rs.getString("periodicity");
                rs_type = rs.getString("type");

                l.add(rs_isbn);
                l.add(rs_periodicity);
                l.add(rs_type);
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-25s", l.get(i)));
                System.out.println(sb);
            }
}

static void displayPublication() throws SQLException {
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("View Publication Table:");
    System.out.println("");

    String rs_isbn = "";
    String rs_dateofcreation = "";
    String rs_title = "";
    String rs_topic = "";
    String rs_dateofpublishing = "";
    int rs_price = -1;

    String data_retrieval = "Select ISBN, dateofcreation, title, topic, publicationdate, price from Publication;";
    ps = conn.prepareStatement(data_retrieval);
    ResultSet rs = ps.executeQuery();

        List<String> l = new ArrayList<String>();
            l.add("ISBN");
            l.add("dateofcreation");
            l.add("Title");
            l.add("Topic");
            l.add("Publication Date");
            l.add("Price");

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<l.size(); i++)
                sb.append(String.format("| %-30s", l.get(i)));
            System.out.println(sb);

            while (rs.next()) {
                l.clear();
                sb.setLength(0);
                rs_isbn = rs.getString("ISBN");
                rs_dateofcreation = rs.getString("dateofcreation");
                rs_title = rs.getString("title");
                rs_topic = rs.getString("topic");
                rs_dateofpublishing = rs.getString("publicationdate");
                rs_price = rs.getInt("price");

                l.add(rs_isbn);
                l.add(rs_dateofcreation);
                l.add(rs_title);
                l.add(rs_topic);
                l.add(rs_dateofpublishing);
                l.add(Integer.toString(rs_price));
                
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-30s", l.get(i)));
                System.out.println(sb);
}
}
    }
