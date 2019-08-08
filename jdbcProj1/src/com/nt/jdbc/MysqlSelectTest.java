package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlSelectTest {
	
	public static void main(String []args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			// register jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ganesh1112","root","root");
			// create statement object
			if(con!=null)
				st=con.createStatement();
			// send and execute query in db table
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT");
			// process the result
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+""+rs.getString(2)+""+rs.getString(3)+""+rs.getFloat(4));
				}//while
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
		} //finally
	} // main

} // class
