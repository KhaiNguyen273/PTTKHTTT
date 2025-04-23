/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nguyen
 */
public class NguyenLieuDTO {
    private int maNguyenLieu;
    private String tenNguyenLieu;
    private int donGia;
    private int soLuongTon;
    private String donvi;
    private int trangThai;

    public NguyenLieuDTO() {
    }

    public NguyenLieuDTO(int maNguyenLieu, String tenNguyenLieu, int donGia, int soLuongTon, String donvi, int trangThai) {
        this.maNguyenLieu = maNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu;
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
        this.donvi = donvi;
        this.trangThai = trangThai;
    }
    
    public NguyenLieuDTO( String tenNguyenLieu, int donGia, int soLuongTon, String donvi, int trangThai) {
        this.tenNguyenLieu = tenNguyenLieu;
        this.donGia = donGia;
        this.soLuongTon = soLuongTon;
        this.donvi = donvi;
        this.trangThai = trangThai;
    }

    public int getMaNguyenLieu() {
        return maNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public int getDonGia() {
        return donGia;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public String getDonvi() {
        return donvi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaNguyenLieu(int maNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
