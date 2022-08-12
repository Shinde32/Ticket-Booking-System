import java.io.*;
import java.util.*;
import java.sql.*;

class INT_CancelTicket
{
	int tdd;

	public void getTicketId()
	{
		Scanner sc = new Scanner(System.in);

		//Case 3 for Cancel tickets booked already
		System.out.println("Enter the ticket to cancel: id");
		tdd = sc.nextInt();

	}
}
class CNT_CancelTicket extends INT_CancelTicket
{
	public void validateticket() throws Exception
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		getTicketId();

		String query = "delete from Tickets where T_id = ?";

		PreparedStatement preparedStmt2 = con.prepareStatement(query);
		preparedStmt2.setInt(1,tdd);
		preparedStmt2.execute();
		ENT_Tickets et = new ENT_Tickets();
		et.canceledTicket();
	}
}
class ENT_Tickets
{
	public void canceledTicket()
	{
		System.out.println("Ticket Canceled");
		System.out.println("Money Refunded By Admin to Your Account");
	}
}
public class CancelTickets
{
	public static void main(String args[]) throws Exception
	{
		CNT_CancelTicket cc = new CNT_CancelTicket();
		cc.validateticket();
	}
}