import java.io.*;
import java.util.*;
import java.sql.*;

class INT_Payment
{
	int pid,amt;
	String cname;
	String s1 = "Paid";

	public void getPaymengdet()
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter payment details: Payment Id, Amount,customer name");
		pid = sc.nextInt();
		amt = sc.nextInt();
		cname = sc.next();

	}
}
class CNT_Payment
{
	public void validateDetails()
	{
		System.out.println("\nPayment details are Valid.\n");
	}
}
class ENT_Payment extends INT_Payment
{
	public void makePayment() throws Exception
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		getPaymengdet();
		CNT_Payment cr = new CNT_Payment();
		cr.validateDetails();
		String query = " insert into Payment(P_id,Amount,C_name,Pstatus)"
        											+ " values (?, ?, ?,?)";

        PreparedStatement preparedStmt3 = con.prepareStatement(query);
  		preparedStmt3.setInt (1, pid);
   		preparedStmt3.setInt (2, amt);
     	preparedStmt3.setString(3, cname);
      	preparedStmt3.setString(4,s1);
      								
      	preparedStmt3.execute();

      	System.out.println("Payment Successful");
      	System.out.println("Ticket Booked");
	}
}
public class Payment
{
	public static void main(String args[]) throws Exception
	{
		ENT_Payment ep = new ENT_Payment();
		ep.makePayment();
	}
}