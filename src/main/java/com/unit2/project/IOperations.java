package com.unit2.project;

import java.util.Map;

public interface IOperations {
	float avg(Map<String,Student> students);
	float minValue(Map<String,Student> students);
	float maxValue(Map<String,Student> students);
	Statistical mostRepeated(Map<String,Student> students);
}
