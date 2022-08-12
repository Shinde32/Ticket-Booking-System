import java.io.*;
import java.util.*;
import java.sql.*;

class INT_TicketBooking
{
	int tid,pric;
	String mv,tp,dateString;

	public void getTicketDetails()
	{
		Scanner sc = new Scanner(System.in);

		// Case 2 is for book the tickets for perticular movie
		System.out.println("Enter the tickets details: id,price,movie name,type,date");
		tid = sc.nextInt();
		pric = sc.nextInt();
		mv = sc.next();
		tp = sc.next();
		dateString = sc.next();

	}
}
class CNT_TicketBooking
{
	public void validateTicket()
	{
		System.out.println("\nValid Ticket requirement\n");
	}
}
class ENT_Tickets extends INT_TicketBooking
{
	public void bookTicket() throws Exception
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		getTicketDetails();
		CNT_TicketBooking ct = new CNT_TicketBooking();
		ct.validateTicket();
		String query = " insert into Tickets(T_id,Movie,Type,Price,tDate)"
  					+ " values (?, ?, ?, ?, ?)";

   			PreparedStatement preparedStmt1 = con.prepareStatement(query);
  			preparedStmt1.setInt (1, tid);
     		preparedStmt1.setString (2, mv);
     		preparedStmt1.setString(3, tp);
     		preparedStmt1.setInt(4, pric);
      		preparedStmt1.setString(5,dateString);

      		preparedStmt1.execute();
			System.out.println("Ticket reserved for You");
	}
}
public class TicketBooking
{
	public static void main(String args[]) throws Exception
	{
		ENT_Tickets et = new ENT_Tickets();
		et.bookTicket();
	}
}