package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.Frame.Login;
import GUI.Frame.Main;
import GUI.pages.NguyenLieuGUI;
import GUI.pages.NhanVienGUI;
import GUI.pages.SanPhamGUI;
import GUI.pages.TaiKhoanGUI;
import GUI.pages.ThuocTinhGUI;
import GUI.pages.TrangChuGUI;

public class SideBar extends JPanel {
    Main main;
    TopNav topNav;
    TaiKhoanDTO taiKhoanDTO;
    JPanel pnlTop, pnlMid, pnlBot;
    ItemBar[] itemBars;

    String[] menuBars = { "Trang chủ", "Sản phẩm", "Thuộc tính", "Nguyên liệu",
                        "Đơn hàng","Phiếu nhập", "Hóa đơn", "Khách hàng",
                        "Nhân viên", "Tài khoản", "Thống kê" };

    String[] icons = { "home", "drink", "attributes", "ingredient","order", "import", "export", "user", "employee", "account", "stats" };
    int thisPage = 0;

    Color mainColor = new Color(100, 149, 237);

    public SideBar(Main main, TaiKhoanDTO taiKhoanDTO) {
        this.taiKhoanDTO = taiKhoanDTO;
        initComponent(main);
    }

    private void initComponent(Main main) {
        this.main = main;

        topNav = new TopNav();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(190, 200));

        // Top panel
        pnlTop = new JPanel(new BorderLayout());
        pnlTop.setPreferredSize(new Dimension(230, 60));
        pnlTop.setBackground(Color.WHITE);

        FlatSVGIcon svgIcon = new FlatSVGIcon("./resources/icon/logo.svg");
        JLabel lblAvatar = new JLabel(svgIcon);
        lblAvatar.setPreferredSize(new Dimension(70, 30));

        JLabel lblName = new JLabel("Phone Store");
        lblName.setFont(new Font("Arial", Font.BOLD, 16));
        lblName.setForeground(Color.BLACK);

        pnlTop.add(lblAvatar, BorderLayout.WEST);
        pnlTop.add(lblName, BorderLayout.CENTER);

        // Middle panel
        pnlMid = new JPanel();
        pnlMid.setPreferredSize(new Dimension(230, 600));
        pnlMid.setBackground(Color.WHITE);
        pnlMid.setLayout(new FlowLayout(0, 0, 5));
        pnlMid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        itemBars = new ItemBar[menuBars.length];
        for (int i = 0; i < menuBars.length; i++) {
            itemBars[i] = new ItemBar(menuBars[i], icons[i]);
            pnlMid.add(itemBars[i]);
        }
        itemBars[0].setBackground(mainColor);

        // Bottom panel
        pnlBot = new JPanel();
        pnlBot.setBackground(Color.WHITE);

        ItemBar logoutItem = new ItemBar("Đăng xuất", "logout");
        logoutItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                main.dispose();
                new Login().setVisible(true);
            }
        });

        pnlBot.add(logoutItem, Label.BOTTOM_ALIGNMENT);

        setBackground(Color.BLACK);
        add(pnlTop, BorderLayout.NORTH);
        add(pnlMid, BorderLayout.CENTER);
        add(pnlBot, BorderLayout.SOUTH);

        itemBars[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(0);
                main.setPanel(new TrangChuGUI());
            }
        });

        itemBars[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(1);
                main.setPanel(new SanPhamGUI(topNav));
            }
        });

        itemBars[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new ThuocTinhGUI(main));
                changePage(2);
            }
        });

        itemBars[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new NguyenLieuGUI(topNav));
                changePage(3);
            }
        });

        itemBars[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(4);
            }
        });

        itemBars[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(5);
            }
        });

        itemBars[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(6);
            }
        });

        itemBars[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(7);
                
            }
        });

        itemBars[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(8);
                main.setPanel(new NhanVienGUI(topNav));
                
            }
        });

        itemBars[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(9);
                main.setPanel(new TaiKhoanGUI(topNav));
            }
        });
        itemBars[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                changePage(9);
            }
        });
    }

    public ItemBar[] getItemBars() {
        return this.itemBars;
    }

    private void changePage(int i) {
        itemBars[thisPage].setBackground(Color.WHITE);
        itemBars[thisPage].iselected = false;
        thisPage = i;
        itemBars[i].setBackground(mainColor);
        itemBars[i].iselected = true;
        topNav = new TopNav();
    }
}
