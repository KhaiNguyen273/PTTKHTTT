package BUS;

import DAO.DaDao;
import DTO.DaDTO;
import DTO.KichThuocDTO;
import java.util.ArrayList;

import java.util.List;

public class DaBUS {
    private DaDao dao = new DaDao();

    public boolean them(DaDTO da) {
        return dao.them(da);
    }

    public boolean sua(DaDTO da) {
        return dao.sua(da);
    }

    public boolean xoaMem(int maDa) {
        return dao.xoaMem(maDa);
    }

    public ArrayList<DaDTO> getAll() {
        return dao.layDanhSach();
    }
    
 public boolean checkdup(int da) {
    for (DaDTO item : dao.layDanhSach()) {
        if (item.getPhantramDa()== da) {
            return false;
        }
    }
    return true;
}

}
