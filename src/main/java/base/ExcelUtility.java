package base;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Nitheesha
 */
public class ExcelUtility {

    /**
     * Use this method to read data from excel sheet and file path are taken from FilePaths interface
     * @param sheetName
     * @param rowNo
     * @param cellNo
     * @return Excel cell value in string
     */
    public static String getExcelData(String sheetName, int rowNo, int cellNo) {
        try {
            FileInputStream file = new FileInputStream(FilePaths.LANGUAGES);
            Workbook workbook = WorkbookFactory.create(file);
            return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return  null;
    }
}
