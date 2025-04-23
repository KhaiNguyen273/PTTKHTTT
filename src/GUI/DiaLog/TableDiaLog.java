package GUI.DiaLog;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableDiaLog extends JDialog {
    private JTable table;
    private JLabel ghiChu;
    private JButton btnSelect;
    private String selectedValue; // Lưu giá trị được chọn

    public TableDiaLog(Frame owner, String title, JTable tbl) {
        super(owner, title, true); // Tạo JDialog với modal
        this.setLayout(new BorderLayout(10, 10)); // Sử dụng BorderLayout
        this.setSize(600, 500);

        // Gán JTable được truyền vào
        table = tbl;

        // Tô màu và tùy chỉnh tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40)); // Chiều cao tiêu đề
        header.setBackground(new Color(100, 149, 237)); // Màu nền tiêu đề
        header.setForeground(Color.WHITE); // Màu chữ tiêu đề
        header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Font tiêu đề

        // Thêm bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER); // Thêm JScrollPane vào giữa dialog

        // Ghi chú
        ghiChu = new JLabel("Hãy chọn một hàng trong bảng.");
        ghiChu.setPreferredSize(new Dimension(0, 30));
        ghiChu.setFont(ghiChu.getFont().deriveFont(14f));
        ghiChu.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa văn bản
        this.add(ghiChu, BorderLayout.NORTH);

        // Nút chọn
        btnSelect = new JButton("Chọn");
        btnSelect.setPreferredSize(new Dimension(90, 60));
        btnSelect.setFont(new Font(getName(), Font.PLAIN, 18));
        btnSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    selectedValue = table.getValueAt(selectedRow, 0).toString(); // Lấy dữ liệu từ cột đầu tiên
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một hàng trước khi xác nhận!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
                dispose(); // Đóng dialog
            }            
        });

        // Panel chứa nút
        JPanel pnlButton = new JPanel();
        pnlButton.add(btnSelect);
        this.add(pnlButton, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null); // Căn giữa dialog trên màn hình
    }

    // Getter để lấy giá trị được chọn
    public String getSelectedValue() {
        return selectedValue;
    }

    // Setter để thay đổi ghi chú
    public void setGhiChu(String text) {
        ghiChu.setText(text);
    }
}
