/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPhamDTO;
import util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author nguyen
 */
public class SanPhamDao {
     public boolean themSanPham(SanPhamDTO sp) {
        String sql = "INSERT INTO SanPham (tenSP,img, giaBan, trangThai) VALUES (?, ?, ?, 1)";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getTenSP());
            stmt.setString(2, sp.getImg());
            stmt.setInt(3, sp.getGiaBan());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaSanPham(SanPhamDTO sp) {
        String sql = "UPDATE SanPham SET tenSP = ?, img = ? ,giaBan = ? WHERE maSP = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getTenSP());
            stmt.setString(2, sp.getImg());
            stmt.setInt(3, sp.getGiaBan());
            stmt.setInt(4, sp.getMaSP());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaMemSanPham(int maSP) {
        String sql = "UPDATE SanPham SET trangThai = 0 WHERE maSP = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maSP);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaSanPham(int maSP) {
        String sql = "DELETE FROM SanPham WHERE maSP = ?";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maSP);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<SanPhamDTO> layDanhSach() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        String sql = "SELECT * FROM SanPham WHERE trangThai = 1";
        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO(
                    rs.getInt("maSP"),
                    rs.getString("tenSP"),
                    rs.getString("img"),
                    rs.getInt("giaBan"),
                    rs.getInt("trangThai")
                );
                result.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
