package quanly.model;

public class DichVu {
	private String tenDichVu;
	private int giaTien;

	public DichVu(int giaTien, String tenDichVu) {
		super();
		this.tenDichVu = tenDichVu;
		this.giaTien = giaTien;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public int getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}
	
}
