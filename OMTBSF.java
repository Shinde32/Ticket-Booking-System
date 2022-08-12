import java.sql.*;
import java.util.*;

/***********************************************************************

PROGRAM : MOVIE TICKET BOOKING SYSTEM
AUTHER : PRATHAMESH SHINDE

FUNCTION : PERFORMS DIFFERENT FUNCTIONS AS PER THE USER CHOICE.

INPUT : STANDARD INPUT BASED ON ACTION SELECTED BY USER OF THE SYSTEM.

OUTPUT : PROGRAM WILL GIVE APPROPRIATE OUTPUT FO THE USER ACTION AFTER PROPRR INPUTS.

************************************************************************/

class OMTBSF
{
	public static void main(String args[]) throws Exception
	{
			int ch1,uid,pass,ch2,aid,apass;

			//This are the methods for java databse connectivity(JDBC)
			//forname for driver path
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//connection class for connection between java and mysql
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
			
			//statement class create statements for database operations
			Statement st = con.createStatement();

			//Options to perform when program runs
			System.out.println("1:New User Register");
			System.out.println("2:User Log In");
			System.out.println("3:Admin log In");

			System.out.println("What you want to do???");
			Scanner sc = new Scanner(System.in);
			ch1 = sc.nextInt();
			switch(ch1)
			{
				case 1:
						//In switch case case 1 is reserved for user registration
						System.out.println("Enter the User details: id,age,mobile,password,name,address");
						int uidd,uage,mb,passwd;
						String nm,add;
						uidd = sc.nextInt();
						uage = sc.nextInt();
						mb = sc.nextInt();
						passwd = sc.nextInt();
						nm = sc.next();
						add = sc.next();
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
						System.out.println("\nUser Registered");
      			break;

      			case 2:
      					//Case 2 is for User loggin to perform various operations
      					System.out.println("Enter UserId and Password");
      					uid = sc.nextInt();
      					pass = sc.nextInt();

      					query = "SELECT * FROM User";
      					ResultSet rs = st.executeQuery(query);
      					while (rs.next())
      					{
      			  			int id = rs.getInt("U_id");
        					int passd = rs.getInt("Password");

        					if(id == uid && pass == passd)
							{
								System.out.println("User Logged In\n");


								//This options will display to the user when he logged in to system.
								System.out.println("1:Movie Details");
								System.out.println("2:Book Tickets");
								System.out.println("3:Cancel Tickets");
								System.out.println("4:View Tickets Details");
								System.out.println("5:Make Payment\n");
								System.out.println("Your Action\n");
								ch2= sc.nextInt();
								switch(ch2)
								{
							case 1:
									//In user registration case 1 is for movie search or movie details
									query = "Select * from Movies";
									ResultSet rs2 = st.executeQuery(query);
									while(rs2.next())
									{
										System.out.println(rs2.getInt(1)+" | "+rs2.getString(2)+" | "+rs2.getString(3)+" | "+rs2.getString(4)+" | "+rs2.getInt(5));

									}
									rs2.close();
								break;
							case 2:
									// Case 2 is for book the tickets for perticular movie
									System.out.println("Enter the tickets details: id,price,movie name,type,date");
									int tid,pric;
									String mv,tp,dateString;
									tid = sc.nextInt();
									pric = sc.nextInt();
									mv = sc.next();
									tp = sc.next();
									dateString = sc.next();
									query = " insert into Tickets(T_id,Movie,Type,Price,tDate)"
        											+ " values (?, ?, ?, ?, ?)";

   									PreparedStatement preparedStmt1 = con.prepareStatement(query);
  					   				preparedStmt1.setInt (1, tid);
   					   				preparedStmt1.setString (2, mv);
     								preparedStmt1.setString(3, tp);
     								preparedStmt1.setInt(4, pric);
      								preparedStmt1.setString(5,dateString);

      								preparedStmt1.execute();
									System.out.println("Ticket reserved for You");

								break;
							case 3:
									//Case 3 for Cancel tickets booked already
									System.out.println("Enter the ticket to cancel: id");
									int tdd = sc.nextInt();

									query = "delete from Tickets where T_id = ?";

									PreparedStatement preparedStmt2 = con.prepareStatement(query);
									preparedStmt2.setInt(1,tdd);

									preparedStmt2.execute();
									System.out.println("Ticket Canceled");
									System.out.println("Money Refunded By Admin to Your Account");
								break;
							case 4:
									//case 4 for checking book tickets details or its status
									System.out.println("Tickets Details");
									query = "Select * from ticket_info";
									ResultSet rs3 = st.executeQuery(query);
									while(rs3.next())
									{
										System.out.println(rs3.getInt(1)+" | "+rs3.getString(2)+" | "+rs3.getInt(3));
									}
								break;
							case 5:
									//Case 5 for payment for booked tickets
									System.out.println("Enter payment details: Payment Id, Amount,customer name");
									int pid,amt;
									String cname;
									String s1 = "Paid";

									pid = sc.nextInt();
									amt = sc.nextInt();
									cname = sc.next();

									query = " insert into Payment(P_id,Amount,C_name,Pstatus)"
        											+ " values (?, ?, ?,?)";

        							PreparedStatement preparedStmt3 = con.prepareStatement(query);
  					   				preparedStmt3.setInt (1, pid);
   					   				preparedStmt3.setInt (2, amt);
     								preparedStmt3.setString(3, cname);
      								preparedStmt3.setString(4,s1);
      								
      								preparedStmt3.execute();

      								System.out.println("Payment Successful");
      								System.out.println("Ticket Booked");
      							break;
									}
								}
								else
								{
									//System.out.println("Enter the valid UserId Or password");
								}
						}
						break;
						case 3:
							// Case 3 of main switch is for admin log in.
							System.out.println("Enter AdminId and Password");
      						aid = sc.nextInt();
      						apass = sc.nextInt();

      						query = "SELECT * FROM Admin";
      						ResultSet rs1 = st.executeQuery(query);
      						while (rs1.next())
      						{
      			  				int id = rs1.getInt("A_id");
        						int passd = rs1.getInt("A_Password");

        						if(id == aid && apass == passd)
								{
									System.out.println("Admin Logged In");
								}
								else
								{
									System.out.println("Enter valid	UserId	Or Password\n");
									return;
								} 
							}
							break;
			}
			//finally connection and statement classes are closed.
			st.close();
			con.close();
	}
}