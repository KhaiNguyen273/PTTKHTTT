package DAO;

import DTO.KichThuocDTO;
import util.JdbcUtil;

import java.sql.*;
import java.util.*;

public class KichThuocDao {
    public boolean them(KichThuocDTO k) {
        String sql = "INSERT INTO KichThuoc (tenKichThuoc, trangThai) VALUES (?, 1)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, k.getTenKichThuoc());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sua(KichThuocDTO k) {
        String sql = "UPDATE KichThuoc SET tenKichThuoc = ? WHERE maKichThuoc = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, k.getTenKichThuoc());
            stmt.setInt(2, k.getMaKichThuoc());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMem(int maKichThuoc) {
        String sql = "UPDATE KichThuoc SET trangThai = 0 WHERE maKichThuoc = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maKichThuoc);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public ArrayList<KichThuocDTO> layDanhSach() {
        ArrayList<KichThuocDTO> result = new ArrayList<KichThuocDTO>();
        String sql = "SELECT * FROM KichThuoc WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KichThuocDTO kt = new KichThuocDTO(
                    rs.getInt("maKichThuoc"),
                    rs.getString("tenKichThuoc"),
                    rs.getInt("trangThai")
                );
                result.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
