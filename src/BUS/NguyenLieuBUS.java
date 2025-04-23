package BUS;

import DAO.NguyenLieuDao;
import DAO.NhanVienDao;
import DTO.NguyenLieuDTO;
import java.util.ArrayList;
import java.util.List;

public class NguyenLieuBUS {
    private NguyenLieuDao dao = new NguyenLieuDao();

    public boolean them(NguyenLieuDTO nl) {
        return dao.them(nl);
    }

    public boolean sua(NguyenLieuDTO nl) {
        return dao.sua(nl);
    }

    public boolean xoaMem(int maNguyenLieu) {
        return dao.xoaMem(maNguyenLieu);
    }

    public ArrayList<NguyenLieuDTO> getAll() {
        return dao.layDanhSach();
    }

    public boolean checkdup(String tenNguyenLieu) {
        for (NguyenLieuDTO item : dao.layDanhSach()) {
            if (item.getTenNguyenLieu().equalsIgnoreCase(tenNguyenLieu)) {
                return false;
            }
        }
        return true;
    }
    
    public int generateNewId() {
    List<NguyenLieuDTO> list = getAll();
    int max = 0;
    for (NguyenLieuDTO nl : list) {
        if (nl.getMaNguyenLieu() > max) {
            max = nl.getMaNguyenLieu();
        }
    }
    return max + 1;
}
    
    public List<NguyenLieuDTO> getNguyenLieuBySearch(String keyword, String type) {
        // Lấy danh sách tất cả nhân viên từ DAO
        List<NguyenLieuDTO> nguyenLieuList = new NguyenLieuBUS().getAll();
        // Tạo danh sách kết quả để lưu nhân viên phù hợp
        List<NguyenLieuDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tiêu chí
        for (NguyenLieuDTO nl : nguyenLieuList) {
            switch (type.toLowerCase()) {
                case "tất cả":
                    if (nl.getTenNguyenLieu().toLowerCase().contains(keyword.toLowerCase()) ||
                        String.valueOf(nl.getMaNguyenLieu()).toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(nl); // Thêm nhân viên phù hợp vào danh sách kết quả
                    }
                    break;
                case "theo tên":
                    if (nl.getTenNguyenLieu().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(nl);
                    }
                    break;
                case "theo mã":
                    if (String.valueOf(nl.getMaNguyenLieu()).toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(nl);
                    }
                    break;
            }
        }

        // Trả về danh sách nhân viên với tiêu chí tìm kiếm
        return filteredList;
    }
}
