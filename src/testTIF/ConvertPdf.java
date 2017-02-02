package testTIF;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class ConvertPdf {
	private PDDocument template;
	private PDDocumentCatalog templateCatalog;
	private PDAcroForm templateForm;
	private List<PDField> templateFields;
	private Iterator<PDField> templateIterator;
	private PDField templateField;

	ConvertPdf(File fileLocation) throws IOException {
		try {
			template = PDDocument.load(fileLocation);
			templateCatalog = template.getDocumentCatalog();
			templateForm = templateCatalog.getAcroForm();
			templateFields = templateForm.getFields();
			templateIterator = templateFields.iterator();
			while (templateIterator.hasNext()){
				templateField = templateIterator.next();
				System.out.println(templateField.getPartialName());
				templateField.setValue("testing");
			}
			template.save(new File("H:/TIF test/filled.pdf"));
			
		} catch (Exception ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
		}
	}
}
