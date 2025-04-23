package DAO;

import DTO.TaiKhoanDTO;
import util.JdbcUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDao {

    // 1. Thêm tài khoản mới
    public boolean addTaiKhoan(TaiKhoanDTO taiKhoan) {
        String sql = "INSERT INTO taikhoan (MaNV, TenDangNhap, MatKhau, TrangThai) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taiKhoan.getMaNV());
            ps.setString(2, taiKhoan.getTenDangNhap());
            ps.setString(3, taiKhoan.getMatKhau());
            ps.setInt(4, taiKhoan.getTrangThai());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Lấy danh sách tài khoản
    public List<TaiKhoanDTO> getAllTaiKhoan() {
        List<TaiKhoanDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan WHERE TrangThai != 0"; // Lọc những tài khoản chưa bị xóa mềm
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TaiKhoanDTO taiKhoan = new TaiKhoanDTO(
                        rs.getInt("MaNV"),
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getInt("TrangThai")
                );
                list.add(taiKhoan);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Cập nhật tài khoản
    public boolean updateTaiKhoan(TaiKhoanDTO taiKhoan) {
        String sql = "UPDATE taikhoan SET TenDangNhap = ?, MatKhau = ?, TrangThai = ? WHERE MaNV = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, taiKhoan.getTenDangNhap());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setInt(4, taiKhoan.getTrangThai());
            ps.setInt(5, taiKhoan.getMaNV());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa mềm tài khoản
    public boolean xoaMemTaiKhoan(int maNV) {
        String sql = "UPDATE taikhoan SET TrangThai = 0 WHERE MaNV = ?"; // Đánh dấu trạng thái = 0
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maNV);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Kiểm tra thông tin đăng nhập
    public TaiKhoanDTO login(String tenDangNhap, String matKhau) {
        String sql = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND MatKhau = ? AND TrangThai != 0";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanDTO(
                            rs.getInt("MaNV"),
                            rs.getString("TenDangNhap"),
                            rs.getString("MatKhau"),
                            rs.getInt("TrangThai")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Đăng nhập thất bại
    }

    // 6. Lấy tài khoản theo tên đăng nhập
    public TaiKhoanDTO getTaiKhoanByTenDangNhap(String tenDangNhap) {
        TaiKhoanDTO taiKhoan = null;
        String query = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND TrangThai != 0";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenDangNhap);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maNV = rs.getInt("MaNV");
                    String matKhau = rs.getString("MatKhau");
                    int trangThai = rs.getInt("TrangThai");
                    taiKhoan = new TaiKhoanDTO(maNV, tenDangNhap, matKhau, trangThai);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }
}
