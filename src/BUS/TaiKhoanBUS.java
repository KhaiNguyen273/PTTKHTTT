/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.TaiKhoanDao;
import DTO.TaiKhoanDTO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class TaiKhoanBUS {
    private TaiKhoanDao taiKhoanDao;

    // Constructor
    public TaiKhoanBUS() {
        // Khởi tạo DAO (giả sử đã có kết nối CSDL trong TaiKhoanDao)
        taiKhoanDao = new TaiKhoanDao();
    }

    // 1. Lấy danh sách tất cả tài khoản
    public List<TaiKhoanDTO> getAllTaiKhoan() {
        return taiKhoanDao.getAllTaiKhoan();
    }

    // 2. Thêm một tài khoản mới
    public boolean addTaiKhoan(TaiKhoanDTO taiKhoan) {
        return taiKhoanDao.addTaiKhoan(taiKhoan);
    }

    // 3. Cập nhật thông tin tài khoản
    public boolean updateTaiKhoan(TaiKhoanDTO taiKhoan) {
        // Kiểm tra dữ liệu hợp lệ trước khi cập nhật
        if (taiKhoan.getMaNV() <= 0) {
            throw new IllegalArgumentException("Mã nhân viên không hợp lệ");
        }
        return taiKhoanDao.updateTaiKhoan(taiKhoan);
    }

    // 4. Xóa một tài khoản
    public boolean deleteTaiKhoan(int maNV) {
        // Thực hiện các logic trước khi xóa (ví dụ: kiểm tra quyền hạn, ...)
        return taiKhoanDao.xoaMemTaiKhoan(maNV);
    }

    // 5. Kiểm tra thông tin đăng nhập
    public TaiKhoanDTO login(String tenDangNhap, String matKhau) {
        TaiKhoanDTO taiKhoan = taiKhoanDao.login(tenDangNhap, matKhau);
        return taiKhoan;
    }

    public TaiKhoanDTO getTaiKhoanByTenDangNhap(String tenDangNhap){
        return taiKhoanDao.getTaiKhoanByTenDangNhap(tenDangNhap);
    }

    // Lấy danh sách tài khoản theo tên đăng nhập tìm kiếm
    public List<TaiKhoanDTO> getTaiKhoanByNameSearch(String keyword) {
        // Lấy danh sách tất cả tài khoản từ DAO
        List<TaiKhoanDTO> taiKhoanList = taiKhoanDao.getAllTaiKhoan();

        // Tạo danh sách kết quả để lưu tài khoản phù hợp
        List<TaiKhoanDTO> filteredList = new ArrayList<>();

        // Tìm kiếm theo tên đăng nhập
        for (TaiKhoanDTO taiKhoan : taiKhoanList) {
            if (taiKhoan.getTenDangNhap().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(taiKhoan);
            }
        }

        // Trả về danh sách tài khoản khớp với tiêu chí tìm kiếm
        return filteredList;
    }

}