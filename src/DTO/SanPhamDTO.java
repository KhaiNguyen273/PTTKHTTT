/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nguyen
 */
public class SanPhamDTO {
    private int maSP;
    private String tenSP;
    private String img;
    private int giaBan;
    private int trangThai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSP, String tenSP, String img, int giaBan, int trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.img = img;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public int getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getImg() {
        return img;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }


    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    

    
    
}
