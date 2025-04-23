// package GUI.Frame;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class PasswordToggleExample extends JFrame {
//     public PasswordToggleExample() {
//         // Tạo JFrame cơ bản
//         this.setTitle("Ẩn/Hiện Mật Khẩu");
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setSize(300, 150);
//         this.setLayout(new FlowLayout());

//         // Tạo JPasswordField
//         JPasswordField passwordField = new JPasswordField(20);

//         // Tạo JCheckBox để ẩn/hiện mật khẩu
//         JCheckBox showPasswordCheckBox = new JCheckBox("Hiển thị mật khẩu");

//         // Lắng nghe sự kiện của JCheckBox
//         showPasswordCheckBox.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (showPasswordCheckBox.isSelected()) {
//                     passwordField.setEchoChar((char) 0); // Hiện mật khẩu
//                 } else {
//                     passwordField.setEchoChar('●'); // Ẩn mật khẩu
//                 }
//             }
//         });

//         // Thêm các thành phần vào JFrame
//         this.add(new JLabel("Mật khẩu:"));
//         this.add(passwordField);
//         this.add(showPasswordCheckBox);

//         this.setVisible(true);
//     }

//     public static void main(String[] args) {
//         new PasswordToggleExample();
//     }
// }
