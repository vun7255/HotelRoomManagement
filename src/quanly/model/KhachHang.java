package quanly.model;

import java.sql.Date;

public class KhachHang {
	private String idKhachHang;
	private String tenKhachHang;
	private String gioiTinh;
	private String quocTich;
	private String cmnd;
	private String soDienThoai;
	private Integer soPhong;
	private Date ngayDen;
	private Date ngayDi;
	private String dichVu;
	private int soNguoi;
	

	
	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public String getIdKhachHang() {
		return idKhachHang;
	}

	public void setIdKhachHang(String idKhachHang) {
		this.idKhachHang = idKhachHang;
	}


	public String getDichVu() {
		return dichVu;
	}

	public void setDichVu(String dichVu) {
		this.dichVu = dichVu;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getQuocTich() {
		return quocTich;
	}

	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public Integer getSoPhong() {
		return soPhong;
	}

	public void setSoPhong(Integer soPhong) {
		this.soPhong = soPhong;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public Date getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}

	public KhachHang() {
		super();
	}

	public KhachHang(String tenKhachHang, String gioiTinh, String quocTich, String cmnd,
			String soDienThoai, Integer soPhong, Date ngayDen, Date ngayDi, int soNguoi) {
		super();
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.quocTich = quocTich;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.soPhong = soPhong;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.soNguoi = soNguoi;
	}



}
