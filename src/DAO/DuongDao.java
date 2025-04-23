package DAO;

import DTO.DuongDTO;
import util.JdbcUtil;

import java.sql.*;
import java.util.*;

public class DuongDao {
    public boolean them(DuongDTO d) {
        String sql = "INSERT INTO Duong (phantramDuong, trangThai) VALUES (?, 1)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, d.getphantramDuong());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sua(DuongDTO d) {
        String sql = "UPDATE Duong SET phantramDuong = ? WHERE maDuong = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, d.getphantramDuong());
            stmt.setInt(2, d.getMaDuong());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMem(int maDuong) {
        String sql = "UPDATE Duong SET trangThai = 0 WHERE maDuong = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maDuong);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<DuongDTO> layDanhSach() {
        ArrayList<DuongDTO> result = new ArrayList<DuongDTO>();
        String sql = "SELECT * FROM Duong WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DuongDTO dg = new DuongDTO(
                    rs.getInt("maDuong"),
                    rs.getInt("phantramDuong"),
                    rs.getInt("trangThai")
                );
                result.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
