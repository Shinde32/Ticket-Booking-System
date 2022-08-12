import java.io.*;
import java.util.*;
import java.sql.*;

class INT_Tickets
{
	int aid,apass;

	public void tickets()
	{
		//case 4 for checking book tickets details or its status
		System.out.println("\nDetails of Booking tickets\n");
	}
}
class CNT_Tickets extends INT_Tickets
{
	public void validateDetails()
	{
		tickets();
		System.out.println("Ticket_ID\tType\tTicketPrice\n");
	}
}
class ENT_Tickets extends CNT_Tickets
{
	public void ticketDetails() throws Exception
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		validateDetails();
		//In user registration case 1 is for movie search or movie details
		String query = "Select * from ticket_info";
		ResultSet rs3 = st.executeQuery(query);
		while(rs3.next())
		{
			System.out.println(rs3.getInt(1)+" | "+rs3.getString(2)+" | "+rs3.getInt(3));
		}
	}
}
public class Tickets
{
	public static void main(String args[]) throws Exception
	{
		ENT_Tickets et = new ENT_Tickets();
		et.ticketDetails();
	}
}