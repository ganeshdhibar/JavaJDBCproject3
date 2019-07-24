package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertRecord {
	public static void main(String[] args) {
		
		Scanner sc=null;
		int empno=0;
		float sal=0.0f;
		String ename=null;
		String job=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		try {
			// reading input from end user
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("PLEASE ENTER EMPNO");
				empno=sc.nextInt();
				System.out.println("PLEASE ENTER EMPLOYEE NAME");
				ename=sc.next();
				System.out.println("PLEASE ENTER JOB NAME");
				job=sc.next();
				System.out.println("PLEASE ENTER EMPLOYEE SAL");
				sal=sc.nextFloat();
			}
			// register driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			// create statement
			if(con!=null)
				st=con.createStatement();
			// prepare query
			// insert into emp (empno,ename,job,sal)values(empno,'ename','job',sal);
			query="INSERT INTO EMP (EMPNO,ENAME,JOB,SAL)VALUES ("+empno+",'"+ename+"','"+job+"',"+sal+")";
			System.out.println(query);
			// send query to db
			if(st!=null)
				count=st.executeUpdate(query);
			// processing
			if(count==1)
				System.out.println("Record insert Successfull");
				
			
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
		// close the connection
		finally {
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

