/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.pages;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import GUI.Panel.TopNav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nguyen
 */
public class SanPhamGUI extends JPanel{
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    SanPhamBUS sanPhamBUS;

    public SanPhamGUI(TopNav topNav) {
        sanPhamBUS = new SanPhamBUS();
        initComponent(topNav);
//        chucNang();
//        addSearchFunctionality();
        loadData(sanPhamBUS.getAll());

    }

    private void initComponent(TopNav topNav) {
        this.topNav = topNav;
        String[] itemFindFor = { "Tất Cả","Theo tên"};
        topNav.setItemComboBox(itemFindFor);

        // Panel dưới
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));

        // Tạo JTable
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //tbl.setRowHeight(35); 
        tbl.setRowHeight(50);
        tbl.setFocusable(false);
        tbl.setAutoCreateRowSorter(true);   
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

        
        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
    }
    
    private void loadData(List<SanPhamDTO> sanPhamList) {
    // Tên cột hiển thị (chưa bao gồm cột ẩn)
    String[] columnNames = { "Mã SP", "Tên SP", "Image", "Path", "Giá Bán"};

    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
        @Override
        public Class<?> getColumnClass(int column) {
            return (column == 2) ? ImageIcon.class : Object.class; // Cột hình dùng ImageIcon
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Không cho phép chỉnh sửa các ô trong bảng
        }
    };

    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    for (SanPhamDTO sp : sanPhamList) {
        ImageIcon icon = new ImageIcon(sp.getImg());
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        Object[] rowData = {
            sp.getMaSP(),
            sp.getTenSP(),
            resizedIcon,
            sp.getImg(), // Đường dẫn ảnh (ẩn)
            formatter.format(sp.getGiaBan()),
        };
        model.addRow(rowData);
    }

    tbl.setModel(model);
    tbl.setRowHeight(80);

    // Ẩn cột chứa đường dẫn (cột 3)
    tbl.getColumnModel().getColumn(3).setMinWidth(0);
    tbl.getColumnModel().getColumn(3).setMaxWidth(0);
    tbl.getColumnModel().getColumn(3).setWidth(0);

    // Căn giữa nội dung cho các cột (trừ cột ảnh - index 2)
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < tbl.getColumnCount(); i++) {
        if (i != 2) { // Không căn giữa cột ảnh
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}


}