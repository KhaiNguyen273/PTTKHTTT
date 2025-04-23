package DAO;

import DTO.NguyenLieuDTO;
import util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;

public class NguyenLieuDao {
    
    public boolean them(NguyenLieuDTO nl) {
        String sql = "INSERT INTO NguyenLieu (tenNguyenLieu, donGia, donvi, trangThai) VALUES (?, ?, ?, 1)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nl.getTenNguyenLieu());
            stmt.setInt(2, nl.getDonGia());
            stmt.setString(3, nl.getDonvi());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sua(NguyenLieuDTO nl) {
        String sql = "UPDATE NguyenLieu SET tenNguyenLieu = ?, donGia = ?, donvi = ? WHERE maNguyenLieu = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nl.getTenNguyenLieu());
            stmt.setInt(2, nl.getDonGia());
            stmt.setString(3, nl.getDonvi());
            stmt.setInt(4, nl.getMaNguyenLieu());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMem(int maNguyenLieu) {
        String sql = "UPDATE NguyenLieu SET trangThai = 0 WHERE maNguyenLieu = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maNguyenLieu);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<NguyenLieuDTO> layDanhSach() {
        ArrayList<NguyenLieuDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NguyenLieu WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NguyenLieuDTO nl = new NguyenLieuDTO(
                    rs.getInt("maNguyenLieu"),
                    rs.getString("tenNguyenLieu"),
                    rs.getInt("donGia"),
                    rs.getInt("soluongton"),
                    rs.getString("donvi"),
                    rs.getInt("trangThai")
                );
                list.add(nl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
