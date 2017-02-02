package testTIF;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CreatePdf {
	static private final float ROW_HEIGHT = 20f;
	static private final float CELL_MARGIN = 5f;
	private static final float DIFFERENCE = 15f;
	private static final float FONT_SIZE = 20;

	private PDPageContentStream contentStream;
	private PDDocument document;
	private PDPage page;
	private float nextRow;
	private float nextCol;
	private float rows;
	private float columns;
	private float tableWidth;
	private float colWidth;
	private float tableHeight;

	CreatePdf(float y, float margin, String[][] content) throws IOException {
		//System.out.println("Creating createPDF");
		try {
			document = new PDDocument();
			page = new PDPage();
			document.addPage(page);
			contentStream = new PDPageContentStream(document, page);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		nextRow = y;
		nextCol = margin;
		rows = content.length;
		columns = content[0].length;
		tableWidth = page.getCropBox().getWidth() - margin - margin;
		tableHeight = ROW_HEIGHT * rows;
		colWidth = tableWidth / (float) columns;
	}

	@SuppressWarnings("deprecation")
	public void drawPdf() throws IOException {
		//System.out.println("Drawpdf called");
		float nexty = nextRow;
		float nextx = nextCol;
		try {
			for (int i = 0; i <= rows; i++) {
				contentStream.drawLine(nextCol, nexty, nextCol + tableWidth, nexty);
				nexty -= ROW_HEIGHT;
			}
			for (int i = 0; i <= columns; i++) {
				contentStream.drawLine(nextx, nextRow, nextx, nextRow - tableHeight);
				nextx += colWidth;
			}
		} catch (Exception ex) {
			System.out.println("Error" + ex.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void drawContent(String[][] localContent) throws IOException {
		//System.out.println("drawcontent called");
		float tempx = nextCol + CELL_MARGIN;
		float tempy = nextRow - DIFFERENCE;
		try {
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);
			for (int i = 0; i < localContent.length; i++) {
				for (int j = 0; j < localContent[0].length; j++) {
					String temp = localContent[i][j];
					contentStream.beginText();
					contentStream.moveTextPositionByAmount(tempx, tempy);
					contentStream.drawString(temp);
					contentStream.endText();
					tempx += colWidth;
				}
				tempy -= ROW_HEIGHT;
				tempx = nextCol + CELL_MARGIN;
			}
			contentStream.close();
			document.save("H:/TIF test/result.pdf");
		} catch (Exception ex) {
			System.out.println("error" + ex.getMessage());
		}
	}
}
