package GUI.pages;

import GUI.DiaLog.DaDialog;
import GUI.DiaLog.DuongDialog;
import GUI.DiaLog.KichThuocDialog;
import GUI.Frame.Main;
import GUI.Panel.itemTaskbar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ThuocTinhGUI extends JPanel {

    JPanel contentCenter;
    Main m;
    public itemTaskbar[] listitem;

    String iconst[] = {"duong", "da", "size"};
    String header[] = {"Đường", "Đá", "Kích thước"};
    Color BackgroundColor = new Color(240, 247, 250);

    public ThuocTinhGUI(Main m) {
        this.m = m;
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[header.length];

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new GridLayout(1, 3, 20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        for (int i = 0; i < header.length; i++) {
            listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
            contentCenter.add(listitem[i]);
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                DuongDialog dlg = new DuongDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý đường", true);
                dlg.setVisible(true);
            }
        });

        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                DaDialog dlg = new DaDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý đá", true);
                dlg.setVisible(true);
            }
        });

        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                KichThuocDialog dlg = new KichThuocDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý kích thước", true);
                dlg.setVisible(true);
            }
        });
    }

    private JFrame getOwnerFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }
}
