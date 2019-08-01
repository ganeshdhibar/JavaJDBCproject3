package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsert {

	// prepared sql query
	private static final String query="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int sno=0;
		String sname=null,sadd=null;
	    Float savg=0.0f;
		int result=0;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("How many Student details you are inserted to insert");
				count=sc.nextInt();
			}
			
			// register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			// create prepareStatement object
			if(con!=null)
				ps=con.prepareStatement(query);
			// read and set input values to query parameters
			if(ps!=null&&sc!=null)
				for(int i=1;i<=count;++i) {
					System.out.println("Enter "+i+"Student details");
					System.out.println("Enter Student sno");
					sno=sc.nextInt();
					System.out.println("Enter Student sname");
					sname=sc.next();
					System.out.println("Enter Student sadd");
					sadd=sc.next();
					System.out.println("Enter Student savg");
					savg=sc.nextFloat();
					//set the input values read from enduser to query parameters
					ps.setInt(1,sno);
					ps.setString(2,sname);
					ps.setString(3,sadd);
					ps.setFloat(4,savg);
					// execute the query
					result=ps.executeUpdate();
					if(result==0)
						System.out.println(i+"Student details are not inserted");
					else
						System.out.println(i+"Student details are inserted");
					
				} // for
		} //try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// close the connection
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
				}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		} // finally
			
		} // main
	} // class

		
		
		