package DTO;

public class TaiKhoanDTO {
    private int maNV;            // Mã nhân viên
    private String tenDangNhap;  // Tên đăng nhập
    private String matKhau;      // Mật khẩu
    private int trangThai;    // Trạng thái được thêm vào

    private static TaiKhoanDTO taiKhoanHienTai;
    
    // Constructor không tham số
    public TaiKhoanDTO() {}

    // Constructor có tham số
    public TaiKhoanDTO(int maNV, String tenDangNhap, String matKhau, int trangThai) {
        this.maNV = maNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
    }

    public static TaiKhoanDTO getTaiKhoanHienTai() {
        return taiKhoanHienTai;
    }

    public static void setTaiKhoanHienTai(TaiKhoanDTO taiKhoan) {
        taiKhoanHienTai = taiKhoan;
    }
        
    // Getter và Setter cho từng thuộc tính
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
