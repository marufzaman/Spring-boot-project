package com.dbmanagement.student.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

	private final StudentProfileRepository studentProfileRepository;

	@Autowired
	public StudentService(StudentProfileRepository studentProfileRepository) {
		this.studentProfileRepository = studentProfileRepository;
	}

	public List<StudentProfile> getStudentProfiles(){
		return studentProfileRepository.findAll();
	}

	public void addNewStudent(StudentProfile studentProfile) {
		Optional<StudentProfile> findStudentProfile =
				studentProfileRepository.findStudentProfile(
						studentProfile.getName(),
						studentProfile.getDob(),
						studentProfile.getGender(),
						studentProfile.getNote()
				);
		 if (findStudentProfile.isPresent()){
			 throw new IllegalStateException("Profile Already Exist! Try Another One!");
		 }
		 studentProfileRepository.save(studentProfile);
	}

	public void deleteStudentProfile(Long studentID) {
		studentProfileRepository.findById(studentID);
		boolean exists = studentProfileRepository.existsById(studentID);
		if(!exists){
			throw new IllegalStateException(
					"Record for the student doesn't exists. Probably removed by a user."
			);
		}
		studentProfileRepository.deleteById(studentID);
	}

	@Transactional
	public void editStudentProfile(Long studentID, String name, LocalDate dob, String gender, String note) {
		StudentProfile studentProfile =  studentProfileRepository.findById(studentID)
				.orElseThrow(() -> new IllegalStateException(
						"Record for the student doesn't exists. Probably removed by a user."
				));

		if (name != null && name.length() > 0 &&
				!Objects.equals(studentProfile.getName(), name)){
			studentProfile.setName(name);
		}

		if (dob != null &&
				!Objects.equals(studentProfile.getDob(), dob)){
			studentProfile.setDob(dob);
		}

		if (gender != null && gender.length() > 0 &&
				!Objects.equals(studentProfile.getGender(), gender)){
			studentProfile.setGender(gender);
		}

		if (!Objects.equals(studentProfile.getNote(), note)){
			studentProfile.setNote(note);
		}
	}
}
