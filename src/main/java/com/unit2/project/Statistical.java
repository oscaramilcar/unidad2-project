package com.unit2.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class Statistical implements IOperations {
	private float grade;
	private int numberOfGrades;

	@Override
	public float avg(Map<String, Student> students) {
		float avg;
		avg = 0;
		for (Student s : students.values()) {
			avg += s.getGrade();
		}
		avg = avg / (students.size());
		return avg;
	}

	@Override
	public float minValue(Map<String, Student> students) {
		float grade = 0;
		for (Student s : students.values()) {
			grade = s.getGrade();
			for (Student st : students.values()) {
				if (grade > st.getGrade()) {
					grade = st.getGrade();
				}
			}
		}
		return grade;
	}

	@Override
	public float maxValue(Map<String, Student> students) {
		float note = 0;
		for (Student s : students.values()) {
			note = s.getGrade();
			for (Student st : students.values()) {
				if (note < st.getGrade()) {
					note = st.getGrade();
				}
			}
		}
		return note;
	}

	@Override
	public Statistical mostRepeated(Map<String, Student> students) {
		float grade = 0;
		int frequency = 1;
		Map<Float, Integer> notesCount = gradesMapCounter(students);
		// Iterating through elementCountMap to get the most frequent element and its
		// frequency
		for (Entry<Float, Integer> entry : notesCount.entrySet()) {
			if (entry.getValue() > frequency) {
				grade = entry.getKey();
				frequency = entry.getValue();
			}
		}
		return new Statistical(grade, frequency);
	}

	
	private Map<Float, Integer> gradesMapCounter(Map<String, Student> students) {
		List<Float> grades = new ArrayList<Float>();
		Map<Float, Integer> gradesCount = new HashMap<Float, Integer>();

		for (Student s : students.values()) {
			grades.add(s.getGrade());
		}

		for (float i : grades) {
			if (gradesCount.containsKey(i)) {
				gradesCount.put(i, gradesCount.get(i) + 1);
			} else {
				gradesCount.put(i, 1);
			}
		}
		return gradesCount;
	}
	
	public List<Student> studentsWithEqualsGrades(float grade, Map<String, Student> students){
		List<Student> listStidents = new ArrayList<Student>();
		for (var s : students.values()) {
			if(s.getGrade() == grade) {
				listStidents.add(s);
			}
		}
		return listStidents;
	}
}
