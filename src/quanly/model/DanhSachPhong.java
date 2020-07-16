package quanly.model;

public class DanhSachPhong {
	private int soPhong;
	private String loaiPhong;
	private int giaPhong;
	private int tinhTrang;
	public DanhSachPhong() {
		super();
	}
	public DanhSachPhong(int soPhong, String loaiPhong, int giaPhong, int tinhTrang) {
		super();
		this.soPhong = soPhong;
		this.loaiPhong = loaiPhong;
		this.giaPhong = giaPhong;
		this.tinhTrang = tinhTrang;
	}
	public int getSoPhong() {
		return soPhong;
	}
	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}
	public String getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public int getGiaPhong() {
		return giaPhong;
	}
	public void setGiaPhong(int giaPhong) {
		this.giaPhong = giaPhong;
	}
	public int getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	

}
