package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateSal {

	public static void main(String [] args) {
		
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			// register driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			
			// Create Statement Object
			st=con.createStatement();
			
			// prepare query
			// update emp set sal=sal+(sal*10/100) where job='clerk'
			query="UPDATE EMP SET SAL =SAL+(SAL*10/100) WHERE JOB='CLERK'";
			System.out.println(query);
			
			//send to query database
				if(st!=null)
					count=st.executeUpdate(query);
				System.out.println(count+"Row Update Successfully");
		} // try
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
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try {
				if(con!=null) 
					con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
			} // finally52
				
		} // main
	} // class

