package com.mbugajski.springdemo.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJbdc {
	public static void main(String[] args) {

		String jbdcUrl = "jdbc:mysql://localhost/hb-01-one-to-many?useSSL=false&useUnicode=true&"
				+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
			System.out.println("Connecting to database: " + jbdcUrl);
			Connection myConn = DriverManager.getConnection(jbdcUrl, user, pass);
			System.out.println("Connection successful!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
