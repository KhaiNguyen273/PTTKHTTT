package DTO;

public class DuongDTO {
    private int maDuong;
    private int phantramDuong;
    private int trangThai;

    public DuongDTO() {}

    public DuongDTO(int maDuong, int phantramDuong, int trangThai) {
        this.maDuong = maDuong;
        this.phantramDuong = phantramDuong;
        this.trangThai = trangThai;
    }

    public int getMaDuong() {
        return maDuong;
    }

    public void setMaDuong(int maDuong) {
        this.maDuong = maDuong;
    }

    public int getphantramDuong() {
        return phantramDuong;
    }

    public void setphantramDuong(int phantramDuong) {
        this.phantramDuong = phantramDuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
