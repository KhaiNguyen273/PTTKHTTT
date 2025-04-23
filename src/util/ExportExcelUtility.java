package util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Workbook; // Workbook cho file Excel
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Workbook cho định dạng .xlsx
import org.apache.poi.ss.usermodel.Sheet; // Sheet trong Excel
import org.apache.poi.ss.usermodel.Row; // Hàng trong Excel
import org.apache.poi.ss.usermodel.Cell; // Ô trong Excel
import javax.swing.table.TableModel; // Lấy dữ liệu từ JTable
import java.io.FileOutputStream; // Ghi dữ liệu ra file


public class ExportExcelUtility {

    public static void saveTableToExcel(JTable table, String sheetName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        // Hiển thị hộp thoại lưu tệp
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            // Đảm bảo file có đuôi .xlsx
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try {
                // Gọi phương thức xuất dữ liệu từ JTable ra Excel
                exportToExcel(table, sheetName, filePath);
                JOptionPane.showMessageDialog(null, "Xuất dữ liệu ra Excel thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất dữ liệu: " + ex.getMessage());
            }
        }
    }

    private static void exportToExcel(JTable table, String sheetName, String filePath) throws Exception {
        // Nội dung của phương thức xuất Excel (như đã tạo trước đó)
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        // Ghi tiêu đề cột từ JTable
        Row headerRow = sheet.createRow(0);
        TableModel model = table.getModel();

        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
        }

        // Ghi dữ liệu từ JTable vào Excel
        for (int row = 0; row < model.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = excelRow.createCell(col);
                Object value = model.getValueAt(row, col);
                cell.setCellValue(value != null ? value.toString() : "");
            }
        }

        // Tự động điều chỉnh độ rộng cột
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
        }

        // Ghi dữ liệu ra file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}
