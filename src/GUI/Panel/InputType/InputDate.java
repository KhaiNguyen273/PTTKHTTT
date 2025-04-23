package GUI.Panel.InputType;

import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class InputDate extends JPanel {
    JLabel lblTitle, lblError;
    JDateChooser dateChooser;
    SimpleDateFormat dateFormat; // Định dạng ngày

    public InputDate(String title) {
        // Thiết lập định dạng ngày là dd/MM/yyyy
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Cài đặt layout và khoảng cách
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding

        // Tạo tiêu đề
        lblTitle = new JLabel(title);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
        lblTitle.setFont(lblTitle.getFont().deriveFont(14f)); // Font chữ lớn hơn

        // Tạo JDateChooser
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy"); // Hiển thị theo định dạng dd/MM/yyyy
        dateChooser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Linh hoạt chiều rộng, cố định chiều cao
        dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái

        // Tạo nhãn lỗi
        lblError = new JLabel();
        lblError.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
        lblError.setFont(lblError.getFont().deriveFont(12f)); // Font chữ nhỏ
        lblError.setForeground(java.awt.Color.RED); // Màu lỗi đỏ

        // Thêm các thành phần vào panel
        this.add(lblTitle);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách giữa các thành phần
        this.add(dateChooser);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(lblError);
    }

    // Getter và Setter cho nhãn lỗi
    public void setLblError(String errorMessage) {
        lblError.setText(errorMessage); // Hiển thị lỗi
    }

    // Trả về giá trị ngày được chọn dưới dạng chuỗi với định dạng dd/MM/yyyy
    public String getDateAsString() {
        Date selectedDate = dateChooser.getDate();
        if (selectedDate != null) {
            return dateFormat.format(selectedDate); // Chuyển đổi sang chuỗi
        }
        return null; // Trả về null nếu chưa chọn ngày
    }

    // Trả về giá trị ngày được chọn dưới dạng Date
    public Date getDate() {
        return dateChooser.getDate();
    }

    // Thiết lập giá trị cho JDateChooser
    public void setDate(Date date) {
        dateChooser.setDate(date);
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    
}
