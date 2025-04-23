package DTO;

import java.util.Date;

public class NhanVienDTO {
    private int maNV;         // Mã nhân viên
    private String hoTen;     // Họ và tên
    private Date ngaySinh;    // Ngày sinh
    private int gioiTinh;     // Giới tính (0: nữ, 1: nam)
    private String sDT;       // Số điện thoại
    private int trangThai;    // Trạng thái (0: đã xóa mềm, 1: còn hiệu lực)

    // Constructor không tham số
    public NhanVienDTO() {}

    // Constructor có tham số
    public NhanVienDTO(int maNV, String hoTen, Date ngaySinh, int gioiTinh, String sDT, int trangThai) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.trangThai = trangThai;
    }

    // Getter và Setter cho từng thuộc tính
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức toString()
    @Override
    public String toString() {
        return "NhanVienDTO{" +
                "maNV=" + maNV +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", sDT='" + sDT + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
