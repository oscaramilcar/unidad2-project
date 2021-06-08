package com.unit2.project;

import java.util.Map;
import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String submenu, menu;
		PdfFile filePdf;
		while(true) {
			System.out.println("choose one of the options");
			System.out.println("a) load subject file");
			System.out.println("b) add student to subject");
			System.out.println("c) generate and send reports");
			System.out.println("exit press (s)");
			menu = scanner.nextLine();
			switch (menu) {
			case "a":
				System.out.println("choose one of the options");
				System.out.println("1) Math");
				System.out.println("2) Physics");
				System.out.println("3) Chemical");
				submenu = scanner.nextLine();
				switch (submenu) {
				case "1":
					System.out.println("Matematicas");
					loadFileSubjectStudent("math.txt");
					TxtFile.copyContent("math.txt", "mathFinal.txt");
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				case "2":
					System.out.println("Physics");
					loadFileSubjectStudent("physics.txt");
					TxtFile.copyContent("physics.txt", "physicsFinal.txt");
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				case "3":
					System.out.println("Chemical");
					loadFileSubjectStudent("chemical.txt");
					TxtFile.copyContent("chemical.txt", "chemicalFinal.txt");
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;

				default:
					System.out.println("ERROR: Invalid Selection");
					System.out.println("press any key to return to the menu");
					break;
				}
				break;
			case "b":
				System.out.println("choose one of the options");
				System.out.println("1) Math");
				System.out.println("2) Fisica");
				System.out.println("3) Chemical");
				submenu = scanner.nextLine();
				switch (submenu) {
				case "1":
					TxtFile.txtWriter("mathFinal.txt");
					break;
				case "2":
					TxtFile.txtWriter("physicsFinal.txt");
					break;
				case "3":
					TxtFile.txtWriter("chemicalFinal.txt");
					break;
				default:
					System.out.println("ERROR: Invalid Selection");
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				}
				break;
			case "c":
				System.out.println("1) Math");
				System.out.println("2) Physics");
				System.out.println("3) Chemical");
				submenu = scanner.nextLine();
				switch (submenu) {
				case "1":
					System.out.println("Report math");
					FileBase filem = new FileBase("math.txt");
					filem.studentsFromTxtReader();
					TxtFile txtFilem = new TxtFile("mathFinal.txt");
					txtFilem.createTxTReport();
					filePdf = new PdfFile("mathFinal.txt");
					filePdf.createPDFfile("mathFinal.pdf","Math grades - Report",filePdf.getFooter());
					EmailSend.emailSendAttachment("mathFinal.pdf","mathFinal.txt",filem.getHeader());
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				case "2":
					System.out.println("Report Physics");
					FileBase filep = new FileBase("physics.txt");
					filep.studentsFromTxtReader();
					TxtFile txtFilep = new TxtFile("physicsFinal.txt");
					txtFilep.createTxTReport();
					filePdf = new PdfFile("physicsFinal.txt");
					filePdf.createPDFfile("physicsFinal.pdf","Physics grades - Report",filePdf.getFooter());
					EmailSend.emailSendAttachment("physicsFinal.pdf","physicsFinal.txt",filep.getHeader());
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				case "3":
					System.out.println("Report chemical");
					FileBase filec = new FileBase("chemical.txt");
					filec.studentsFromTxtReader();
					TxtFile txtFilec = new TxtFile("chemicalFinal.txt");
					txtFilec.createTxTReport();
					filePdf = new PdfFile("chemicalFinal.txt");
					filePdf.createPDFfile("chemicalFinal.pdf","Chemical grades - Report",filePdf.getFooter());
					EmailSend.emailSendAttachment("chemicalFinal.pdf","chemicalFinal.txt",filec.getHeader());
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				default:
					System.out.println("ERROR: Invalid Selection");
					System.out.println("press any key to return to the menu");
					scanner.nextLine();
					break;
				}
				break;
			case "s":
				System.out.println("Success end.");
				System.exit(0);
				break;

			default:
				System.out.println("ERROR: Invalid Selection");
				System.out.println("press any key to return to the menu");
				scanner.nextLine();
				break;
			}
		}
	}
	
	static void loadFileSubjectStudent(String fileName) {
		FileBase file = new FileBase(fileName);
		Map<String, Student> students = file.studentsFromTxtReader();
		int contador;
		contador = 1;
		for(Student s: students.values()) {
			System.out.println((contador++)+") " +s.getName() +" --> "+s.getGrade());
		}
	}
}
