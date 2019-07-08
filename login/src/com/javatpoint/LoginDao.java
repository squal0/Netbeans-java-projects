package com.javatpoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	public static boolean validate(String username,String userpass){
		boolean status=false;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			PreparedStatement ps=con.prepareStatement("select * from user3333 where name=? and password=?");
			ps.setString(1,username);
			ps.setString(2,userpass);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
		}catch(Exception e){e.printStackTrace();}
		return status;
	}
}
