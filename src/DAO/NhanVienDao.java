package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.NhanVienDTO;
import util.JdbcUtil;

public class NhanVienDao {

    public boolean addNhanVien(NhanVienDTO nhanVien) {
        String query = "INSERT INTO nhanvien (maNV, hoTen, ngaySinh, gioiTinh, sDT, trangthai) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nhanVien.getMaNV());
            stmt.setString(2, nhanVien.getHoTen());
            stmt.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            stmt.setInt(4, nhanVien.getGioiTinh());
            stmt.setString(5, nhanVien.getSDT());
            stmt.setInt(6, 1); // Mặc định trạng thái là 1 (còn hiệu lực)

            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Thêm thất bại
    }

    public boolean updateNhanVien(NhanVienDTO nhanVien) {
        String query = "UPDATE nhanvien SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, sDT = ? WHERE maNV = ? AND trangthai = 1";
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nhanVien.getHoTen());
            stmt.setDate(2, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            stmt.setInt(3, nhanVien.getGioiTinh());
            stmt.setString(4, nhanVien.getSDT());
            stmt.setInt(5, nhanVien.getMaNV());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMemNhanVien(int maNV) {
        // Xóa mềm bằng cách cập nhật "trangthai" thành 0
        String query = "UPDATE nhanvien SET trangthai = 0 WHERE maNV = ?";
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maNV);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public NhanVienDTO getNhanVienById(int maNV) {
        String query = "SELECT * FROM nhanvien WHERE maNV = ? AND trangthai = 1";
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, maNV);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hoTen = rs.getString("hoTen");
                    Date ngaySinh = rs.getDate("ngaySinh");
                    int gioiTinh = rs.getInt("gioiTinh");
                    String sDT = rs.getString("sDT");
                    int trangThai = rs.getInt("trangthai");
    
                    return new NhanVienDTO(maNV, hoTen, ngaySinh, gioiTinh, sDT, trangThai);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public List<NhanVienDTO> getAllNhanVien() {
        List<NhanVienDTO> nhanVienList = new ArrayList<>();
        String query = "SELECT * FROM nhanvien WHERE trangthai = 1"; // Chỉ lấy nhân viên còn hiệu lực
        try (Connection conn = JdbcUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                int maNV = rs.getInt("maNV");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                int gioiTinh = rs.getInt("gioiTinh");
                String sDT = rs.getString("sDT");
                int trangThai = rs.getInt("trangthai");
    
                nhanVienList.add(new NhanVienDTO(maNV, hoTen, ngaySinh, gioiTinh, sDT, trangThai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVienList;
    }
    

    public List<NhanVienDTO> getNhanVienChuaCoTaiKhoan() {
        List<NhanVienDTO> nhanVienList = new ArrayList<>();
        String query = "SELECT * " +
                       "FROM nhanvien nv " +
                       "LEFT JOIN taikhoan tk ON nv.maNV = tk.maNV " +
                       "WHERE tk.maNV IS NULL AND nv.trangthai = 1"; // Chỉ lấy nhân viên còn hiệu lực
    
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                int maNV = rs.getInt("maNV");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                int gioiTinh = rs.getInt("gioiTinh");
                String sDT = rs.getString("sDT");
                int trangThai = rs.getInt("trangthai");
    
                nhanVienList.add(new NhanVienDTO(maNV, hoTen, ngaySinh, gioiTinh, sDT, trangThai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVienList;
    }    

}
