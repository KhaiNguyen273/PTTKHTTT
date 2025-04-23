package DTO;

public class DaDTO {
    private int maDa;
    private int phantramDa;
    private int trangThai;

    public DaDTO() {
    }

    public DaDTO(int maDa, int phantramDa, int trangThai) {
        this.maDa = maDa;
        this.phantramDa = phantramDa;
        this.trangThai = trangThai;
    }

    public int getMaDa() {
        return maDa;
    }

    public int getPhantramDa() {
        return phantramDa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaDa(int maDa) {
        this.maDa = maDa;
    }

    public void setPhantramDa(int phantramDa) {
        this.phantramDa = phantramDa;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}
   
