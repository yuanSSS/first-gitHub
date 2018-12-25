package com.bdqn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//1.获得连接的方法：
	public void getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2.创建连接对象
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myschool","root","mysql");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//2.关闭所有对象的方法：
	public void closeAll(){
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(pstmt!=null)
				pstmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//3.增、删、改的方法：
	public int executeSql(String sql,Object... obj){
		getConn();
		int count=0;
		try {
			this.pstmt=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				pstmt.setObject(i+1, obj[i]);
			}
			count=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.closeAll();
		}
		return count;
	}
}
