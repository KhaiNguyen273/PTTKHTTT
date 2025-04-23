package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;

public class ImportExcelUtility {

    public static <T> void importFromExcelAndSave(
            File file,
            DataHandler<T> dataHandler,
            DataRowMapper<T> rowMapper) throws Exception {

        // Sử dụng try-with-resources để tự động đóng Workbook
        try (FileInputStream fileInputStream = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            // Đọc dữ liệu từ Excel
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // Bỏ qua dòng đầu tiên (tiêu đề)
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    Object[] rowData = new Object[row.getLastCellNum() - 1]; // Bỏ qua cột đầu tiên
                    for (int colIndex = 1; colIndex < row.getLastCellNum(); colIndex++) { // Bắt đầu từ cột thứ 2
                        Cell cell = row.getCell(colIndex);
                        rowData[colIndex - 1] = (cell != null) ? cell.toString() : "";
                    }

                    // Chuyển từ rowData sang DTO (nhờ rowMapper)
                    T dto = rowMapper.mapRow(rowData);

                    // Lưu dữ liệu qua DataHandler
                    if (!dataHandler.handleData(dto)) {
                        throw new Exception("Lỗi khi lưu: " + rowData[0]);
                    }
                }
            }
        }
    }

    public static <T> void openAndImportExcel(
            DataHandler<T> dataHandler,
            DataRowMapper<T> rowMapper) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        // Hiển thị hộp thoại chọn tệp
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                importFromExcelAndSave(selectedFile, dataHandler, rowMapper);
                JOptionPane.showMessageDialog(null, "Nhập dữ liệu từ Excel thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu: " + ex.getMessage());
            }
        }
    }
}