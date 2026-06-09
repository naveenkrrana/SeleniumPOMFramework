package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    // Parses Excel data into a 2D array so TestNG DataProviders can map it to tests
    public static Object[][] getExcelData(String fileName, String sheetName) {
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            // Size the array to fit the data, dropping the first row (headers)
            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read the Excel file: " + fileName);
        }

        return data;
    }
}