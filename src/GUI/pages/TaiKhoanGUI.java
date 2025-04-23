package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.DiaLog.TaiKhoanDialog;
import GUI.Panel.TopNav;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableCellRenderer;
import util.ExportExcelUtility;

public class TaiKhoanGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    TaiKhoanBUS taiKhoanBUS;

    public TaiKhoanGUI(TopNav topNav) {
        taiKhoanBUS = new TaiKhoanBUS();
        initComponent(topNav);
        loadData(taiKhoanBUS.getAllTaiKhoan());
        chucNang(); // Add functionality to the buttons
    }

    private void initComponent(TopNav topNav) {
        this.topNav= topNav;
        String[] itemFindFor = { "Tất Cả", "Mã Nhân Viên", "Tên Nhân Viên" };
        topNav.setItemComboBox(itemFindFor);

        // Bottom Panel
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        // Create JTable
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Style the table header
        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Add JScrollPane containing the table
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlBot.add(scrollPane, BorderLayout.CENTER);

        // Set layout for TaiKhoanGUI
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void loadData(List<TaiKhoanDTO> taiKhoanList) {
        // Fetch all account data from BLL
        String[] columnNames = { "Mã Nhân Viên", "Tên Đăng Nhập", "Mật Khẩu"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Add data to the table model
        for (TaiKhoanDTO tk : taiKhoanList) {
            Object[] rowData = {
                    tk.getMaNV(),
                    tk.getTenDangNhap(),
                    tk.getMatKhau()
            };
            model.addRow(rowData);
        }

        // Set the new model to the table
        tbl.setModel(model);
        tbl.getColumnModel().removeColumn(tbl.getColumnModel().getColumn(2)); // Ẩn cột thứ 3 (Mật Khẩu)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Add account
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaiKhoanGUI.this);
                TaiKhoanDialog dialog = new TaiKhoanDialog(parentFrame, null, "Thêm Tài Khoản");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    try {
                        TaiKhoanDTO newTaiKhoan = dialog.getTaiKhoanData();
                        if (taiKhoanBUS.addTaiKhoan(newTaiKhoan)) {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
                            loadData(taiKhoanBUS.getAllTaiKhoan());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi thêm tài khoản: " + ex.getMessage());
                    }
                }
            }
        });

        // Edit account
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để sửa!");
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) tbl.getModel();

                int maNV = (int) model.getValueAt(selectedRow, 0); // Lấy Mã Nhân Viên
                String tenDangNhap = (String) model.getValueAt(selectedRow, 1); // Lấy Tên Đăng Nhập
                String matKhau = (String) model.getValueAt(selectedRow, 2); // Lấy Mật Khẩu (dù đã ẩn cột)

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaiKhoanGUI.this);
                TaiKhoanDialog dialog = new TaiKhoanDialog(parentFrame,
                        new TaiKhoanDTO(maNV, tenDangNhap, matKhau,1), "Chỉnh Sửa Tài Khoản");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    TaiKhoanDTO updatedTaiKhoan = dialog.getTaiKhoanData();
                    if (taiKhoanBUS.updateTaiKhoan(updatedTaiKhoan)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công!");
                        loadData(taiKhoanBUS.getAllTaiKhoan());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                    }
                }
            }
        });

        // Delete account
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để xóa!");
                    return;
                }

                int maNV = (int) tbl.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này?",
                        "Xóa Tài Khoản", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (taiKhoanBUS.deleteTaiKhoan(maNV)) {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
                        loadData(taiKhoanBUS.getAllTaiKhoan());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!");
                    }
                }
            }
        });

        // View details of account
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một tài khoản để xem chi tiết!");
                    return;
                }

                DefaultTableModel model = (DefaultTableModel) tbl.getModel();

                int maNV = (int) model.getValueAt(selectedRow, 0);
                String tenDangNhap = (String) model.getValueAt(selectedRow, 1);
                String matKhau = (String) model.getValueAt(selectedRow, 2);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(TaiKhoanGUI.this);
                TaiKhoanDialog dialog = new TaiKhoanDialog(parentFrame,
                        new TaiKhoanDTO(maNV, tenDangNhap, matKhau,1), "Xem chi tiết");
                dialog.setVisible(true);
            }
        });

        
        JTextField textSearch = topNav.getTextSearch();
        JButton btnRefresh = topNav.getBtnRefresh();

         textSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){
            String keyword = textSearch.getText().trim();
            loadData(taiKhoanBUS.getTaiKhoanByNameSearch(keyword));
            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText(""); // Clear the search keyword
                loadData(taiKhoanBUS.getAllTaiKhoan()); // Reload all data
            }
        });
    }
}
