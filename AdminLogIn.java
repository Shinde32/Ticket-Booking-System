import java.io.*;
import java.util.*;
import java.sql.*;

class INT_AdminLogIn
{
	int aid,apass;

	public void getAdminDetails()
	{
		Scanner sc = new Scanner(System.in);

		// Case 3 of main switch is for admin log in.
		System.out.println("Enter AdminId and Password");
      	aid = sc.nextInt();
     	apass = sc.nextInt();

	}
}
class CNT_AdminLogIn extends INT_AdminLogIn
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

		getAdminDetails();

		String query = "SELECT * FROM Admin";
      	ResultSet rs1 = st.executeQuery(query);
      	while (rs1.next())
      	{
      		int id = rs1.getInt("A_id");
       		int passd = rs1.getInt("A_Password");

        	if(id == aid && apass == passd)
			{
				ENT_Admin ed = new ENT_Admin();
				ed.logInStatus();
				break;
			}
			else
			{
				System.out.println("Enter valid	UserId Or Password\n");
				break;
			} 
		}
	}
}
class ENT_Admin
{
	public void logInStatus()
	{
		System.out.println("Admin LogedIn\n");
	}
}
public class AdminLogIn
{
	public static void main(String args[]) throws Exception
	{
		CNT_AdminLogIn cl = new CNT_AdminLogIn();
		cl.validateDetails();
	}
}