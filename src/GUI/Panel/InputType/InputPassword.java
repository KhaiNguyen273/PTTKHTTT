package GUI.Panel.InputType;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.HashUtil;

public class InputPassword extends JPanel {
    JPasswordField passwordField;
    JLabel lblTitle, lblError;

    public InputPassword(String title) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Bố cục dọc
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Thêm padding

        // Tiêu đề
        lblTitle = new JLabel(title, SwingConstants.LEFT);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái tiêu đề
        lblTitle.setFont(lblTitle.getFont().deriveFont(14f)); // Tùy chỉnh font chữ

        // Trường nhập mật khẩu
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Chiều rộng linh hoạt, chiều cao cố định
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái

        // Nhãn lỗi
        lblError = new JLabel();
        lblError.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái nhãn lỗi
        lblError.setFont(lblError.getFont().deriveFont(12f)); // Font nhỏ hơn
        lblError.setForeground(Color.RED); // Màu đỏ để hiển thị lỗi

        // Thêm các thành phần với khoảng cách
        this.add(lblTitle);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách giữa các thành phần
        this.add(passwordField);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(lblError);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(String title) {
        this.lblTitle.setText(title); // Cập nhật nội dung tiêu đề
    }

    public JLabel getLblError() {
        return lblError;
    }

    public void setLblError(String errorMessage) {
        this.lblError.setText(errorMessage); // Hiển thị thông báo lỗi
    }

    public void setPassWord(String passWord) {
        passwordField.setText(passWord);
    }

    public String getPassWord() {
        char[] passwordArray = passwordField.getPassword();
        String password = new String(passwordArray);
        java.util.Arrays.fill(passwordArray, '\0'); // Xóa mảng để bảo mật
        String hashedPassword = HashUtil.hashPassword(password);
        return hashedPassword;
    }

}
