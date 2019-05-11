/**
 * 
 */
package com.pamten.microservice.training.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pamten.microservice.training.exception.StudentNotFoundException;
import com.pamten.microservice.training.model.Student;
import com.pamten.microservice.training.service.StudentService;

/**
 * @author aakash_gupta
 *
 */
@Api(value = "Student Controller to test student service", tags = "Student service")
@RequestMapping("/api/training")
@RestController
public class StudentController {

	private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@ApiOperation(value = "Get student by ID operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "No data found") })
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable String id)
			throws StudentNotFoundException {
		Optional<Student> student = studentService.findById(id);
		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + id);

		return student.get();
	}

	@ApiOperation(value = "Create student operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "No data found") })
	@PostMapping(value = "/students", consumes ={"application/json"})
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@ApiOperation(value = "Update student operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "No data found") })
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student,
			@PathVariable String id) {

		Optional<Student> studentOptional = studentService.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);

		studentService.saveStudent(student);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Delete student operation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "No data found") })
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable String id) {
		studentService.deleteById(id);
	}
}
