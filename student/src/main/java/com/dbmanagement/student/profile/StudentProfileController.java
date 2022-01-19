package com.dbmanagement.student.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentProfileController {

	private final StudentService studentService;

	@Autowired
	public StudentProfileController(StudentService studentService){
		this.studentService = studentService;
	}

	@GetMapping
	public List<StudentProfile> getStudentProfiles(){
		return studentService.getStudentProfiles();
	}

	@PostMapping
	public void addNewStudentProfile(@RequestBody StudentProfile studentProfile){
		studentService.addNewStudent(studentProfile);
	}

	@DeleteMapping(path = "{studentID}")
	public void deleteStudentProfile(
			@PathVariable("studentID") Long studentID){
		studentService.deleteStudentProfile(studentID);
	}

	@PutMapping(path = "{studentID}")
	public void editStudentProfile(
			@PathVariable("studentID") Long studentID,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob,
			@RequestParam(required = false) String gender,
			@RequestParam(required = false) String note
	){
		studentService.editStudentProfile(studentID, name, dob, gender, note);
	}
}
