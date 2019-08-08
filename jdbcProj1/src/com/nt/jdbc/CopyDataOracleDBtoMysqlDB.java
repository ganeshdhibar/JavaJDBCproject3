package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CopyDataOracleDBtoMysqlDB {
	private static final String INSERT_MYSQL_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String SELECT_ORACLE_QUERY="SELECT * FROM STUDENT";
	
	public static void main(String[] args) {

		Connection oraCon=null,mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int sno=0;
		String sname=null;
		String sadd=null;
		Float savg=0.0f;
		
		try {
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		//establish the connection
		oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		mysqlCon=DriverManager.getConnection("jdbc:mysql:///ganesh1112","root","root");
		//create Statement class
		if(oraCon!=null)
			st=oraCon.createStatement();
		if(mysqlCon!=null)
			ps=mysqlCon.prepareStatement(INSERT_MYSQL_QUERY);
		// get record from oracle DB table
		if(st!=null)
			rs=st.executeQuery(SELECT_ORACLE_QUERY);
		// process oracle records (ResultSet)
		if(rs!=null && ps!=null) {
			while(rs.next()) {
				// get each records from resultSet obj orcle
				sno=rs.getInt(1);
				sname=rs.getString(2);
				sadd=rs.getString(3);
				savg=rs.getFloat(4);
				//set each above record values to mysql insert query
				ps.setInt(1,sno);
				ps.setString(2,sname);
				ps.setString(3,sadd);
				ps.setFloat(4,savg);
				// execute the query
				ps.executeUpdate();
			}// while
		}// if
		System.out.println("Record are copied from oracle db table to mysql");
	}//try
		
	catch(SQLException se) {
		se.printStackTrace();
		System.out.println("Record copied faild !");
	}
	catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
		System.out.println("Record copied faild !");
	}
	catch(Exception e) {
		e.printStackTrace();
		System.out.println("Record copied faild !");
	}
		
	 finally{
		// close jdbc object
		try {
			if(rs!=null)
				rs.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			if(ps!=null)
				ps.close();
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
			if(oraCon!=null)
				oraCon.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			if(mysqlCon!=null)
				mysqlCon.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		
			}//finally
	
		}//main

	}//class


