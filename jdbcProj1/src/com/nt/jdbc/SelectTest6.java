package com.nt.jdbc;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SelectTest6 {

	public static void main(String[] args) {
		
             Scanner sc=null;
             int rank=0;
             Connection con=null;
             Statement st=null;
             String query=null;
             ResultSet rs=null;
             boolean flag=false;
             
             try {
            	 // reading inputs from end user
            	 sc=new Scanner(System.in);
            	 if(sc!=null) {
            		 System.out.println("Enter nth highest avg score in odi:");
            		 rank=sc.nextInt();
            	 }// if
            	 // register driver
            	 Class.forName("oracle.jdbc.driver.OracleDriver");
            	 // establish connection
            	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
            	 if(con!=null) {
            		 // create Statement
            		 st=con.createStatement();
            		 // prepare query
            		 	// select * from(select sno,player,runs,avgs,dense_rank()over(over by desc)rank from rum_score_in_odi) where ranks=1
            		 query=("SELECT * FROM(SELECT SNO,PLAYER,RUNS,AVGS,DENSE_RANK() OVER (ORDER BY AVGS DESC)RANKS FROM RUN_SCORE_IN_ODI)WHERE RANKS=1");
            		 System.out.println(query);
            	 }// if
            	 if(st!=null) {
            		 // send query to db
            		 rs=st.executeQuery(query);
            	 }// if
            	 //processing
            	 flag=false;
            	 while (rs.next()) {
            		 flag=true;
            		 System.out.println("SNO\tPLAYER        \tRUNS\tAVGS\tRANKS");
            		 System.out.println("...............................................");
            		 System.out.println(rs.getString("SNO")+"  "+rs.getString("PLAYER")+"   "+rs.getString("RUNS")+"   "+rs.getString("AVGS")+"  "+rs.getString("RANK"));
            	 }// while
            	 if(flag=false) {
            		 System.out.println("SORRY ! NO RECORD FOUND");
            	 }// if
             }// try
             catch(SQLException se) {
            	 se.printStackTrace(); // knows exception
             }
             catch(ClassNotFoundException cnf) {
            	 cnf.printStackTrace();
             } // known exception
             catch(Exception e) {
            	 e.printStackTrace();
             }
             finally {
            	 try {
            		 if(rs!=null) {
            			 rs.close();
            		 }
            	 }
            	 catch(SQLException se) {
            		 se.printStackTrace();
            	 }
            	 try {
            		 if(sc!=null) {
            			 sc.close();
            		 }
            	 }
            	 catch(Exception e) {
            		 e.printStackTrace();
            	 }
             } //finally
      
	}// main
}// class
