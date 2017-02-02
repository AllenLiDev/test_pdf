package testTIF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class GetFile {
	private POIFSFileSystem fileSystem;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
	private int rows;

	GetFile(File input) throws FileNotFoundException, IOException {
		try {
			//System.out.println("creating getFile");
			fileSystem = new POIFSFileSystem(new FileInputStream(input));
			workbook = new HSSFWorkbook(fileSystem);
			sheet = workbook.getSheetAt(0);
			rows = sheet.getPhysicalNumberOfRows();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public String[][] loadData() {
		String[][] temp = new String[rows - 1][5];
		//System.out.println("getting data");
		for (int i = 1; i < rows; i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < 5; j++) {
				cell = row.getCell(j);
				temp[i - 1][j] = cell.toString();
				//System.out.print(temp[i - 1][j] + "\t");
			}
			//System.out.println();
		}
		return temp;
	}

}
