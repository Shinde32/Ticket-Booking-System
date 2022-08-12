import java.io.*;
import java.util.*;
import java.sql.*;

class INT_LogIn
{
	int uid,pass;

	public void getUserDetails()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter UserId and Password");
      	uid = sc.nextInt();
      	pass = sc.nextInt();

	}
}
class CNT_LogIn extends INT_LogIn
{
	public void validateDetails() throws Exception
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		getUserDetails();

		String query = "SELECT * FROM User";
      	ResultSet rs = st.executeQuery(query);
      	while (rs.next())
      	{
      		int id = rs.getInt("U_id");
        	int passd = rs.getInt("Password");

        	if(id == uid && pass == passd)
			{
				ENT_User es = new ENT_User();
				es.logInStatus();
				break;
			}
			else
			{
				System.out.println("Invalid User");
				break;
			}
		}
	}
}
class ENT_User
{
	public void logInStatus()
	{
		System.out.println("UserLogedIn\n");
		//This options will display to the user when he logged in to system.
		System.out.println("1:Movie Details");
		System.out.println("2:Book Tickets");
		System.out.println("3:Cancel Tickets");
		System.out.println("4:View Tickets Details");
		System.out.println("5:Make Payment\n");
		
	}
}
public class UserLogIn
{
	public static void main(String args[]) throws Exception
	{
		CNT_LogIn cl = new CNT_LogIn();
		cl.validateDetails();
	}
}