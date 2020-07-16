package quanly.controller;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import quanly.model.DangNhap;
import quanly.vendors.BCrypt;

public class DangNhapController extends ConnectMySQL {

	public DangNhap login(String userName) {
		DangNhap account = null;
		try {
			String sql = "Select * from taikhoan where Username = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			// preparedStatement.setString(2, passWord);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				account = new DangNhap();
				account.setUserName(resultSet.getString(1));
				account.setPassWord(resultSet.getString(2));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return account;
	}

	public boolean changePassword(String username, String plainPassword) {
		try {
			String sql = "update taikhoan set Password = ? where Username = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			String newPass = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
			preparedStatement.setString(1, newPass);
			preparedStatement.setString(2, username);
			int x = preparedStatement.executeUpdate();
			if(x > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
