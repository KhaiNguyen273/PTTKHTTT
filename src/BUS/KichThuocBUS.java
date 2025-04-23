package BUS;

import DAO.KichThuocDao;
import DTO.KichThuocDTO;
import java.util.ArrayList;

import java.util.List;

public class KichThuocBUS {
    private KichThuocDao dao = new KichThuocDao();

    public boolean them(KichThuocDTO k) {
        return dao.them(k);
    }

    public boolean sua(KichThuocDTO k) {
        return dao.sua(k);
    }

    public boolean xoaMem(int maKichThuoc) {
        return dao.xoaMem(maKichThuoc);
    }

    public ArrayList<KichThuocDTO>getAll() {
        return dao.layDanhSach();
    }
    
    public boolean checkdup(String kt) {
    ArrayList<KichThuocDTO> listkt = dao.layDanhSach();
    for (KichThuocDTO item : listkt) {
        if (item.getTenKichThuoc().equalsIgnoreCase(kt)) {
            return false;
        }
    }
    return true;
    }

}
