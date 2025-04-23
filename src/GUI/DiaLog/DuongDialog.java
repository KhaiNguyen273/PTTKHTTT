package GUI.DiaLog;

import BUS.DuongBUS;
import DAO.DuongDao;
import DTO.DuongDTO;
import GUI.Panel.Component.ButtonCustom;
import GUI.Panel.Component.HeaderTitle;
import GUI.Panel.InputType.InputForm;
import GUI.Panel.InputType.InputText;
import GUI.pages.ThuocTinhGUI;
import util.Validation;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class DuongDialog extends JDialog{

    HeaderTitle headTite;
    JPanel top, main, bottom;
    InputText ms;
    DefaultTableModel tblModel;
    JTable table;
    ButtonCustom add, del, update;
    DuongBUS dgBUS = new DuongBUS();
    ThuocTinhGUI qltt;

    public DuongDialog(JFrame owner, ThuocTinhGUI qlttsp, String title, boolean modal) {
           super(owner, title, modal);
        initComponent(qlttsp);
        loadDataTable(dgBUS.getAll());

    }

    public void initComponent(ThuocTinhGUI qltt) {
        this.qltt = qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        this.setResizable(false);
        headTite = new HeaderTitle("QUẢN LÝ ĐƯỜNG");
        this.setBackground(Color.white);

        top = new JPanel();
        top.setLayout(new GridLayout(1, 1));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 70));
        top.add(headTite);

        main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));

        ms = new InputText("Phần trăm đường");
        ms.setInputType("number");
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
        String[] header = new String[]{"Phần trăm đường"};
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

   public void loadDataTable(ArrayList<DuongDTO> result) {
    tblModel.setRowCount(0);
    for (DuongDTO da : result) {
        tblModel.addRow(new Object[]{
             da.getphantramDuong()
        });
    }
}

   public void handleTable() {
    int index = table.getSelectedRow();
    ArrayList<DuongDTO> list = dgBUS.getAll();
    if (index >= 0 && index < list.size()) {
        ms.setText(String.valueOf(list.get(index).getphantramDuong()));
    }
}

    public void handleAdd() {
    if (Validation.isEmpty(ms.getText())) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm đường!");
    } else {
        try {
            int pt = Integer.parseInt(ms.getText());
            if (pt < 0) {
                JOptionPane.showMessageDialog(this, "Phần trăm đường phải lớn hơn hoặc bằng 0!");
                return;
            }
            if (!dgBUS.checkdup(pt)) {
                JOptionPane.showMessageDialog(this, "Phần trăm đường đã tồn tại!");
            } else {
                DuongDTO da = new DuongDTO();
                da.setphantramDuong(pt);
                dgBUS.them(da);
                loadDataTable(dgBUS.getAll()); // lấy mới
                ms.setText("");
                table.clearSelection();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Phần trăm đường phải là số nguyên!");
        }
    }
}

    
   public void handleDel() {
    int index = getRowSelected();
    if (index != -1) {
        ArrayList<DuongDTO> list = dgBUS.getAll();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dgBUS.xoaMem(list.get(index).getMaDuong());
            loadDataTable(dgBUS.getAll());
            ms.setText("");
            table.clearSelection();
        }
    }
}

    
    public void handleUpdate() {
    int index = getRowSelected();
    if (index != -1) {
        if (Validation.isEmpty(ms.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm đá mới!");
        } else {
            try {
                int pt = Integer.parseInt(ms.getText());
                if (pt <= 0) {
                    JOptionPane.showMessageDialog(this, "Phần trăm đá phải lớn hơn 0!");
                    return;
                }
                if (!dgBUS.checkdup(pt)) {
                    JOptionPane.showMessageDialog(this, "Phần trăm đá đã tồn tại!");
                } else {
                    ArrayList<DuongDTO> list = dgBUS.getAll();
                    DuongDTO da = list.get(index);
                    da.setphantramDuong(pt);
                    dgBUS.sua(da);
                    loadDataTable(dgBUS.getAll());
                    ms.setText("");
                    table.clearSelection();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Phần trăm đá phải là số nguyên!");
            }
        }
    }
}




    
    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đá!");
        }
        return index;
    }


}
