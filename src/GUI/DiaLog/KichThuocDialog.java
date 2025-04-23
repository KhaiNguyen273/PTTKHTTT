/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.DiaLog;

import BUS.KichThuocBUS;
import DTO.KichThuocDTO;
import GUI.Panel.Component.ButtonCustom;
import GUI.Panel.Component.HeaderTitle;
import GUI.Panel.InputType.InputForm;
import GUI.Panel.InputType.InputText;
import GUI.pages.ThuocTinhGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import util.Validation;

/**
 *
 * @author nguyen
 */
public class KichThuocDialog extends JDialog{

    HeaderTitle headTite;
    JPanel top, main, bottom;
    InputText ms;
    DefaultTableModel tblModel;
    JTable table;
    ButtonCustom add, del, update;
    KichThuocBUS ktBUS = new KichThuocBUS();
    ThuocTinhGUI qltt;

    public KichThuocDialog(JFrame owner, ThuocTinhGUI qlttsp, String title, boolean modal) {
        super(owner, title, modal);
        initComponent(qlttsp);
        loadDataTable(ktBUS.getAll());
    }

    public void initComponent(ThuocTinhGUI qltt) {
        this.qltt = qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTite = new HeaderTitle("QUẢN LÝ KÍCH THƯỚC");
        this.setBackground(Color.white);

        top = new JPanel();
        top.setLayout(new GridLayout(1, 1));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 80));
        top.add(headTite);

        main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));

        ms = new InputText("Tên kích thước");
        ms.setInputType("text");
        ms.setBackground(Color.white);
        ms.setPreferredSize(new Dimension(250, 70));


        table = new JTable();
        table.setBackground(Color.WHITE);
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                handleTable();
            }
        }); 

        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Kích thước"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollTable.setViewportView(table);
        scrollTable.setPreferredSize(new Dimension(420, 250));

        main.add(ms);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel", 15, 100, 40);
        add.addActionListener(e -> handleAdd());
        del = new ButtonCustom("Xóa", "danger", 15, 100, 40);
        del.addActionListener(e -> handleDel());
        update = new ButtonCustom("Sửa", "success", 15, 100, 40);
        update.addActionListener(e -> handleUpdate());
        bottom = new JPanel();
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1, 20, 20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

  public void loadDataTable(ArrayList<KichThuocDTO> result) {
    tblModel.setRowCount(0);
    for (KichThuocDTO kt : result) {
        tblModel.addRow(new Object[]{
             kt.getTenKichThuoc()
        });
    }
}

   public void handleTable() {
    int index = table.getSelectedRow();
    ArrayList<KichThuocDTO> list = ktBUS.getAll();
    if (index >= 0 && index < list.size()) {
        ms.setText(list.get(index).getTenKichThuoc());
    }
}

   
    public void handleAdd() {
    if (Validation.isEmpty(ms.getText())) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập kích thước!");
    } else {
            String kt = ms.getText();
            if (!ktBUS.checkdup(kt)) {
                JOptionPane.showMessageDialog(this, "Kích thước đã tồn tại!");
                return;
            } else {
                KichThuocDTO kthuoc = new KichThuocDTO();
                kthuoc.setTenKichThuoc(capitalizeWords(kt));
                ktBUS.them(kthuoc);
                loadDataTable(ktBUS.getAll());
                ms.setText("");
                table.clearSelection();
            }
    }
}

    
    
   public void handleDel() {
    int index = getRowSelected();
    if (index != -1) {
        ArrayList<KichThuocDTO> list = ktBUS.getAll();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ktBUS.xoaMem(list.get(index).getMaKichThuoc());
            loadDataTable(ktBUS.getAll());
            ms.setText("");
            table.clearSelection();
        }
    }
}

    
    public void handleUpdate() {
    int index = getRowSelected();
    if (index != -1) {
        if (Validation.isEmpty(ms.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập kích thước mới!");
        } else {
            String kt = ms.getText();
            if (!ktBUS.checkdup(kt)) {
                JOptionPane.showMessageDialog(this, "Kích thước đã tồn tại!");
            } else {
                ArrayList<KichThuocDTO> list = ktBUS.getAll();
                KichThuocDTO ktdto = list.get(index);
                ktdto.setTenKichThuoc(capitalizeWords(kt));
                ktBUS.sua(ktdto);
                loadDataTable(ktBUS.getAll());
                ms.setText("");
                table.clearSelection();
            }
        }
    }
}


    public static String capitalizeWords(String str) {
    if (str == null || str.isEmpty()) {
        return str;
    }

    String[] words = str.toLowerCase().split("\\s+");
    StringBuilder capitalized = new StringBuilder();

    for (String word : words) {
        if (word.length() > 0) {
            capitalized.append(Character.toUpperCase(word.charAt(0)))
                       .append(word.substring(1))
                       .append(" ");
        }
    }

    return capitalized.toString().trim();
}


    
    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kích thước!");
        }
        return index;
    }



}

