/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.DiaLog;

import DTO.NguyenLieuDTO;
import DTO.NhanVienDTO;
import GUI.Panel.InputType.InputDate;
import GUI.Panel.InputType.InputText;
import java.awt.BorderLayout;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author nguyen
 */
public class NguyenLieuDialog extends JDialog {
    private InputText tfMaNL;
    private InputText tfTenNL;
    private InputText tfDonGia;
    private InputText tfDonVi;
    private boolean isSaved = false;

    public NguyenLieuDialog(JFrame owner, int maNL, String tenNL, int donGia, String donVi, String title) {
        super(owner, title, true);
        initComponents(maNL, tenNL, donGia, donVi, title);
        setLocationRelativeTo(owner); // Căn giữa với cửa sổ cha
    }

    private void initComponents(int maNL, String tenNL, int donGia, String donVi, String title) {
        setSize(500, 600);
        setLayout(new BorderLayout(10, 10));

        JLabel lblContent = new JLabel(title);
        lblContent.setFont(new Font(getName(), Font.BOLD, 20));
        JPanel pnlContent = new JPanel();
        pnlContent.add(lblContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(5, 10, 5, 10));

        tfMaNL = new InputText("Mã Nguyên Liệu");
        tfMaNL.setInputType("Number");
        tfTenNL = new InputText("Tên Nguyên Liệu");
        tfTenNL.setInputType("Text");
        tfDonGia = new InputText("Đơn Giá");
        tfDonGia.setInputType("Number");
        tfDonVi = new InputText("Đơn Vị");
        tfDonVi.setInputType("Text");

        if (maNL != 0) {
            pnlMain.add(tfMaNL);
            tfMaNL.setText(String.valueOf(maNL));
            tfMaNL.getTxtForm().setEditable(false); // không cho sửa mã
            tfTenNL.setText(tenNL);
            tfDonGia.setText(String.valueOf(donGia));
            tfDonVi.setText(donVi);
        }

        if (title.equals("Xem chi tiết")) {
            tfTenNL.getTxtForm().setEditable(false);
            tfDonGia.getTxtForm().setEditable(false);
            tfDonVi.getTxtForm().setEditable(false);
        }

        // Thêm các trường vào giao diện
        
        pnlMain.add(tfTenNL);
        pnlMain.add(tfDonGia);
        pnlMain.add(tfDonVi);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSave = new JButton("Lưu");
        btnSave.setPreferredSize(new Dimension(90, 60));
        btnSave.setFont(new Font(getName(), Font.PLAIN, 20));

        if (!title.equals("Xem chi tiết")) {
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSaved = true;
                    validateNguyenLieu();
                    if (isSaved) {
                        dispose();
                    }
                }
            });
            pnlButtons.add(btnSave);
        }

        add(pnlContent, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        setResizable(false);
    }

    private void validateNguyenLieu() {
        isSaved = true;

        if (tfTenNL.getText().trim().isEmpty()) {
            isSaved = false;
            tfTenNL.setLblError("Tên nguyên liệu không được để trống.");
        } else {
            tfTenNL.setLblError("");
        }

        if (tfDonGia.getText().trim().isEmpty()) {
            isSaved = false;
            tfDonGia.setLblError("Đơn giá không được để trống.");
        } else {
            tfDonGia.setLblError("");
        }

        if (tfDonVi.getText().trim().isEmpty()) {
            isSaved = false;
            tfDonVi.setLblError("Đơn vị không được để trống.");
        } else {
            tfDonVi.setLblError("");
        }

        try {
            Integer.parseInt(tfDonGia.getText().trim());
        } catch (NumberFormatException e) {
            isSaved = false;
            tfDonGia.setLblError("Đơn giá phải là số.");
        }
    }

    public boolean isSaved() {
        return isSaved;
    }

    public NguyenLieuDTO getDataNguyenLieuThemDTO() {
        int donGia = Integer.parseInt(tfDonGia.getText().trim());
        return new NguyenLieuDTO( tfTenNL.getText(), donGia, 0, tfDonVi.getText(), 1);
    }
    
     public NguyenLieuDTO getDataNguyenLieuSuaDTO(int maNL) {
        int donGia = Integer.parseInt(tfDonGia.getText().trim());
        return new NguyenLieuDTO(maNL, tfTenNL.getText(), donGia, 0, tfDonVi.getText(), 1);
    }
}

