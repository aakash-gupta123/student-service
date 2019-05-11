/**
 * 
 */
package com.pamten.microservice.training.service;

import java.util.Optional;

import com.pamten.microservice.training.model.Student;

/**
 * @author aakash_gupta
 *
 */
public interface StudentService {

	/**
	 * @return Student
	 */
	Optional<Student> findById(String id);

	Student saveStudent(Student student);

	void deleteById(String id);

}
