package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LogginApp {

	public static void main(String[] args) {
		
		Scanner sc=null;
		String User=null;
		String Pass=null;
		Connection con=null;
		ResultSet rs=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		try {
			// reading input 
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Username");
				User=sc.next();
				System.out.println("Enter Password");
				Pass=sc.next();
				// convert input values as required for sql query
				User="'"+User+"'";
				Pass="'"+Pass+"'";
			}
			
			// register driver class
						Class.forName("oracle.jdbc.driver.OracleDriver");
						// establish connection
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
						// create statement
				st=con.createStatement();
			// prepare sql query
			// select count(*) from userlist where uname='raja' and pwd='rani'
			
			query="SELECT COUNT(*) FROM USERLIST WHERE UNAME="+User+" and pwd="+Pass;
			
			System.out.println(query);
			
			// execute the query
			
			if(st!=null)
				rs=st.executeQuery(query);
			// process the result
			
			if(rs!=null) {
				if(rs.next())
					count=rs.getInt(1);
				System.out.println(count);
			}
			if(count==0)
				System.out.println("Invalid Credential");
				else
				System.out.println("Valid Credential");
		}// try
		
		catch(SQLException se) {
		se.printStackTrace();
		System.out.println("Record Insertion Faild");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			System.out.println("Record Insertion Faild");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Record Insertion Faild");
		}
		finally {
			// close jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
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
		

