package com.unit2.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class TxtFile extends FileBase{
	public TxtFile(String txtFilePath) {
		super(txtFilePath);
	}
	
	public static void copyContent(String a, String b) throws Exception {
		File origen = new File(a);
		File destino = new File(b);
		FileInputStream in = new FileInputStream(origen);
		FileOutputStream out = new FileOutputStream(destino);

		try {
			int n;
			// read() function to read the
			// byte of data
			while ((n = in.read()) != -1) {
				// write() function to write
				// the byte of data
				out.write(n);
			}
		} finally {
			if (in != null) {

				// close() function to close the
				// stream
				in.close();
			}
			// close() function to close
			// the stream
			if (out != null) {
				out.close();
			}
		}
		System.out.println("File imported");
	}
	
	public static void txtWriter(String fileName) {
		try {
			Scanner scanner = new Scanner(System.in);
			String name, res;
			int grade = 0;
			int index;
			index = 1;
			String s;
			File file = new File(fileName);
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			do {
				System.out.println(String.format("%d - %s", index, "Enter name and grade"));
				name = scanner.nextLine();
				do {
					try {
						grade = Integer.parseInt(scanner.nextLine());
						if (grade > 0 && grade <=10) break;
						else if (grade > 10 || grade < 1) System.out.println("error number out of range, Try again:");
					}catch (NumberFormatException e) {
						System.out.println("Error number format Try again: ");
					}
				}while(grade < 1 || grade > 10);
				s = System.lineSeparator();
				pw.append(String.format("%s%s,%d",s,name, grade));
				System.out.println("press (y) to continue and any key to exit to main menu");
				res = scanner.nextLine();
				index++;
			} while (res.equals("y"));
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
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
	
	public void createTxTReport() {
		try {
			File file = new File(super.getTxtFilePath());
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.append(String.format("\n\n%s", getFooter()));
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}
