package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class TopNav extends JPanel {
    JPanel pnlChucNang, pnlSearch;
    JButton[] btn;
    JComboBox<String> findFor;
    JTextField textSearch;
    JButton btnRefresh;

    public TopNav() {
        initComponent();
    }

    private void initComponent() {
        // Set up main panel with BorderLayout
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        setBackground(new Color(245, 245, 245)); // Soft gray background
        setPreferredSize(new Dimension(800, 80)); // Adjust height

        // Search panel (Top right section)
        pnlSearch = new JPanel();
        pnlSearch.setPreferredSize(new Dimension(600, 50)); // Increased height for better spacing
        pnlSearch.setBackground(Color.WHITE);
        pnlSearch.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray)); // Subtle border

        // Add some padding around the panel
        pnlSearch.setLayout(new FlowLayout(1, 10, 0));
        pnlSearch.setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding inside the panel

        // Search icon
        JLabel iconSearch = new JLabel(new FlatSVGIcon("./resources/icon/search.svg", 0.3f));
        iconSearch.putClientProperty(FlatClientProperties.STYLE, "arc: 99"); // Add rounded style
        iconSearch.setOpaque(true); // Set opaque to apply background
        iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
        iconSearch.setPreferredSize(new Dimension(40, 40)); // Make it square
        pnlSearch.add(iconSearch);

        // Search input field
        JPanel inputSearch = new JPanel(new BorderLayout());
        inputSearch.setBackground(Color.WHITE);
        inputSearch.setBorder(new MatteBorder(0, 0, 2, 0, Color.BLUE)); // Underline-style border

        textSearch = new JTextField(20); // Adjust width
        textSearch.setBorder(null);
        textSearch.setFont(textSearch.getFont().deriveFont(14f)); // Slightly larger font

        inputSearch.add(textSearch, BorderLayout.CENTER);
        pnlSearch.add(inputSearch);

        // ComboBox for search options
        findFor = new JComboBox<>();
        findFor.setPreferredSize(new Dimension(100, 40)); // Adjust ComboBox size
        pnlSearch.add(findFor);

        // Refresh button
        btnRefresh = new JButton("Làm Mới", new FlatSVGIcon("./resources/icon/refresh.svg", 0.3f));
        btnRefresh.setToolTipText("Làm mới");
        btnRefresh.putClientProperty(FlatClientProperties.STYLE, "arc: 12; hoverBackground: #E0E0E0"); // Rounded and hover effect
        btnRefresh.setPreferredSize(new Dimension(110, 50)); // Adjust button size
        pnlSearch.add(btnRefresh);

        // Functional buttons panel (Bottom section)
        pnlChucNang = new JPanel(new FlowLayout(1, 5, 0)); // Grid layout with spacing
        pnlChucNang.setBackground(new Color(240, 240, 240)); // Match parent background

        btn = new JButton[4];

        // Configure buttons
        String[] buttonTexts = { "Thêm", "Sửa", "Xóa", "Chi tiết" };
        String[] icons = { "add", "fix", "del", "detail" };
        float[] iconSizes = { 0.4f, 0.4f, 0.4f, 0.6f };

        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton(buttonTexts[i],
                    new FlatSVGIcon("./resources/icon/" + icons[i] + ".svg", iconSizes[i]));
            btn[i].setToolTipText(buttonTexts[i]);
            btn[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            btn[i].setHorizontalTextPosition(SwingConstants.CENTER);
            btn[i].putClientProperty(FlatClientProperties.STYLE, "arc: 12");
            btn[i].setOpaque(false);
            btn[i].setPreferredSize(new Dimension(90, 60));
            pnlChucNang.add(btn[i]);
        }

        add(pnlChucNang, BorderLayout.WEST);
        add(pnlSearch, BorderLayout.EAST);
    }

    public JButton[] getBtn() {
        return btn;
    }

    public JTextField getTextSearch() {
        return textSearch;
    }

    public JComboBox<String> getFindFor() {
        return findFor;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public void setItemComboBox(String[] items) {
        findFor.setModel(new DefaultComboBoxModel<>(items));
    }

}
