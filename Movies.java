 import java.io.*;
import java.util.*;
import java.sql.*;

class INT_Movies
{
	int aid,apass;

	public void movies()
	{
		System.out.println("MOVIES:\n");
	}
}
class CNT_Movies
{
	public void validateDetails()
	{
		System.out.println("");
	}
}
class ENT_Movies
{
	public void movieDetails()
	{
		//This are the methods for java databse connectivity(JDBC)
		//forname for driver path
		Class.forName("com.mysql.cj.jdbc.Driver");
		//connection class for connection between java and mysql
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
		//statement class create statements for database operations
		Statement st = con.createStatement();

		//In user registration case 1 is for movie search or movie details
		String query = "Select * from Movies";
		ResultSet rs2 = st.executeQuery(query);
		while(rs2.next())
		{
			System.out.println(rs2.getInt(1)+" | "+rs2.getString(2)+" | "+rs2.getString(3)+" | "+rs2.getString(4)+" | "+rs2.getInt(5));

		}
	}
}
public class Movies
{
	public static void main(String args[]) throws Exception
	{
		ENT_Movies em = new ENT_Movies();
		em.movieDetails();
	}
}