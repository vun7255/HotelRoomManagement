package quanly.model;

import java.sql.Date;

public class HoaDon {
	private String idHoaDon;
	private String tenKhachHang;
	private Date tuNgay;
	private Date denNgay;
	private int tienPhong;
	private int tienDichVu;
	private int tong;

	public HoaDon(String idHoaDon, String tenKhachHang, int tienPhong, int tienDichVu, int tong, Date tuNgay,
			Date denNgay) {
		super();
		this.idHoaDon = idHoaDon;
		this.tenKhachHang = tenKhachHang;
		this.tienPhong = tienPhong;
		this.tienDichVu = tienDichVu;
		this.tong = tong;
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
	}

	public HoaDon() {
		super();
	}

	public String getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(String idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public int getTienPhong() {
		return tienPhong;
	}

	public void setTienPhong(int tienPhong) {
		this.tienPhong = tienPhong;
	}

	public int getTienDichVu() {
		return tienDichVu;
	}

	public void setTienDichVu(int tienDichVu) {
		this.tienDichVu = tienDichVu;
	}

	public int getTong() {
		return tong;
	}

	public void setTong(int tong) {
		this.tong = tong;
	}

	public Date getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(Date tuNgay) {
		this.tuNgay = tuNgay;
	}

	public Date getDenNgay() {
		return denNgay;
	}

	public void setDenNgay(Date denNgay) {
		this.denNgay = denNgay;
	}

}
