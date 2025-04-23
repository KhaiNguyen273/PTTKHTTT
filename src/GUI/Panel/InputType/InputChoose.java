package GUI.Panel.InputType;

import GUI.DiaLog.TableDiaLog;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;


public class InputChoose extends JPanel {
    JLabel lblTitle, lblError;
    JTextField txtForm;
    JButton btnChoose;
    String ghiChuChoDiaLog;

    public InputChoose(String title, JTable tbl) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Khoảng cách padding

        // Tiêu đề
        lblTitle = new JLabel(title);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái tiêu đề
        lblTitle.setFont(lblTitle.getFont().deriveFont(14f)); // Tùy chỉnh kích cỡ font chữ

        JPanel pnlChoose = new JPanel(new BorderLayout(5, 0));
        pnlChoose.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        pnlChoose.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Trường nhập
        txtForm = new JTextField();
        txtForm.setEditable(false);
        txtForm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Chiều rộng linh hoạt, chiều cao cố định
        txtForm.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái trường nhập
        pnlChoose.add(txtForm, BorderLayout.CENTER);

        // Nút chọn
        btnChoose = new JButton(new FlatSVGIcon("./resources/icon/table.svg", 0.4f));
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Tạo và hiển thị TableDialog
                TableDiaLog dialog = new TableDiaLog(null, "Chọn dữ liệu", tbl);
                dialog.setGhiChu(ghiChuChoDiaLog);
                dialog.setVisible(true);
                String selectedValue = dialog.getSelectedValue();
                if (selectedValue != null) {
                    txtForm.setText(selectedValue); // Cập nhật giá trị được chọn vào trường nhập
                }
            }
        });
        pnlChoose.add(btnChoose, BorderLayout.EAST);

        // Nhãn lỗi
        lblError = new JLabel();
        lblError.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái nhãn lỗi
        lblError.setFont(lblError.getFont().deriveFont(12f)); // Font chữ nhỏ hơn
        lblError.setForeground(java.awt.Color.RED); // Màu lỗi đỏ

        // Thêm các thành phần với khoảng cách
        this.add(lblTitle);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách giữa các thành phần
        this.add(pnlChoose);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(lblError);
    }

    public String getText(){
        return txtForm.getText();
    }

    public JButton getBtnChoose() {
        return btnChoose;
    }

    public void setText(String text){
        txtForm.setText(text);
    }

    public void setGhiChuChoDiaLog(String ghiChuChoDiaLog) {
        this.ghiChuChoDiaLog = ghiChuChoDiaLog;
    }

    public void setLblError(String text) {
        this.lblError.setText(text);;
    }

    

}
