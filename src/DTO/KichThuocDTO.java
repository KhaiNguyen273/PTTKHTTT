package DTO;

public class KichThuocDTO {
    private int maKichThuoc;
    private String tenKichThuoc;
    private int trangThai;

    public KichThuocDTO() {}

    public KichThuocDTO(int maKichThuoc, String tenKichThuoc, int trangThai) {
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
        this.trangThai = trangThai;
    }

    public int getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(int maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
