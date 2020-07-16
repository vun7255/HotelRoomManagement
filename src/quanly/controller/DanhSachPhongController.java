package quanly.controller;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import quanly.model.DanhSachPhong;
import quanly.view.GiaoDienNhanVien;

public class DanhSachPhongController extends ConnectMySQL {
	private ImageIcon imgFreeRoom = new ImageIcon(DanhSachPhongController.class.getClassLoader().getResource("hotel_freeRoom.png"));
	private ImageIcon imgOccupied = new ImageIcon(DanhSachPhongController.class.getClassLoader().getResource("hotel_occupiedRoom.png"));
	private LinkedHashMap<Integer, JButton> phong;

	//hiển thị danh sách tất cả các phòng
	public LinkedHashMap<Integer, JButton> kiemTraPhong() {
		int width = 226;
		int height = 107;
		int countX = 0;
		int countY = 0;
		int countTab = 0;
		phong = new LinkedHashMap<Integer, JButton>();
		try {
			String sql = "select * from danhsachphong";
			Statement preparedStatement = (Statement) conn.createStatement();
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				JButton btnPhong = new JButton();
				btnPhong.setBounds(0, 0, width, height);
	
				int soPhong = resultSet.getInt(1);
				String loaiPhong = resultSet.getString(2);
				int giaPhong = resultSet.getInt(3);
				int tinhTrang = resultSet.getInt(4);
				
				String thongBao = "Phòng: " + soPhong + "\nLoại phòng: " + loaiPhong + "\nGiá phòng: " + giaPhong;
				
				
				if(tinhTrang == 0) {
					btnPhong.setIcon(imgOccupied);
				}
				else if(tinhTrang == 1) {
					btnPhong.setIcon(imgFreeRoom);
				}
				
				btnPhong.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnPhong.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(null, thongBao, "Phòng " + soPhong, JOptionPane.INFORMATION_MESSAGE);

					}
				});
				
				phong.put(soPhong, btnPhong);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return phong;

	}
	
	public HashMap<Integer, Integer> layDanhSachPhongTrong(){
		HashMap<Integer, Integer> danhSachPhongTrong = new HashMap<Integer, Integer>();
		try {
			String sql = "select * from danhsachphong";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int soPhong = resultSet.getInt(1);
				int tinhTrang= resultSet.getInt(4);
				danhSachPhongTrong.put(soPhong, tinhTrang);
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return danhSachPhongTrong;
		
		
	}
}
