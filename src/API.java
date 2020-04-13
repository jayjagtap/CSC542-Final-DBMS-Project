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

public class API {

    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/nkashya";

    static Connection conn = null;
    static String user = "nkashya";
    static String passwd = "200314563";

    /*public static void main(String[] args) 
    {

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (Exception e) {
            System.out.println("Driver missing!");
        }
        try {



        }catch(Exception e){
            System.out.println(e);
        }   
    }
*/
static void add_staff() throws SQLException
{
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            String sql_chk = "select id from Staff WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, s_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                    s_id=-1;
                    System.out.println("Entered value already present in the database");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                String sql_chk = "select id from Authors WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, s_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    s_id=-1;
                    System.out.println("ID not in Author Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                String sql_chk = "select id from Editors WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, s_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{;
                    s_id=-1;
                    System.out.println("ID not in Editors Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                String sql_chk = "select id from Journalists WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, s_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    s_id=-1;
                    System.out.println("ID not in Journalist Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                String sql_chk = "select isbn from Books WHERE ISBN = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    isbn="";
                    System.out.println("ISBN not in the Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                String sql_chk = "select isbn from Periodicals WHERE ISBN = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    isbn="";
                    System.out.println("ISBN not in the Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                String sql_chk = "select id from Staff WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, s_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    s_id=-1;
                    System.out.println("ID not in the Database, please enter valid id");
                }
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

    static void delete_manager() throws SQLException{
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Add Chapters Menu:");
        int manager_id = -1;
        int flag=1;

        do{
            System.out.println("Enter Manager_ID to be deleted");
        try{
                manager_id = getinput();
                String sql_chk = "select id from Manager WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, manager_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                    flag=2;
                }
                else{
                    flag=1;
                    manager_id=-1;
                    System.out.println("ID not in the Database, please enter valid id");
                }
        }catch(Exception e){
            System.out.println(e);
        }
    }while(manager_id<0);

    if(flag==2){
        sql_insert_stmt = "DELETE FROM Manager WHERE id = ?;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, manager_id);
        ps.executeUpdate();
    }
}

static void delete_staff() throws SQLException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    displayStaff();
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
                String sql_chk = "select id from Staff WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, s_id);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    s_id=-1;
                    flag=1;
                    System.out.println("ID not in the Database, please enter valid id");
                }
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

    displayStaff();
}

static void add_publications() throws SQLException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
            String sql_chk = "select ISBN from Publication WHERE ISBN = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                     isbn="";
                    System.out.println("ISBN already in the database");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        String sql_chk = "select isbn from Publication WHERE ISBN = ?;"; 
        ps = conn.prepareStatement(sql_chk);
        ps.setString(1, isbn);
        ResultSet rs = ps.executeQuery();
                
        if(rs.next() == true){
            }
            else{
                    isbn="";
                    System.out.println("ISBN not in the Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
            String sql_chk = "select isbn from Publication WHERE ISBN = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                    flag=2;
                }
                else{
                    isbn="";
                    flag=1;
                    System.out.println("ISBN not in the Table");
                    System.out.println("Please enter valid id");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            String sql_chk = "select id from Orders WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, orderid);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                    orderid=-1;
                    System.out.println("Entered value already present in the database");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PreparedStatement ps = null;
    conn = DriverManager.getConnection(jdbcURL, user, passwd);
    String sql_insert_stmt;
    System.out.println("delete from the Orders Menu:");

    int orderid = -1;
    do{
        try{
            System.out.println("Enter OrderId");
            orderid = getinput();
            String sql_chk = "select id from Orders WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, orderid);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    orderid=-1;
                    System.out.println("Entered value not present in the database");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        String sql_chk = "select id from Orders WHERE id = ?;"; 
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, orderid);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next() == true){
                }
                else{
                    orderid=-1;
                    System.out.println("Entered value not present in the database");
                }
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

 static void add_chapters() throws SQLException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
            try {
                isbn = br.readLine();

                String sql_chk = "SELECT * FROM Books WHERE ISBN = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag = 2;
                    break;
                } else {
                    System.out.println("ISBN does not exist in Books table");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (isbn.isEmpty());

        if (flag == 2) {
            do {
                System.out.println("Enter the Text:");

                try {
                    texts = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (texts.isEmpty());
        }

        if (flag == 2) {
            do {
                System.out.println("Enter Chapter_ID:");

                try {
                    tc_id = br.readLine();
                    if (!tc_id.isEmpty()) {
                        c_id = Integer.parseInt(tc_id);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (tc_id.isEmpty());
        }

        if (flag == 2) {
            sql_insert_stmt = "INSERT INTO Chapters(ISBN, texts, id) VALUES(?,?,?);";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ps.setString(2, texts);
            ps.setInt(3, c_id);

            ps.executeUpdate();
        }
    }


    //journalists
    static void add_articles() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                isbn = br.readLine();

                String sql_chk = "SELECT * FROM Periodicals WHERE ISBN = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag = 2;
                    break;
                } else {
                    System.out.println("ISBN does not exist in the Periodicals table");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (isbn.isEmpty());

        if (flag == 2) {
            do {
                System.out.println("Enter the Text:");

                try {
                    texts = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (texts.isEmpty());
        }

        if (flag == 2) {
            do {
                System.out.println("Enter Chapter_ID:");

                try {
                    te_id = br.readLine();
                    if (!te_id.isEmpty()) {
                        e_id = Integer.parseInt(te_id);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (te_id.isEmpty());
        }

        if (flag == 2) {
            sql_insert_stmt = "INSERT INTO Articles(ISBN, texts, id) VALUES(?,?,?);";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ps.setString(2, texts);
            ps.setInt(3, e_id);

            ps.executeUpdate();
        }
    }

    //editors, authors
    static void total_chapters() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Total Number of Chapters Menu:");

        String isbn = "";

        int flag = 1;
        do {
            System.out.println("Enter ISBN:");
            try {
                isbn = br.readLine();

                String sql_chk = "SELECT * FROM Books WHERE ISBN = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag = 2;
                    break;
                } else {
                    System.out.println("ISBN does not exist in the Chapters table");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (isbn.isEmpty());

        if (flag == 2) {
            sql_insert_stmt = "SELECT COUNT(*) FROM Chapters WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Total number of chapters in ISBN - " + isbn + " = " + rs.getInt(1));
            }
        }
    }


    // editors, journalists
    static void total_articles() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Total Number of Articles Menu:");

        String isbn = "";

        int flag = 1;
        do {
            System.out.println("Enter ISBN:");
            try {
                isbn = br.readLine();

                String sql_chk = "SELECT * FROM Periodicals WHERE ISBN = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag = 2;
                    break;
                } else {
                    System.out.println("ISBN does not exist in the Periodicals table");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (isbn.isEmpty());

        if (flag == 2) {
            sql_insert_stmt = "SELECT COUNT(*) FROM Articles WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Total number of articles in ISBN - " + isbn + " = " + rs.getInt(1));
            }
        }
    }


    // manager
    static void add_distributor() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sd_id.isEmpty()) {
                try {
                    d_id = Integer.parseInt(sd_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid Integer");
                    sd_id = "";
                }
            }

        } while (sd_id.isEmpty());

        do {
            System.out.println("Enter the Distributor Name:");

            try {
                name = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (name.isEmpty());

        do {
            System.out.println("Enter Distributor Phone Number"); // ensure it's of 10 digits
            try {
                phone_number = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (phone_number.isEmpty());

        do {
            System.out.println("Enter Distributor City");
            try {
                city = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (city.isEmpty());

        do {
            System.out.println("Enter Distributor Address");
            try {
                address = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (address.isEmpty());

        do {
            System.out.println("Enter Distributor Type");
            try {
                type = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (type.isEmpty());

        do {
            System.out.println("Enter Distributor Owed Amount");
            try {
                sa_o = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sa_o.isEmpty()) {
                try {
                    amount_owed = Double.parseDouble(sa_o);
                } catch (Exception e) {
                    System.out.println("Enter a valid amount value");
                    sa_o = "";
                }
            }

        } while (sa_o.isEmpty());

        do {
            System.out.println("Enter Distributor Contact Person");
            try {
                contact_person = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (contact_person.isEmpty());

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


    // manager
    static void delete_distributor() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Delete Distributor Menu:");
        String sd_id = "";

        int d_id = 0;
        int flag = 1;
        do {
            System.out.println("Enter Distributor ID:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, sd_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag = 2;
                try {
                    d_id = Integer.parseInt(sd_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid Distributor ID");
                    sd_id = "";
                }
            }

        } while (sd_id.isEmpty());

        if (flag == 2) {
            sql_insert_stmt = "DELETE FROM Distributors WHERE id = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, d_id);

            ps.executeUpdate();
        } else {
            System.out.println("Entered Distributor ID does not exist");
        }

    }

    // manager
    static void bill_distributor() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Bill Distributor Menu:");
        String sd_id = "";

        int d_id = 0;
        int flag = 1;
        do {
            System.out.println("Enter Distributor ID:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sd_id.isEmpty()) {
                String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, sd_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag = 2;
                    try {
                        d_id = Integer.parseInt(sd_id);
                    } catch (Exception e) {
                        System.out.println("Enter a valid Distributor ID");
                        sd_id = "";
                    }
                } else {
                    System.out.println("Entered Distributor ID does not exist");
                }
            }

        } while (sd_id.isEmpty());

        if (flag == 2) {
            sql_insert_stmt = "SELECT sum(cost) + sum(shippingcost) as distributor_Bill FROM Orders WHERE distributorid = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, d_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Total Bill for Distributor ID - " + d_id + " = " + rs.getInt(1));
            }
        }
    }

    // manager
    static void update_distributor_balance() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sd_id.isEmpty()) {
                try {
                    d_id = Integer.parseInt(sd_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid Distributor ID");
                    sd_id = "";
                }
            }
        } while (sd_id.isEmpty());

        String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
        ps = conn.prepareStatement(sql_chk);
        ps.setInt(1, d_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            flag = 2;
            // break;
        } else {
            System.out.println("Entered Distributor ID does not Exist");
        }

        if (flag == 2) {
            do {
                System.out.println("Enter Distributor Balance:");
                try {
                    da_a = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!da_a.isEmpty()) {
                    try {
                        amount_owed = Double.parseDouble(da_a);
                    } catch (Exception e) {
                        System.out.println("Enter a valid AMount");
                        da_a = "";
                    }
                }

            } while (da_a.isEmpty());
        }

        if (flag == 2) {
            sql_insert_stmt = "UPDATE Distributors SET amountowed = ? WHERE id = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setDouble(1, amount_owed);
            ps.setInt(2, d_id);
            ps.executeUpdate();
        }
    }


    // editors, journalists
    static void total_number_articles() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        // ystem.out.println("Total Number of Articles Menu:");

        sql_insert_stmt = "SELECT Count(*) AS Total_Number_of_Articles FROM Articles;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("Total number of Articles" + " = " + rs.getInt(1));
        }
    }


    // editors, journalists
    static void update_articles_text() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                isbn = br.readLine();

                String sql_chk = "SELECT * FROM Articles WHERE ISBN = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setString(1, isbn);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flag_o = 2;
                    break;
                } else {
                    System.out.println("ISBN does not exist");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (isbn.isEmpty());

        if (flag_o == 2) {
            do {
                System.out.println("Enter the Text:");

                try {
                    texts = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (texts.isEmpty());
        }

        if (flag_o == 2) {
            do {
                System.out.println("Enter Article_ID:");

                try {
                    tc_id = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!tc_id.isEmpty()) {
                    try {
                        c_id = Integer.parseInt(tc_id);
                    } catch (Exception e) {
                        System.out.println("Enter a valid ID");
                        tc_id = "";
                    }
                }
            } while (tc_id.isEmpty());
            String sql_chk = "SELECT * FROM Articles WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setInt(1, c_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag_t = 2;
                // break;
            } else {
                System.out.println("Article ID does not exist");
            }
        }

        if (flag_o == 2 && flag_t == 2) {
            sql_insert_stmt = "UPDATE Articles SET texts = ? WHERE ISBN = ? AND id = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, texts);
            ps.setString(2, isbn);
            ps.setInt(3, c_id);

            ps.executeUpdate();
        }
    }


    // editors, authors
    static void update_chapters_text() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                isbn = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            String sql_chk = "SELECT * FROM Chapters WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag_o = 2;
                break;
            } else {
                System.out.println("ISBN does not exist");
            }

        } while (isbn.isEmpty());

        if (flag_o == 2) {
            do {
                System.out.println("Enter the Updated Text:");

                try {
                    texts = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (texts.isEmpty());
        }

        if (flag_o == 2) {
            do {
                System.out.println("Enter Chapter_ID:");

                try {
                    tc_id = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!tc_id.isEmpty()) {
                    try {
                        c_id = Integer.parseInt(tc_id);
                    } catch (Exception e) {
                        System.out.println("Enter a valid ID");
                        tc_id = "";
                    }
                }
            } while (tc_id.isEmpty());

            String sql_chk = "SELECT * FROM Chapters WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setInt(1, c_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag_t = 2;
                // break;
            } else {
                System.out.println("Chapter ID does not exist");
            }
        }

        if (flag_o == 2 && flag_t == 2) {
            sql_insert_stmt = "UPDATE Chapters SET texts = ? WHERE ISBN = ? AND id = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setString(1, texts);
            ps.setString(2, isbn);
            ps.setInt(3, c_id);

            ps.executeUpdate();
        }
    }

    // editor, journalist
    static void delete_article() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                isbn = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            String sql_chk = "SELECT * FROM Articles WHERE ISBN = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag_o = 2;
                break;
            } else {
                System.out.println("ISBN does not exist");
            }
        } while (isbn.isEmpty());

        if (flag_o == 2) {
            do {
                System.out.println("Enter Article_ID:");

                try {
                    tc_id = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!tc_id.isEmpty()) {
                    try {
                        c_id = Integer.parseInt(tc_id);
                    } catch (Exception e) {
                        System.out.println("Enter a valid ID");
                        tc_id = "";
                    }
                }
            } while (tc_id.isEmpty());

            String sql_chk = "SELECT * FROM Articles WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk);
            ps.setInt(1, c_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flag_t = 2;
                // break;
            } else {
                System.out.println("Article_ID does not exist");
            }
        }

        if (flag_o == 2 && flag_t == 2) {
            sql_insert_stmt = "DELETE FROM Articles WHERE ISBN = ? and id = ?;";
            ps = conn.prepareStatement(sql_insert_stmt);

            ps.setString(1, isbn);
            ps.setInt(2, c_id);

            ps.executeUpdate();
        }
    }

    // manager
    static void assign() throws SQLException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            try {
                sm_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sm_id.isEmpty()) {
                try {
                    m_id = Integer.parseInt(sm_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid ID");
                    sm_id = "";
                }
            }
        } while (sm_id.isEmpty());

        String sql_chk = "SELECT * FROM Manager WHERE id = ?;";
        ps = conn.prepareStatement(sql_chk);
        ps.setInt(1, m_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            t1 = 2;
            // break;
        } else {
            System.out.println("Manager ID does not exist");
        }

        if (t1 == 2) {
            do {
                System.out.println("Enter the Editor ID:");

                try {
                    se_id = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!se_id.isEmpty()) {
                    try {
                        e_id = Integer.parseInt(se_id);
                    } catch (Exception e) {
                        System.out.println("Enter a valid ID");
                        se_id = "";
                    }
                }
            } while (se_id.isEmpty());

            String sql_chk1 = "SELECT * FROM Editors WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk1);
            ps.setInt(1, e_id);
            ResultSet rs1 = ps.executeQuery();
            if (rs1.next()) {
                t2 = 2;
                // break;
            } else {
                System.out.println("Editor ID does not exist");
            }
        }

        if (t1 == 2 && t2 == 2) {
            do {
                System.out.println("Enter ISBN:");
                try {
                    isbn = br.readLine();

                    String sql_chk1 = "SELECT * FROM Publication WHERE ISBN = ?;";
                    ps = conn.prepareStatement(sql_chk1);
                    ps.setString(1, isbn);
                    ResultSet rs1 = ps.executeQuery();
                    if (rs1.next()) {
                        t3 = 2;
                        break;
                    } else {
                        System.out.println("ISBN does not exist");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (isbn.isEmpty());
        }

        if (t1 == 2 && t2 == 2 && t3 == 2) {
            sql_insert_stmt = "INSERT INTO Assign VALUES(?,?,?);";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, m_id);
            ps.setInt(2, e_id);
            ps.setString(3, isbn);

            ps.executeUpdate();
        }

    }


    //  manager
    static void publication_by_editor() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Find Publication by Editor ID Menu:");

        int e_id = 0;

        String se_id = "";
        int t1 = 1;

        do {
            System.out.println("Enter Editor ID:");
            try {
                se_id = br.readLine();

            } catch (Exception e) {
                System.out.println(e);
            }
            if (!se_id.isEmpty()) {
                try {
                    e_id = Integer.parseInt(se_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid ID");
                    se_id = "";
                }
            }
        } while (se_id.isEmpty());

        String sql_chk = "SELECT * FROM Editors WHERE id = ?;";
        ps = conn.prepareStatement(sql_chk);
        ps.setInt(1, e_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            t1 = 2;
            // break;
        } else {
            System.out.println("Editor does not exist");
        }

        if (t1 == 2) {
            sql_insert_stmt = "Select * From Publication WHERE ISBN IN (Select ISBN from Assign where editorid=?);";

            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, e_id);
            ResultSet rs1 = ps.executeQuery();

            if (!rs1.isBeforeFirst()) {
                System.out.println("Query returns no rows!");
                // continue;
            }

            else {
                System.out.println(
                        "--------------------------------------------------------------------------------------------------------------------------------------------------------");
                String c1 = "ISBN";
                String c2 = "Date_of_Creation";
                String c3 = "Title";
                String c4 = "Topic";
                String c5 = "Publication_Date";
                String c6 = "Price";
                System.out.format("%-10s%-30s%-40s%-20s%-24s%-10s", c1, c2, c3, c4, c5, c6);

                System.out.println();

                while (rs1.next()) {
                    String r1 = rs1.getString(1);
                    String r2 = rs1.getString(2);
                    String r3 = rs1.getString(3);
                    String r4 = rs1.getString(4);
                    String r5 = rs1.getString(5);
                    String r6 = rs1.getString(6);

                    System.out.format("%-10s%-30s%-40s%-20s%-24s%-10s", r1, r2, r3, r4, r5, r6);
                    System.out.println();
                }
                System.out.println(
                        "--------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    // manager
    static void insert_pay() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Insert into Pays Menu:");
        String sm_id = "";
        String se_id = "";
        String spayment = "";
        String day = "";
        String month = "";
        String year = "";
        String date = "";
        String track = "";

        int e_id = 0;
        int m_id = 0;
        int payment = 0;
        int m = 0, d = 0;

        int flag1 = 1;
        int flag2 = 1;

        do {
            System.out.println("Enter Manager ID:");
            try {
                sm_id = br.readLine();

            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sm_id.isEmpty()) {

                try {
                    m_id = Integer.parseInt(sm_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid ID");
                    sm_id = "";
                }
            }
        } while (sm_id.isEmpty());

        String sql_chk = "SELECT * FROM Manager WHERE id = ?;";
        ps = conn.prepareStatement(sql_chk);
        ps.setInt(1, m_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            flag1 = 2;
            // break;
        } else {
            System.out.println("Manager does not exist");
        }

        if (flag1 == 2) {
            do {
                System.out.println("Enter Editor_ID:");
                try {
                    se_id = br.readLine();

                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!se_id.isEmpty()) {
                    try {
                        e_id = Integer.parseInt(se_id);
                    } catch (Exception e) {
                        System.out.println("ENter a valid ID");
                        se_id = "";
                    }
                }
            } while (se_id.isEmpty());

            String sql_chk1 = "SELECT * FROM Editors WHERE id = ?;";
            ps = conn.prepareStatement(sql_chk1);
            ps.setInt(1, e_id);
            ResultSet rs1 = ps.executeQuery();
            if (rs1.next()) {
                flag2 = 2;
                // break;
            } else {
                System.out.println("Editor does not exist");
            }

        }

        if (flag1 == 2 && flag2 == 2) {
            do {
                System.out.println("Enter Payment to be made:");
                try {
                    spayment = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!spayment.isEmpty()) {
                    try {
                        payment = Integer.parseInt(spayment);
                    } catch (Exception e) {
                        System.out.println("Enter a valid Amount");
                        spayment = "";
                    }
                }
            } while (spayment.isEmpty());

            System.out.println(
                    "Entering the date now: --------------------------------------------------------------------------");
            do {
                System.out.println("Enter the Year");
                try {
                    year = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (year.isEmpty());
            do {
                System.out.println("Enter the Month");
                try {
                    month = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!month.isEmpty()) {
                    try {
                        m = Integer.parseInt(month);
                    } catch (Exception e) {
                        System.out.println("Enter a valid value");
                        month = "";
                    }
                }
            } while (month.isEmpty() || m > 12 || m <= 0);

            do {
                System.out.println("Enter the Day");
                try {
                    day = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (!day.isEmpty()) {
                    try {
                        d = Integer.parseInt(day);
                    } catch (Exception e) {
                        System.out.println("ENter a valid value");
                        day = "";
                    }
                }

            } while (day.isEmpty() || d > 31 || d <= 0);

            date = year + "-" + month + "-" + day;

            do {
                System.out.println("Enter if it is Being Tracked or Not?");
                try {
                    track = br.readLine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } while (track.isEmpty());
        }

        if (flag1 == 2 && flag2 == 2) {
            sql_insert_stmt = "INSERT INTO Pays(managerid, staffid, payment, paydate, TrackingPayment) VALUES (?, ?, ?, ?, ?);";
            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, m_id);
            ps.setInt(2, e_id);
            ps.setInt(3, payment);
            ps.setDate(4, java.sql.Date.valueOf(date));
            ps.setString(5, track);

            ps.executeUpdate();
            System.out.println("Inserted");
        }
    }

    // manager
    static void generate_distributor_report() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Generate Order Report for an Individual Distributor:");

        int d_id = 0;
        String sd_id = "";

        // String e_id = "";
        int t1 = 1;

        do {
            System.out.println("Enter Distributor ID:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sd_id.isEmpty()) {
                try {
                    d_id = Integer.parseInt(sd_id);
                } catch (Exception e) {
                    System.out.println("Enter a valid value");
                    sd_id = "";
                }
            }
        } while (sd_id.isEmpty());

        String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
        ps = conn.prepareStatement(sql_chk);
        ps.setInt(1, d_id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            t1 = 2;
            // break;
        } else {
            System.out.println("Distributor does not exist");
        }

        if (t1 == 2) {
            sql_insert_stmt = "Select A.id as Distributorid, A.phonenumber, A.city, A.type, A.amountowed,B.orderid, B.ISBN,B.numcopies, B.cost from Distributors as A join Orders as B where A.id = B.distributorid and A.id = ?;";

            ps = conn.prepareStatement(sql_insert_stmt);
            ps.setInt(1, d_id);
            ResultSet rs1 = ps.executeQuery();

            if (!rs1.isBeforeFirst()) {
                System.out.println("Query returns no rows!");
                // continue;
            }

            else {
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                String c1 = "Distributor ID";
                String c2 = "Phone Number";
                String c3 = "City";
                String c4 = "Type";
                String c5 = "Amount Owed";
                String c6 = "Order ID";
                String c7 = "ISBN";
                String c8 = "Number of Copies";
                String c9 = "Cost";
                System.out.format("%-30s%-20s%-20s%-20s%-20s%-10s%-15s%-36s%-10s", c1, c2, c3, c4, c5, c6, c7, c8, c9);

                System.out.println();

                while (rs1.next()) {
                    String r1 = rs1.getString(1);
                    String r2 = rs1.getString(2);
                    String r3 = rs1.getString(3);
                    String r4 = rs1.getString(4);
                    String r5 = rs1.getString(5);
                    String r6 = rs1.getString(6);
                    String r7 = rs1.getString(7);
                    String r8 = rs1.getString(8);
                    String r9 = rs1.getString(9);

                    System.out.format("%-30s%-20s%-20s%-20s%-20s%-10s%-15s%-36s%-10s", r1, r2, r3, r4, r5, r6, r7, r8,
                            r9);
                    System.out.println();
                }
                System.out.println(
                        "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    // manager
    static void generate_distributors_report() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Generate Order Report for all Distributors:");

        sql_insert_stmt = "select A.id as Distributorid, A.phonenumber, A.city, A.type, A.amountowed,B.orderid, B.ISBN, B.numcopies, B.cost from Distributors as A join Orders as B where A.id = B.distributorid;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            String c1 = "Distributor ID";
            String c2 = "Phone Number";
            String c3 = "City";
            String c4 = "Type";
            String c5 = "Amount Owed";
            String c6 = "Order ID";
            String c7 = "ISBN";
            String c8 = "Number of Copies";
            String c9 = "Cost";
            System.out.format("%-30s%-20s%-20s%-20s%-20s%-10s%-15s%-36s%-10s", c1, c2, c3, c4, c5, c6, c7, c8, c9);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);
                String r4 = rs.getString(4);
                String r5 = rs.getString(5);
                String r6 = rs.getString(6);
                String r7 = rs.getString(7);
                String r8 = rs.getString(8);
                String r9 = rs.getString(9);

                System.out.format("%-30s%-20s%-20s%-20s%-20s%-10s%-15s%-36s%-10s", r1, r2, r3, r4, r5, r6, r7, r8, r9);
                System.out.println();
            }
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    // manager
    static void orders_by_month() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Group Orders by Month:");

        sql_insert_stmt = "select month(orderdate),orderid,ISBN from Orders group by month(orderdate);";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            String c1 = "Month";
            String c2 = "Order ID";
            String c3 = "ISBN";

            System.out.format("%-10s%-30s%-40s", c1, c2, c3);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);

                System.out.format("%-10s%-30s%-40s", r1, r2, r3);
                System.out.println();
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
        }
    }

    // manager
    static void total_distributors() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Total Distributors:");

        sql_insert_stmt = "select count(id) as \"No of Distributors \" from Distributors;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("------------------------------------------------");
            String c1 = "No of Distributors";

            System.out.format("%-10s", c1);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);

                System.out.format("%-10s", r1);
                System.out.println();
            }
            System.out.println("------------------------------------------------");
        }
    }

    // manager
    static void total_expense() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Total Expense:");

        sql_insert_stmt = "select sum(payment) as Expense from Pays where TrackingPayment = \"Yes\";";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("--------------------------------------------------");
            String c1 = "Expense";

            System.out.format("%-10s", c1);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);

                System.out.format("%-10s", r1);
                System.out.println();
            }
            System.out.println("--------------------------------------------------");
        }
    }

    // manager
    static void Revenue_by_City() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Total Revenue for every given city :");

        sql_insert_stmt = "select d.city,sum(d.amountowed) + sum(o.cost) + sum(o.shippingcost) as \"Revenue Generated\" from Distributors d left join Orders o on d.id=o.distributorid group by d.city;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("-----------------------------------------------------------------");
            String c1 = "City";
            String c2 = "Revenue Generated";

            System.out.format("%-20s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-20s%-30s", r1, r2);
                System.out.println();
            }
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // manager
    static void distributor_report_by_month() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        String mon = "";
        int month_no = 0;
        System.out.println("Distributor Report by Month:");
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("january", 1);
        map.put("february", 2);
        map.put("march", 3);
        map.put("april", 4);
        map.put("may", 5);
        map.put("june", 6);
        map.put("july", 7);
        map.put("august", 8);
        map.put("september", 9);
        map.put("october", 10);
        map.put("november", 11);
        map.put("december", 12);

        do {
            System.out.println("Enter Month:");
            try {
                mon = br.readLine();
            } catch (Exception e) {

            }
            mon = mon.toLowerCase();
            try {
                month_no = map.get(mon);
            } catch (Exception e) {
                System.out.println("Entered String is invalid---------------------");
                mon = "";
            }
        } while (mon.isEmpty());

        sql_insert_stmt = "select A.id as Distributorid, A.phonenumber, A.city, A.type, A.amountowed,B.orderid, B.orderdate, B.deliverydate, B.ISBN, B.numcopies, B.cost from Distributors as A join Orders as B where A.id = B.distributorid and MONTH(orderdate) = ? ;";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setInt(1, month_no);
        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------");
            String c1 = "Distributor ID";
            String c2 = "Phone Number";
            String c3 = "City";
            String c4 = "Type";
            String c5 = "Amount Owed";
            String c6 = "Order ID";
            String c7 = "Order Date";
            String c8 = "Delivery Date";
            String c9 = "ISBN";
            String c10 = "Number of Copies";
            String c11 = "Cost";
            System.out.format("%-23s%-20s%-18s%-20s%-20s%-12s%-19s%-19s%-10s%-12s%-11s", c1, c2, c3, c4, c5, c6, c7, c8,
                    c9, c10, c11);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);
                String r4 = rs.getString(4);
                String r5 = rs.getString(5);
                String r6 = rs.getString(6);
                String r7 = rs.getString(7);
                String r8 = rs.getString(8);
                String r9 = rs.getString(9);
                String r10 = rs.getString(10);
                String r11 = rs.getString(11);

                System.out.format("%-23s%-20s%-18s%-20s%-20s%-12s%-19s%-19s%-10s%-12s%-11s", r1, r2, r3, r4, r5, r6, r7,
                        r8, r9, r10, r11);
                System.out.println();
            }
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    // manager
    static void update_distributors() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Update Distributor's Details Menu:------------------------------");

        String sd_id = "";

        int d_id = 0;
        int flag1 = 1;

        do {
            System.out.println("Enter Distributor's ID:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sd_id.isEmpty()) {
                try {
                    d_id = Integer.parseInt(sd_id);
                } catch (Exception e) {
                    System.out.println("Invalid Manager ID");
                    sd_id = "";
                }
            }

            if (!sd_id.isEmpty()) {
                String sql_chk = "SELECT * FROM Distributors WHERE id = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, d_id);
                ResultSet rs = ps.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("Manager ID does not exist");
                    // continue;
                }

                else {
                    flag1 = 2;
                    while (rs.next()) {
                        String r1 = rs.getString(1);
                        String r2 = rs.getString(2);
                        String r3 = rs.getString(3);
                        String r4 = rs.getString(4);
                        String r5 = rs.getString(5);
                        String r6 = rs.getString(6);
                        String r7 = rs.getString(7);
                        String r8 = rs.getString(8);

                        System.out.println("id: " + r1);
                        System.out.println("Name: " + r2);
                        System.out.println("Phone_Number: " + r3);
                        System.out.println("City: " + r4);
                        System.out.println("Address: " + r5);
                        System.out.println("Type: " + r6);
                        System.out.println("Amount_Owed: " + r7);
                        System.out.println("Contact_Person: " + r8);
                    }
                }
            }

        } while (sd_id.isEmpty());

        if (flag1 == 2) {
            System.out.println("What Do you want to update? Enter space separated values like 1 2 3 4...");
            System.out.println("1: Name");
            System.out.println("2: Phone_Number");
            System.out.println("3: City");
            System.out.println("4: Address");
            System.out.println("5: Type");
            System.out.println("6: Amount_Owed");
            System.out.println("7: Contact_Person");
            System.out.println("Enter your Choice: ");

            String ch = "";

            try {
                ch = br.readLine();
            } catch (Exception e) {

            }

            int i;
            int f1 = -1;
            int f2 = -1;
            int f3 = -1;
            int f4 = -1;
            int f5 = -1;
            int f6 = -1;
            int f7 = -1;
            // int f8 = -1;

            String new_name = "";
            String new_number = "";
            String new_city = "";
            String new_address = "";
            String new_type = "";
            String new_contact = "";

            double a_o = 0.0d;

            for (i = 0; i < ch.length(); i++) {
                if (ch.charAt(i) == ' ') {
                    continue;
                } else if (ch.charAt(i) == '1') {
                    if (f1 == -1) {
                        f1 = 1;
                        try {
                            new_name = get_name();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("Name Already Entered");
                        continue;
                    }
                } else if (ch.charAt(i) == '2') {
                    if (f2 == -1) {
                        f2 = 1;
                        try {
                            new_number = get_number();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("Phone Number Already Entered");
                        continue;
                    }
                } else if (ch.charAt(i) == '3') {
                    if (f3 == -1) {
                        f3 = 1;
                        try {
                            new_city = get_city();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("City Already Entered");
                        continue;
                    }
                } else if (ch.charAt(i) == '4') {
                    if (f4 == -1) {
                        f4 = 1;
                        try {
                            new_address = get_address();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("Address Already Entered");
                        continue;
                    }
                } else if (ch.charAt(i) == '5') {
                    if (f5 == -1) {
                        f5 = 1;
                        try {
                            new_type = get_type();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("Type Already Entered");
                        continue;
                    }
                } else if (ch.charAt(i) == '6') {
                    if (f6 == -1) {
                        f6 = 1;
                        try {
                            a_o = get_amount();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("Amount Owed Already Entered");
                        continue;
                    }
                } else if (ch.charAt(i) == '7') {
                    if (f7 == -1) {
                        f7 = 1;
                        try {
                            new_contact = get_contact();
                        } catch (Exception e) {
                        }
                    } else {
                        // System.out.println("Contact Person Already Entered");
                        continue;
                    }
                } else {
                    continue;
                }
            }

            if (!ch.isEmpty()) {

                if (f1 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET name = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_name);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f2 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET phonenumber = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_number);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f3 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET city = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_city);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f4 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET address = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_address);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f5 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET type = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_type);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f6 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET amountowed = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setDouble(1, a_o);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f7 == 1) {
                    sql_insert_stmt = "UPDATE Distributors SET contactperson = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_contact);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }

            } else {
                // go to all options menu-----------
            }
        }
    }

    static String get_name() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New Name:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {

                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;
    }

    static String get_number() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New Number:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;

    }

    static String get_city() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New City:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;

    }

    static String get_address() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New Address:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;

    }

    static String get_type() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New Type:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;

    }

