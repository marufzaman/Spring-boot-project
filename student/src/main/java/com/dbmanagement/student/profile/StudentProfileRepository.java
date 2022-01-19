package com.dbmanagement.student.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    @Query("SELECT s FROM StudentProfile s " +
            "WHERE s.name = :name and s.dob = :dob and " +
            "s.gender = :gender and s.note = :note")
    Optional<StudentProfile> findStudentProfile(
            @Param("name") String name,
            @Param("dob") LocalDate dob,
            @Param("gender") String gender,
            @Param("note") String note
    );

}
