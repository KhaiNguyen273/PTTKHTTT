package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.NhanVienDao;
import DTO.NhanVienDTO;

public class NhanVienBUS {
    private NhanVienDao NhanVienDao;

    public NhanVienBUS() {
        NhanVienDao = new NhanVienDao();
    }

    // Thêm nhân viên mới
    public boolean addNhanVien(NhanVienDTO NhanVien) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return NhanVienDao.addNhanVien(NhanVien);
    }

    // Cập nhật thông tin nhân viên
    public boolean updateNhanVien(NhanVienDTO NhanVien) {
        // Kiểm tra logic kinh doanh ở đây nếu cần
        return NhanVienDao.updateNhanVien(NhanVien);
    }

    // Xóa mềm nhân viên
    public boolean deleteNhanVien(int manv) {
        // Sử dụng phương thức xóa mềm từ DAO
        return NhanVienDao.xoaMemNhanVien(manv);
    }

    // Lấy thông tin nhân viên theo mã
    public NhanVienDTO getNhanVienById(int manv) {
        return NhanVienDao.getNhanVienById(manv);
    }

    // Lấy danh sách tất cả nhân viên
    public List<NhanVienDTO> getAllNhanVien() {
        return NhanVienDao.getAllNhanVien();
    }

    // Lấy danh sách nhân viên chưa có tài khoản
    public List<NhanVienDTO> getNhanVienChuaCoTaiKhoan() {
        return NhanVienDao.getNhanVienChuaCoTaiKhoan();
    }

    // Lấy danh sách nhân viên theo tiêu chí tìm kiếm
    public List<NhanVienDTO> getNhanVienBySearch(String keyword, String type) {
        // Lấy danh sách tất cả nhân viên từ DAO
        List<NhanVienDTO> nhanVienList = NhanVienDao.getAllNhanVien();

        // Tạo danh sách kết quả để lưu nhân viên phù hợp
        List<NhanVienDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tiêu chí
        for (NhanVienDTO nv : nhanVienList) {
            switch (type.toLowerCase()) {
                case "tất cả":
                    if (nv.getHoTen().toLowerCase().contains(keyword.toLowerCase()) ||
                            nv.getSDT().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(nv); // Thêm nhân viên phù hợp vào danh sách kết quả
                    }
                    break;
                case "theo tên":
                    if (nv.getHoTen().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(nv);
                    }
                    break;
                case "theo sdt":
                    if (nv.getSDT().toLowerCase().contains(keyword.toLowerCase())) {
                        filteredList.add(nv);
                    }
                    break;
            }
        }

        // Trả về danh sách nhân viên với tiêu chí tìm kiếm
        return filteredList;
    }

    // Tạo mã ID mới tự động
    public int generateNewId() {
        // Đảm bảo danh sách không rỗng trước khi truy cập phần tử cuối
        List<NhanVienDTO> nhanVienList = NhanVienDao.getAllNhanVien();
        if (!nhanVienList.isEmpty()) {
            return nhanVienList.get(nhanVienList.size() - 1).getMaNV() + 1;
        } else {
            return 1; // Nếu danh sách rỗng, trả về ID bắt đầu là 1
        }
    }
}
