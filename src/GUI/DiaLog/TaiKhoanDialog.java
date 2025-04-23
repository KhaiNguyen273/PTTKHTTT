package GUI.DiaLog;

import BUS.NhanVienBUS;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Panel.InputType.InputChoose;
import GUI.Panel.InputType.InputPassword;
import GUI.Panel.InputType.InputText;
import util.HashUtil;

public class TaiKhoanDialog extends JDialog {
    private InputChoose tfMaNV;
    private InputText tfTenDangNhap;
    private InputPassword tfMatKhau;
    private boolean isSaved = false;
    private String matKhau;

    public TaiKhoanDialog(JFrame owner, TaiKhoanDTO taiKhoan, String titleString) {
        super(owner, titleString, true);
        initComponents(taiKhoan, titleString);
        setLocationRelativeTo(owner); // Center the dialog relative to the parent frame
    }

    private void initComponents(TaiKhoanDTO taiKhoan, String titleString) {
        setSize(450, 550); // Set a reasonable size
        setLayout(new BorderLayout(10, 10)); // Add spacing between sections

        JLabel lblContent = new JLabel(titleString);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));
        pnlMain.setPreferredSize(new Dimension(400, 300));

//         Input fields
        tfMaNV = new InputChoose("Mã Nhân Viên",createTableNVChuaCoTK());
        tfMaNV.setGhiChuChoDiaLog("*Chỉ những nhân vien chưa có tài khoản mới xuất thiên trong bảng này!");
        tfTenDangNhap = new InputText("Tên Đăng Nhập");
        tfMatKhau = new InputPassword("Mật Khẩu");
//        tfMaQuyen = new InputChoose("Mã Quyền",CreateTableQuyen());

        if (taiKhoan != null) {
            tfMaNV.setText(String.valueOf(taiKhoan.getMaNV()));
            tfTenDangNhap.setText(taiKhoan.getTenDangNhap());
            tfMatKhau.setPassWord(taiKhoan.getMatKhau());
            matKhau = new String(taiKhoan.getMatKhau());
            tfMatKhau.setVisible(false);

            // Set fields to read-only if "Xem chi tiết"
            if (titleString.equals("Xem chi tiết")) {
                tfMaNV.getBtnChoose().setEnabled(false);
                tfTenDangNhap.getTxtForm().setEditable(false);
            }
        }

        pnlMain.add(tfMaNV);
        pnlMain.add(tfTenDangNhap);
        pnlMain.add(tfMatKhau);

        // Buttons
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSave = new JButton("Lưu");
        btnSave.setPreferredSize(new Dimension(90, 60));
        btnSave.setFont(new Font(getName(), Font.PLAIN, 20));

        if (titleString.equals("Xem chi tiết")) {
            btnSave.setVisible(false); // Hide the Save button
        } else {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateTaiKhoan();
                    if (isSaved) {
                        dispose();
                    }
                }
            });
            pnlButtons.add(btnSave);
        }

        // Add components to dialog
        add(pnlContent, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        // Configure the dialog
        setResizable(false); // Disable resizing
    }

    public void validateTaiKhoan() {
        if (tfMaNV.getText() == null || tfMaNV.getText().isEmpty()) {
            isSaved = false;
            tfMaNV.setLblError("Mã nhân viên không được để trống.");
        }else
            tfMaNV.setLblError("");
        if (tfTenDangNhap.getText() == null || tfTenDangNhap.getText().isEmpty()) {
            isSaved = false;
            tfTenDangNhap.setLblError("Tên đăng nhập không được để trống.");
        } else {
            if (new TaiKhoanBUS().getTaiKhoanByTenDangNhap(tfTenDangNhap.getText()) != null){
                isSaved = false;
                tfTenDangNhap.setLblError("Tên đăng nhập bị trùng");
            }else
                tfTenDangNhap.setLblError("");
        }

        if (tfMatKhau.getPassWord() == null || tfMatKhau.getPassWord().isEmpty() || tfMatKhau.getPassWord().equals(HashUtil.hashPassword(""))) {
            isSaved = false; // Check if the password is empty
            tfMatKhau.setLblError("Mật khẩu không được để trống.");
        } else {
            tfMatKhau.setLblError("");
        }

   
    }

    public boolean isSaved() {
        return isSaved;
    }

    public TaiKhoanDTO getTaiKhoanData() {
        return new TaiKhoanDTO(Integer.parseInt(tfMaNV.getText()), tfTenDangNhap.getText(), matKhau == null? tfMatKhau.getPassWord() : matKhau,1);
    }

    private JTable createTableNVChuaCoTK() {
        // Lấy danh sách nhân viên chưa có tài khoản từ BLL
        List <NhanVienDTO> nhanVienList = new NhanVienBUS().getNhanVienChuaCoTaiKhoan();
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

        JTable table = new JTable(model);
        // Gán model mới vào bảng
        return table;
    }
//
//    private JTable CreateTableQuyen() {
//        List<QuyenDTO> quyenList = new QuyenBLL().getAllQuyen();
//        // Fetch all permissions data from BLL
//        String[] columnNames = { "Mã Quyền", "Tên Quyền" };
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//
//        // Add data to the table model
//        for (QuyenDTO quyen : quyenList) {
//            Object[] rowData = {
//                    quyen.getMaQuyen(),
//                    quyen.getTenQuyen()
//            };
//            model.addRow(rowData);
//        }
//
//        JTable table = new JTable(model);
//        return table;
//    }
}