    static double get_amount() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        double a_o = 0.0d;

        do {
            System.out.println("Enter New Amount Owed:");
            try {
                sd_id = br.readLine();
                if (!sd_id.isEmpty()) {
                    try {
                        a_o = Double.parseDouble(sd_id);
                    } catch (Exception e) {
                        System.out.println("Enter a valid value");
                        sd_id = "";
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return a_o;
    }

    static String get_contact() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New Contact:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;

    }

    static int get_age() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        int a_o = 0;

        do {
            System.out.println("Enter New Age");
            try {
                sd_id = br.readLine();
                if (!sd_id.isEmpty()) {
                    try {
                        a_o = Integer.parseInt(sd_id);
                    } catch (Exception e) {
                        System.out.println("Enter valid value");
                        sd_id = "";
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return a_o;
    }

    static String get_gender() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sd_id = "";
        do {
            System.out.println("Enter New Gender:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (sd_id.isEmpty());

        return sd_id;

    }

    // manager
    static void add_manager() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Add Manager Menu:");

        String name = "";
        String phone_number = "";
        String gender = "";
        String address = "";
        String sm_id = "";
        String sage = "";

        int age = 0;
        int m_id = 0;

        do {
            System.out.println("Enter Manager ID:");
            try {
                sm_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sm_id.isEmpty()) {
                try {
                    m_id = Integer.parseInt(sm_id);
                } catch (Exception e) {
                    System.out.println("Enter a proper integer ID");
                    sm_id = "";
                }
            }
        } while (sm_id.isEmpty());

        do {
            System.out.println("Enter the Manager Name:");

            try {
                name = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (name.isEmpty());

        do {
            System.out.println("Enter Manager Phone Number"); // ensure it's of 10 digits
            try {
                phone_number = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (phone_number.isEmpty());

        do {
            System.out.println("Enter Manager Address");
            try {
                address = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (address.isEmpty());

        do {
            System.out.println("Enter Manager Age");
            try {
                sage = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sage.isEmpty()) {
                try {
                    age = Integer.parseInt(sage);
                } catch (Exception e) {
                    System.out.println("Enter a proper integer age");
                    sage = "";
                }
            }
            if (age > 100 || age < 18) {
                System.out.println("Enter a valid age.");
                sage = "";
            }
        } while (sage.isEmpty());

        do {
            System.out.println("Enter Manager Gender:");
            try {
                gender = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (gender.isEmpty());

        sql_insert_stmt = "INSERT INTO Manager VALUES(?,?,?,?,?,?);";
        ps = conn.prepareStatement(sql_insert_stmt);
        ps.setString(1, name);
        ps.setInt(2, m_id);
        ps.setString(3, phone_number);
        ps.setString(4, address);
        ps.setInt(5, age);
        ps.setString(6, gender);
        ps.executeUpdate();
    }

    // manager
    static void update_manager() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Update Manager's Details Menu:------------------------------");

        String sd_id = "";

        int d_id = 0;
        int flag1 = 1;

        do {
            System.out.println("Enter Manager's ID:");
            try {
                sd_id = br.readLine();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (!sd_id.isEmpty()) {
                try {
                    d_id = Integer.parseInt(sd_id);
                } catch (Exception e) {
                    System.out.println("Invalid Manager ID");
                    sd_id = "";
                }
            }

            if (!sd_id.isEmpty()) {
                String sql_chk = "SELECT * FROM Manager WHERE id = ?;";
                ps = conn.prepareStatement(sql_chk);
                ps.setInt(1, d_id);
                ResultSet rs = ps.executeQuery();

                if (!rs.isBeforeFirst()) {
                    System.out.println("Manager ID does not exist");
                    // continue;
                }

                else {
                    flag1 = 2;
                    while (rs.next()) {
                        String r1 = rs.getString(1);
                        String r2 = rs.getString(2);
                        String r3 = rs.getString(3);
                        String r4 = rs.getString(4);
                        String r5 = rs.getString(5);
                        String r6 = rs.getString(6);

                        System.out.println("Name: " + r1);
                        System.out.println("ID: " + r2);
                        System.out.println("Phone_Number: " + r3);
                        System.out.println("Address: " + r4);
                        System.out.println("age: " + r5);
                        System.out.println("Gender: " + r6);
                    }
                }
            }

        } while (sd_id.isEmpty());

        if (flag1 == 2) {
            System.out.println("What Do you want to update? Enter space separated values like 1 2 3 4...");
            System.out.println("1: Name");
            System.out.println("2: Phone_Number");
            System.out.println("3: Address");
            System.out.println("4: Age");
            System.out.println("5: Gender");
            System.out.println("Enter your Choice: ");

            String ch = "";

            try {
                ch = br.readLine();
            } catch (Exception e) {

            }

            int i;
            int f1 = -1;
            int f2 = -1;
            int f3 = -1;
            int f4 = -1;
            int f5 = -1;
            ;
            // int f8 = -1;

            String new_name = "";
            String new_number = "";
            String new_address = "";
            int new_age = 0;
            String new_gender = "";

            for (i = 0; i < ch.length(); i++) {

                if (ch.charAt(i) == ' ') {
                    continue;
                } else if (ch.charAt(i) == '1') {
                    if (f1 == -1) {
                        f1 = 1;
                        try {
                            new_name = get_name();
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("Name Already Entered");
                    }
                }

                else if (ch.charAt(i) == '2') {
                    if (f2 == -1) {
                        f2 = 1;
                        try {
                            new_number = get_number();
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("Phone Number Already Entered");
                    }
                }

                else if (ch.charAt(i) == '3') {
                    if (f3 == -1) {
                        f3 = 1;
                        try {
                            new_address = get_address();
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("Address Already Entered");
                    }
                }

                else if (ch.charAt(i) == '4') {
                    if (f4 == -1) {
                        f4 = 1;
                        try {
                            new_age = get_age();
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("Age Already Entered");
                    }
                }

                else if (ch.charAt(i) == '5') {
                    if (f5 == -1) {
                        f5 = 1;
                        try {
                            new_gender = get_gender();
                        } catch (Exception e) {

                        }
                    } else {
                        System.out.println("Gender Already Entered");
                    }
                }

            }

            if (!ch.isEmpty()) {

                if (f1 == 1) {
                    sql_insert_stmt = "UPDATE Manager SET name = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_name);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f2 == 1) {
                    sql_insert_stmt = "UPDATE Manager SET phonenumber = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_number);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f3 == 1) {
                    sql_insert_stmt = "UPDATE Manager SET address = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_address);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f4 == 1) {
                    sql_insert_stmt = "UPDATE Manager SET age = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setInt(1, new_age);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }
                if (f5 == 1) {
                    sql_insert_stmt = "UPDATE Manager SET gender = ? WHERE id = ?;";
                    ps = conn.prepareStatement(sql_insert_stmt);
                    ps.setString(1, new_gender);
                    ps.setInt(2, d_id);
                    ps.executeUpdate();
                }

                System.out.println("All queries have executed--------------");

            } else {
                // go to all options menu-----------
            }
        }
    }

    // to print all the tables in the db
    static void articles() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Show all Articles:");

        sql_insert_stmt = "select * from Articles;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            String c1 = "ISBN";
            String c2 = "Texts";

            System.out.format("%-10s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-10s%-30s", r1, r2);
                System.out.println();
            }
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    static void assigns() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("All Assigns Table:");

        sql_insert_stmt = "select * from Assign;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("----------------------------------------------------------------------");
            String c1 = "Manager ID";
            String c2 = "Editor ID";
            String c3 = "ISBN";

            System.out.format("%-10s%-30s", c1, c2, c3);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);

                System.out.format("%-10s%-30s", r1, r2, r3);
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------");
        }
    }

    static void authors() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("All Authors Table:");

        sql_insert_stmt = "select * from Authors;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("-------------------------------------------------");
            String c1 = "ID";
            String c2 = "Type";

            System.out.format("%-10s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-10s%-30s", r1, r2);
                System.out.println();
            }
            System.out.println("-------------------------------------------------");
        }
    }

    static void books() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Books Table:");

        sql_insert_stmt = "select * from Books;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("-------------------------------------------------");
            String c1 = "ISBN";
            String c2 = "Edition";

            System.out.format("%-10s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-10s%-30s", r1, r2);
                System.out.println();
            }
            System.out.println("-------------------------------------------------");
        }
    }

    static void chapters() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Chapters Table:");

        sql_insert_stmt = "select * from Chapters;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out
                    .println("--------------------------------------------------------------------------------------");
            String c1 = "ISBN";
            String c2 = "Texts";

            System.out.format("%-10s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-10s%-30s", r1, r2);
                System.out.println();
            }
            System.out
                    .println("--------------------------------------------------------------------------------------");
        }
    }

    static void distributors() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Distributors Table:");

        sql_insert_stmt = "select * from Distributors;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            String c1 = "ID";
            String c2 = "Name";
            String c3 = "Phone Number";
            String c4 = "City";
            String c5 = "Address";
            String c6 = "Type";
            String c7 = "Amount Owed";
            String c8 = "Contact Person";

            System.out.format("%-7s%-25s%-20s%-15s%-34s%-20s%-20s%-20s", c1, c2, c3, c4, c5, c6, c7, c8);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);
                String r4 = rs.getString(4);
                String r5 = rs.getString(5);
                String r6 = rs.getString(6);
                String r7 = rs.getString(7);
                String r8 = rs.getString(8);

                System.out.format("%-7s%-25s%-20s%-15s%-34s%-20s%-20s%-20s", r1, r2, r3, r4, r5, r6, r7, r8);
                System.out.println();
            }
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    static void editBooks() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("EditBooks Table:");

        sql_insert_stmt = "select * from EditBooks;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("------------------------------------------------------------------------");
            String c1 = "Editor ID";
            String c2 = "ISBN";
            String c3 = "Chapter ID";

            System.out.format("%-10s%-20s%-20s", c1, c2, c3);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);

                System.out.format("%-10s%-20s%-20s", r1, r2, r3);
                System.out.println();
            }
            System.out.println("------------------------------------------------------------------------");
        }
    }

    static void editPeriodicals() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("EditPeriodicals Table:");

        sql_insert_stmt = "select * from EditPeriodicals;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("----------------------------------------------------");
            String c1 = "Editor ID";
            String c2 = "ISBN";
            String c3 = "Article ID";

            System.out.format("%-10s%-20s%-20s", c1, c2, c3);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);
                String r3 = rs.getString(3);

                System.out.format("%-10s%-20s%-20s", r1, r2, r3);
                System.out.println();
            }
            System.out.println("----------------------------------------------------");
        }
    }

    static void editors() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Editors Table:");

        sql_insert_stmt = "select * from Editors;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("------------------------------------");
            String c1 = "ID";
            String c2 = "Type";

            System.out.format("%-10s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-10s%-30s", r1, r2);
                System.out.println();
            }
            System.out.println("------------------------------------");
        }
    }

    static void journalists() throws SQLException {
final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        conn = DriverManager.getConnection(jdbcURL, user, passwd);
        String sql_insert_stmt;
        System.out.println("Journalists Table:");

        sql_insert_stmt = "select * from Journalists;";
        ps = conn.prepareStatement(sql_insert_stmt);

        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            System.out.println("Query returns no rows!");
            // continue;
        }

        else {
            System.out.println("------------------------------------");
            String c1 = "ID";
            String c2 = "Type";

            System.out.format("%-10s%-30s", c1, c2);

            System.out.println();

            while (rs.next()) {
                String r1 = rs.getString(1);
                String r2 = rs.getString(2);

                System.out.format("%-10s%-30s", r1, r2);
                System.out.println();
            }
            System.out.println("------------------------------------");
        }
    }

    public static void total_revenue_for_each_city() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        System.out.println("Enter the name of the city to get revenue details: ");
        
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        String city = "";
        do {
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
                    flag = 0;
                    System.out.println("City does not exist in Distributor table");
                    System.out.println("Please enter city again");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(flag == 0 );
        
    
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
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PreparedStatement ps = null;
            System.out.println("Enter the name of the city to get distributors details in that city: ");
        
            int flag = 0;
            String city = "";
            do {
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
                        flag = 0;
                        System.out.println("City does not exist in Distributor table");
                        System.out.println("Please Enter valid city");
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }while(flag == 0);
            
        
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
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        System.out.println("Enter Editor ID: ");
    
        int flag = 0;
        String id = "";
        
        do {
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
                    flag = 0;
                    System.out.println("Editor ID not in Assign Table");
                    System.out.println("Please enter valid editor id");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }while(flag == 0);
        
    
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
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String mydate1 = "";
        String mydate2 = "";
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );  // United States style of format.
        format.setLenient(false);
        java.util.Date myDate1 =  new java.util.Date();
        java.util.Date myDate2 = new java.util.Date();
        
        
        int flag = 1;
        do {
        try {
            System.out.println("Enter Begin of Date Range (YYYY-MM-DD)");
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
            System.out.println("Enter End of Date Range (YYYY-MM-DD)");
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
    
    public static void orders_date_range() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String mydate1 = "";
        String mydate2 = "";
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );  // United States style of format.
        format.setLenient(false);
        java.util.Date myDate1 =  new java.util.Date();
        java.util.Date myDate2 = new java.util.Date();
        
        
        int flag = 1;
        do {
        try {
            System.out.println("Enter Begin of Date Range (YYYY-MM-DD)");
            mydate1 = br.readLine();
            try {
                myDate1 = format.parse( mydate1 );
                flag = 1;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                flag = 0;
                System.out.println("Invalid Date, Please Enter again (YYYY-MM-DD)");
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
            System.out.println("Enter End of Date Range (YYYY-MM-DD)");
            mydate2 = br.readLine();
            try {
                myDate2 = format.parse( mydate2 );
                flag = 1;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                flag = 0;
                System.out.println("Invalid Date, Please Enter again (YYYY-MM-DD)");
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
            String sql_chk = "SELECT * FROM Orders WHERE orderdate BETWEEN ? AND ?;"; 
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
                System.out.println("No results for this date range");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    
        if(flag == 1) {
            //System.out.println("Came in try after flag = 1");
            try {
                conn = DriverManager.getConnection(jdbcURL, user, passwd);
                String sql_chk = "SELECT * FROM Orders WHERE orderdate BETWEEN ? AND ?;"; 
                ps = conn.prepareStatement(sql_chk);
                java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());
                java.sql.Date sqlDate2 = new java.sql.Date(myDate2.getTime());
                ps.setDate(1, sqlDate1);
                ps.setDate(2, sqlDate2);
                ResultSet rs = ps.executeQuery();
                ArrayList<String> l = new ArrayList<String>( 
                        Arrays.asList("distributorid", "managerid","orderid", "ISBN", "numcopies", "deliverydate","orderdate","cost", "shippingcost"));
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-15s", l.get(i)));
                System.out.println(sb);
                
                while (rs.next()) {
                    sb.setLength(0);
                     
                    int did = rs.getInt(l.get(0));
                    int mid = rs.getInt(l.get(1));
                    int oid= rs.getInt(l.get(2));
                    String ISBN = rs.getString(l.get(3));
                    int copies = rs.getInt(l.get(4));
                    String ddate = rs.getString(l.get(5));
                    String odate = rs.getString(l.get(6));
                    int cost = rs.getInt(l.get(7));
                    int scost= rs.getInt(l.get(8));
                    ArrayList<String> m = new ArrayList<String>();
                    m.add(Integer.toString(did));
                    m.add(Integer.toString(mid));
                    m.add(Integer.toString(oid));
                    m.add(ISBN);
                    m.add(Integer.toString(copies));
                    m.add(ddate);
                    m.add(odate);
                    m.add(Integer.toString(cost));
                    m.add(Integer.toString(scost));
                    for(int i=0; i<l.size(); i++)
                        sb.append(String.format("| %-15s", m.get(i)));
                    System.out.println(sb);
                 }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
    
    public static void display_orders() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        PreparedStatement ps = null;
        
    
            //System.out.println("Came in try after flag = 1");
            try {
                conn = DriverManager.getConnection(jdbcURL, user, passwd);
                String sql_chk = "SELECT * FROM Orders;"; 
                ps = conn.prepareStatement(sql_chk);
                ResultSet rs = ps.executeQuery();
                System.out.println("Orders Table");
                ArrayList<String> l = new ArrayList<String>( 
                        Arrays.asList("distributorid", "managerid","orderid", "ISBN", "numcopies", "deliverydate","orderdate","cost", "shippingcost"));
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-15s", l.get(i)));
                System.out.println(sb);
                
                while (rs.next()) {
                    sb.setLength(0);
                     
                    int did = rs.getInt(l.get(0));
                    int mid = rs.getInt(l.get(1));
                    int oid= rs.getInt(l.get(2));
                    String ISBN = rs.getString(l.get(3));
                    int copies = rs.getInt(l.get(4));
                    String ddate = rs.getString(l.get(5));
                    String odate = rs.getString(l.get(6));
                    int cost = rs.getInt(l.get(7));
                    int scost= rs.getInt(l.get(8));
                    ArrayList<String> m = new ArrayList<String>();
                    m.add(Integer.toString(did));
                    m.add(Integer.toString(mid));
                    m.add(Integer.toString(oid));
                    m.add(ISBN);
                    m.add(Integer.toString(copies));
                    m.add(ddate);
                    m.add(odate);
                    m.add(Integer.toString(cost));
                    m.add(Integer.toString(scost));
                    for(int i=0; i<l.size(); i++)
                        sb.append(String.format("| %-15s", m.get(i)));
                    System.out.println(sb);
                 }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    
public static void display_pays() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement ps = null;
        
    
            
            try {
                conn = DriverManager.getConnection(jdbcURL, user, passwd);
                String sql_chk = "SELECT * FROM Pays;"; 
                ps = conn.prepareStatement(sql_chk);
                ResultSet rs = ps.executeQuery();
                System.out.println("Pays Table");
                ArrayList<String> l = new ArrayList<String>( 
                        Arrays.asList("managerid", "staffid","payment", "paydate", "Payment Availed"));
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-15s", l.get(i)));
                System.out.println(sb);
                
                while (rs.next()) {
                    sb.setLength(0);
                     
                    int mid = rs.getInt(l.get(0));
                    int sid = rs.getInt(l.get(1));
                    int payment = rs.getInt(l.get(2));
                    String pdate = rs.getString(l.get(3));
                    String track = rs.getString("TrackingPayment");
                    ArrayList<String> m = new ArrayList<String>();
                    m.add(Integer.toString(mid));
                    m.add(Integer.toString(sid));
                    m.add(Integer.toString(payment));
                    m.add(pdate);
                    m.add(track);
                    for(int i=0; i<l.size(); i++)
                        sb.append(String.format("| %-15s", m.get(i)));
                    System.out.println(sb);
                 }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

public static void display_WriteBooks() {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(jdbcURL, user, passwd);
            String sql_chk = "SELECT * FROM WriteBooks;"; 
            ps = conn.prepareStatement(sql_chk);
            ResultSet rs = ps.executeQuery();
            System.out.println("Write Books Table");
            ArrayList<String> l = new ArrayList<String>( 
                    Arrays.asList("authorid","ISBN","chapterid"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<l.size(); i++)
                sb.append(String.format("| %-15s", l.get(i)));
            System.out.println(sb);
            
            while (rs.next()) {
                sb.setLength(0);
                 
                int aid = rs.getInt(l.get(0));
                String ISBN = rs.getString(l.get(1));
                int cid = rs.getInt(l.get(2));
                ArrayList<String> m = new ArrayList<String>();
                m.add(Integer.toString(aid));
                m.add(ISBN);
                m.add(Integer.toString(cid));
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-15s", m.get(i)));
                System.out.println(sb);
             }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void display_WritePeriodicals() {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(jdbcURL, user, passwd);
            String sql_chk = "SELECT * FROM Writeperiodicals;"; 
            ps = conn.prepareStatement(sql_chk);
            ResultSet rs = ps.executeQuery();
            System.out.println("Write Periodicals Table");
            ArrayList<String> l = new ArrayList<String>( 
                    Arrays.asList("journalistid","ISBN","articleid"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<l.size(); i++)
                sb.append(String.format("| %-15s", l.get(i)));
            System.out.println(sb);
            
            while (rs.next()) {
                sb.setLength(0);
                 
                int jid = rs.getInt(l.get(0));
                String ISBN = rs.getString(l.get(1));
                int aid = rs.getInt(l.get(2));
                ArrayList<String> m = new ArrayList<String>();
                m.add(Integer.toString(jid));
                m.add(ISBN);
                m.add(Integer.toString(aid));
                for(int i=0; i<l.size(); i++)
                    sb.append(String.format("| %-15s", m.get(i)));
                System.out.println(sb);
             }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
