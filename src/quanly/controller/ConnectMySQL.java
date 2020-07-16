package quanly.controller;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConnectMySQL {
	protected Connection conn;
	public ConnectMySQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://localhost:3306/quanlykhachsan?autoReconnect=true&useSSL=false";
			conn = (Connection) DriverManager.getConnection(connectionURL,"root","Nguyenduyvu25!");
			Statement stm = (Statement) conn.createStatement();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
