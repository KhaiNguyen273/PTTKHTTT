package GUI.DiaLog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DTO.NhanVienDTO;
import GUI.Panel.InputType.InputDate;
import GUI.Panel.InputType.InputText;

public class NhanVienDialog extends JDialog {
    private InputText tfMaNV;
    private InputText tfHoTen;
    private InputDate inputNgaySinh; // Sử dụng InputDate
    private JComboBox<String> cbGioiTinh;
    private InputText tfSdt;
    private boolean isSaved = false;

    public NhanVienDialog(JFrame owner, int maNV, String hoTen, LocalDate ngaySinh, int gioiTinh, String sDT, String title) {
        super(owner, title, true);
        initComponents(maNV, hoTen, ngaySinh, gioiTinh, sDT, title);
        setLocationRelativeTo(owner); // Căn giữa với cửa sổ cha
    }

    private void initComponents(int maNV, String hoTen, LocalDate ngaySinh, int gioiTinh, String sDT, String title) {
        setSize(500, 600); // Kích thước hợp lý
        setLayout(new BorderLayout(10, 10)); // Khoảng cách giữa các phần

        JLabel lblContent = new JLabel(title);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));

        // Tạo các trường InputText và InputDate
        tfMaNV = new InputText("Mã Nhân Viên");
        tfHoTen = new InputText("Họ Tên");
        inputNgaySinh = new InputDate("Ngày Sinh"); // Dùng InputDate
        tfSdt = new InputText("Số Điện Thoại");

        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        JPanel pnlGioiTinh = new JPanel();
        pnlGioiTinh.setLayout(new BoxLayout(pnlGioiTinh, BoxLayout.Y_AXIS));
        pnlGioiTinh.setBorder(new EmptyBorder(10,10,10,10));

        JLabel lblGioiTinh = new JLabel("Giới Tính");
        lblGioiTinh.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblGioiTinh.setFont(lblGioiTinh.getFont().deriveFont(14f));
        pnlGioiTinh.add(lblGioiTinh);

        cbGioiTinh.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cbGioiTinh.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái trường nhập
        pnlGioiTinh.add(cbGioiTinh);

        // Gán dữ liệu nếu có sẵn
        if (maNV != 0) {
            tfMaNV.setText(String.valueOf(maNV));
            tfHoTen.setText(hoTen);
            if (ngaySinh != null) {
                Date date = Date.from(ngaySinh.atStartOfDay(ZoneId.systemDefault()).toInstant());
                inputNgaySinh.setDate(date); // Gán ngày sinh vào InputDate
            }
            tfSdt.setText(sDT);
            cbGioiTinh.setSelectedIndex(gioiTinh);

            // Đặt các trường chỉ đọc nếu ở chế độ "Xem chi tiết"
            if (title.equals("Xem chi tiết")) {
                pnlMain.add(tfMaNV);
                tfMaNV.getTxtForm().setEditable(false);
                tfHoTen.getTxtForm().setEditable(false);
                inputNgaySinh.getDateChooser().setEnabled(false);
                tfSdt.getTxtForm().setEditable(false);
                cbGioiTinh.setEnabled(false);
            }
        }

        // Thêm các trường vào giao diện chính
        pnlMain.add(tfHoTen);
        pnlMain.add(inputNgaySinh); // Thêm InputDate
        pnlMain.add(pnlGioiTinh);
        pnlMain.add(tfSdt);

        // Nút Lưu
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSave = new JButton("Lưu");
        btnSave.setPreferredSize(new Dimension(90, 60));
        btnSave.setFont(new Font(getName(), Font.PLAIN, 20));

        if (title.equals("Xem chi tiết")) {
            btnSave.setVisible(false);
        } else {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateNhanVien(); // Kiểm tra thông tin nhập

                    if (isSaved) {
                        dispose(); // Đóng dialog nếu hợp lệ
                    }
                }
            });
            pnlButtons.add(btnSave);
        }

        // Cài đặt giao diện
        add(pnlContent, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        setResizable(false); // Không cho phép thay đổi kích thước
    }

    private void validateNhanVien() {
        // Kiểm tra thông tin nhập
        if (tfHoTen.getText() == null || tfHoTen.getText().isEmpty()) {
            isSaved = false;
            tfHoTen.setLblError("Họ tên không được để trống.");
        } else {
            tfHoTen.setLblError("");
        }

        if (inputNgaySinh.getDateChooser().getDate() == null) {
            isSaved = false;
            inputNgaySinh.setLblError("Ngày sinh không được để trống.");
        } else {
            inputNgaySinh.setLblError("");
        }

        if (!tfSdt.getText().matches("\\d{10}")) {
            isSaved = false;
            tfSdt.setLblError("Số điện thoại không hợp lệ. Phải có 10 chữ số.");
        } else {
            tfSdt.setLblError("");
        }
    }

    public boolean isSaved() {
        return isSaved;
    }

    public NhanVienDTO getDataNhanVienDTO(int maNV){
        return new NhanVienDTO(maNV, tfHoTen.getText(),inputNgaySinh.getDate(), cbGioiTinh.getSelectedIndex(), tfSdt.getText(),1);
    }
}
