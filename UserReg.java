import java.io.*;
import java.util.*;
import java.sql.*;

class INT_Register
{
	int uidd,uage,mb,passwd;
	String nm,add;

	public void getUserDetails()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the User details: id,age,mobile,password,name,address");
		uidd = sc.nextInt();
		uage = sc.nextInt();
		mb = sc.nextInt();
		passwd = sc.nextInt();
		nm = sc.next();
		add = sc.next();

	}
}
class CNT_Register
{
	public void validateDetails()
	{
		System.out.println("\nValid User\n");
	}
}
class ENT_User extends INT_Register
{
	public void registerUser() throws Exception
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		getUserDetails();
		CNT_Register cr = new CNT_Register();
		cr.validateDetails();
		String query = " insert into User(U_id, Name, Age, Mobile, Address,Password)"
        						+ " values (?, ?, ?, ?, ?,?)";

        //prepared class is used to create statement to modify or add data in databse.
 		PreparedStatement preparedStmt = con.prepareStatement(query);
  		preparedStmt.setInt (1, uidd);
   	   	preparedStmt.setString (2, nm);
   		preparedStmt.setInt   (3, uage);
     	preparedStmt.setInt(4, mb); 
      	preparedStmt.setString(5,add);
     	preparedStmt.setInt(6, passwd );

      	preparedStmt.execute();
		System.out.println("User Registered");
	}
}
public class UserReg
{
	public static void main(String args[]) throws Exception
	{
		ENT_User er = new ENT_User();
		er.registerUser();
	}
}