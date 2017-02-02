package testTIF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DriverTIF {

	public static void main(String args[]) throws FileNotFoundException, IOException {
		//System.out.println("starting program");
		File testFile = new File("H:/TIF test/testTIF.xls");
		File pdfFile = new File("H:/TIF test/test.pdf");
		GetFile test = new GetFile(testFile);
		test.loadData();

		//System.out.println("testing pdf creator");
		CreatePdf test1 = new CreatePdf(700, 100, test.loadData());
		test1.drawPdf();
		test1.drawContent(test.loadData());
		
		System.out.println("testing pdf fill");
		ConvertPdf test2 = new ConvertPdf(pdfFile);
	}
}
