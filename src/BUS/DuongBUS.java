package BUS;

import DAO.DuongDao;
import DTO.DuongDTO;
import java.util.ArrayList;

import java.util.List;

public class DuongBUS {
    private DuongDao dao = new DuongDao();

    public boolean them(DuongDTO d) {
        return dao.them(d);
    }

    public boolean sua(DuongDTO d) {
        return dao.sua(d);
    }

    public boolean xoaMem(int maDuong) {
        return dao.xoaMem(maDuong);
    }

    public ArrayList<DuongDTO> getAll() {
        return dao.layDanhSach();
    }

    public boolean checkdup(int dg) {
        for (DuongDTO item : dao.layDanhSach()) {
            if (item.getphantramDuong()== dg) {
                return false;
            }
        }
        return true;
    }
}
