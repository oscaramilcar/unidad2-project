package com.unit2.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class FileBase {
	@NonNull
	@Getter
	private String txtFilePath;
	@Getter
	@Setter
	private String header;
	
	@Getter
	private String footer;
	

	public Map<String, Student> studentsFromTxtReader() {
		Map<String, Student> students = new HashMap<String, Student>();
		try {
			File file = new File(this.txtFilePath);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String data = scanner.nextLine();
				if (data.split(",").length == 2) {
					Student student = studentFromTxtLineReader(data);
					students.put(student.getName(), student);
				} else {
					setHeader(data);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return students;
	}

	private Student studentFromTxtLineReader(String txtLine) {
		String[] parts = txtLine.split(",");

		String name = parts[0];
		float grade = Float.parseFloat(parts[1]);

		return new Student(name, grade);
	}
}
