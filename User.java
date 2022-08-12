import java.sql.*;
import java.util.*;

class User
{
	public static void main(String args[]) throws Exception
	{
			int ch1,uid,pass,ch2,aid,apass;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Paddy32?autoReconnect=true&useSSL=false","root","MOTHIAAI32");
			Statement st = con.createStatement();

			System.out.println("1:New User Register");
			System.out.println("2:User Log In");
			System.out.println("3:Admin log In");
			System.out.println("What you want to do???");
			Scanner sc = new Scanner(System.in);
			ch1 = sc.nextInt();
			switch(ch1)
			{
				case 1:
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

   						PreparedStatement preparedStmt = con.prepareStatement(query);
  					    preparedStmt.setInt (1, uidd);
   					   	preparedStmt.setString (2, nm);
     					preparedStmt.setInt   (3, uage);
     					preparedStmt.setInt(4, mb);
      					preparedStmt.setString(5,add);
      					preparedStmt.setInt(6, passwd );

      					preparedStmt.execute();
						System.out.println("User Registered");
      			break;

      			case 2:
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
							}
						}
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
									query = "Select * from Movies";
									ResultSet rs2 = st.executeQuery(query);
									while(rs2.next())
									{
										System.out.println(rs2.getInt(1)+" | "+rs2.getString(2)+" | "+rs2.getString(3)+" | "+rs2.getString(4)+" | "+rs2.getInt(5));
									}
								break;
							case 2:
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
									System.out.println("Ticket Booked");

								break;
							case 3:
									System.out.println("Enter the ticket to cancel: id");
									int tdd = sc.nextInt();

									query = "delete from Tickets where T_id = ?";

									PreparedStatement preparedStmt2 = con.prepareStatement(query);
									preparedStmt2.setInt(1,tdd);

									preparedStmt2.execute();
									System.out.println("Ticket Canceled");
									System.out.println("Money Refunded By Admin");
								break;
							case 4:
									System.out.println("Tickets Details");
									query = "Select * from ticket_info";
									ResultSet rs3 = st.executeQuery(query);
									while(rs3.next())
									{
										System.out.println(rs3.getInt(1)+" | "+rs3.getString(2)+" | "+rs3.getInt(3));
									}
								break;
							case 5:
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
      							break;
							}
						break;
						case 3:
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
							}
							break;
			}
			st.close();
			con.close();
	}
}