package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.DiaLog.NhanVienDialog;
import GUI.Panel.TopNav;
import util.ExportExcelUtility;

import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class NhanVienGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    NhanVienBUS nhanVienBUS;

    public NhanVienGUI(TopNav topNav) {
        nhanVienBUS = new NhanVienBUS();
        initComponent(topNav);
        chucNang();
        addSearchFunctionality();
        loadData(nhanVienBUS.getAllNhanVien());
    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả", "Theo tên", "Theo SDT" };
        topNav.setItemComboBox(itemFindFor);

        // Panel dưới
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        // Tạo JTable
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);

        // Tô màu header bảng
        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237)); // Màu nền header (Cornflower Blue)
        header.setForeground(Color.WHITE); // Màu chữ header
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font chữ header

        // Thêm JScrollPane chứa bảng
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlBot.setLayout(new BorderLayout());
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlBot.add(scrollPane, BorderLayout.CENTER);

        // Thiết lập layout cho NhanVien
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhanVienGUI.this);
                NhanVienDialog dialog = new NhanVienDialog(parentFrame, 0, null, null, 0, null, "Thêm Nhân Viên");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        // Assign a new unique ID
                        int maNV = nhanVienBUS.generateNewId();
                        NhanVienDTO newNhanVien = dialog.getDataNhanVienDTO(maNV);
                        if (nhanVienBUS.addNhanVien(newNhanVien)) {
                            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
                            loadData(nhanVienBUS.getAllNhanVien());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên: " + ex.getMessage());
                    }
                }
            }
        });

        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nhân viên để sửa!");
                    return;
                }

                // Lấy dữ liệu nhân viên từ bảng
                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                String hoTen = (String) tbl.getValueAt(selectedRow, 1);
                String ngaySinhStr = (String) tbl.getValueAt(selectedRow, 2);
                LocalDate ngaySinh = LocalDate.parse(ngaySinhStr);
                int gioiTinh = "Nam".equals(tbl.getValueAt(selectedRow, 3)) ? 0 : 1;
                String sDT = (String) tbl.getValueAt(selectedRow, 4);

                // Hiển thị JDialog NhanVienDialog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhanVienGUI.this);
                NhanVienDialog dialog = new NhanVienDialog(parentFrame, maNV, hoTen, ngaySinh, gioiTinh, sDT,
                        "Chỉnh Sửa Nhân Viên");
                dialog.setVisible(true);

                // Sau khi dialog đóng, cập nhật dữ liệu
                if (dialog.isSaved()) {
                    NhanVienDTO updatedNhanVien = dialog.getDataNhanVienDTO(maNV);
                    if (nhanVienBUS.updateNhanVien(updatedNhanVien)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");
                        loadData(nhanVienBUS.getAllNhanVien()); // Tải lại bảng
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!");
                    }
                }
            }
        });

        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nhân viên để xóa!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhân viên này?",
                        "Xóa Nhân Viên", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (nhanVienBUS.deleteNhanVien(maNV)) {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
                        loadData(nhanVienBUS.getAllNhanVien());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!");
                    }
                }
            }
        });

        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nhân viên để xem chi tiết!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                String hoTen = (String) tbl.getValueAt(selectedRow, 1);
                String ngaySinhStr = (String) tbl.getValueAt(selectedRow, 2);
                LocalDate ngaySinh = LocalDate.parse(ngaySinhStr);
                int gioiTinh = "Nam".equals(tbl.getValueAt(selectedRow, 3)) ? 0 : 1;
                String sDT = (String) tbl.getValueAt(selectedRow, 4);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NhanVienGUI.this);
                NhanVienDialog dialog = new NhanVienDialog(parentFrame, maNV, hoTen, ngaySinh, gioiTinh, sDT,
                        "Xem chi tiết");
                dialog.setVisible(true);
            }
        });
    }

    private void addSearchFunctionality() {
        JTextField textSearch = topNav.getTextSearch();
        JButton btnRefresh = topNav.getBtnRefresh();

        textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = topNav.getFindFor().getSelectedItem().toString().toLowerCase();
                String keyword = textSearch.getText().trim();

                loadData(nhanVienBUS.getNhanVienBySearch(keyword, type));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Xóa từ khóa tìm kiếm
                loadData(nhanVienBUS.getAllNhanVien()); // Tải lại toàn bộ dữ liệu
            }
        });
    }
    
    private void loadData(List<NhanVienDTO> nhanVienList) {
        // Lấy danh sách tất cả nhân viên từ BLL
        String[] columnNames = { "Mã NV", "Họ Tên", "Ngày Sinh", "Giới Tính", "SĐT" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Thêm dữ liệu từ danh sách nhân viên vào bảng
        for (NhanVienDTO nv : nhanVienList) {
            Object[] rowData = {
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getNgaySinh().toString(),
                    nv.getGioiTinh() == 0 ? "Nam" : "Nữ",
                    nv.getSDT()
            };
            model.addRow(rowData);
        }

        // Gán model mới vào bảng
        tbl.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

}
