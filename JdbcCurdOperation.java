package com.gwpt.cs.curd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcCurdOperation {
	
	public void insertRecord(int rn,String na,double fees){
		try {
			//Class.forName("oracle.jdbc.OracleDriver");
			
			//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","gwpt","gwpt123");
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/gwptdb","root","root");  
			String query = "insert into student values(?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			//setting values to place holder
			ps.setDouble(3, fees);
			ps.setInt(1,rn);
			ps.setString(2,na);
			
			
			ps.executeUpdate(); //DML
			
			System.out.println("record inserted successfully");
			con.close();
					
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	public void getOneRecord(int rn) throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","gwpt","gwpt123");
		
		String query ="select * from student where rollno =?";//? is place holder
		
		PreparedStatement ps = con.prepareStatement(query);
		
		//setting values to place holder
		ps.setInt(1,rn);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+": "+rs.getString("name")+" :"+rs.getDouble(3));
			
		}
		con.close();	
		
	}
	public void updateRecord(int rn,double fe) {
		try{
		
		Class.forName("oracle.jdbc.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","gwpt","gwpt123");
		
		String query = "update student set fees=? where rollno=?";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setDouble(1, fe);
		ps.setInt(2, rn);
		
		ps.executeUpdate();
		System.out.println("record updated successfully");
		con.close();
				
	}catch(Exception e) {
		e.printStackTrace();
	}
		
}
		
	public void deleteRecord(int rn) throws Exception {
		
		Class.forName("oracle.jdbc.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","gwpt","gwpt123");
		
		String query = "delete from student where rollno=?";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1,rn);
		
		ps.executeUpdate();
		
		System.out.println("deleted sucessfull");
		con.close();
			}
	public static void main(String[] args) throws Exception{
		JdbcCurdOperation obj = new JdbcCurdOperation();
		//obj.getOneRecord(10);
		obj.insertRecord(103,"potter",6500.50);
		//obj.updateRecord(11,7500);
		//obj.deleteRecord(12);
	}
}

