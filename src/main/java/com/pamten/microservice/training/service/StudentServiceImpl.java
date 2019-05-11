/**
 * 
 */
package com.pamten.microservice.training.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pamten.microservice.training.model.Course;
import com.pamten.microservice.training.model.Student;

/**
 * @author aakash_gupta
 *
 */
@Component
public class StudentServiceImpl implements StudentService {

	private static List<Student> studentList = new ArrayList<>();

	static {
		// Initialize Data
		Course course1 = new Course("Course1", "Spring", "10 Steps",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course3 = new Course("Course3", "Spring Boot", "6K Students",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		Course course4 = new Course("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));

		Student ranga = new Student("Student1", "Ranga Karanam",
				"Hiker, Programmer and Architect", new ArrayList<>(
						Arrays.asList(course1, course2, course3, course4)));

		Student satish = new Student("Student2", "Satish T",
				"Hiker, Programmer and Architect", new ArrayList<>(
						Arrays.asList(course1, course2, course3, course4)));

		studentList.add(ranga);
		studentList.add(satish);
	}

	@Override
	public Optional<Student> findById(String id) {
	 Student s = null;
       for(Student student : studentList){
		if(student.getId().equalsIgnoreCase(id)){
			s = student;
			break;
    	   }
       }
		return Optional.ofNullable(s);
	}

	@Override
	public Student saveStudent(Student student) {
		studentList.add(student);
		Optional<Student> s = findById(student.getId());
		if(s.isPresent())
			return s.get();
		else
			return null;
	}

	@Override
	public void deleteById(String id) {
		Optional<Student> s = findById(id);
		if(s.isPresent())
			studentList.remove(s.get());
	}
}
