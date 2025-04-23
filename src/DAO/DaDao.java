package DAO;

import DTO.DaDTO;
import util.JdbcUtil;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaDao {
    public boolean them(DaDTO da) {
        String sql = "INSERT INTO Da (phantramDa, trangThai) VALUES (?, 1)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, da.getPhantramDa());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sua(DaDTO da) {
        String sql = "UPDATE Da SET phantramDa = ? WHERE maDa = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, da.getPhantramDa());
            stmt.setInt(2, da.getMaDa());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMem(int maDa) {
        String sql = "UPDATE Da SET trangThai = 0 WHERE maDa = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maDa);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<DaDTO> layDanhSach() {
        ArrayList<DaDTO> result = new ArrayList<DaDTO>();
        String sql = "SELECT * FROM Da WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DaDTO da = new DaDTO(
                    rs.getInt("maDa"),
                    rs.getInt("phantramDa"),
                    rs.getInt("trangThai")
                );
                result.add(da);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
}
