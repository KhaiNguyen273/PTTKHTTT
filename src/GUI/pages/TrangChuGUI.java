package GUI.pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TrangChuGUI extends JPanel {
    private JPanel pnlHeader, pnlContent, pnlLeft, pnlRight, pnlCustomer, pnlSecurity, pnlStore, pnlFooter;

    public TrangChuGUI() {
        initComponents();
    }

    // Phương thức tạo panel với ảnh bên trái và chữ bên phải
    private JPanel createPanel(String title, String imagePath, Color backgroundColor, String description, Dimension imageSize) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
    
        // Tiêu đề (đặt ở phía trên của panel)
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Khoảng cách
        panel.add(lblTitle, BorderLayout.NORTH);
    
        // Nội dung chính
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(backgroundColor);
    
        // Hình ảnh bên trái
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(imageSize.width, imageSize.height, Image.SCALE_SMOOTH);
        JLabel lblImage = new JLabel(new ImageIcon(scaledImage));
        lblImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Khoảng cách
    
        // Mô tả bên phải
        JTextArea txtDescription = new JTextArea(description);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setLineWrap(true);
        txtDescription.setEditable(false);
        txtDescription.setFont(new Font("Arial", Font.PLAIN, 17));
        txtDescription.setBackground(backgroundColor);
        txtDescription.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Khoảng cách
    
        // Cấu hình layout cho contentPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(lblImage, gbc); // Thêm ảnh vào bên trái
    
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(txtDescription, gbc); // Thêm mô tả vào bên phải
    
        // Thêm nội dung chính vào panel
        panel.add(contentPanel, BorderLayout.CENTER);
    
        return panel;
    }

    protected void initComponents() {
        setLayout(new BorderLayout());

        // Header Panel
        pnlHeader = new JPanel();
        pnlHeader.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120)); // Tăng chiều cao header
        JLabel lblHeaderTitle = new JLabel("SGU COFFEE STORE - Trang Chủ");
        lblHeaderTitle.setFont(new Font("Poppins", Font.BOLD, 32));
        pnlHeader.setBorder(new EmptyBorder(25, 10, 10, 10));
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.add(lblHeaderTitle);

        // Content Panel
        pnlContent = new JPanel(new GridLayout(1, 2, 20, 20)); // Khoảng cách giữa các panel
        pnlContent.setBackground(Color.WHITE);

        // Panel bên trái (Cửa hàng)
        pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBackground(Color.WHITE);
        pnlStore = createPanel(
            "Cửa Hàng",
            "src/resources/img/store.jpg",
            Color.WHITE,
            "SGU COFFEE STORE cung cấp các sản phẩm nước uống ngon với giá cả hợp lý và dịch vụ tận tình.",
            new Dimension(400, 400) // Kích thước lớn hơn cho ảnh
        );
        pnlLeft.add(pnlStore, BorderLayout.CENTER);

        // Panel bên phải (Khách hàng & Bảo mật)
        pnlRight = new JPanel(new GridLayout(2, 1, 10, 10)); // Khoảng cách giữa các mục
        pnlRight.setBackground(Color.WHITE);
        pnlCustomer = createPanel(
            "Khách Hàng",
            "src/resources/img/customers.png",
            Color.WHITE,
            "Chúng tôi luôn tận tâm hỗ trợ khách hàng với các chương trình ưu đãi tuyệt vời.",
            new Dimension(300, 200)
        );
        pnlSecurity = createPanel(
            "Sức Khỏe",
            "src/resources/img/security.png",
            Color.WHITE,
            "Nguồn nguyên liệu được đảm bảo an toàn với sức khỏe mọi người.",
            new Dimension(300, 200)
        );
        pnlRight.add(pnlCustomer);
        pnlRight.add(pnlSecurity);

        // Thêm các panel vào khu vực nội dung
        pnlContent.add(pnlLeft);
        pnlContent.add(pnlRight);

        // Footer Panel
        pnlFooter = new JPanel();
        pnlFooter.setBackground(Color.WHITE);
        pnlFooter.setPreferredSize(new Dimension(Integer.MAX_VALUE, 60)); // Chiều cao footer
        JLabel lblFooter = new JLabel("© 2025 Sgu Coffee Store Inc. All Rights Reserved");
        lblFooter.setFont(new Font("Roboto", Font.ITALIC, 14)); // Font lớn hơn cho footer
        pnlFooter.add(lblFooter);

        // Thêm các khu vực vào layout chính
        add(pnlHeader, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
        add(pnlFooter, BorderLayout.SOUTH);
    }
}
