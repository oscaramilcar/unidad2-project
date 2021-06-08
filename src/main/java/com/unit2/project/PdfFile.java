package com.unit2.project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfFile extends FileBase {
	public PdfFile(String txtFilePath) {
		super(txtFilePath);
	}

	public void createPDFfile(String outputFileName, String titleFileReport, String footerReport) {
		try {
			Map<String, Student> students = super.studentsFromTxtReader();
			Document document = new Document(); //The document is created
			FileOutputStream outputStream = new FileOutputStream(outputFileName); //file where the PDF will be created
			PdfWriter.getInstance(document, outputStream); // the document is assoca outputStream
			document.open(); // open document
			Paragraph title = new Paragraph(String.format("%s \n\n", titleFileReport), FontFactory.getFont("arial",22,Font.BOLD,BaseColor.DARK_GRAY));
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title); //add title to document
	
			PdfPTable pdfPTable = new PdfPTable(2); // create table
			pdfPTable.addCell("Name");
			pdfPTable.addCell("Grade");
			
			for(Student s: students.values()) {
				pdfPTable.addCell(s.getName());
				pdfPTable.addCell(Float.toString(s.getGrade()));
			}
			document.add(pdfPTable); //add table to document
			
			
			Paragraph footer = new Paragraph(String.format("\n\n%s", footerReport), FontFactory.getFont("arial",10,Font.BOLD,BaseColor.DARK_GRAY));
			document.add(footer); //add title to document
			
			document.close(); // close document
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
	}
	

	@Override
	public String getFooter() {
		FileBase file = new FileBase(super.getTxtFilePath());
		Map<String, Student> students = file.studentsFromTxtReader();
		StringBuilder stringBuilder = new StringBuilder();
		Statistical statistical = new Statistical();
		Statistical mostRepeatedNote = statistical.mostRepeated(students);
		
		stringBuilder.append("AVG: ")
					.append(statistical.avg(students))
					.append("\n")
					.append("minor note: ")
					.append(statistical.minValue(students))
					.append("\n")
					.append("major note: ")
					.append(statistical.maxValue(students))
					.append("\n")
					.append("most repeated note: ")
					.append(mostRepeatedNote.getGrade())
					.append(" and repeats ")
					.append(mostRepeatedNote.getNumberOfGrades())
					.append(" times\n\n")
					.append("Students with the most repeated grades".toUpperCase())
					.append("\n");
		
		for (Student s : statistical.studentsWithEqualsGrades(mostRepeatedNote.getGrade(), students)) {
			stringBuilder.append(String.format("%s <-----------> %s\n", s.getName(), s.getGrade()));
		}
		
		return stringBuilder.toString();
	}
	
	
}
