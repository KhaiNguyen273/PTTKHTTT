package BUS;

import DAO.SanPhamDao;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS {
    private SanPhamDao dao = new SanPhamDao();

    public boolean them(SanPhamDTO sp) {
        return dao.themSanPham(sp);
    }

    public boolean sua(SanPhamDTO sp) {
        return dao.suaSanPham(sp);
    }

    public boolean xoaMem(int maSP) {
        return dao.xoaMemSanPham(maSP);
    }

    public boolean xoaCung(int maSP) {
        return dao.xoaSanPham(maSP);
    }
    
    public ArrayList<SanPhamDTO> getAll() {
        return dao.layDanhSach();
    }
}
