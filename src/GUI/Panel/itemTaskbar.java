package GUI.Panel;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import GUI.Panel.InputType.InputImage;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class itemTaskbar extends JPanel {

    Color FontColor = new Color(96, 125, 139);
    Color ColorBlack = new Color(26, 26, 26);
    Color DefaultColor = new Color(255, 255, 255);
    JLabel lblIcon, pnlContent, pnlSoLuong, pnlContent1;
    JPanel right;
    JLabel img;
    public boolean isSelected;

    public itemTaskbar(String linkIcon, String content) {
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(225, 45));
        this.setBackground(DefaultColor);
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(235, 237, 240));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(255, 255, 255));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Implement behavior when clicked if necessary
            }
        });
        
        lblIcon = new JLabel();
        lblIcon.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblIcon.setPreferredSize(new Dimension(45, 30));
        lblIcon.setIcon(new FlatSVGIcon("./icon/" + linkIcon));
        this.add(lblIcon);

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(155, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 145% $medium.font");
        pnlContent.setForeground(ColorBlack);
        this.add(pnlContent);
    }

    public itemTaskbar(String linkIcon, String content1, String content2) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 150)); 
        this.setBackground(DefaultColor);
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(235, 237, 240));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    setBackground(new Color(255, 255, 255));
                }
            }
        });

        lblIcon = new JLabel();
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER); 
        lblIcon.setIcon(new FlatSVGIcon("./resources/icon/" + linkIcon + ".svg", 4.0f)); 
        this.add(lblIcon, BorderLayout.NORTH);

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setBackground(DefaultColor);
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblContent1 = new JLabel(content1);
        lblContent1.setHorizontalAlignment(SwingConstants.CENTER); 
        lblContent1.putClientProperty("FlatLaf.style", "font: 300% $semibold.font"); 
        lblContent1.setForeground(FontColor);

        JLabel lblContent2 = new JLabel(content2);
        lblContent2.setHorizontalAlignment(SwingConstants.CENTER); 
        lblContent2.putClientProperty("FlatLaf.style", "font: 200% $medium.font");
        lblContent2.setForeground(FontColor);

        this.add(lblContent1, BorderLayout.CENTER);
    }

    public itemTaskbar(String linkImg, String tenSP, int soLuong) {
        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(380, 60));
        this.setBackground(Color.white);

        img = new JLabel("");
        img.setIcon(InputImage.resizeImage(new ImageIcon("src/resources/img/" + linkImg), 38));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new FlowLayout(0, 0, 0));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        pnlContent = new JLabel(tenSP);
        pnlContent.putClientProperty("FlatLaf.style", "font: 120% $semibold.font");
        pnlContent.setForeground(Color.black);
        right.add(pnlContent);

        pnlSoLuong = new JLabel("Số lượng: " + soLuong);
        pnlSoLuong.setPreferredSize(new Dimension(350, 20));
        pnlSoLuong.putClientProperty("FlatLaf.style", "font: 100% $medium.font");
        pnlSoLuong.setForeground(Color.gray);
        right.add(pnlSoLuong);
    }
}
