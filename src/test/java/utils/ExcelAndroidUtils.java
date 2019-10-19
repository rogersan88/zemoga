package utils;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelAndroidUtils {

	static String projectPath;
	static XSSFWorkbook workBook;
	static XSSFSheet sheet;

	public ExcelAndroidUtils(String excelPath, String sheetName) {
		try {
			projectPath = System.getProperty("user.dir");
			ZipSecureFile.setMinInflateRatio(0);
			workBook = new XSSFWorkbook(excelPath);
			sheet = workBook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		getCellDataString(0, 0);
//		getCellDataNumber(1, 1);
//	}

	public int getRowCount() {

		int rowCount = 0;

		try {

			rowCount = sheet.getPhysicalNumberOfRows();

			System.out.println("Number of Rows: " + rowCount);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return rowCount;

	}

	public int getColCount() {

		int colCount = 0;
		try {

			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("NUMBER OF COLUMNS : " + colCount);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return colCount;

	}

	public String getCellDataString(int rowNum, int colNum) {
		String cellData = null;

		try {

			if (sheet.getRow(rowNum).getCell(colNum) == null) {

				sheet.getRow(rowNum).createCell(colNum);

				sheet.getRow(rowNum).getCell(colNum).setCellValue("");

			}

			cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

			// System.out.println(cellData);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return cellData;
	}

	public static void getCellDataNumber(int rowNum, int colNum) {
		try {

			// double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();

			// System.out.println(cellData);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

	}

}
