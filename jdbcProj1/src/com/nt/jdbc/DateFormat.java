package com.nt.jdbc;

import java.text.SimpleDateFormat;

public class DateFormat {

	public static void main(String[] args)throws Exception {
		SimpleDateFormat sdf=null;
		String d1="01-08-2019";
		sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(d1);
		System.out.println(ud1);

	}

}
