package GUI.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class ItemBar extends JPanel {
    JLabel icon, title;
    boolean iselected;
    Color mainColor =new Color(187, 222, 251);

    public ItemBar(String content,String filename){
        initComponent(content,filename);
    }

    private void initComponent(String content, String filename){
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(180, 42));
        this.setBackground(Color.WHITE);
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 10");

        
        FlatSVGIcon flatSVGIcon= new FlatSVGIcon("./resources/icon/"+filename+".svg",(float)0.4);

        icon = new JLabel();
        icon.setIcon(flatSVGIcon);
        icon.setPreferredSize(new Dimension(35, 30));
        icon.setBorder(new EmptyBorder(0, 10, 0, 0));
        
        
        this.add(icon);

        title = new JLabel(content);
        title.setPreferredSize(new Dimension(120, 30));
        title.putClientProperty("FlatLaf.style", "font: 145% $medium.font");
        title.setForeground(Color.BLACK);
        this.setBorder(new EmptyBorder(2,2,2,2));
        this.add(title);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!iselected)
                    setBackground(Color.GRAY); // Màu khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!iselected)
                    setBackground(Color.WHITE); // Màu gốc
            }
        });
    }
}
