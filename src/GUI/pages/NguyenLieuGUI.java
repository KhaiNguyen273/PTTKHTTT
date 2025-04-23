package GUI.pages;

import BUS.NguyenLieuBUS;
import DTO.NguyenLieuDTO;
import GUI.DiaLog.NguyenLieuDialog;
import GUI.Panel.TopNav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class NguyenLieuGUI extends JPanel {
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    NguyenLieuBUS nguyenLieuBUS;

    public NguyenLieuGUI(TopNav topNav) {
        nguyenLieuBUS = new NguyenLieuBUS();
        initComponent(topNav);
        chucNang();
        addSearchFunctionality();
        loadData(nguyenLieuBUS.getAll());
    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả", "Theo tên", "Theo mã" };
        topNav.setItemComboBox(itemFindFor);

        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);

        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        pnlBot.setLayout(new BorderLayout());
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlBot.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }

    private void chucNang() {
        JButton[] btn = topNav.getBtn();

        // Thêm
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NguyenLieuGUI.this);
                NguyenLieuDialog dialog = new NguyenLieuDialog(parentFrame, 0, null, 0, null, "Thêm Nguyên Liệu");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    NguyenLieuDTO newNL = dialog.getDataNguyenLieuThemDTO();
                    if (nguyenLieuBUS.them(newNL)) {
                        JOptionPane.showMessageDialog(null, "Thêm nguyên liệu thành công!");
                        loadData(nguyenLieuBUS.getAll());
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm nguyên liệu thất bại!");
                    }
                }
            }
        });

        // Sửa
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nguyên liệu để sửa!");
                    return;
                }

                int ma = (int) tbl.getValueAt(selectedRow, 0);
                String ten = (String) tbl.getValueAt(selectedRow, 1);
                int donGia = Integer.parseInt(tbl.getValueAt(selectedRow, 4).toString());
                String donVi = (String) tbl.getValueAt(selectedRow, 2);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NguyenLieuGUI.this);
                NguyenLieuDialog dialog = new NguyenLieuDialog(parentFrame, ma, ten, donGia, donVi, "Chỉnh Sửa Nguyên Liệu");
                dialog.setVisible(true);

                if (dialog.isSaved()) {
                    NguyenLieuDTO updated = dialog.getDataNguyenLieuSuaDTO(ma);
                    if (nguyenLieuBUS.sua(updated)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật nguyên liệu thành công!");
                        loadData(nguyenLieuBUS.getAll());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật nguyên liệu thất bại!");
                    }
                }
            }
        });

        // Xóa mềm
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nguyên liệu để xóa!");
                    return;
                }

                int ma = (int) tbl.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nguyên liệu này?",
                        "Xóa Nguyên Liệu", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (nguyenLieuBUS.xoaMem(ma)) {
                        JOptionPane.showMessageDialog(null, "Xóa nguyên liệu thành công!");
                        loadData(nguyenLieuBUS.getAll());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa nguyên liệu thất bại!");
                    }
                }
            }
        });

        // Xem chi tiết
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một nguyên liệu để xem chi tiết!");
                    return;
                }

                int ma = (int) tbl.getValueAt(selectedRow, 0);
                String ten = (String) tbl.getValueAt(selectedRow, 1);
                int donGia = Integer.parseInt(tbl.getValueAt(selectedRow, 4).toString());
                String donVi = (String) tbl.getValueAt(selectedRow, 2);

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(NguyenLieuGUI.this);
                NguyenLieuDialog dialog = new NguyenLieuDialog(parentFrame, ma, ten, donGia, donVi, "Xem chi tiết");
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

                loadData(nguyenLieuBUS.getNguyenLieuBySearch(keyword, type));
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topNav.getFindFor().setSelectedIndex(0);
                textSearch.setText("");
                loadData(nguyenLieuBUS.getAll());
            }
        });
    }

    private void loadData(List<NguyenLieuDTO> list) {
        String[] columnNames = { "Mã NL", "Tên Nguyên Liệu", "Đơn Vị", "Số Lượng Tồn", "Đơn Giá/1 Đơn Vị" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (NguyenLieuDTO nl : list) {
            Object[] row = {
                    nl.getMaNguyenLieu(),
                    nl.getTenNguyenLieu(),
                    nl.getDonvi(),
                    nl.getSoLuongTon(),
                    nl.getDonGia()
            };
            model.addRow(row);
        }

        tbl.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
