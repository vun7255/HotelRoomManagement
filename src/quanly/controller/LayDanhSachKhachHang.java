package quanly.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.PreparedStatement;

import quanly.model.KhachHang;

public class LayDanhSachKhachHang extends ConnectMySQL {

	// danh sách tất cả khách hàng
	private Vector<KhachHang> khachHang = new Vector<KhachHang>();

	public Vector<KhachHang> danhSachKhachHang() {
		try {
			String sql = "select * from khachhang";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				KhachHang addKhachHang = new KhachHang();
				addKhachHang.setIdKhachHang(resultSet.getString(1));
				addKhachHang.setTenKhachHang(resultSet.getString(2));
				addKhachHang.setGioiTinh(resultSet.getString(3));
				addKhachHang.setQuocTich(resultSet.getString(4));
				addKhachHang.setCmnd(resultSet.getString(5));
				addKhachHang.setSoDienThoai(resultSet.getString(6));
				addKhachHang.setSoPhong(resultSet.getInt(7));
				addKhachHang.setNgayDen(resultSet.getDate(8));
				addKhachHang.setNgayDi(resultSet.getDate(9));
				addKhachHang.setDichVu(resultSet.getString(10));
				addKhachHang.setSoNguoi(resultSet.getInt(11));
				khachHang.add(addKhachHang);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return khachHang;
	}

	// lấy thông tin 1 khách hàng
	public KhachHang thongTinMotKhachHang(int soPhong) {
		KhachHang khachHang = new KhachHang();
		try {
			String sql = "select * from khachhang where SoPhong = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setInt(1, soPhong);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				khachHang.setIdKhachHang(resultSet.getString(1));
				khachHang.setTenKhachHang(resultSet.getString(2));
				khachHang.setGioiTinh(resultSet.getString(3));
				khachHang.setQuocTich(resultSet.getString(4));
				khachHang.setCmnd(resultSet.getString(5));
				khachHang.setSoDienThoai(resultSet.getString(6));
				khachHang.setNgayDen(resultSet.getDate(8));
				khachHang.setNgayDi(resultSet.getDate(9));
				khachHang.setDichVu(resultSet.getString(10));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return khachHang;
	}

	// thêm khách hàng check in
	public boolean themKhachHang(KhachHang khachHang) {
		boolean check = false;
		PreparedStatement preparedStatement;
		int x = 0, y = 0;
		try {
			String sql = "insert into khachhang"
					+ "(TenKhachHang, GioiTinh, QuocTich, CMND, SoDienThoai, SoPhong, NgayDen, NgayDi, SoNguoi) "
					+ "value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, khachHang.getTenKhachHang());
			preparedStatement.setString(2, khachHang.getGioiTinh());
			preparedStatement.setString(3, khachHang.getQuocTich());
			preparedStatement.setString(4, khachHang.getCmnd());
			preparedStatement.setString(5, khachHang.getSoDienThoai());
			preparedStatement.setInt(6, khachHang.getSoPhong());
			preparedStatement.setDate(7, khachHang.getNgayDen());
			preparedStatement.setDate(8, khachHang.getNgayDi());
			preparedStatement.setInt(9, khachHang.getSoNguoi());

			x = preparedStatement.executeUpdate();
			if (x > 0) {
				System.out.println("Lưu thành công");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String sql = "update danhsachphong set TinhTrang = ? where SoPhong = ?";
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, khachHang.getSoPhong());
			y = preparedStatement.executeUpdate();
			if (x > 0 && y > 0) {
				System.out.println("Phòng đã được chọn");
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// xóa khách hàng check out
	public boolean xoaKhachHang(int roomNumber) {
		try {
			String sql = "delete from khachhang where SoPhong = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setInt(1, roomNumber);

			String sql1 = "update danhsachphong set TinhTrang = ? where SoPhong = ?";
			PreparedStatement preparedStatement2 = (PreparedStatement) conn.prepareStatement(sql1);
			preparedStatement2.setInt(1, 1);
			preparedStatement2.setInt(2, roomNumber);
			int x = preparedStatement.executeUpdate();
			int y = preparedStatement2.executeUpdate();
			if (x > 0 && y > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	//update thông tin khách hàng
	public boolean updateThongTinKhachHang(KhachHang khachHang, int IDKhachHang) {
		try {
			String sql = "update khachhang set TenKhachHang = ?,"
					+ " GioiTinh = ?, QuocTich = ?, CMND = ?, SoDienThoai = ?,"
					+ " SoPhong = ?, NgayDen = ?, NgayDi = ?, SoNguoi = ? "
					+ " where IDkhachhang = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setString(1, khachHang.getTenKhachHang());
			preparedStatement.setString(2, khachHang.getGioiTinh());
			preparedStatement.setString(3, khachHang.getQuocTich());
			preparedStatement.setString(4, khachHang.getCmnd());
			preparedStatement.setString(5, khachHang.getSoDienThoai());
			preparedStatement.setInt(6, khachHang.getSoPhong());
			preparedStatement.setDate(7, khachHang.getNgayDen());
			preparedStatement.setDate(8, khachHang.getNgayDi());
			preparedStatement.setInt(9, khachHang.getSoNguoi());
			preparedStatement.setInt(10, IDKhachHang);
			
			
			int x = preparedStatement.executeUpdate();
			if (x > 0) {
				return true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
	public void thayDoiTrangThaiPhongSauKhiUpdate(int phongCu, int phongMoi) {
		try {
			String sql = "update danhsachphong set TinhTrang = ? where SoPhong = ?";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, phongCu);
			int x = preparedStatement.executeUpdate();
			PreparedStatement preparedStatement2 = (PreparedStatement) conn.prepareStatement(sql);
			preparedStatement2.setInt(1, 0);
			preparedStatement2.setInt(2, phongMoi);
			int y = preparedStatement2.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
