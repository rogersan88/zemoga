
package utils;

import java.util.ArrayList;
import java.util.List;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	public Object[][] testData(String excelPath, String sheetName) {

		ExcelAndroidUtils excel = new ExcelAndroidUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();

		int colCount = excel.getColCount();

		int count = 0;

		String cellData = "";

		String ejecutar = "Si";
		List<Integer> posicion = new ArrayList<Integer>();

		int total = 1;

		if (excel.getCellDataString(0, colCount - 1).equals("Ejecutar")) {

			while (count < rowCount) {

				cellData = excel.getCellDataString(count, colCount - 1);

				if (cellData.equals("Si")) {

					posicion.add(count);
					total++;

				}

				count++;
			}

			rowCount = total;

		} else {

			ejecutar = "No";

		}

		// -1 por que la primera es el Header:
		Object data[][] = new Object[rowCount - 1][colCount];

		count = 0;

		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				if (ejecutar.equals("Si")) {
					cellData = excel.getCellDataString(posicion.get(count), j);

					data[i - 1][j] = cellData;

				}

				if (ejecutar.equals("No")) {
					cellData = excel.getCellDataString(i, j);
					data[i - 1][j] = cellData;

				}

			}

			count++;

		}

		System.out.println("AMOUNT OF DATA STORED " + data.length);

		return data;
	}

	
	@DataProvider(name = "dataUserStory1")
	public Object[][] getDataValidarBrandrooms(ITestContext context) {

		String projectPath = System.getProperty("user.dir");
		Object data[][] = testData(
				projectPath + "/src/test/resources/Data.xlsx",
				"UserStory1");
		return data;
	}

	@DataProvider(name = "dataUserStory2")
	public Object[][] getDataLogin(ITestContext context) {

		String projectPath = System.getProperty("user.dir");
		Object data[][] = testData(
				projectPath + "/src/test/resources/Data.xlsx",
				"UserStory2");
		
		return data;
	}
}
