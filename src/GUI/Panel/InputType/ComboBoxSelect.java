package GUI.Panel.InputType;

import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ComboBoxSelect extends Panel{
    JLabel lbl;
    JComboBox<String> comboBox;
    public ComboBoxSelect(String[] items, String label){
        initComponent(items,label);
    }

    private void initComponent(String[] items, String label){
        // Tạo JLabel và JComboBox
        lbl = new JLabel(label);
        comboBox = new JComboBox<>(items);  
        setLayout(new FlowLayout()); // Sắp xếp các phần tử trên cùng một hàng
        add(lbl);
        add(comboBox);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    

}
