package utils;

import com.relevantcodes.extentreports.ExtentReports;

import tests.testBase;

public class ExtentManager extends testBase {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			String workingDir = System.getProperty("user.dir");
			extent = new ExtentReports(workingDir + "/Reportes/ExtentReportResults_" + fecha + ".html", true);
		}

		return extent;
	}

}
