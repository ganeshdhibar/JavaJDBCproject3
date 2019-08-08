package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Insertdate {
	
	public static void main(String[] args) {
		
		Scanner sc=null;
		String name=null,dob=null,doj=null;
		java.util.Date udob=null;
		java.sql.Date sdob=null,sdoj=null;
		SimpleDateFormat sdf=null;
		long ms=0;
		Connection con=null;
		String Query="INSERT INTO PERSON VALUES(?,?,?)";
		PreparedStatement ps=null;
		int result=0;
		sc=new Scanner(System.in);
		try {
		//read input values
		if(sc!=null) {
			System.out.println("Enter name ::");
			name=sc.next();
			System.out.println("Enter Date of birth(dd-mm-yyyy)::");
			dob=sc.next();
			System.out.println("Enter Date of join(yyyy-mm-dd)::");
			doj=sc.next();
			
		}//if
		sdf=new SimpleDateFormat("dd-MM-yyyy");
		if(sdf!=null)
		udob=sdf.parse(dob);
		if(udob!=null)
		ms=udob.getTime();
		sdob=new java.sql.Date(ms);
		
		sdoj=java.sql.Date.valueOf(doj);
		
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Establish connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		
		//prepare query
		
		ps=con.prepareStatement(Query);
		if(ps!=null) {
			ps.setString(1,name);
			ps.setDate(2,sdob);
			ps.setDate(3,sdoj);
		}//if
		
		//execute query
		if(ps!=null)
		result=ps.executeUpdate();
		
		if(result==0)
			System.out.println("record not inserted");
		else
			System.out.println("record inserted ");
	}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("record insertion faild ");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			System.out.println("record insertion faild ");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("record insertion faild ");
		}
		
		finally {
			try {
				if(ps!=null)
					ps.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
}//main
}//class